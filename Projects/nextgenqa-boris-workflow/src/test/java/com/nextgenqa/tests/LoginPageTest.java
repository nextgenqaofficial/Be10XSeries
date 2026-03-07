package com.nextgenqa.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Login Page Tests — scaffold for Tab 1 (Feature Writer session)
 *
 * Target: https://the-internet.herokuapp.com/login
 * Valid credentials: tomsmith / SuperSecretPassword!
 *
 * Claude should complete all TODO methods using:
 * - Page Object Model (LoginPage.java already exists)
 * - Explicit waits (WebDriverWait, not Thread.sleep)
 * - Descriptive assertions with failure messages
 */
public class LoginPageTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://the-internet.herokuapp.com/login";
    private static final String VALID_USERNAME = "tomsmith";
    private static final String VALID_PASSWORD = "SuperSecretPassword!";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // TODO: Implement test_successful_login
    // Login with valid credentials
    // Assert flash message contains "You logged into a secure area!"

    // TODO: Implement test_invalid_username
    // Login with wrong username, correct password
    // Assert error message appears

    // TODO: Implement test_invalid_password
    // Login with correct username, wrong password
    // Assert error message appears

    // TODO: Implement test_empty_credentials
    // Submit login form with empty fields
    // Assert validation error appears

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
