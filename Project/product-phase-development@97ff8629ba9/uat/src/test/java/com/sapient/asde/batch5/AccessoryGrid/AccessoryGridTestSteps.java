/**
 @Author <Hrishant> <hrishant.raj@publicissapient.com>
 */

package com.sapient.asde.batch5.AccessoryGrid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccessoryGridTestSteps {
    WebDriver driver;
    @Before
    public void setup() {
        String driverLocation = "D:\\SeleniumDrivers\\";
        String chromeDriver = driverLocation + "chromedriver_win32\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        // options.addArguments("--window-size=1200x600");
        driver = new ChromeDriver(options);
    }

    
    @After
    public void tearDown() {
        driver.quit();
    }
    @Given("The search type is set to accessories")
    public void the_search_type_is_set_to_accessories() {
        driver.get("http://localhost:3000");
        Select select = new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/form/select")));
        select.selectByValue("accessories");
    }

    @When("The user types the search text and presses enter")
    public void the_user_types_the_search_text_and_presses_enter() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/form/input")).sendKeys("Honda");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/form/input")).sendKeys(Keys.ENTER);
    }

    @Then("I should see {int} cards of accessory")
    public void i_should_see_cards_of_accessory(int total) {
        List<WebElement> cards = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[4]/div[1]"));
        assertEquals(total, cards.size());
    }
}