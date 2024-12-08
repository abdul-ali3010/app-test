package org.test.my_app_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AppFB {
    public static void main(String[] args) {
        // Set up ChromeDriver
       // System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Open Facebook
            driver.get("https://www.facebook.com/");
            System.out.println("Page Title: " + driver.getTitle());

            // Implicit Wait
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Locate and interact with elements
            WebElement userName = driver.findElement(By.id("email"));
            WebElement password = driver.findElement(By.name("pass"));
            WebElement loginButton = driver.findElement(By.name("login"));

            // Provide credentials
            userName.sendKeys("test_user@example.com");
            password.sendKeys("test_password");
            loginButton.click();
         // Wait for post-login element (adjust locator as per actual response)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='Profile']")));

            System.out.println("Login successful. Profile link found: " + profileLink.getText());
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
        } finally {
            // Quit driver
            driver.quit();
        }
    }
}