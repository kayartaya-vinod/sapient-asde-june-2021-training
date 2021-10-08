# /**
# @Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
# */
Feature: Edit Vehicle Details
    Scenario: Edit vehicle model
        Given I am on edit vehicle page
        When I edit details of the vehicle
        Then I should see updated details of the vehicle
