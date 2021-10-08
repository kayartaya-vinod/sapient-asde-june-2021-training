Feature: dealer Dashboard
    Scenario: All options available for dealers can be seen
        Given A dealer is logged in
        When The dealer is on dashboard page
        Then There should be option for <expected1>, <expected2>, <expected3>, <expected4> and <expected5>

        Examples:
            | expected1  | expected2    | expected3       | expected4    | expected5       |
            | My Account | Edit Profile | Change Password | Add Vehicles | Upload Vehicles |

    Scenario: All options available for dealers can be seen
        Given A dealer is logged in
        When The dealer is on dashboard page
        Then The dealer should see their profile details


    Scenario: All options available for dealers can be seen
        Given A dealer is logged in and dealer is on dashboard page
        When dealer clicks on Edit Profile
        Then Page should update with edit options

    Scenario: All options available for dealers can be seen
        Given A dealer is logged in and dealer is on dashboard page
        When dealer clicks on change password
        Then Page should update with change password option

    Scenario: All options available for dealers can be seen
        Given A dealer is logged in and dealer is on dashboard page
        When dealer clicks on Add Vehicles
        Then Page should update with Add Vehicles

    Scenario: All options available for dealers can be seen
        Given A dealer is logged in and dealer is on dashboard page
        When dealer clicks on Upload Vehicles
        Then Page should update with their Upload Vehicles