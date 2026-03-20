package com.nextgenqa.tests;

import com.nextgenqa.pages.DropdownPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * DropdownTest — Selenium tests for the Dropdown page.
 * Target: https://the-internet.herokuapp.com/dropdown
 *
 * Test Scenarios:
 *   TC01 — Verify default state (placeholder option selected)
 *   TC02 — Select Option 1 by value and verify selected text
 *   TC03 — Select Option 2 by value and verify selected text
 *
 * Run with: mvn test -Dtest=DropdownTest
 */
public class DropdownTest extends BaseTest {

    // -----------------------------------------------------------------------
    // TC01 — Default state: placeholder option is selected
    // -----------------------------------------------------------------------

    @Test(description = "TC01: Default state — placeholder option is selected on page load")
    public void tc01_verifyDefaultState() {
        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.open();

        Assert.assertTrue(dropdownPage.isDefaultSelected(),
                "TC01 FAIL: Default selection should be the placeholder option with no value");

        log.info("TC01 PASS: Default state verified — placeholder option is selected.");
    }

    // -----------------------------------------------------------------------
    // TC02 — Select Option 1 by value
    // -----------------------------------------------------------------------

    @Test(description = "TC02: Select Option 1 by value '1' and verify selected text")
    public void tc02_selectOption1() {
        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.open();

        dropdownPage.selectByValue("1");

        String selectedText = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedText, "Option 1",
                "TC02 FAIL: Selected option text should be 'Option 1'");

        log.info("TC02 PASS: Option 1 selected — text is '{}'", selectedText);
    }

    // -----------------------------------------------------------------------
    // TC03 — Select Option 2 by value
    // -----------------------------------------------------------------------

    @Test(description = "TC03: Select Option 2 by value '2' and verify selected text")
    public void tc03_selectOption2() {
        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.open();

        dropdownPage.selectByValue("2");

        String selectedText = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedText, "Option 2",
                "TC03 FAIL: Selected option text should be 'Option 2'");

        log.info("TC03 PASS: Option 2 selected — text is '{}'", selectedText);
    }
}
