# NextGenQA — Selenium Agent Teams Project

This is a Selenium Java test automation project used to demonstrate Claude Code Agent Teams.

## Project Overview

- **Purpose**: Automated UI testing of https://the-internet.herokuapp.com
- **Framework**: Selenium WebDriver 4 + TestNG + Page Object Model
- **Build tool**: Maven (`mvn test` to run all tests)
- **Java version**: 17

## Project Structure

```
src/test/java/com/nextgenqa/
├── pages/
│   ├── BasePage.java        — shared wait helpers, base for all page objects
│   ├── LoginPage.java       — login page locators + actions
│   └── DashboardPage.java   — secure area after login
└── tests/
    ├── BaseTest.java        — WebDriver setup/teardown (Chrome via WebDriverManager)
    └── LoginTest.java       — 8 test scenarios for the login page
```

## How to Run Tests

```bash
mvn test                                    # run all tests
mvn test -Dtest=LoginTest                   # run one class
mvn test -Dtest=LoginTest#tc01_successfulLogin  # run one method
HEADLESS=true mvn test                      # headless mode (no browser window)
```

## Test Site Credentials

- URL: https://the-internet.herokuapp.com/login
- Valid username: `tomsmith`
- Valid password: `SuperSecretPassword!`

## Conventions

- All page objects extend `BasePage` and use `@FindBy` locators
- All test classes extend `BaseTest` (handles WebDriver setup/teardown)
- Use `WebDriverWait` + `ExpectedConditions` — never `Thread.sleep`
- New page objects go in `src/test/java/com/nextgenqa/pages/`
- New test classes go in `src/test/java/com/nextgenqa/tests/`
- Add new test classes to `testng.xml` to include them in the suite

## Pages Available on the-internet.herokuapp.com

The site has many pages useful for Selenium practice:
- `/login` — form authentication (already tested)
- `/checkboxes` — checkbox interactions
- `/dropdown` — dropdown selection
- `/dynamic_loading/1` — element appearing after delay
- `/upload` — file upload
- `/drag_and_drop` — drag and drop
- `/javascript_alerts` — JS alert/confirm/prompt dialogs
- `/frames` — iFrames
- `/tables` — web tables and sorting
- `/hovers` — mouse hover actions

## Current Test Coverage

| Page | Status |
|------|--------|
| Login (`/login`) | 8 tests — complete |
| All other pages | Not yet covered |
