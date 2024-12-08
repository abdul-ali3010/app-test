package org.test.my_app_test;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class AppFB {
    public static void main(String[] args) {
        // Configure the path to chromedriver if required
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Use ChromeOptions for optional headless mode or other settings
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // headless

        WebDriver driver = new ChromeDriver(options);

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

            // Wait for a post-login element (adjust locator as per actual response)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='Profile']")));

            System.out.println("Login successful. Profile link found: " + profileLink.getText());

            // Take a screenshot for a successful test
            takeScreenshot(driver, "login_success.png");
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());

            // Take a screenshot for a failed test
            takeScreenshot(driver, "login_failure.png");
        } finally {
            // Quit driver
            driver.quit();
        }
    }

    // Utility method for taking screenshots
    public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), Paths.get(fileName));
            System.out.println("Screenshot saved: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
