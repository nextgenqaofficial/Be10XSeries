package com.nextgenqa.tests;

import com.nextgenqa.pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * =====================================================
 * CheckoutPageTest — Selenium Tests for Checkboxes Page
 * NextGenQA | Claude Code Git Worktree Tutorial
 * =====================================================
 *
 * ╔══════════════════════════════════════════════════╗
 * ║  🐛  WORKTREE DEMO — BUG-FIX BRANCH  🐛          ║
 * ║                                                  ║
 * ║  This file contains a FAILING TEST.              ║
 * ║  It simulates a real CI failure scenario.        ║
 * ║                                                  ║
 * ║  TUTORIAL STEP:                                  ║
 * ║  Open Terminal 2 and run:                        ║
 * ║                                                  ║
 * ║    claude --worktree fix/checkout-tests          ║
 * ║                                                  ║
 * ║  Then ask Claude:                                ║
 * ║  "There is a failing test in                     ║
 * ║   CheckoutPageTest.java. Investigate the bug,   ║
 * ║   explain what is wrong, and fix it."            ║
 * ╚══════════════════════════════════════════════════╝
 *
 * Target site: https://the-internet.herokuapp.com/checkboxes
 *
 * ── THE BUG ────────────────────────────────────────
 * The page has 2 checkboxes:
 *   Checkbox 1 — UNCHECKED by default
 *   Checkbox 2 — CHECKED by default
 *
 * A developer recently changed the initial page state
 * and the assertion below now has the wrong expected
 * value. This causes the test to fail in CI.
 * ────────────────────────────────────────────────────
 */
public class CheckoutPageTest extends BaseTest {

    // ─────────────────────────────────────────────────────────────
    //  TEST 1: Default State  ✅  (Passes)
    // ─────────────────────────────────────────────────────────────

    @Test(description = "Verify the checkboxes page loads correctly")
    public void testCheckboxesPageLoads() {
        CheckoutPage checkoutPage = new CheckoutPage(driver).open();

        Assert.assertEquals(checkoutPage.getHeadingText(), "Checkboxes",
                "Page heading should be 'Checkboxes'");
    }

    // ─────────────────────────────────────────────────────────────
    //  TEST 2: First Checkbox Default State  🐛  (FAILING TEST)
    // ─────────────────────────────────────────────────────────────

    @Test(description = "Verify first checkbox is unchecked by default")
    public void testFirstCheckboxIsUncheckedByDefault() {
        CheckoutPage checkoutPage = new CheckoutPage(driver).open();

        // BUG IS HERE ↓
        // The first checkbox is UNCHECKED by default on this page.
        // But this assertion wrongly expects it to be CHECKED (true).
        // Fix: change `true` to `false` to match the actual page behavior.
        Assert.assertFalse(checkoutPage.isFirstCheckboxSelected(),
                "First checkbox should be UNCHECKED by default");
    }

    // ─────────────────────────────────────────────────────────────
    //  TEST 3: Second Checkbox Default State  ✅  (Passes)
    // ─────────────────────────────────────────────────────────────

    @Test(description = "Verify second checkbox is checked by default")
    public void testSecondCheckboxIsCheckedByDefault() {
        CheckoutPage checkoutPage = new CheckoutPage(driver).open();

        Assert.assertTrue(checkoutPage.isSecondCheckboxSelected(),
                "Second checkbox should be CHECKED by default");
    }
}
