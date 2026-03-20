# Prompt: Test Coverage Expansion Team

Use this prompt in Claude Code to spawn an agent team that expands test coverage
across multiple pages in parallel.

---

## The Prompt

```
Create an agent team to expand Selenium test coverage for this project.

Spawn 3 teammates:
- Teammate 1 (Checkboxes): Write a CheckboxPage page object and CheckboxTest
  covering: checking a box, unchecking a box, verifying state persists.
  Target: https://the-internet.herokuapp.com/checkboxes

- Teammate 2 (Dropdown): Write a DropdownPage page object and DropdownTest
  covering: selecting each option by value, verifying selected text,
  verifying default state.
  Target: https://the-internet.herokuapp.com/dropdown

- Teammate 3 (Dynamic Loading): Write a DynamicLoadingPage page object and
  DynamicLoadingTest covering: triggering load, waiting for element to appear,
  verifying text content.
  Target: https://the-internet.herokuapp.com/dynamic_loading/1

Each teammate should follow the existing patterns in the codebase:
- Extend BasePage for page objects
- Extend BaseTest for test classes
- Use @FindBy locators and WebDriverWait (no Thread.sleep)
- After writing their files, add the test class to testng.xml

When all teammates are done, review all 3 new test files for consistency
and confirm mvn test passes.
```

---

## What to expect

- 3 teammates work in parallel — each owns a different page
- Each produces 2 files: a Page Object + a Test class
- Lead synthesises and verifies consistency at the end
- Total time: ~3-5 minutes instead of ~10-15 minutes sequentially

## Files created by this team

```
src/test/java/com/nextgenqa/pages/CheckboxPage.java
src/test/java/com/nextgenqa/pages/DropdownPage.java
src/test/java/com/nextgenqa/pages/DynamicLoadingPage.java
src/test/java/com/nextgenqa/tests/CheckboxTest.java
src/test/java/com/nextgenqa/tests/DropdownTest.java
src/test/java/com/nextgenqa/tests/DynamicLoadingTest.java
```
