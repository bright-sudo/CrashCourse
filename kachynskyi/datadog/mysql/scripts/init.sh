#!/bin/bash
bash -c "/usr/local/bin/docker-entrypoint.sh"
bash -c "datadog-agent start"
