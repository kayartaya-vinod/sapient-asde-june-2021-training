#@Author <Yogeshwar> <yogeshwar.chaturvedi@publicissapient.com>

Feature: View Vehicle Grid
    Scenario: When I go to vehicles
        Given I am a user or guest
        When I go to the page
        Then I should see <expected> cards of car
        Examples:
            | expected |
            | 12       |
    Scenario: Clicking load more button
        Given I am on the vehicles page
        When I click on load more button
        Then I should see <expected> more cards of car
        Examples:
            | expected |
            | 12       |
