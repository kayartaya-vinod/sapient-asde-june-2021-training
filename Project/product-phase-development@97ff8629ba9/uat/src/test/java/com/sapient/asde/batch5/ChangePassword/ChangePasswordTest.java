/**
@Author <Hrishant> <hrishant.raj@publicissapient.com>
*/

package com.sapient.asde.batch5.ChangePassword;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/changepassword.feature" }, glue = { "com.sapient.asde.batch5.ChangePassword" }, plugin = { "pretty",
        "json:target/reports/changepassword.json" })
public class ChangePasswordTest {
    
}
