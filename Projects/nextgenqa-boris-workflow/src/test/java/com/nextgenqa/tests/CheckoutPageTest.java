package com.nextgenqa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Checkout Page Tests — contains a DELIBERATE BUG for Tab 2 (Bug Fixer session)
 *
 * Bug: The title assertion expects "The Internet - Login Page"
 *      but the actual page title is "The Internet"
 *
 * Claude should:
 * 1. Identify the wrong assertion
 * 2. Fix it to match the actual page title
 * 3. Verify with mvn test -Dtest=CheckoutPageTest
 */
public class CheckoutPageTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Verify checkout page loads with correct title")
    public void test_checkout_page_title() {
        driver.get("https://the-internet.herokuapp.com/login");

        // BUG: wrong assertion — checking wrong text
        String title = driver.getTitle();
        Assert.assertEquals(title, "The Internet - Login Page",
                "Page title should match expected value");
        // actual title is "The Internet"
    }

    @Test(description = "Verify login form is present on the page")
    public void test_login_form_is_displayed() {
        driver.get("https://the-internet.herokuapp.com/login");

        boolean formExists = driver.findElement(By.id("login")).isDisplayed();
        Assert.assertTrue(formExists, "Login form should be visible on the page");
    }

    @Test(description = "Verify page heading text")
    public void test_page_heading() {
        driver.get("https://the-internet.herokuapp.com/login");

        String heading = driver.findElement(By.cssSelector("h2")).getText();
        Assert.assertEquals(heading, "Login Page",
                "Page heading should be 'Login Page'");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
