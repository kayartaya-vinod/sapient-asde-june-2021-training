resource "aws_alb" "frontend" {
  name            = "tf-frontend-blue-lb"
  subnets         = data.aws_subnet_ids.this.ids
  security_groups = data.aws_security_groups.this.ids
}

resource "aws_alb_target_group" "tg" {
  name        = "tf-frontend-blue-tg"
  port        = 80
  protocol    = "HTTP"
  vpc_id      = var.vpc_id
  target_type = "ip"

  health_check {
    healthy_threshold   = "2"
    interval            = "30"
    protocol            = "HTTP"
    matcher             = "200"
    timeout             = "10"
    path                = "/"
    unhealthy_threshold = "3"
  }
}

# Redirect all traffic from the ALB to the target group
resource "aws_alb_listener" "front_end" {
  load_balancer_arn = aws_alb.frontend.id
  port              = 443
  protocol          = "HTTPS"
  certificate_arn   = "arn:aws:acm:us-east-1:153294646920:certificate/aa402ae9-bfe1-4aa3-a15e-98bfe27df52d"
  default_action {
    target_group_arn = aws_alb_target_group.tg.id
    type             = "forward"
  }
}

# resource "aws_route53_zone" "primary" {
#   name = "mycarsolutions.net"
# }
resource "aws_route53_record" "frontend" {
  zone_id = var.zone_id
  name    = "mycarsolutions.net"
  type    = "A"
  weighted_routing_policy {
    weight = 100
  }
  set_identifier = "tf-blue-id"
  alias {
    name                   = aws_alb.frontend.dns_name
    zone_id                = aws_alb.frontend.zone_id
    evaluate_target_health = true
  }
}

resource "aws_lb_listener" "front_end" {
  load_balancer_arn = aws_alb.frontend.id
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type = "redirect"

    redirect {
      port        = "443"
      protocol    = "HTTPS"
      status_code = "HTTP_301"
    }
  }
}

### ALB for backend
resource "aws_alb" "backend" {
  name            = "tf-backend-blue-lb"
  subnets         = data.aws_subnet_ids.this.ids
  security_groups = data.aws_security_groups.this.ids
}

resource "aws_alb_target_group" "btg" {
  name        = "tf-backend-blue-tg"
  port        = 8400
  protocol    = "HTTP"
  vpc_id      = var.vpc_id
  target_type = "ip"

  health_check {
    healthy_threshold   = "2"
    interval            = "30"
    protocol            = "HTTP"
    matcher             = "200"
    timeout             = "10"
    path                = "/actuator/health"
    unhealthy_threshold = "3"
  }
}

# Redirect all traffic from the ALB to the target group
resource "aws_alb_listener" "backend" {
  load_balancer_arn = aws_alb.backend.id
  port              = 443
  certificate_arn   = "arn:aws:acm:us-east-1:153294646920:certificate/b77ac424-6b82-4837-a448-c645809d7018"
  protocol          = "HTTPS"
  default_action {
    target_group_arn = aws_alb_target_group.btg.id
    type             = "forward"
  }
}

resource "aws_route53_record" "backend" {
 zone_id = var.zone_id
 name    = "api-blue.mycarsolutions.net"
 type    = "A"

 alias {
   name                   = aws_alb.backend.dns_name
   zone_id                = aws_alb.backend.zone_id
   evaluate_target_health = true
 }
}