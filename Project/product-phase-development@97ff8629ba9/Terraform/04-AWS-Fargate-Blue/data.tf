data "aws_subnet_ids" "this" {
  vpc_id = var.vpc_id
}

data "aws_iam_role" "ecs_task_execution_role" {
  name = "ecsTaskExecutionRole"
}

data "aws_security_groups" "this" {
  filter {
    name   = "group-name"
    values = ["ASDE-Jun2021-Batch5"]
  }

  filter {
    name   = "vpc-id"
    values = [var.vpc_id]
  }
}

