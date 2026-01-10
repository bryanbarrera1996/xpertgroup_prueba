resource "aws_vpc" "this" {
  cidr_block = var.vpc_cidr
  tags = {
    Name = "mock-vpc"
  }
}

resource "aws_subnet" "this" {
  count             = 2
  vpc_id            = aws_vpc.this.id
  cidr_block        = cidrsubnet(var.vpc_cidr, 8, count.index)
  availability_zone = "us-east-1a"

  tags = {
    Name = "mock-subnet-${count.index}"
  }
}
