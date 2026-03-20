package com.nextgenqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * CheckboxPage — Page Object for https://the-internet.herokuapp.com/checkboxes
 *
 * Covers two checkboxes:
 *   - Checkbox 1: unchecked by default
 *   - Checkbox 2: checked by default
 */
public class CheckboxPage extends BasePage {

    private static final String PAGE_URL = "https://the-internet.herokuapp.com/checkboxes";

    // -----------------------------------------------------------------------
    // Locators
    // -----------------------------------------------------------------------

    @FindBy(css = "input[type='checkbox']:nth-of-type(1)")
    private WebElement checkbox1;

    @FindBy(css = "input[type='checkbox']:nth-of-type(2)")
    private WebElement checkbox2;

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    public CheckboxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // -----------------------------------------------------------------------
    // Actions
    // -----------------------------------------------------------------------

    /** Navigate directly to the checkboxes page */
    public CheckboxPage open() {
        navigateTo(PAGE_URL);
        log.info("Opened checkboxes page: {}", PAGE_URL);
        return this;
    }

    /** Returns true if checkbox 1 is currently checked */
    public boolean isCheckbox1Checked() {
        return waitForVisible(checkbox1).isSelected();
    }

    /** Returns true if checkbox 2 is currently checked */
    public boolean isCheckbox2Checked() {
        return waitForVisible(checkbox2).isSelected();
    }

    /** Checks checkbox 1 (only clicks if currently unchecked) */
    public CheckboxPage checkCheckbox1() {
        if (!isCheckbox1Checked()) {
            waitAndClick(checkbox1);
            log.info("Checked checkbox 1");
        }
        return this;
    }

    /** Unchecks checkbox 1 (only clicks if currently checked) */
    public CheckboxPage uncheckCheckbox1() {
        if (isCheckbox1Checked()) {
            waitAndClick(checkbox1);
            log.info("Unchecked checkbox 1");
        }
        return this;
    }

    /** Checks checkbox 2 (only clicks if currently unchecked) */
    public CheckboxPage checkCheckbox2() {
        if (!isCheckbox2Checked()) {
            waitAndClick(checkbox2);
            log.info("Checked checkbox 2");
        }
        return this;
    }

    /** Unchecks checkbox 2 (only clicks if currently checked) */
    public CheckboxPage uncheckCheckbox2() {
        if (isCheckbox2Checked()) {
            waitAndClick(checkbox2);
            log.info("Unchecked checkbox 2");
        }
        return this;
    }
}
