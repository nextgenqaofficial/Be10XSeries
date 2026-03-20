# NextGenQA — Claude Code Agent Teams + Selenium Demo

> YouTube demo showing how Claude Code's native Agent Teams feature
> coordinates multiple AI agents working in parallel on a Selenium Java project.

---

## What This Is

**Claude Code Agent Teams** is an official experimental feature in Claude Code CLI
that lets you spawn multiple Claude instances working as a coordinated team —
each with its own context window, communicating via a shared task list and mailbox.

This project is the **Selenium Java codebase** the agent team works on.

---

## Quick Start

```bash
# 1. Install Claude Code (if not already installed)
npm install -g @anthropic-ai/claude-code

# 2. Open this project in Claude Code
#    First launch opens a browser — log in with your Claude.ai account
cd AgentTeams
claude

# 3. Agent teams are already enabled via .claude/settings.json
#    Just use one of the ready-made prompts in the prompts/ folder
```

---

## Ready-Made Agent Team Prompts

Copy any prompt from the `prompts/` folder and paste it into Claude Code:

| Prompt file | What it does |
|---|---|
| `prompts/01_test_coverage_team.md` | 3 teammates write tests for 3 pages in parallel |
| `prompts/02_code_review_team.md` | 3 reviewers analyse code from different angles |
| `prompts/03_debug_team.md` | 3 teammates investigate a failing test with competing hypotheses |
| `prompts/04_refactor_team.md` | 3 teammates refactor different layers simultaneously |

---

## Requirements

- Claude Code v2.1.32 or later (`claude --version`)
- Claude.ai account — Pro, Max, Teams, or Enterprise ([claude.com/pricing](https://claude.com/pricing))
- Java 17+, Maven 3.8+, Google Chrome

---

## Run the Existing Selenium Tests

```bash
mvn test               # run all tests
HEADLESS=true mvn test # headless (no browser window)
```

---

## Docs

| File | What it covers |
|---|---|
| `STEPS.md` | Full step-by-step setup and usage guide |
| `FEATURES.md` | Claude Code Agent Teams feature breakdown |
| `ARCHITECTURE.md` | How agent teams work under the hood |
| `AGENT_ROLES.md` | Lead vs teammates, communication, task claiming |
| `CLAUDE.md` | Project context loaded by every agent automatically |

---

*YouTube: NextGenQA | Claude Code Agent Teams + Selenium Java*
