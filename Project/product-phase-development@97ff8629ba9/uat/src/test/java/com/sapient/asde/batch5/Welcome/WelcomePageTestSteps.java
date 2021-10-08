package com.sapient.asde.batch5.Welcome;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


/*
 * @Author Shubham Chaudhari shubham.chaudhari@publicissapient.com
 */

public class WelcomePageTestSteps {

    WebDriver driver;

    @Before
    public void setup() {
        String driverLocation = "D:/Users/shuchaud1/Desktop/devtools/browserdriver/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @After
    public void tearDown() {
         driver.quit();
    }

    @Given("website is running on localhost:{int}")
    public void website_is_running_on_localhost(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    @When("I visit localhost:{int}")
    public void i_visit_localhost(Integer int1) {
        driver.get("http://localhost:3000/");
    }
    @Then("I should see a Welcome Text on screen")
    public void i_should_see_a_welcome_text_on_screen() {
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/h1")).getText();
        assertEquals("Welcome to My Car Solutions", actual);
    }
    
}
