package com.nextgenqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * LoginPage — Page Object for https://the-internet.herokuapp.com/login
 *
 * Valid credentials:
 *   Username: tomsmith
 *   Password: SuperSecretPassword!
 */
public class LoginPage extends BasePage {

    private static final String PAGE_URL = "https://the-internet.herokuapp.com/login";

    private static final By USERNAME_FIELD = By.id("username");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON   = By.cssSelector("button[type='submit']");
    private static final By FLASH_MESSAGE  = By.id("flash");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(USERNAME_FIELD, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(PASSWORD_FIELD, password);
        return this;
    }

    public HomePage clickLogin() {
        click(LOGIN_BUTTON);
        return new HomePage(driver);
    }

    public HomePage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }

    public LoginPage loginWithInvalidCredentials(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        click(LOGIN_BUTTON);
        return this;
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(FLASH_MESSAGE);
    }

    public String getFlashMessage() {
        return getText(FLASH_MESSAGE);
    }
}
