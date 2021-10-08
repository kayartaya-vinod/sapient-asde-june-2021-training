package com.sapient;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AppTest {
    WebDriver driver;

    @BeforeClass
    public static void setSystemProps() {
        String driverLocation = "/Users/vinod/selenium-webdrivers/";
        String chromeDriver = driverLocation + "chromedriver";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
    }

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("https://vinod.co/phonebook-webapp/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void shouldAddNewRowInTheTable() {

        List<WebElement> listBefore = driver.findElements(By.cssSelector("table>tbody>tr"));

        driver.findElement(By.id("name")).sendKeys("Vinay Rao");
        driver.findElement(By.name("email")).sendKeys("vinayrao@xmpl.com");
        driver.findElement(By.cssSelector("div.form>div:nth-child(3)>input[type=text]")).sendKeys("9089786756");
        driver.findElement(By.xpath("//*[@id=\"btnAdd\"]")).click();
        List<WebElement> listAfter = driver.findElements(By.cssSelector("table>tbody>tr"));

        assertEquals(listBefore.size() + 1, listAfter.size());

    }

}
