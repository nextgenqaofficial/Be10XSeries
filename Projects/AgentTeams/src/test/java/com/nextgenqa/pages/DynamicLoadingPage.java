package com.nextgenqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * DynamicLoadingPage — Page Object for https://the-internet.herokuapp.com/dynamic_loading/1
 *
 * The page hides an element behind a "Start" button + loading spinner.
 * After clicking Start, a loader appears, then the #finish element becomes visible
 * with the text "Hello World!".
 */
public class DynamicLoadingPage extends BasePage {

    private static final String PAGE_URL = "https://the-internet.herokuapp.com/dynamic_loading/1";

    // -----------------------------------------------------------------------
    // Locators
    // -----------------------------------------------------------------------

    @FindBy(css = "#start button")
    private WebElement startButton;

    @FindBy(id = "finish")
    private WebElement finishElement;

    @FindBy(id = "loading")
    private WebElement loadingSpinner;

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // -----------------------------------------------------------------------
    // Actions
    // -----------------------------------------------------------------------

    /** Navigate directly to the dynamic loading page */
    public DynamicLoadingPage open() {
        navigateTo(PAGE_URL);
        log.info("Opened dynamic loading page: {}", PAGE_URL);
        return this;
    }

    /** Click the Start button to trigger the dynamic load */
    public DynamicLoadingPage clickStart() {
        waitAndClick(startButton);
        log.info("Clicked Start button");
        return this;
    }

    /**
     * Waits for the #finish element to become visible, then returns its text.
     * Uses the 10-second WebDriverWait inherited from BasePage — no Thread.sleep.
     */
    public String waitForFinishText() {
        String text = waitForVisible(finishElement).getText().trim();
        log.info("Finish element visible with text: '{}'", text);
        return text;
    }

    /**
     * Returns true once the loading spinner has disappeared.
     * Uses explicit wait — no Thread.sleep.
     */
    public boolean isLoadingSpinnerGone() {
        wait.until(ExpectedConditions.invisibilityOf(loadingSpinner));
        log.info("Loading spinner is gone");
        return true;
    }

    /** Returns true if the loading spinner is currently visible */
    public boolean isLoadingSpinnerVisible() {
        try {
            return loadingSpinner.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
