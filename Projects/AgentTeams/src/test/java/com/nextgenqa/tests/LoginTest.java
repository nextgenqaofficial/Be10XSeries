package com.nextgenqa.tests;

import com.nextgenqa.pages.DashboardPage;
import com.nextgenqa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LoginTest — Selenium tests for the Login page.
 * Target: https://the-internet.herokuapp.com/login
 *
 * This is an example of what the CodeGeneratorAgent produces.
 * These tests were designed based on the TestPlannerAgent's test plan and
 * reviewed by the CodeReviewerAgent before being finalized.
 *
 * Test Scenarios covered:
 *   TC01 — Successful login with valid credentials
 *   TC02 — Failed login with invalid password
 *   TC03 — Failed login with invalid username
 *   TC04 — Failed login with empty username
 *   TC05 — Failed login with empty password
 *   TC06 — Successful logout after login
 *   TC07 — Page title verification
 *   TC08 — Error message content validation
 *
 * Run with: mvn test
 *           OR: mvn test -Dtest=LoginTest#tc01_successfulLogin
 */
public class LoginTest extends BaseTest {

    // Valid credentials for the-internet.herokuapp.com
    private static final String VALID_USERNAME   = "tomsmith";
    private static final String VALID_PASSWORD   = "SuperSecretPassword!";
    private static final String INVALID_USERNAME = "wronguser";
    private static final String INVALID_PASSWORD = "wrongpassword";

    // -----------------------------------------------------------------------
    // TC01 — Happy Path: valid credentials → redirect to secure area
    // -----------------------------------------------------------------------

    @Test(description = "TC01: Successful login with valid credentials")
    public void tc01_successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);

        Assert.assertTrue(dashboard.isOnDashboard(),
                "TC01 FAIL: Should be redirected to /secure after login");
        Assert.assertTrue(dashboard.isLogoutButtonVisible(),
                "TC01 FAIL: Logout button should be visible after login");
        Assert.assertTrue(dashboard.getSuccessMessageText().contains("You logged into a secure area"),
                "TC01 FAIL: Success message should contain expected text");

        log.info("TC01 PASS: Successful login verified.");
    }

    // -----------------------------------------------------------------------
    // TC02 — Negative: invalid password → error message
    // -----------------------------------------------------------------------

    @Test(description = "TC02: Login fails with invalid password")
    public void tc02_loginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                 .enterUsername(VALID_USERNAME)
                 .enterPassword(INVALID_PASSWORD)
                 .clickLoginExpectingFailure();

        Assert.assertFalse(loginPage.getCurrentUrl().contains("/secure"),
                "TC02 FAIL: Should NOT be redirected to /secure with wrong password");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "TC02 FAIL: Error message should be displayed");
        Assert.assertTrue(loginPage.getFlashMessageText().contains("Your password is invalid"),
                "TC02 FAIL: Error message should mention invalid password");

        log.info("TC02 PASS: Invalid password rejection verified.");
    }

    // -----------------------------------------------------------------------
    // TC03 — Negative: invalid username → error message
    // -----------------------------------------------------------------------

    @Test(description = "TC03: Login fails with invalid username")
    public void tc03_loginWithInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                 .enterUsername(INVALID_USERNAME)
                 .enterPassword(VALID_PASSWORD)
                 .clickLoginExpectingFailure();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "TC03 FAIL: Error message should be displayed for invalid username");
        Assert.assertTrue(loginPage.getFlashMessageText().contains("Your username is invalid"),
                "TC03 FAIL: Error message should mention invalid username");

        log.info("TC03 PASS: Invalid username rejection verified.");
    }

    // -----------------------------------------------------------------------
    // TC04 — Edge case: empty username field
    // -----------------------------------------------------------------------

    @Test(description = "TC04: Login fails with empty username")
    public void tc04_loginWithEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                 .enterUsername("")
                 .enterPassword(VALID_PASSWORD)
                 .clickLoginExpectingFailure();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "TC04 FAIL: Error message should be displayed when username is empty");

        log.info("TC04 PASS: Empty username validation verified.");
    }

    // -----------------------------------------------------------------------
    // TC05 — Edge case: empty password field
    // -----------------------------------------------------------------------

    @Test(description = "TC05: Login fails with empty password")
    public void tc05_loginWithEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                 .enterUsername(VALID_USERNAME)
                 .enterPassword("")
                 .clickLoginExpectingFailure();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "TC05 FAIL: Error message should be displayed when password is empty");

        log.info("TC05 PASS: Empty password validation verified.");
    }

    // -----------------------------------------------------------------------
    // TC06 — Logout after successful login
    // -----------------------------------------------------------------------

    @Test(description = "TC06: Successful logout after login")
    public void tc06_successfulLogout() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);

        // Verify we're logged in
        Assert.assertTrue(dashboard.isOnDashboard(), "TC06 FAIL: Should be on dashboard before logout");

        // Logout
        LoginPage loginPageAfterLogout = dashboard.logout();

        // Verify we're back on the login page
        Assert.assertTrue(loginPageAfterLogout.getCurrentUrl().contains("/login"),
                "TC06 FAIL: Should be redirected to /login after logout");

        log.info("TC06 PASS: Logout flow verified.");
    }

    // -----------------------------------------------------------------------
    // TC07 — Page title verification
    // -----------------------------------------------------------------------

    @Test(description = "TC07: Login page has correct title")
    public void tc07_loginPageTitle() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        String title = loginPage.getPageTitle();
        Assert.assertNotNull(title, "TC07 FAIL: Page title should not be null");
        Assert.assertFalse(title.isBlank(), "TC07 FAIL: Page title should not be blank");

        log.info("TC07 PASS: Page title is '{}'", title);
    }

    // -----------------------------------------------------------------------
    // TC08 — URL does not change to /secure on failed login
    // -----------------------------------------------------------------------

    @Test(description = "TC08: URL stays on /login after failed attempt")
    public void tc08_urlRemainsOnLoginAfterFailure() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                 .enterUsername(INVALID_USERNAME)
                 .enterPassword(INVALID_PASSWORD)
                 .clickLoginExpectingFailure();

        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"),
                "TC08 FAIL: URL should remain on /login after failed login attempt");
        Assert.assertFalse(currentUrl.contains("/secure"),
                "TC08 FAIL: URL should NOT contain /secure after failed login attempt");

        log.info("TC08 PASS: URL correctly stays on /login. Current URL: {}", currentUrl);
    }
}
