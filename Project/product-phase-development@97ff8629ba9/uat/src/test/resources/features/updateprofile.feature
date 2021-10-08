Feature: Edit Profile
    Scenario: Navigate to edit profile form when clicked on edit profile button
        Given I am on the profile page
        When I click the edit profile button
        Then I should see a form where i can enter my details

    Scenario: Update the user details when clicked on update details button without changing anything
        Given I am on the update details page
        When I click the update details button without changing anything
        Then I should rediect and see same full name <fullname> on the profile page
        Examples:
            |fullname|
            |"Rohit Singh"|


    Scenario: Updating first name and last name as <first> and <second>
        Given I am on the update details page
        When I enter <first> and <second> as first name and last name and click update details button
        Then I should see <expected> on the profile page

        Examples:
            |first| second | expected |
            | "Rohit"| "Singh"   | "Rohit Singh"|

    Scenario: Updating email as <email>
        Given I am on the update details page
        When I enter <newemail> as my email and click update details button
        Then I should see <newemail> on my profile page

        Examples:
        |newemail|
        |"rohit@gmail.com"|

    Scenario: Updating alternate email 
        Given I am on the update details page
        When I enter <email> as my alternate email and click update details button
        Then I should see <email> as alternate email on my profile page

        Examples:
        |email|
        |"rohit2@gmail.com"|

    Scenario: Updating contact number 
        Given I am on the update details page
        When I enter <phone> as my contact number and click update details button
        Then I should see <phone> as my contact on my profile page

        Examples:
        |phone|
        |"7898789878"|

    Scenario: Updating alternate contact 
        Given I am on the update details page
        When I enter <phone> as my alternate contact and click update details button
        Then I should see <phone> as alternate contact on my profile page

        Examples:
        |phone|
        |"78987898752"|

    