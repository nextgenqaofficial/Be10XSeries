package com.nextgenqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * HomePage — Page Object for the secure area after login.
 * URL: https://the-internet.herokuapp.com/secure
 */
public class HomePage extends BasePage {

    private static final By SECURE_AREA_HEADING = By.tagName("h2");
    private static final By FLASH_MESSAGE       = By.id("flash");
    private static final By LOGOUT_BUTTON       = By.cssSelector("a.button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isSecureAreaDisplayed() {
        return isDisplayed(SECURE_AREA_HEADING);
    }

    public String getSuccessMessage() {
        return getText(FLASH_MESSAGE);
    }

    public String getHeadingText() {
        return getText(SECURE_AREA_HEADING);
    }

    public LoginPage logout() {
        click(LOGOUT_BUTTON);
        return new LoginPage(driver);
    }
}
