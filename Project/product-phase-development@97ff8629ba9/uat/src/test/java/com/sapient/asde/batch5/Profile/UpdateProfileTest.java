package com.sapient.asde.batch5.Profile;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/*
 * @Author Shubham Chaudhari shubham.chaudhari@publicissapient.com
 */


@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/updateprofile.feature" }, glue = { "com.sapient" }, plugin = { "pretty",
        "json:target/reports/profile.json" })
public class UpdateProfileTest {
    
}

