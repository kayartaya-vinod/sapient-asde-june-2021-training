locals {
  application_name = "tf-fargate-mycarsolution"
  launch_type      = "FARGATE"
}

resource "aws_ecs_cluster" "this" {
  name = local.application_name
  setting {
    name  = "containerInsights"
    value = "enabled"
  }
}

resource "aws_ecs_service" "this" {
  name        = "terraform-frontend-service"
  cluster     = aws_ecs_cluster.this.arn
  launch_type = local.launch_type

  deployment_maximum_percent         = 200
  deployment_minimum_healthy_percent = 0
  desired_count                      = 3
  task_definition                    = "${aws_ecs_task_definition.frontend.family}:${aws_ecs_task_definition.frontend.revision}"

  network_configuration {
    assign_public_ip = true
    subnets          = data.aws_subnet_ids.this.ids
    security_groups  = data.aws_security_groups.this.ids
  }
  
  load_balancer {
    target_group_arn = aws_alb_target_group.tg.id
    container_name   = "tf-frontend-container"
    container_port   = 80
  }
}

resource "aws_ecs_service" "micro-service" {
  name        = "terraform-micro-service"
  cluster     = aws_ecs_cluster.this.arn
  launch_type = local.launch_type

  deployment_maximum_percent         = 200
  deployment_minimum_healthy_percent = 0
  desired_count                      = 3
  task_definition                    = "${aws_ecs_task_definition.this.family}:${aws_ecs_task_definition.this.revision}"

  network_configuration {
    assign_public_ip = true
    subnets          = data.aws_subnet_ids.this.ids
    security_groups  = data.aws_security_groups.this.ids
  }

  load_balancer {
    target_group_arn = aws_alb_target_group.btg.id
    container_name   = "tf-gateway-container"
    container_port   = 8400
  }
}
