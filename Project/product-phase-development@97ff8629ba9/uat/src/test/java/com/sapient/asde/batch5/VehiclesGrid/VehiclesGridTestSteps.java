package com.sapient.asde.batch5.VehiclesGrid;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VehiclesGridTestSteps {
    WebDriver driver;
    List<WebElement> cardsBefore;

    @Before
    public void setup() {
        String driverLocation = "D:/Users/yogchatu/Downloads/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("start-maximized");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I am a user or guest")
    public void i_have_a_grid_of_car() {
    }

    @When("I go to the page")
    public void i_go_to_the_page() {
        driver.get("http://localhost:3000/vehicles");

    }

    @Then("I should see {int} cards of car")
    public void i_should_see_cards_of_car(int total) {
        List<WebElement> cards = driver.findElements(By.xpath("//*[@class='card col-6 col-md-4']"));
        assertEquals(total, cards.size());
    }

    @Given("I am on the vehicles page")
    public void i_am_on_the_vehicles_page() {
        driver.get("http://localhost:3000/vehicles");
    }

    @When("I click on load more button")
    public void i_click_on_load_more_button() {
        cardsBefore = driver.findElements(By.xpath("//*[@class='card col-6 col-md-4']"));
        driver.findElement(By.xpath("//*[@data-testid='load-more-btn']")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.xpath("//*[@data-testid='load-more-btn']")).click();
    }

    @Then("I should see {int} more cards of car")
    public void i_should_see_more_cards_of_car(int more) {
        List<WebElement> cardsAfter = driver.findElements(By.xpath("//*[@class='card col-6 col-md-4']"));
        assertEquals(cardsBefore.size() + 12, cardsAfter.size());

    }
}
