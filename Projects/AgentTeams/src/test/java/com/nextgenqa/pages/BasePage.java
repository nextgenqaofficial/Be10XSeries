package com.nextgenqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * BasePage — the parent class for all Page Object classes.
 *
 * Every page object extends this to get:
 *   - WebDriver reference
 *   - Explicit wait helper methods
 *   - Common navigation helpers
 *
 * YouTube Demo:
 *   This is the foundation of the Page Object Model (POM) design pattern.
 *   The agent team generates page objects that automatically extend this.
 */
public abstract class BasePage {

    protected final Logger        log    = LoggerFactory.getLogger(getClass());
    protected final WebDriver     driver;
    protected final WebDriverWait wait;

    private static final int DEFAULT_WAIT_SECONDS = 10;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
    }

    // -----------------------------------------------------------------------
    // Common wait helpers — used by all generated page objects
    // -----------------------------------------------------------------------

    /** Wait until element is visible, then return it */
    protected WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /** Wait until element is clickable, then click it */
    protected void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /** Clear a field and type text into it */
    protected void clearAndType(WebElement element, String text) {
        waitForVisible(element).clear();
        element.sendKeys(text);
    }

    /** Get the current page title */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /** Get the current page URL */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /** Navigate to a URL */
    public void navigateTo(String url) {
        log.info("Navigating to: {}", url);
        driver.get(url);
    }
}
