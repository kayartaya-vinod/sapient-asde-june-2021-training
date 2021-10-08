

Feature:  Vehicle Details Page
    Scenario: Vehicle Details Page of a particular vehicle
        Given I am on Home Page
        When I click on vehicle card
        Then I should see  details of the vehicle
