# /**
# @Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
# */
Feature: Vehicle Feedbacks
    Scenario: Must Navigate to Total Reviews
        Given I am on a specific vehicle details page and I am not Logged in
        When I click on View Reviews
        Then I must navigate to Reviews section
    
    Scenario: No of reviews should be same
        Given I am on a specific vehicle details page and I am not Logged in
        When I click on View Reviews and get Total Reviews
        Then I must get <expected> reviews

        Examples:
            | expected |
            | 3       |

