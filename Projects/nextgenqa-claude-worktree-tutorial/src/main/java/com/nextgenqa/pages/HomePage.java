package com.nextgenqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * =====================================================
 * HomePage — Page Object Model
 * NextGenQA | Claude Code Git Worktree Tutorial
 * =====================================================
 *
 * Represents the page shown after a successful login.
 * URL: https://the-internet.herokuapp.com/secure
 */
public class HomePage extends BasePage {

    // ─── Locators ────────────────────────────────────
    private static final By SECURE_AREA_HEADING = By.tagName("h2");
    private static final By FLASH_MESSAGE       = By.id("flash");
    private static final By LOGOUT_BUTTON       = By.cssSelector("a.button");

    // ─── Constructor ─────────────────────────────────
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ─── Page Actions ────────────────────────────────

    /**
     * Click logout and return to the login page.
     */
    public LoginPage logout() {
        click(LOGOUT_BUTTON);
        return new LoginPage(driver);
    }

    // ─── Page Assertions ─────────────────────────────

    /**
     * Returns true when the secure area heading is visible.
     * Use this to confirm login was successful.
     */
    public boolean isSecureAreaDisplayed() {
        return isDisplayed(SECURE_AREA_HEADING);
    }

    /**
     * Get the success flash message text after login.
     */
    public String getSuccessMessage() {
        return getText(FLASH_MESSAGE);
    }

    /**
     * Get the main heading text ("Secure Area").
     */
    public String getHeadingText() {
        return getText(SECURE_AREA_HEADING);
    }
}
