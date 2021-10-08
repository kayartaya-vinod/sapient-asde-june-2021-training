# /**
# @Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
# */
Feature: Add Accessory
    Scenario: Add Accessory model
        Given I am on add accessory page
        When I add details of the vehicle accesory
        Then I should see updated details of the vehicle accessory