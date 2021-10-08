Feature: Dealer Uploaded Files
    Scenario: The page should show past vehicles uploads
        Given I am logged in as a dealer
        When I navigate to dealer dashboard
        Then I should see a list of past uploaded files