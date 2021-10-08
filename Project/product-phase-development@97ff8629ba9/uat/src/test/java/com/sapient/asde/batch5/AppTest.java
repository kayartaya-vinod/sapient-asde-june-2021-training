package com.sapient.asde.batch5;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Unit test for simple App.
 */

public class AppTest 
{
    WebDriver driver;

    @BeforeClass
    public static void setSystemProps() {
        String driverLocation = "D:/Users/shuchaud1/Desktop/devtools/browserdriver/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
    }

    @Before
    public void setup() {
       
    }

    @After
    public void tearDown() {
        driver.quit();
    }
        
}
