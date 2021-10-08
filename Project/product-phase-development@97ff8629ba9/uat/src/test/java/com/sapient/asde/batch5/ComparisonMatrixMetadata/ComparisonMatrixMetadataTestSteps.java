package com.sapient.asde.batch5.ComparisonMatrixMetadata;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ComparisonMatrixMetadataTestSteps {
    WebDriver driver;

    List<WebElement> listBefore;
    String link;

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

    @Given("I am on the change comparison matrix metadata page")
    public void i_am_on_the_comparison_matrix_metadata_page() {
        driver.get("http://localhost:3000/customer/vehicle-comparisons");
    }

    @When("I click on a delete button")
    public void i_click_on_a_delete_button() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        listBefore = driver
                .findElements(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/table/tbody/tr"));
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[4]")).click(); 
    }

    @Then("The record should be deleted")
    public void the_record_should_be_deleted() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> listAfter = driver
                .findElements(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/table/tbody/tr"));

        assertEquals(listBefore.size() - 1, listAfter.size());
    }

}
