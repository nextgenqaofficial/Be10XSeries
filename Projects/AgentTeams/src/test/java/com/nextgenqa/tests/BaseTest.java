package com.nextgenqa.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * BaseTest — the parent class for all TestNG test classes.
 *
 * Handles:
 *   - WebDriver setup with WebDriverManager (no manual chromedriver download)
 *   - Headless Chrome option for CI environments
 *   - Automatic driver teardown after each test
 *
 * YouTube Demo:
 *   The CodeGeneratorAgent always generates test classes that extend this,
 *   so tests are immediately runnable without any extra setup.
 *
 * To run in headless mode (e.g., in CI/CD):
 *   Set environment variable: HEADLESS=true
 */
public abstract class BaseTest {

    protected final Logger    log    = LoggerFactory.getLogger(getClass());
    protected       WebDriver driver;

    // -----------------------------------------------------------------------
    // Suite-level setup — runs once before all tests
    // -----------------------------------------------------------------------

    @BeforeSuite
    public void setUpSuite() {
        // WebDriverManager downloads the correct ChromeDriver automatically
        WebDriverManager.chromedriver().setup();
        log.info("WebDriverManager: ChromeDriver setup complete.");
    }

    // -----------------------------------------------------------------------
    // Method-level setup — runs before each @Test method
    // -----------------------------------------------------------------------

    @BeforeMethod
    public void setUpDriver() {
        ChromeOptions options = new ChromeOptions();

        // Headless mode: set HEADLESS=true env var to run without a browser window
        boolean headless = Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS", "false"));
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            log.info("Running in HEADLESS mode.");
        } else {
            log.info("Running in HEADED mode (browser window will open).");
        }

        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        log.info("ChromeDriver started.");
    }

    // -----------------------------------------------------------------------
    // Method-level teardown — runs after each @Test method
    // -----------------------------------------------------------------------

    @AfterMethod
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
            log.info("ChromeDriver closed.");
        }
    }
}
