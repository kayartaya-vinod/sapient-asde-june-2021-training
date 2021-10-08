
Feature: View Accessory Grid
    Scenario: When I go to vehicles
        Given The search type is set to accessories
        When The user types the search text and presses enter
        Then I should see <expected> cards of accessory
        Examples:
            | expected |
            | 12       |