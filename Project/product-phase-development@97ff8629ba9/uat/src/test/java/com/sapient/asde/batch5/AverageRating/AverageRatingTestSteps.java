/* @Author - Manvendra Singh
*/
package com.sapient.asde.batch5.AverageRating;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AverageRatingTestSteps {
        WebDriver driver;

        @Before
        public void setup() {
                String driverLocation = "C:/Devtools/chromedriver_win32/";
                String chromeDriver = driverLocation + "chromedriver.exe";

                System.setProperty("webdriver.chrome.driver", chromeDriver);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1200x600");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
        }

        @After
        public void tearDown() {
                // driver.quit();
        }

        @Given("I am on  vehicle details  page")
        public void i_am_on_vehicle_details_page() {
                driver.get("http://localhost:3000/vehicles/vehicle123");
        }

        @When("I edit rating of the vehicle")
        public void i_edit_rating_of_the_vehicle() {
        }

        @Then("I should see updated details of the vehicle")
        public void i_should_see_updated_model_name() {
                String actualRating = driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div[1]/div[2]/div/div/div[2]/div/span[1]"))
                                .getAttribute("value");
                assertEquals("200 Ratings", actualRating);
                
        }
}
