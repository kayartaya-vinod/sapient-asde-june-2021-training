package com.sapient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
    public static void main(String[] args) {
        String driverLocation = "/Users/vinod/selenium-webdrivers/";
        String chromeDriver = driverLocation + "chromedriver";
        String edgeDriver = driverLocation + "msedgedriver";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        System.setProperty("webdriver.edge.driver", edgeDriver);

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless")

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://vinod.co/phonebook-webapp/");
        driver.findElement(By.id("name")).sendKeys("Vinay Rao");
        driver.findElement(By.name("email")).sendKeys("vinayrao@xmpl.com");
        driver.findElement(By.cssSelector("div.form>div:nth-child(3)>input[type=text]")).sendKeys("9089786756");

        driver.findElement(By.xpath("//*[@id=\"btnAdd\"]")).click();
        // driver.quit()
    }
}
