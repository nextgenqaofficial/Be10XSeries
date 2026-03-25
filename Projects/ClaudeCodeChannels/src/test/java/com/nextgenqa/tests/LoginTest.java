package com.nextgenqa.tests;

import com.nextgenqa.pages.HomePage;
import com.nextgenqa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LoginTest — tests for https://the-internet.herokuapp.com/login
 *
 * Valid credentials:
 *   Username : tomsmith
 *   Password : SuperSecretPassword!
 */
public class LoginTest extends BaseTest {

    @Test(description = "Successful login with valid credentials")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver).open();
        HomePage homePage = loginPage.loginAs("tomsmith", "SuperSecretPassword!");

        Assert.assertTrue(homePage.isSecureAreaDisplayed(),
                "Secure area heading should be visible after login");
        Assert.assertTrue(homePage.getSuccessMessage().contains("You logged into a secure area"),
                "Success flash message should appear");
    }

    @Test(description = "Invalid credentials show an error message")
    public void testInvalidLoginShowsError() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.loginWithInvalidCredentials("wronguser", "wrongpass");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error flash message should be visible after invalid login");
        Assert.assertTrue(loginPage.getFlashMessage().contains("Your username is invalid!"),
                "Flash message should indicate invalid username");
    }

    @Test(description = "Empty credentials show an error message")
    public void testEmptyCredentialsShowError() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.loginWithInvalidCredentials("", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error flash message should be visible for empty credentials");
    }
}
