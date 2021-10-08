# /**
# @Author Rohit Bhatt rohit.bhatt1@publicissapient.com
# */

Feature: Customer Verification
    Scenario: clicling on the verify link in email when it is not expired
        Given I got a valid token from email
        When I process the token
        Then I should see a success message