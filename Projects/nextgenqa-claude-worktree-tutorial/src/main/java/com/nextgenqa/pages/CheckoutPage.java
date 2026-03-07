package com.nextgenqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * =====================================================
 * CheckoutPage — Page Object Model
 * NextGenQA | Claude Code Git Worktree Tutorial
 * =====================================================
 *
 * Represents a simple checkout/form page.
 * Target URL: https://the-internet.herokuapp.com/checkboxes
 *
 * ── TUTORIAL NOTE ──────────────────────────────────
 * In the Git Worktree demo, the CheckoutPageTest.java
 * contains a FAILING test that needs to be fixed.
 *
 * Claude Code will fix it in a separate worktree:
 *   fix/checkout-tests
 *
 * The bug: test checks for wrong checkbox state.
 * This simulates a real-world scenario where a dev
 * changed the default state and tests broke in CI.
 * ────────────────────────────────────────────────────
 */
public class CheckoutPage extends BasePage {

    // ─── Page URL ────────────────────────────────────
    private static final String PAGE_URL =
            "https://the-internet.herokuapp.com/checkboxes";

    // ─── Locators ────────────────────────────────────
    // On this page, there are 2 checkboxes:
    //   checkbox[0] — unchecked by default
    //   checkbox[1] — checked by default
    private static final By FIRST_CHECKBOX  = By.cssSelector("input[type='checkbox']:nth-of-type(1)");
    private static final By SECOND_CHECKBOX = By.cssSelector("input[type='checkbox']:nth-of-type(2)");
    private static final By PAGE_HEADING    = By.tagName("h3");

    // ─── Constructor ─────────────────────────────────
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // ─── Page Actions ────────────────────────────────

    /**
     * Open the checkboxes page.
     */
    public CheckoutPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    /**
     * Check the first checkbox (if not already checked).
     */
    public CheckoutPage checkFirstCheckbox() {
        if (!isFirstCheckboxSelected()) {
            click(FIRST_CHECKBOX);
        }
        return this;
    }

    /**
     * Uncheck the second checkbox (if currently checked).
     */
    public CheckoutPage uncheckSecondCheckbox() {
        if (isSecondCheckboxSelected()) {
            click(SECOND_CHECKBOX);
        }
        return this;
    }

    // ─── Page Assertions ─────────────────────────────

    /**
     * Returns true if the first checkbox is currently selected/checked.
     */
    public boolean isFirstCheckboxSelected() {
        return driver.findElement(FIRST_CHECKBOX).isSelected();
    }

    /**
     * Returns true if the second checkbox is currently selected/checked.
     */
    public boolean isSecondCheckboxSelected() {
        return driver.findElement(SECOND_CHECKBOX).isSelected();
    }

    /**
     * Get the page heading text.
     */
    public String getHeadingText() {
        return getText(PAGE_HEADING);
    }
}
