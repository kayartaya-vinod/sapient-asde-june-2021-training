/* @Author - <Sumitesh Naithani> <sumitesh.naithani@publicissapient.com>
*/
package com.sapient.asde.batch5.EditVehicleDetails;

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

public class EditVehicleDetailsTestSteps {
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

        @Given("I am on edit vehicle page")
        public void i_am_on_edit_vehicle_page() {
                driver.get("http://localhost:3000/login");
                driver.findElement(By.xpath("/html/body/div/div/div[3]/div[2]/div/div/form/div[1]/input"))
                                .sendKeys("dealer@domain.com");
                driver.findElement(By.xpath("/html/body/div/div/div[3]/div[2]/div/div/form/div[2]/input"))
                                .sendKeys("password");
                driver.findElement(By.xpath("/html/body/div/div/div[3]/div[2]/div/div/form/div[3]/button")).click();
                driver.get("http://localhost:3000/dealer/edit-vehicle-details/vehicle123");
        }

        @When("I edit details of the vehicle")
        public void i_edit_details_of_the_vehicle() {
        }

        @Then("I should see updated details of the vehicle")
        public void i_should_see_updated_model_name() {
                String actualModel = driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div/form/div/div[1]/div/div[1]/table/tbody/tr[1]/td[2]/input"))
                                .getAttribute("value");
                assertEquals("C100", actualModel);
                String actualPrice = driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div/form/div/div[1]/div/div[1]/table/tbody/tr[2]/td[2]/input"))
                                .getAttribute("value");
                assertEquals("2700000", actualPrice);
                String actualColor = driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div/form/div/div[1]/div/div[1]/table/tbody/tr[3]/td[2]/input"))
                                .getAttribute("value");
                assertEquals("Red", actualColor);
                String actualReleaseYear = driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div/form/div/div[1]/div/div[1]/table/tbody/tr[4]/td[2]/input"))
                                .getAttribute("value");
                assertEquals("2010", actualReleaseYear);
                String actualVehicleType = driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div/form/div/div[1]/div/div[1]/table/tbody/tr[5]/td[2]/input"))
                                .getAttribute("value");
                assertEquals("SUV", actualVehicleType);
                boolean isPetrolSelected = driver.findElement(By.cssSelector("#flexCheckDefault")).isSelected();
                assertEquals(true, isPetrolSelected);
                boolean isCngSelected = driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div/form/div/div[2]/div/div[1]/table/tbody/tr[5]/td[2]/input[3]"))
                                .isSelected();
                assertEquals(false, isCngSelected);

                boolean isFogLightOn = driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div/form/div/div[2]/div/div[2]/table/tbody/tr[1]/td[2]/input[1]"))
                                .isEnabled();
                assertEquals(true, isFogLightOn);

                driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div/form/div/div[1]/div/div[2]/table/tbody/tr[5]/input"))
                                .sendKeys("abc.jpg");
                driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div/form/div/div[1]/div/div[2]/table/tbody/tr[5]/button/b"))
                                .click();

                String actualPictureUrl = driver.findElement(By.xpath(
                                "/html/body/div/div/div[3]/div/form/div/div[1]/div/div[2]/table/tbody/tr[5]/label"))
                                .getText();
                assertEquals("abc.jpg", actualPictureUrl);
        }
}
