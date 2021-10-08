
# @Author <Shubham> <shubham.chaudhary@publicissapient.com>

Feature: Add vehicle Test
    Scenario: When all input all field and press add vehicle button, it should submit
        Given I am on the add vehicle page
        When I enter all fields '<brand>' '<description>' '<color>' '<model>' '<tankCapacity>' '<mediaInterface>' '<tripMeter>' '<price>' '<year>' 
        Then I will see an '<expected>' success message

        Examples:
            | brand | description | color | model | tankCapacity | mediaInterface | tripMeter | price | year | expected |
            | Nano | this is a nano car | blue |n12| 25L | button | analog | 10000 | 2018 | Added Successfully, You will be redirected to homepage shortly.|
