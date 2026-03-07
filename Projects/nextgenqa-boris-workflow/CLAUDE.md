# CLAUDE.md — NextGenQA Boris Workflow Project

## Project Context
This is a Selenium Java TestNG project for QA automation tutorials.
Target site: https://the-internet.herokuapp.com
Test framework: TestNG + Maven
Pattern: Page Object Model (POM)

## Rules Claude Must Always Follow

### Wait Strategy
- NEVER use Thread.sleep() for any reason
- ALWAYS use WebDriverWait with ExpectedConditions
- Default wait timeout: 10 seconds
- Example:
  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

### Test Method Naming
- Pattern: test_[action]_[expected_outcome]
- Good: test_login_with_valid_credentials_succeeds
- Bad: testLogin, loginTest, test1

### Assertions
- Always assert the visible user-facing outcome, not just element presence
- Include a failure message in every Assert call:
  Assert.assertTrue(condition, "Expected X but condition was false")

### Driver Setup
- Always initialize in @BeforeMethod, quit in @AfterMethod
- Always wrap driver.quit() in a null check:
  if (driver != null) { driver.quit(); }

### Page Objects
- Every page gets its own class in com.nextgenqa.pages
- All locators defined as private static final By fields at the top
- No driver references outside of page object classes
