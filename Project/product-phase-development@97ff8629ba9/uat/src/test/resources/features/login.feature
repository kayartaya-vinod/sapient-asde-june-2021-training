# /**
# @Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
# */
Feature: Login
    Scenario:clicking_Login_button
        Given I am on home page
        When I click on Login button
        Then I should see Login Page

    Scenario:email_error_message
        Given I have a login page
        When I enter only password "<password>" and click Submit 
        Then I should see email error message

        Examples:
            | password |
            | uchiha |

    Scenario:password_error_message
        Given I have a login page
        When I enter only email "<email>" and click Submit
        Then I should see password error message

        Examples:
            | email |
            | naruto@gmail.com |

    Scenario:password_email_error_message
        Given I have a login page
        When I do not give email and password and click Submit
        Then I should see email and password error message

    Scenario:correct_Login_credentials_given
        Given I have a login page
        When I enter correct email "<email>" and password "<password>" and click Submit
        Then I should see welcome message on dashboard

        Examples:
            | email          | password  |
            | vinod@vinod.co | topsecret |
    # Scenario:incorrect_Login_credentials_given
    #     Given I have a login page
    #     When I enter incorrect email "<email>" or password "<password>" and click Submit
    #     Then I should see error message on dashboard

    #     Examples:
    #         | email | password |
    #         | ooz6076@gmail.com | d |