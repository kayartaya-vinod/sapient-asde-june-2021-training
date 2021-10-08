/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
package com.sapient.asde.batch5.Login;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/login.feature" }, glue = { "com.sapient.asde.batch5.Login" }, plugin = { "pretty",
        "json:target/reports/login.json" })
public class LoginTests {
    /**
     * Rigorous Test :-)
     */

}