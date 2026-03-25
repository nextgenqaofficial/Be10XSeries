# Claude Code Channels — Telegram Integration

**NextGenQA Be10X AI Series — Video #5**

Control Claude Code from your phone via Telegram. Send commands to Claude while it runs tests, reads files, and debugs your project — all from a mobile chat.

---

## What This Project Does

This is a Selenium Java test project used as the demo codebase for the Claude Code Channels tutorial. It shows how to:

- Connect Claude Code to a Telegram bot
- Send commands from your phone and get live results back
- Run and diagnose tests remotely without touching your machine

The Selenium project itself tests login flows on [the-internet.herokuapp.com](https://the-internet.herokuapp.com/login) using the Page Object Model pattern.

---

## Tech Stack

| Tool | Version |
|------|---------|
| Java | 11+ |
| Maven | 3.6+ |
| Selenium | 4.18.1 |
| TestNG | 7.9.0 |
| WebDriverManager | 5.7.0 |
| Claude Code | 2.1.80+ |

---

## Project Structure

```
ClaudeCodeChannels/
├── src/
│   ├── main/java/com/nextgenqa/pages/
│   │   ├── BasePage.java       # Common Selenium helpers
│   │   ├── LoginPage.java      # Login page interactions
│   │   └── HomePage.java       # Post-login page assertions
│   └── test/java/com/nextgenqa/tests/
│       ├── BaseTest.java       # Browser setup/teardown
│       └── LoginTest.java      # 3 login test scenarios
├── testng.xml                  # TestNG suite config
├── pom.xml                     # Maven dependencies
└── HOW_TO_RUN.md               # Step-by-step setup guide
```

---

## Quick Start

### 1. Run the Tests

```bash
mvn clean test
```

Expected: 3 tests pass against `https://the-internet.herokuapp.com/login`

### 2. Set Up Telegram Channel

See [HOW_TO_RUN.md](HOW_TO_RUN.md) for the full step-by-step guide covering:
- Creating a Telegram bot via @BotFather
- Installing the Claude Code Telegram plugin
- Pairing your account and setting access policy
- Keeping your session alive with tmux

### 3. Start Claude Code with Telegram

```bash
claude --channels plugin:telegram@claude-plugins-official
```

Then send commands from Telegram:

```
Run mvn test and tell me if anything is failing
What files are in this project?
How many tests do we have and what are they testing?
```

---

## Prerequisites

- Java 11+, Maven 3.6+
- Claude Code 2.1.80+ (`npm update -g @anthropic-ai/claude-code`)
- Bun runtime (`curl -fsSL https://bun.sh/install | bash`)
- Claude Pro plan (API key auth does not work with Channels)
- Google Chrome installed

---

## Series

This project is part of the **NextGenQA Be10X AI Series** — a YouTube series on AI-powered QA automation.

YouTube: [Next Gen QA](https://www.youtube.com/@nextgenqa)
