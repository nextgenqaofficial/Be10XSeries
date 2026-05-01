# Boris Workflow Demo

Demo project for a Boris-style workflow where multiple Claude Code sessions work in parallel on separate copies of the same Selenium Java repository.

## What This Project Covers

- Running 3 Claude Code sessions in parallel
- Using one session for feature work, one for bug fixing, and one for project rules
- Reviewing and approving AI output instead of doing all edits manually
- Coordinating Selenium test work with isolated repo copies

## Stack

- Java 11
- Maven
- Selenium
- TestNG

## Quick Start

```bash
mvn clean compile
mvn clean test
```

## Demo Roles

- Tab 1: complete `LoginPageTest.java`
- Tab 2: fix the bug in `CheckoutPageTest.java`
- Tab 3: review the project and update `CLAUDE.md`

## Important Files

- `instructions.md` - fuller tutorial and recording script
- `steps.md` - demo walk-through
- `CLAUDE.md` - project rules and conventions for Claude
- `src/main/java/com/nextgenqa/pages/` - page objects
- `src/test/java/com/nextgenqa/tests/` - demo test classes

## Notes

- This workflow uses separate repo copies rather than git worktrees.
- Local `.properties` files are ignored and should not be committed.
- The project is intentionally set up with incomplete or buggy test code for the demo.
