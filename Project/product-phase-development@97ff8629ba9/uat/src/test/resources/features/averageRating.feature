# /**
# @Author Manvendra Singh
# */
Feature: Edit Vehicle Details
    Scenario: Edit vehicle model
        Given I am on  vehicle details  page
        When I edit rating of the vehicle
        Then I should see updated rating  of the vehicle
