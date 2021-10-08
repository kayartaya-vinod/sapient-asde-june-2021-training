Feature: Customer Dashboard
    Scenario: All options available for customers can be seen
        Given A customer is logged in
        When The customer is on dashboard page
        Then There should be option for <expected1>, <expected2>, <expected3>, <expected4> and <expected5>

        Examples:
            | expected1  | expected2    | expected3       | expected4         | expected5         |
            | My Account | Edit Profile | Change Password | Saved Comparisons | Favorite Vehicles |

    Scenario: All options available for customers can be seen
        Given A customer is logged in
        When The customer is on dashboard page
        Then The customer should see their profile details


    Scenario: All options available for customers can be seen
        Given A customer is logged in and customer is on dashboard page
        When Customer clicks on Edit Profile
        Then Page should update with edit options

    Scenario: All options available for customers can be seen
        Given A customer is logged in and customer is on dashboard page
        When Customer clicks on change password
        Then Page should update with change password option

    Scenario: All options available for customers can be seen
        Given A customer is logged in and customer is on dashboard page
        When Customer clicks on Saved Comparisons
        Then Page should update with Saved Comparisons

    Scenario: All options available for customers can be seen
        Given A customer is logged in and customer is on dashboard page
        When Customer clicks on Favorite Vehicles
        Then Page should update with their Favorite Vehicles