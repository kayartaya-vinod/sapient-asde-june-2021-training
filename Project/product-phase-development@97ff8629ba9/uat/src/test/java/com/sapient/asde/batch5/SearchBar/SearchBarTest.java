/**
 @Author <Hrishant> <hrishant.raj@publicissapient.com>
 */

package com.sapient.asde.batch5.SearchBar;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/searchbar.feature" }, glue = { "com.sapient.asde.batch5.SearchBar" }, plugin = { "pretty",
        "json:target/reports/searchbar.json" })

public class SearchBarTest {
    
}
