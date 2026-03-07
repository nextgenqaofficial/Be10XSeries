package com.nextgenqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * =====================================================
 * LoginPage — Page Object Model
 * NextGenQA | Claude Code Git Worktree Tutorial
 * =====================================================
 *
 * Represents the Login page of our demo application.
 * Target URL: https://the-internet.herokuapp.com/login
 *
 * ── TUTORIAL NOTE ──────────────────────────────────
 * In the Git Worktree demo, Claude Code will be asked
 * to COMPLETE the LoginPageTest.java in a separate
 * worktree branch: feature/login-tests
 *
 * Valid credentials for demo site:
 *   Username: tomsmith
 *   Password: SuperSecretPassword!
 * ────────────────────────────────────────────────────
 */
public class LoginPage extends BasePage {

    // ─── Page URL ────────────────────────────────────
    private static final String PAGE_URL =
            "https://the-internet.herokuapp.com/login";

    // ─── Locators ────────────────────────────────────
    private static final By USERNAME_FIELD  = By.id("username");
    private static final By PASSWORD_FIELD  = By.id("password");
    private static final By LOGIN_BUTTON    = By.cssSelector("button[type='submit']");
    private static final By ERROR_MESSAGE   = By.id("flash");
    private static final By SUCCESS_MESSAGE = By.id("flash");

    // ─── Constructor ─────────────────────────────────
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ─── Page Actions ────────────────────────────────

    /**
     * Navigate directly to the login page.
     */
    public LoginPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    /**
     * Enter username into the username field.
     */
    public LoginPage enterUsername(String username) {
        type(USERNAME_FIELD, username);
        return this;
    }

    /**
     * Enter password into the password field.
     */
    public LoginPage enterPassword(String password) {
        type(PASSWORD_FIELD, password);
        return this;
    }

    /**
     * Click the login button and return the resulting HomePage.
     */
    public HomePage clickLoginButton() {
        click(LOGIN_BUTTON);
        return new HomePage(driver);
    }

    /**
     * Full login flow in one call — convenience method.
     */
    public HomePage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }

    /**
     * Submit login with invalid credentials (stays on login page).
     */
    public LoginPage loginWithInvalidCredentials(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        click(LOGIN_BUTTON);
        return this;
    }

    // ─── Page Assertions (used in tests) ─────────────

    /**
     * Returns true if an error message flash is visible.
     */
    public boolean isErrorDisplayed() {
        return isDisplayed(ERROR_MESSAGE);
    }

    /**
     * Get the text of the error/success flash message.
     */
    public String getFlashMessage() {
        return getText(ERROR_MESSAGE);
    }
}
