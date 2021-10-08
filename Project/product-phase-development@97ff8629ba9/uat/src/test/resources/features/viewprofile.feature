#@Author <Hrishant> <hrishant.raj@publicissapient.com>

Feature: View Profile
    Scenario: When I click on the View Profile button
        Given I have a view profile button
        When I click on the view profile button
        Then I should see <expected> field on customer profile

        Examples:
            | expected          |
            | Email             |
            | Alternate Email   |
            | Default Address   |
            | Alternate Address |
            | Contact           |
            | Alternate Contact |
            | Edit Profile      |
