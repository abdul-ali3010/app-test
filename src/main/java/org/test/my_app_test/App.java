package org.test.my_app_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");

        driver.getTitle();
        System.out.println("Page Title: " + driver.getTitle());

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement userName = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        WebElement password = driver.findElement(By.name("pass"));
//        WebElement loginButton = driver.findElement(By.id("loginbutton"));
        WebElement loginButton = driver.findElement(By.name("login"));
//        loginButton.click();


        userName.sendKeys("abc@test.com");
        Thread.sleep(2000);
        password.sendKeys("abc");
        Thread.sleep(2000);
        loginButton.click();

        WebElement message = driver.findElement(By.id("message"));
        message.getText();

        driver.quit();
        
    }
}
