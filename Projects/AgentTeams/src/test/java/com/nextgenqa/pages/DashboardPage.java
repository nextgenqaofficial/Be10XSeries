package com.nextgenqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * DashboardPage — Page Object for the secure area after login.
 * URL: https://the-internet.herokuapp.com/secure
 *
 * YouTube Demo:
 *   The CodeGeneratorAgent creates this alongside LoginPage.java
 *   because the test plan says "verify successful login leads to dashboard".
 */
public class DashboardPage extends BasePage {

    // -----------------------------------------------------------------------
    // Locators
    // -----------------------------------------------------------------------

    @FindBy(css = "h2")
    private WebElement pageHeading;

    @FindBy(css = "a[href='/logout']")
    private WebElement logoutLink;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // -----------------------------------------------------------------------
    // Actions
    // -----------------------------------------------------------------------

    /** Click the logout link and return to LoginPage */
    public LoginPage logout() {
        waitAndClick(logoutLink);
        log.info("Clicked logout");
        return new LoginPage(driver);
    }

    // -----------------------------------------------------------------------
    // Assertion helpers
    // -----------------------------------------------------------------------

    /** Returns true if the user is on the secure dashboard */
    public boolean isOnDashboard() {
        return getCurrentUrl().contains("/secure");
    }

    /** Returns the heading text of the dashboard page */
    public String getHeadingText() {
        try {
            return waitForVisible(pageHeading).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    /** Returns the login success flash message text */
    public String getSuccessMessageText() {
        try {
            return waitForVisible(flashMessage).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    /** Returns true if the logout button is visible (confirms authenticated session) */
    public boolean isLogoutButtonVisible() {
        try {
            return waitForVisible(logoutLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
