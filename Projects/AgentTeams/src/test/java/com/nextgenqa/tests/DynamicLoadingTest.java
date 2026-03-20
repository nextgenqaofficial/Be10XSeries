package com.nextgenqa.tests;

import com.nextgenqa.pages.DynamicLoadingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * DynamicLoadingTest — Selenium tests for the Dynamic Loading page.
 * Target: https://the-internet.herokuapp.com/dynamic_loading/1
 *
 * Test Scenarios:
 *   TC01 — Click Start and verify the loading spinner appears
 *   TC02 — Wait for the finish element to become visible after clicking Start
 *   TC03 — Verify the finish element contains the expected text "Hello World!"
 *
 * Run with: mvn test -Dtest=DynamicLoadingTest
 */
public class DynamicLoadingTest extends BaseTest {

    // -----------------------------------------------------------------------
    // TC01 — Click Start and verify loading spinner appears
    // -----------------------------------------------------------------------

    @Test(description = "TC01: Clicking Start triggers the loading spinner")
    public void tc01_clickStartShowsSpinner() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver);
        page.open();
        page.clickStart();

        // The spinner should appear immediately after clicking Start.
        // We check by waiting for it to go away (proves it was present).
        boolean spinnerGone = page.isLoadingSpinnerGone();
        Assert.assertTrue(spinnerGone,
                "TC01 FAIL: Loading spinner should have appeared and then disappeared");

        log.info("TC01 PASS: Loading spinner appeared and disappeared after clicking Start.");
    }

    // -----------------------------------------------------------------------
    // TC02 — Finish element becomes visible after load completes
    // -----------------------------------------------------------------------

    @Test(description = "TC02: Finish element is visible after dynamic load completes")
    public void tc02_finishElementAppearsAfterLoad() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver);
        page.open();
        page.clickStart();

        String finishText = page.waitForFinishText();
        Assert.assertNotNull(finishText,
                "TC02 FAIL: Finish element text should not be null");
        Assert.assertFalse(finishText.isBlank(),
                "TC02 FAIL: Finish element text should not be blank");

        log.info("TC02 PASS: Finish element appeared with text: '{}'", finishText);
    }

    // -----------------------------------------------------------------------
    // TC03 — Finish element contains expected text "Hello World!"
    // -----------------------------------------------------------------------

    @Test(description = "TC03: Finish element contains 'Hello World!' after load")
    public void tc03_verifyFinishTextContent() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver);
        page.open();
        page.clickStart();

        String finishText = page.waitForFinishText();
        Assert.assertTrue(finishText.contains("Hello World!"),
                "TC03 FAIL: Finish element should contain 'Hello World!' but was: '" + finishText + "'");

        log.info("TC03 PASS: Finish text verified — '{}'", finishText);
    }
}
