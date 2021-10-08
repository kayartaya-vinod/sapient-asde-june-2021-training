/**
 @Author <Hrishant> <hrishant.raj@publicissapient.com>
 */

package com.sapient.asde.batch5.ViewProfile;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/viewprofile.feature" }, glue = { "com.sapient.asde.batch5.ViewProfile" }, plugin = { "pretty",
        "json:target/reports/viewprofile.json" })
public class ViewProfileTest 
{
    /**
     * Rigorous Test :-)
     */

}