
#@Author <Hrishant> <hrishant.raj@publicissapient.com>

Feature: Change Password
    Scenario: When all password field is empty and change password button is clicked
        Given I am on the change password page
        When I do not enter anything in some or all field and click change password button
        Then I will see an <expected> error message

        Examples:
            | expected                  |
            | Please enter all details. |

    Scenario: When new password and confirm password are different and change password button is clicked
        Given I am on the change password page
        When I type right old password <oldpwd> and different new password <newpwd1> and confirm password <newpwd2>
        Then I will see an <expected> error message

        Examples:
            | oldpwd          | newpwd1       | newpwd2       | expected               |
            | 'dummypassword' | 'Sapient@123' | 'Welcome@123' | Passwords don't match. |

    Scenario: When only old password and new password is entered and change password button is clicked
        Given I am on the change password page
        When I type right old password <oldpwd> and only enter the new password <newpwd1>
        Then I will see an <expected> error message

        Examples:
            | oldpwd | newpwd1 | expected                  |
            | old    | 789     | Please enter all details. |


    Scenario: When only old password and confirm password is entered and change password button is clicked
        Given I am on the change password page
        When I type right old password <oldpwd> and only enter the confirm password <newpwd2>
        Then I will see an <expected> error message

        Examples:
            | oldpwd | newpwd2 | expected                  |
            | old    | 789     | Please enter all details. |


    Scenario: When old password is correct and new password and confirm password is same and change password button is clicked
        Given I am on the change password page
        When I type right old password <oldpwd> and same new password <newpwd1> and confirm password <newpwd2>
        Then I will see an <expected> message

        Examples:
            | oldpwd          | newpwd1       | newpwd2       | expected                  |
            | 'dummypassword' | 'Welcome@123' | 'Welcome@123' | Password has been changed |


    Scenario: When old password is correct and new password and confirm password does not follow pattern and change password button is clicked
        Given I am on the change password page
        When I type right old password <oldpwd> and new password <newpwd1> and confirm password <newpwd2> without following pattern
        Then I will see an <expected> message

        Examples:
            | oldpwd          | newpwd1   | newpwd2      | expected                                                                                                                                   |
            | 'dummypassword' | 'Welcome' | 'Welcome123' | 'Must contain at least one digit and one uppercase and one lowercase letter and one special character, and at least 8 or more characters.' |


    Scenario: When old password is not entered and new password is same and change password button is clicked
        Given I am on the change password page
        When I type same new password <newpwd1> and confirm password <newpwd2> only
        Then I will see an <expected> message

        Examples:
            | newpwd1 | newpwd2 | expected                  |
            | new     | new     | Please enter all details. |