Feature: Dealer Reviews Paginated List
    Scenario: The page should list the reviews for dealer
        Given I am logged in as a dealer
        When I navigate to dealer dashboard
        Then I should see a list of reviews