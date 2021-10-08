/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
package com.sapient.asde.batch5.VehicleFeedbacks;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VehicleFeedbacksTestSteps {
    WebDriver driver;
    String[] arr;
    @Before
    public void setup() {
        String driverLocation = "C:/Users/rdrl/Downloads/chromeDriver/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("start-maximized");
        options.addArguments("--headless");
        options.addArguments("--window-size=1440x900");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I am on a specific vehicle details page and I am not Logged in")
    public void given1(){
        driver.get("http://localhost:3000/vehicles/vehicle123");
    }

    @When("I click on View Reviews")
    public void when1(){
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[2]/div/div/div[3]/p")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Then("I must navigate to Reviews section")
    public void then1(){
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[7]/div/h4")).getText();
        assertEquals("Total 3 reviews", actual);
    }

    @When("I click on View Reviews and get Total Reviews")
    public void when2(){
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[2]/div/div/div[3]/p")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[7]/div/h4")).getText();
        arr = actual.split(" ", 5);
    }

    @Then("I must get {int} reviews")
    public void then2(int total){
    List<WebElement> cardsAfter = driver.findElements(By.xpath("//*[@class=\"feedbacks\"]"));
        assertEquals(total, cardsAfter.size());
    }
}