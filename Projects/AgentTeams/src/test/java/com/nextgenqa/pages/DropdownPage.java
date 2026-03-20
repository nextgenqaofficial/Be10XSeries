package com.nextgenqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * DropdownPage — Page Object for https://the-internet.herokuapp.com/dropdown
 *
 * The page contains a single &lt;select&gt; with:
 *   - Default: "Please select an option" (value="")
 *   - Option 1 (value="1")
 *   - Option 2 (value="2")
 */
public class DropdownPage extends BasePage {

    private static final String PAGE_URL = "https://the-internet.herokuapp.com/dropdown";

    // -----------------------------------------------------------------------
    // Locators
    // -----------------------------------------------------------------------

    @FindBy(id = "dropdown")
    private WebElement dropdownSelect;

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    public DropdownPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // -----------------------------------------------------------------------
    // Actions
    // -----------------------------------------------------------------------

    /** Navigate directly to the dropdown page */
    public DropdownPage open() {
        navigateTo(PAGE_URL);
        log.info("Opened dropdown page: {}", PAGE_URL);
        return this;
    }

    /** Select an option by its value attribute (e.g. "1" or "2") */
    public DropdownPage selectByValue(String value) {
        Select select = new Select(waitForVisible(dropdownSelect));
        select.selectByValue(value);
        log.info("Selected option with value: {}", value);
        return this;
    }

    /** Returns the text of the currently selected option */
    public String getSelectedOptionText() {
        Select select = new Select(waitForVisible(dropdownSelect));
        return select.getFirstSelectedOption().getText();
    }

    /** Returns true if the default placeholder option is selected */
    public boolean isDefaultSelected() {
        Select select = new Select(waitForVisible(dropdownSelect));
        String selectedValue = select.getFirstSelectedOption().getAttribute("value");
        return selectedValue == null || selectedValue.isEmpty();
    }

    /** Returns the text of the first real option (Option 1) */
    public String getFirstOptionText() {
        Select select = new Select(waitForVisible(dropdownSelect));
        // index 0 is the placeholder, index 1 is "Option 1"
        return select.getOptions().get(1).getText();
    }
}
