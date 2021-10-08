Feature: WelcomePageLoading
    Scenario: Display welcome page when visit the website
        Given website is running on localhost:3000
        When I visit localhost:3000
        Then I should see a Welcome Text on screen

