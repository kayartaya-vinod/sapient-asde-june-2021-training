#@Author <Hrishant> <hrishant.raj@publicissapient.com>

Feature: Search Result
    Scenario: When I enter a string present in the database
        Given I have a searchbar
        When I enter a string present in the database <name> and press enter
        Then I should get <expected> search result

    Examples:
        | name | expected |
        | Honda | Your searched vehicles |

    Scenario: When I enter a string not present in the database
        Given I have a searchbar
        When I enter a string not present in the database <name> and press enter
        Then I should get <expected> search result

        Examples:
            | name | expected             |
            | asd  | No Vehicles found!!! |