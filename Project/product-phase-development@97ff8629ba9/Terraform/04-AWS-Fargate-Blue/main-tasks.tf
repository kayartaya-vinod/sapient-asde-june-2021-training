resource "aws_ecs_task_definition" "this" {
    family = local.application_name
    requires_compatibilities = [local.launch_type]
    execution_role_arn       = "${data.aws_iam_role.ecs_task_execution_role.arn}"
    network_mode = "awsvpc"
    cpu       = "4096"
    memory    = "30720"
    container_definitions = jsonencode([
    {
      name      = "tf-auth-container"
      image     = "153294646920.dkr.ecr.us-east-1.amazonaws.com/auth-service:latest"
      essential = true
      portMappings = [
        {
          containerPort = 8095
          hostPort      = 8095
        }
      ]
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-backend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    },
    {
      name      = "tf-customer-service-container"
      image     = "153294646920.dkr.ecr.us-east-1.amazonaws.com/customer-service:latest"
      essential = true
      portMappings = [
        {
          containerPort = 8096
          hostPort      = 8096
        }
      ]
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-backend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    },
    {
      name      = "tf-eureka-container"
      image     = "153294646920.dkr.ecr.us-east-1.amazonaws.com/eureka-service:latest"
      essential = true
      portMappings = [
        {
          containerPort = 8761
          hostPort      = 8761
        }
      ]
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-backend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    },
    {
      name      = "tf-gateway-container"
      image     = "153294646920.dkr.ecr.us-east-1.amazonaws.com/gateway-service:latest"
      essential = true
      portMappings = [
        {
          containerPort = 8400
          hostPort      = 8400
        }
      ]
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-backend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    },
    {
      name      = "tf-vehicle-container"
      image     = "153294646920.dkr.ecr.us-east-1.amazonaws.com/vehicle-service:latest"
      essential = true
      portMappings = [
        {
          containerPort = 8097
          hostPort      = 8097
        }
      ]
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-backend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    },
    {
      name      = "tf-vehicle-data-container"
      image     = "153294646920.dkr.ecr.us-east-1.amazonaws.com/vehicle-data-service:latest"
      essential = true
      portMappings = [
        {
          containerPort = 8098
          hostPort      = 8098
        }
      ]
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-backend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    },
    {
      name      = "tf-email-container"
      image     = "153294646920.dkr.ecr.us-east-1.amazonaws.com/email-service:latest"
      essential = true
      portMappings = [
        {
          containerPort = 8090
          hostPort      = 8090
        }
      ]
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-backend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    },
    {
      name      = "tf-zookeeper-container"
      image     = "docker.io/bitnami/zookeeper:3.7"
      essential = true
      portMappings = [
        {
          containerPort = 2181
          hostPort      = 2181
        }
      ]
      "environment" : [
        { "name" : "ALLOW_ANONYMOUS_LOGIN", "value" : "yes" }
      ],
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-backend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    },
    {
      name      = "tf-kafka-container"
      image     = "docker.io/bitnami/kafka:2"
      essential = true
      portMappings = [
        {
          containerPort = 9092
          hostPort      = 9092
        }
      ]
      "environment" : [
        { "name" : "KAFKA_CFG_ZOOKEEPER_CONNECT", "value" : "localhost:2181" },
        { "name" : "ALLOW_PLAINTEXT_LISTENER", "value" : "yes" }
      ],
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-backend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    },
    {
      name      = "tf-redis-container"
      image     = "redis:latest"
      essential = true
      portMappings = [
        {
          containerPort = 6379
          hostPort      = 6379
        }
      ]
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-backend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    }
  ])
}
resource "aws_ecs_task_definition" "frontend" {
    family = local.application_name
    requires_compatibilities = [local.launch_type]
    execution_role_arn       = "${data.aws_iam_role.ecs_task_execution_role.arn}"
    network_mode = "awsvpc"
    cpu       = "4096"
    memory    = "30720"
    container_definitions = jsonencode([
    {
      name      = "tf-frontend-container"
      image     = "153294646920.dkr.ecr.us-east-1.amazonaws.com/front-end:latest"
      essential = true
      portMappings = [
        {
          containerPort = 80
          hostPort      = 80
        }
      ]
      "environment": [
            {"name": "REACT_APP_SERVICES_DNS", "value": "api-blue.mycarsolutions.net"}
            
      ],
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
            "awslogs-group": "/ecs/tf-frontend",
            "awslogs-region": "us-east-1",
            "awslogs-stream-prefix": "ecs"
        }
      }
    }

  ])
}