Feature: Accessory Search
    Scenario: Accessory Search page loads
        Given The search type is set to accessories
        When The user types the search text and presses enter
        Then Accessory Search result page should be shown