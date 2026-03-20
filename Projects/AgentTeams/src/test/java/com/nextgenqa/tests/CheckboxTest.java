package com.nextgenqa.tests;

import com.nextgenqa.pages.CheckboxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * CheckboxTest — Selenium tests for the Checkboxes page.
 * Target: https://the-internet.herokuapp.com/checkboxes
 *
 * Test Scenarios:
 *   TC01 — Check the first checkbox and verify it becomes checked
 *   TC02 — Uncheck the second checkbox and verify it becomes unchecked
 *   TC03 — Verify default state: checkbox 1 unchecked, checkbox 2 checked
 *
 * Run with: mvn test -Dtest=CheckboxTest
 */
public class CheckboxTest extends BaseTest {

    // -----------------------------------------------------------------------
    // TC01 — Check the first checkbox (default: unchecked)
    // -----------------------------------------------------------------------

    @Test(description = "TC01: Check checkbox 1 and verify it becomes checked")
    public void tc01_checkCheckbox1() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.open();

        // Ensure checkbox 1 starts unchecked (default state)
        checkboxPage.uncheckCheckbox1();
        Assert.assertFalse(checkboxPage.isCheckbox1Checked(),
                "TC01 FAIL: Checkbox 1 should be unchecked before the test action");

        // Now check it
        checkboxPage.checkCheckbox1();
        Assert.assertTrue(checkboxPage.isCheckbox1Checked(),
                "TC01 FAIL: Checkbox 1 should be checked after clicking it");

        log.info("TC01 PASS: Checkbox 1 successfully checked.");
    }

    // -----------------------------------------------------------------------
    // TC02 — Uncheck the second checkbox (default: checked)
    // -----------------------------------------------------------------------

    @Test(description = "TC02: Uncheck checkbox 2 and verify it becomes unchecked")
    public void tc02_uncheckCheckbox2() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.open();

        // Ensure checkbox 2 starts checked (default state)
        checkboxPage.checkCheckbox2();
        Assert.assertTrue(checkboxPage.isCheckbox2Checked(),
                "TC02 FAIL: Checkbox 2 should be checked before the test action");

        // Now uncheck it
        checkboxPage.uncheckCheckbox2();
        Assert.assertFalse(checkboxPage.isCheckbox2Checked(),
                "TC02 FAIL: Checkbox 2 should be unchecked after clicking it");

        log.info("TC02 PASS: Checkbox 2 successfully unchecked.");
    }

    // -----------------------------------------------------------------------
    // TC03 — Verify default state on fresh page load
    // -----------------------------------------------------------------------

    @Test(description = "TC03: Verify default state — checkbox 1 unchecked, checkbox 2 checked")
    public void tc03_verifyDefaultState() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.open();

        Assert.assertFalse(checkboxPage.isCheckbox1Checked(),
                "TC03 FAIL: Checkbox 1 should be unchecked by default");
        Assert.assertTrue(checkboxPage.isCheckbox2Checked(),
                "TC03 FAIL: Checkbox 2 should be checked by default");

        log.info("TC03 PASS: Default checkbox states verified (1=unchecked, 2=checked).");
    }
}
