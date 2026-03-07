package com.nextgenqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private static final String CHECKOUT_URL = "https://the-internet.herokuapp.com/login";

    private static final By PAGE_HEADING = By.cssSelector("h2");
    private static final By LOGIN_FORM = By.id("login");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(CHECKOUT_URL);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getHeading() {
        return getText(PAGE_HEADING);
    }

    public boolean isLoginFormDisplayed() {
        return waitForElement(LOGIN_FORM).isDisplayed();
    }
}
