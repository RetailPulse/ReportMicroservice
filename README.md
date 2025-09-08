# ReportManagementMicroservice
# ==========================================
# This microservice is responsible for managing reports within the RetailPulse application.
# It provides functionalities to create, list, and read reports.

# How to deploy the ReportManagementMicroservice to Local Kubernetes Cluster
# --------------------------------------------------------------------------
# Prerequisites
# - A local Kubernetes cluster (e.g., Minikube, Kind, or Docker Desktop with Kubernetes enabled).
# - kubectl command-line tool installed and configured to interact with your local cluster.
# - Docker installed to build the microservice image.
# - Access to the RetailPulse Docker registry or a local Docker registry.
# - A YAML file (k8s-rp-report-service.yaml) that defines the Kubernetes resources for the microservice.
# - Ensure that the retailpulse namespace exists in your cluster.
# Deployment Steps
# 1. Build the Docker Image
# - execute .\build-and-push-docker-image-local.sh
# 2. Delete Existing Namespace (if applicable)
# - If the retailpulse namespace already exists and you want to redeploy the microservice,
#   you may need to delete the existing namespace first.
#   Be cautious as this will remove all resources within that namespace.
#   kubectl delete namespace retailpulse
#   kubectl wait namespace retailpulse --for=delete --timeout=120s
# 3. Apply the Kubernetes Configuration
# - Use kubectl to apply the YAML file that contains the configuration for the ReportManagementMicroservice.
#   kubectl apply -f k8s-rp-report-service.yaml
# 4. Verify the Deployment
# - Check the status of the pods and services in the retailpulse namespace to ensure everything is running smoothly.
#   kubectl get all -n retailpulse
# 5. Access the Microservice
# - http://localhost:31088/api/reports/inventory-transactions?startDateTime=2025-08-01 00:00:00&endDateTime=2025-08-19 23:59:59&dateTimeFormat=yyyy-MM-dd HH:mm:ss
# - http://localhost:31088/api/reports/inventory-transactions/export?startDateTime=2025-08-01%2000:00:00&endDateTime=2025-08-19%2023:59:59&dateTimeFormat=yyyy-MM-dd%20HH:mm:ss&format=excel
# - http://localhost:31088/api/reports