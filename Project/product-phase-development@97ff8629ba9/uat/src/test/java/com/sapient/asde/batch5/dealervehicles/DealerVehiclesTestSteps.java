package com.sapient.asde.batch5.dealervehicles;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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

public class DealerVehiclesTestSteps {
  WebDriver driver;

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

  @Given("I am on the dealer vehicle page")
  public void i_am_on_the_dealer_vehicle_page() {
    driver.get(baseURL + "login");
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    WebElement username = driver.findElement(By.xpath("//*[@id=\"email\"]"));
    WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
    WebElement login = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div[2]/div/div/form/div[3]/button"));
    username.sendKeys("dealer@domain.com");
    password.sendKeys("password");
    login.click();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    driver.get(baseURL + "dealer/dashboard/dealer-vehicles");
  }

  @When("I click on checkbox on the vehicle card")
  public void i_click_anywhere_on_the_vehicle_card() {
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    checkbox = driver
        .findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div[2]/div[3]/div[3]/div[1]/div/div[1]/div[1]/input"));
    checkbox.click();
  }

  @Then("The vehicle should be selected")
  public void the_vehicle_should_be_selected() {
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    assertTrue(checkbox.isSelected());
  }

  @When("I click on delete button")
  public void i_click_on_delete_button() {
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div[2]/div[3]/ul[1]/li[2]/button")).click();
  }

  @Then("I should see the pop-up for delete")
  public void i_should_see_the_pop_up_for_delete() {
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.switchTo().activeElement();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    List<WebElement> modalAlert = driver.findElements(By.xpath("//*[@id=\"alertModal\"]/div/div"));
    assertNotEquals(modalAlert.size(), 0);
  }
}
