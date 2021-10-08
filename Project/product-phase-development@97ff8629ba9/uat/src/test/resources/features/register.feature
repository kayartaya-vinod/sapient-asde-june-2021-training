# /**
# @Author Yogamber Singh Negi yogamber.negi@publicissapient.com
# */
Feature: Register
    Scenario:Go To Register Page
        Given I am not logged in
        When I click register
        Then I should see registration page

    Scenario:Go To Login Page
        Given I am not logged in and a registered user and on registration page
        When I click Login here link
        Then I should see login page

    Scenario:Register with mismatching passwords
        Given I am on register page
        When I pass "<first>" "<last>" "<email>" "<password>" "<confirm>" elements and click register
        Then I should see password not matching message

        Examples:
            | first    | last |  email                    | password   | confirm     |
            | Yogamber | Negi |  yogambersinghn@gmail.com | Qwerty@890 | Qwerty@8900 |

    Scenario:Register without first name
        Given I am on register page
        When I pass "<last>" "<email>" "<password>" "<confirm>" elements and not first name and click register
        Then I should see first name required message

        Examples:
            | first | last |  email                    | password   | confirm    |
            |       | Negi |  yogambersinghn@gmail.com | Qwerty@890 | Qwerty@890 |

    Scenario:Register without email
        Given I am on register page
        When I pass "<first>" "<last>" "<password>" "<confirm>" elements and not email and click register
        Then I should see email required message

        Examples:
            | first    | last |  email | password   | confirm    |
            | yogamber | Negi |        | Qwerty@890 | Qwerty@890 |

    Scenario:Nothing entered
        Given I am on register page
        When I pass "<first>" "<last>" "<email>" "<password>" "<confirm>" no elements and click register
        Then I should see all error messages

        Examples:
            | first    | last |  email | password   | confirm    |
            |          |      |        |            |            |



