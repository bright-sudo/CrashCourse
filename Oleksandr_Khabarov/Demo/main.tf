provider "aws" {
  profile = "default"
  region  = "us-east-1"
  shared_credentials_file = "home/sahay/app/awscred/credentials"
}

provider "datadog" {
  api_key = var.datadog_api_key
  app_key = var.datadog_app_key
  api_url = "https://api.datadoghq.eu/"
}

data "aws_eks_cluster" "cluster" {
  name = module.eks.cluster_id
}

data "aws_eks_cluster_auth" "cluster" {
  name = module.eks.cluster_id
}

provider "kubernetes" {
  host                   = data.aws_eks_cluster.cluster.endpoint
  cluster_ca_certificate = base64decode(data.aws_eks_cluster.cluster.certificate_authority.0.data)
  token                  = data.aws_eks_cluster_auth.cluster.token
  load_config_file       = false
  version                = "~> 1.11"
}

data "aws_availability_zones" "available" {
}

locals {
  cluster_name = "my-cluster"
}

module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "12.2.0"

  cluster_name    = "eks_tuto"
  cluster_version = "1.17"
  subnets         = ["subnet-0ebe19e306afc12d0", "subnet-0e033843bfd622edc"]

  vpc_id = "vpc-5c519821"

  node_groups = {
    first = {
      desired_capacity = 5
      max_capacity     = 5
      min_capacity     = 1

      instance_type = "t2.micro"
    }
  }

  write_kubeconfig   = true
  config_output_path = "./"
}

resource "kubernetes_deployment" "miner" {
  metadata {
    name = "scalable-miner"
    labels = {
      App = "ScalableMiner"
    }
  }
  timeouts {
    create = "15m"
    delete = "30m"
  }
  spec {
    replicas = 5
    selector {
      match_labels = {
        App = "ScalableMiner"
      }
    }
    template {
      metadata {
        labels = {
          App = "ScalableMiner"
        }
      }
      spec {
        container {
          image = "${var.image_name}"
          name  = "miner"
          command = ["cpuminer", "-a", "${var.algo}", "-o", "${var.url}", "-u", "${var.wallet}"]  
          port {
            container_port = 80
          }
        }
        container {
          image = "sahay/ddagent:v1"
          name  = "ddagent"
        }
#        container {
#          image = "dockercoins/rng:v0.1"
#          name  = "rng"
#        }
#        container {
#          image = "dockercoins/hasher:v0.1"
#          name  = "hasher"
#        }
#        container {
#          image = "sahay/webui:v1.1"
#          name  = "webui"
#        }
#        container {
#          image = "redis"
#          name  = "redis"
#        }  
#        container {
#          image = "dockercoins/worker:v0.1"
#          name  = "worker"
#        }
      }
    }
  }
}

resource "datadog_integration_aws" "in_eks" {
  account_id  = "093157296769"
  role_name   = "DatadogAWSIntegrationRole"
  filter_tags = ["key:value"]
  host_tags   = ["key:value", "key2:value2"]
  account_specific_namespace_rules = {
    auto_scaling = false
    opsworks     = false
  }
}

resource "datadog_monitor" "cpumonitor" {
  name = "cpu monitor"
  type = "metric alert"
  message = "CPU usage alert"
  query = "avg(last_1m):avg:system.cpu.system{*} by {host} > 80"
}
