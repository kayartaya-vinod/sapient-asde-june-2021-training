Feature: Dealer Accessories Paginated List
  Scenario: The page should list the accessories uploaded by the dealer
    Given I am logged in as a dealer
    When I navigate to dealer dashboard
    Then I should see a list of uploaded accessories