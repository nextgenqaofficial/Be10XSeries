package com.nextgenqa.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * =====================================================
 * BaseTest — Test Setup & Teardown
 * NextGenQA | Claude Code Git Worktree Tutorial
 * =====================================================
 *
 * All test classes extend this base.
 * It handles:
 *   - Browser setup (ChromeDriver via WebDriverManager)
 *   - Browser teardown after each test
 *
 * The driver is protected so test subclasses can use it.
 */
public class BaseTest {

    protected WebDriver driver;

    /**
     * Before each test: set up ChromeDriver.
     * WebDriverManager automatically downloads the correct ChromeDriver.
     */
    @BeforeMethod
    public void setUp() {
        // WebDriverManager handles driver download automatically — no manual setup needed!
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Run headless in CI environments (comment out locally to see the browser)
        // options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--window-position=-1920,0");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    /**
     * After each test: quit the browser.
     * Always runs even if the test fails (ensures clean state).
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
