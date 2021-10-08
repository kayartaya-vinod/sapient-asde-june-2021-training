Feature: Comparison matrix metadata
    Scenario: A record should be deleted on clicking delete button
        Given I am on the change comparison matrix metadata page
        When I click on a delete button
        Then The record should be deleted
