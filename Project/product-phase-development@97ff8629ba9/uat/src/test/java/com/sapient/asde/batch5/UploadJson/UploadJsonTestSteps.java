/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
package com.sapient.asde.batch5.UploadJson;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UploadJsonTestSteps {
    WebDriver driver;

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

    @Given("I am Logged in as dealer with {string} and {string}")
    public void given1(String email,String password){
        //login as dealer
        driver.get("http://localhost:3000/login");
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//*[@class=\"btn btn-primary\"]")).click();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    @When("I move to upload-json url")
    public void when1(){
        driver.get("http://localhost:3000/upload-json");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    @Then("I should see upload-json page")
    public void then1(){
        String actual = driver.findElement(By.xpath("// *[@id=\"root\"]/div/div[3]/div/div[1]/label")).getText();
        assertEquals("Upload JSON File", actual);
    }
    
    @Given("I am not Logged in as dealer")
    public void given2(){
        driver.get("http://localhost:3000/login");
    }

    // @When("I move to upload-json url")
    // public void when2(){
    //     driver.get("http://localhost:3000/upload-json");
    //     try {
    //         Thread.sleep(2000);
    //     } catch (Exception e) {
    //     }
    // }

    @Then("I should redirect to Login Page")
    public void then2(){
        String actual = driver.findElement(By.xpath("// *[@class=\"form-label mt-3\"]")).getText();
        assertEquals("Email", actual);
    }

    @When("I go to upload-json page and click on Upload File")
    public void when3(){
        driver.get("http://localhost:3000/upload-json");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        driver.findElement(By.xpath("//*[@class=\"btn btn-primary\"]")).click();
    }

    @Then("I should see no file selected message")
    public void then3(){
        try {
            Thread.sleep(900);
        } catch (Exception e) {
        }
        String actual = driver.findElement(By.xpath("//*[@class=\"text-warning\"]")).getText();
        assertEquals("Select a file before uploading", actual);
    }

    @When("I go to upload-json page and click on instructions icon")
    public void when4(){
        driver.get("http://localhost:3000/upload-json");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        driver.findElement(By.xpath("//*[@data-testid=\"instruction\"]")).click();
    }

    @Then("I should see instruction for uploading")
    public void then4(){
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        String actual = driver.findElement(By.xpath("// *[@class=\"modal-title h4\"]")).getText();
        assertEquals("Instructions", actual);
    }

}