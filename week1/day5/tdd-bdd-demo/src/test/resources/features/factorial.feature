Feature: FactorialGenerator
    Scenario: Generate factorial of 3
        Given I have a factorial generator
        When I pass 3 as parameter
        Then I should get 6 as factorial

    Scenario: Generate factorial of <num>
        Given I have a factorial generator
        When I pass <num> as parameter
        Then I should get <expected> as factorial

        Examples:
            | num | expected |
            | 0   | 1        |
            | 1   | 1        |
            | 4   | 24       |
            | 5   | 120      |
            | 10  | 3628800  |


    Scenario: Fail for negative input <num> as
        Given I have a factorial generator
        When I pass negative value <num> as argument
        Then I should get IllegalArgumentException

        Examples:
            | num |
            | -1  |
            | -5  |

