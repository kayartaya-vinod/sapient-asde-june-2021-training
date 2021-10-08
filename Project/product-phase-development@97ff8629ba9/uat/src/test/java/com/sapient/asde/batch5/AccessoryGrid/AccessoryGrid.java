/**
 @Author <Hrishant> <hrishant.raj@publicissapient.com>
 */

package com.sapient.asde.batch5.AccessoryGrid;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/accessorygrid.feature" }, glue = { "com.sapient.asde.batch5.AccessoryGrid" }, plugin = { "pretty",
        "json:target/reports/AccessoryGrid.json" })

public class AccessoryGrid {
    
}
