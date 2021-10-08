# /**
# @Author Yogamber Singh Negi yogamber.negi@publicissapient.com
# */
Feature: Forgot Password
    Scenario:clicking_on_Forgot_Password
        Given I am on Login Page
        When I click on Forgot Password
        Then I should see Forgot Password Page

    Scenario:registered_email_address
        Given I am on Forgot Password Page
        When I enter empty email address 
        Then I should see empty email error message 

        Examples:
            | email |
            |  |

    Scenario:unregistered_email_address
        Given I am on Forgot Password Page
        When I enter incorrect email address "<email>"
        Then I should see error message

        Examples:
            | email             |
            | orochimaru@gmail.com |