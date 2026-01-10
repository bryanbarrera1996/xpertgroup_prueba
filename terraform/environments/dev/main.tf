module "network" {
  source = "../../modules/network"

  vpc_cidr = var.vpc_cidr
}

module "iam" {
  source = "../../modules/iam"
}

module "eks" {
  source = "../../modules/eks"

  cluster_name      = var.cluster_name
  cluster_role_arn = module.iam.eks_role_arn
  subnet_ids        = module.network.subnet_ids
}
