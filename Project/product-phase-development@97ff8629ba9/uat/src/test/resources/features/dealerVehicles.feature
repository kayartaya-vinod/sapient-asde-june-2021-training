Feature: Dealer Vehicle Paginated List
  Scenario: The vehicle should be selected when I click on it
    Given I am on the dealer vehicle page
    When I click on checkbox on the vehicle card
    Then The vehicle should be selected

  Scenario: The delete button should show on selecting vehicle
    Given I am on the dealer vehicle page
    When I click on checkbox on the vehicle card
    And I click on delete button
    Then I should see the pop-up for delete

