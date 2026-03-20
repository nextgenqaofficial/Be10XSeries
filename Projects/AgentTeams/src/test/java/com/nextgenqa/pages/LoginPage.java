package com.nextgenqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginPage — Page Object for https://the-internet.herokuapp.com/login
 *
 * This is an example of what the CodeGeneratorAgent produces.
 * It encapsulates all locators and actions for the Login page so tests
 * don't need to know about HTML implementation details.
 *
 * YouTube Demo:
 *   When the CodeGeneratorAgent runs, it creates files like this one
 *   automatically based on the test plan from TestPlannerAgent.
 */
public class LoginPage extends BasePage {

    private static final String LOGIN_URL = "https://the-internet.herokuapp.com/login";

    // -----------------------------------------------------------------------
    // Locators — using @FindBy annotations (Page Object Model best practice)
    // -----------------------------------------------------------------------

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    @FindBy(css = "#flash.success")
    private WebElement successMessage;

    @FindBy(css = "#flash.error")
    private WebElement errorMessage;

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // -----------------------------------------------------------------------
    // Actions — one public method per user action
    // -----------------------------------------------------------------------

    /** Navigate directly to the login page */
    public LoginPage open() {
        navigateTo(LOGIN_URL);
        log.info("Opened login page: {}", LOGIN_URL);
        return this;
    }

    /** Enter a username */
    public LoginPage enterUsername(String username) {
        clearAndType(usernameField, username);
        log.info("Entered username: {}", username);
        return this;
    }

    /** Enter a password */
    public LoginPage enterPassword(String password) {
        clearAndType(passwordField, password);
        log.info("Entered password: [hidden]");
        return this;
    }

    /** Click the Login button — returns a DashboardPage on success */
    public DashboardPage clickLogin() {
        waitAndClick(loginButton);
        log.info("Clicked login button");
        return new DashboardPage(driver);
    }

    /** Convenience method: perform the full login flow */
    public DashboardPage loginWith(String username, String password) {
        return open()
                .enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    /** Click login without navigating away (useful for negative tests) */
    public LoginPage clickLoginExpectingFailure() {
        waitAndClick(loginButton);
        return this;
    }

    // -----------------------------------------------------------------------
    // Assertions helpers — used by tests
    // -----------------------------------------------------------------------

    /** Returns true if a success flash message is visible */
    public boolean isSuccessMessageDisplayed() {
        try {
            return waitForVisible(flashMessage).getAttribute("class").contains("success");
        } catch (Exception e) {
            return false;
        }
    }

    /** Returns true if an error flash message is visible */
    public boolean isErrorMessageDisplayed() {
        try {
            return waitForVisible(flashMessage).getAttribute("class").contains("error");
        } catch (Exception e) {
            return false;
        }
    }

    /** Returns the text of the flash message */
    public String getFlashMessageText() {
        try {
            return waitForVisible(flashMessage).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    /** Returns true if the username field is empty */
    public boolean isUsernameFieldEmpty() {
        return usernameField.getAttribute("value").isEmpty();
    }

    /** Returns true if the password field is empty */
    public boolean isPasswordFieldEmpty() {
        return passwordField.getAttribute("value").isEmpty();
    }
}
