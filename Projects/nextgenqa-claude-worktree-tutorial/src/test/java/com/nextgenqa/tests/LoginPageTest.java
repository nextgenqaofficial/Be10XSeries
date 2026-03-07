package com.nextgenqa.tests;

import com.nextgenqa.pages.HomePage;
import com.nextgenqa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * =====================================================
 * LoginPageTest — Selenium Tests for Login Page
 * NextGenQA | Claude Code Git Worktree Tutorial
 * =====================================================
 *
 * ╔══════════════════════════════════════════════════╗
 * ║  🚧  WORKTREE DEMO — FEATURE BRANCH  🚧          ║
 * ║                                                  ║
 * ║  This file is INTENTIONALLY INCOMPLETE.          ║
 * ║                                                  ║
 * ║  TUTORIAL STEP:                                  ║
 * ║  Open Terminal 1 and run:                        ║
 * ║                                                  ║
 * ║    claude --worktree feature/login-tests         ║
 * ║                                                  ║
 * ║  Then ask Claude:                                ║
 * ║  "Complete all TODO test methods in              ║
 * ║   LoginPageTest.java using the LoginPage and     ║
 * ║   HomePage page objects. Use the-internet site." ║
 * ╚══════════════════════════════════════════════════╝
 *
 * Target site: https://the-internet.herokuapp.com/login
 *   Valid credentials:
 *     Username: tomsmith
 *     Password: SuperSecretPassword!
 */
public class LoginPageTest extends BaseTest {

    // ─────────────────────────────────────────────────────────────
    //  TEST 1: Valid Login  ✅  (Complete — shown as example)
    // ─────────────────────────────────────────────────────────────

    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver).open();

        HomePage homePage = loginPage.loginAs("tomsmith", "SuperSecretPassword!");

        Assert.assertTrue(homePage.isSecureAreaDisplayed(),
                "Secure area heading should be visible after login");
        Assert.assertTrue(homePage.getSuccessMessage().contains("You logged into a secure area"),
                "Success flash message should appear after login");
    }

    // ─────────────────────────────────────────────────────────────
    //  TEST 2: Invalid Login  🚧  (TODO — complete in worktree)
    // ─────────────────────────────────────────────────────────────

    @Test(description = "Verify error message with invalid credentials")
    public void testInvalidLoginShowsError() {
        LoginPage loginPage = new LoginPage(driver).open();

        loginPage.loginWithInvalidCredentials("wronguser", "wrongpass");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error flash message should be visible after invalid login");
        Assert.assertTrue(loginPage.getFlashMessage().contains("Your username is invalid!"),
                "Flash message should indicate invalid username");
    }

    // ─────────────────────────────────────────────────────────────
    //  TEST 3: Empty Fields  🚧  (TODO — complete in worktree)
    // ─────────────────────────────────────────────────────────────

    @Test(description = "Verify error when username and password are empty")
    public void testLoginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver).open();

        loginPage.loginWithInvalidCredentials("", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error flash message should be visible when credentials are empty");
    }
}
