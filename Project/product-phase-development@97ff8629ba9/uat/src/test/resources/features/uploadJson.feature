Feature: Upload Json File
    Scenario: Moving_to_Upload_Page
        Given I am Logged in as dealer with "<email>" and "<password>"
        When I move to upload-json url
        Then I should see upload-json page

        Examples:
            | email             | password |
            | dealer@domain.com | password |

    Scenario: Getting_Redirected_to_LoginPage
        Given I am not Logged in as dealer
        When I move to upload-json url
        Then I should redirect to Login Page

    Scenario: Without_selecting_any_file_clicking_on_Upload_File
        Given I am Logged in as dealer with "<email>" and "<password>"
        When I go to upload-json page and click on Upload File
        Then I should see no file selected message

        Examples:
            | email             | password |
            | dealer@domain.com | password |

    Scenario: Instruction_Popup
        Given I am Logged in as dealer with "<email>" and "<password>"
        When I go to upload-json page and click on instructions icon
        Then I should see instruction for uploading
        Examples:
            | email             | password |
            | dealer@domain.com | password |


