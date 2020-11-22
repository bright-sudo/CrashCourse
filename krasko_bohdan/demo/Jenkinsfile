pipeline {
    
    environment {
        registry = "kraskobohdan/demo"
        registryCredential = "dockerhub"
        dockerImage = ''
        PATH = "$PATH:$HOME/bin:."
        DD_API_KEY = credentials('DD_API_KEY_DEV')
    }
    
    agent any
    
    parameters {
        choice(
            choices: ['deploy' , 'destroy'],
            description: '',
            name: 'REQUESTED_ACTION')
    }

    tools {
        terraform 'Terraform'
        jdk 'jdk-11'
        maven "M3"
    }
    
    stages {
        stage('CleanWorkspace') {
            when {
                expression { params.REQUESTED_ACTION == 'deploy' }
            }
            steps {
                cleanWs()
                
            }
        }

        stage('Build') {
            when {
                expression { params.REQUESTED_ACTION == 'deploy' }
            }
            steps {
                git 'https://github.com/BohdanKrasko/test'
                dir ('student') { 
                    sh "mvn package -Dmaven.test.skip=true "
                }
                
            }
            
            post {
                success {
                    dir('student') {
                        archiveArtifacts 'target/*.jar'
                    }
                }
            }
        }
        stage('Building image') {
            when {
                expression { params.REQUESTED_ACTION == 'deploy' }
            }
            steps{
                script {
                    dir('student') {
                        dockerImage = docker.build registry + ":$BUILD_NUMBER"
                    }
                }
            }
        }
        
        stage('Deploy Image') {
            when {
                expression { params.REQUESTED_ACTION == 'deploy' }
            }
            steps{  
                script {
                    docker.withRegistry( '', registryCredential ) {
                    dockerImage.push()
                    //sh "print=$registry:$BUILD_NUMBER"
                    }
                }
            }
        }
        
        stage('Terraform') {
            when {
                expression { params.REQUESTED_ACTION == 'deploy' }
            }
            steps {
                dir ('terraform') { 
                    withAWS(credentials:'aws_credentials', region:'eu-west-3') {
                    sh "terraform init"
                    sh "terraform plan"
                    sh "terraform apply -auto-approve"
                    
                    sh "KUBECONFIG=./kubeconfig_my-cluster kubectl apply -f ../kubernetes/postgres-deployment.yml"
                    sleep(time:30,unit:"SECONDS")
                    sh "KUBECONFIG=./kubeconfig_my-cluster kubectl get pods"
                   
                    sh "KUBECONFIG=./kubeconfig_my-cluster kubectl create deployment spring --image=$registry:$BUILD_NUMBER"
                    sleep(time:20,unit:"SECONDS")
                    sh "KUBECONFIG=./kubeconfig_my-cluster kubectl expose deployment spring  --type=LoadBalancer  --name=spring --port=8888"
                    sleep(time:20,unit:"SECONDS")
                    sh "KUBECONFIG=./kubeconfig_my-cluster kubectl describe service spring"
                    
                    }
                    
                }
                
            }
            
        }
        
        stage('Installation of prerequisites') {
            when {
                expression { params.REQUESTED_ACTION == 'deploy' }
            }
            steps {
                sh(
                    label: "Installing Helm and tiller",
                    script: """#!/usr/bin/env bash
                    wget https://get.helm.sh/helm-v3.1.0-linux-amd64.tar.gz
                    tar -xvzf helm-v3.1.0-linux-amd64.tar.gz
                    mv linux-amd64/helm helm
                    helm help
                    """
                )
                sh(
                    label: "Add Datadog Helm Repository",
                    script: """#!/usr/bin/env bash
                    helm repo add datadog https://helm.datadoghq.com
                    helm repo add stable https://charts.helm.sh/stable
                    helm repo update
                    """
                )
            }
        }
        
        stage('Install Datadog') {
            when {
                expression { params.REQUESTED_ACTION == 'deploy' }
            }
            steps {
                dir('datadog') {
                    withAWS(credentials:'aws_credentials', region:'eu-west-3') {
                        sh "KUBECONFIG=../terraform/kubeconfig_my-cluster ../helm install datadogeks -f datadog-values.yaml --set datadog.site='datadoghq.com' --set datadog.apiKey=$DD_API_KEY datadog/datadog "
                    }
                }
            }
        }
        stage('Destroy') {
            when {
                expression { params.REQUESTED_ACTION == 'destroy' }
            }
            steps {
                dir ('terraform') { 
                    withAWS(credentials:'aws_credentials', region:'eu-west-3') {
                        sh(
                            label: "Destroy EKS",
                            script: """#!/usr/bin/env bash
                            KUBECONFIG=./kubeconfig_my-cluster ../helm delete datadogeks
                            KUBECONFIG=./kubeconfig_my-cluster kubectl delete service spring
                            KUBECONFIG=./kubeconfig_my-cluster kubectl delete deployment spring
                            KUBECONFIG=./kubeconfig_my-cluster kubectl delete -f ../kubernetes/postgres-deployment.yml
                            """
                        )
                    
                        sleep(time:10,unit:"SECONDS")
                
                       sh "terraform destroy -auto-approve"
                        
                    }
                    
                }
                
            }
            
        }
        

    }
}

