variable "cluster_name" {
  description = "Nombre del cluster EKS"
  type        = string
}

variable "vpc_cidr" {
  description = "CIDR principal de la VPC"
  type        = string
}

variable "environment" {
  description = "Ambiente de despliegue"
  type        = string
}