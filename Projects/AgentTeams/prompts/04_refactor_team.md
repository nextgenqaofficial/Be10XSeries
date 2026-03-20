# Prompt: Parallel Refactoring Team

Use this prompt to refactor multiple independent parts of the project
at the same time without teammates stepping on each other.

---

## The Prompt

```
Create an agent team to refactor this Selenium project. Each teammate
owns a separate, non-overlapping part of the codebase:

- Teammate 1 (Page Objects): Refactor LoginPage.java and DashboardPage.java.
  Extract all locator strings into named By constants at the top of each class.
  Add Javadoc to every public method. Do not touch any test files.

- Teammate 2 (Test Data): Refactor LoginTest.java to move all test data
  (usernames, passwords, URLs, expected messages) into a dedicated
  TestData.java constants class in the tests package.
  Update LoginTest.java to use the constants. Do not touch page objects.

- Teammate 3 (Configuration): Refactor BaseTest.java to read Chrome options
  and the base URL from a config.properties file in src/test/resources/.
  Create the properties file with sensible defaults.
  Do not touch page objects or test data.

Important: teammates must not edit the same files. Coordinate via
the task list if you discover any overlap.

When all done, verify mvn test still passes.
```

---

## Why agent teams shine here

Each refactoring is in a completely separate layer:
- Teammate 1 touches only page objects
- Teammate 2 touches only test logic
- Teammate 3 touches only infrastructure

No file conflicts. Pure parallel speedup.

## Files affected

```
Teammate 1: src/test/java/com/nextgenqa/pages/*.java
Teammate 2: src/test/java/com/nextgenqa/tests/LoginTest.java
            src/test/java/com/nextgenqa/tests/TestData.java  (new)
Teammate 3: src/test/java/com/nextgenqa/tests/BaseTest.java
            src/test/resources/config.properties  (new)
```
