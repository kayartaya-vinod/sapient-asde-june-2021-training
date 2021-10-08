package com.sapient.asde.batch5.AccessorySearch;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccessorySearchTestSteps {

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

    @Then("Accessory Search result page should be shown")
    public void accessory_search_result_page_should_be_shown() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String expected = "Search results for \"Honda\" accessories";
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/h4")).getText();
        assertEquals(expected, actual);
    }
}
