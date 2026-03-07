package com.nextgenqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * =====================================================
 * BasePage — Page Object Model Foundation
 * NextGenQA | Claude Code Git Worktree Tutorial
 * =====================================================
 *
 * Every page class extends this base.
 * It wraps common Selenium actions (click, type, wait)
 * so individual pages stay clean and readable.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Default explicit wait timeout (in seconds)
    private static final int DEFAULT_WAIT_SECONDS = 10;

    /**
     * Constructor: every page gets a driver reference and a pre-configured wait.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
    }

    // ─────────────────────────────────────────────────
    //  Reusable Helper Methods
    // ─────────────────────────────────────────────────

    /**
     * Click an element after waiting for it to be clickable.
     */
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Type text into a field after clearing it first.
     */
    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get the text content of a visible element.
     */
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Check if an element is currently visible on the page.
     */
    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get current page title.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get the current URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
