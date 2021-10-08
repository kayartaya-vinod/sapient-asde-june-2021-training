package com.sapient.asde.batch5.dealeraccessories;

import static org.junit.Assert.assertNotEquals;

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

public class DealerAccessoriesTestSteps {
  WebDriver driver;

  List<WebElement> listBefore;
  WebElement checkbox;
  String link;
  String baseURL;

  @Before
  public void setup() {
    String driverLocation = "C:\\Users\\abhsingh58\\";
    String chromeDriver = driverLocation + "chromedriver_win32\\chromedriver.exe";

    System.setProperty("webdriver.chrome.driver", chromeDriver);
    ChromeOptions options = new ChromeOptions();
    // options.addArguments("--headless");
    // options.addArguments("--window-size=1200x600");
    driver = new ChromeDriver(options);

    baseURL = "http://localhost:3000/";
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Given("I am logged in as a dealer")
  public void i_am_logged_in_as_a_dealer() {
    driver.get(baseURL + "login");
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    WebElement username = driver.findElement(By.xpath("//*[@id=\"email\"]"));
    WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
    WebElement login = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div[2]/div/div/form/div[3]/button"));
    username.sendKeys("dealer@domain.com");
    password.sendKeys("password");
    login.click();
  }

  @When("I navigate to dealer dashboard")
  public void i_navigate_to_dealer_dashboard() {
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    driver.get(baseURL + "dealer/dashboard/dealer-accessories");
  }

  @Then("I should see a list of uploaded accessories")
  public void i_should_see_a_list_of_uploaded_accessories() {
    List<WebElement> listItems = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[4]/div[2]/div[3]/div/div"));

    assertNotEquals(listItems.size(), 0);
  }
}
