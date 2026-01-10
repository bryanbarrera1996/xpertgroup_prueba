variable "cluster_name" {
  description = "Nombre del cluster EKS"
  type        = string
}

variable "cluster_role_arn" {
  description = "ARN del rol IAM para el cluster"
  type        = string
}

variable "subnet_ids" {
  description = "Lista de subnets para el cluster"
  type        = list(string)
}
