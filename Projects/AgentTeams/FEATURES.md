# Claude Code Agent Teams — Features Guide

> **YouTube Demo:** NextGenQA | Claude Code Agent Teams + Selenium Java

---

## What Is It?

**Claude Code Agent Teams** is an official experimental feature in Claude Code CLI
(v2.1.32+) that lets you coordinate multiple Claude Code instances as a team.

One session is the **team lead** — it coordinates work, assigns tasks, and synthesises results.
The rest are **teammates** — independent Claude Code sessions, each with their own context
window, working in parallel and communicating directly with each other.

> This is built into Claude Code. No extra code, no custom orchestration.
> You enable it with one config line and prompt in plain English.

---

## Key Features

### 1. Parallel Execution

Teammates work simultaneously in independent context windows.

```
Without Agent Teams (sequential):
  Write CheckboxTest    →  Write DropdownTest  →  Write DynamicLoadingTest
  [2 min]                  [2 min]                 [2 min]
  Total: 6 minutes

With Agent Teams (parallel):
  Teammate 1: Write CheckboxTest       ┐
  Teammate 2: Write DropdownTest       ├── all at the same time
  Teammate 3: Write DynamicLoadingTest ┘
  Total: ~2 minutes
```

Best for: independent modules, separate pages, non-overlapping files.

---

### 2. Shared Task List

All agents share a live task list. Teammates:
- Claim available tasks automatically when they finish one
- See each other's task status in real time
- Cannot claim a task that's already in progress (file-locked)

```
Task List example:
  [✓] Write CheckboxPage.java          — Teammate 1 (done)
  [→] Write CheckboxTest.java          — Teammate 1 (in progress)
  [ ] Write DropdownPage.java          — Teammate 2 (pending)
  [→] Write DropdownTest.java          — Teammate 2 (in progress)
  [ ] Write DynamicLoadingPage.java    — Teammate 3 (pending)
```

Press `Ctrl+T` in Claude Code to toggle the task list view.

---

### 3. Direct Teammate Messaging

Teammates communicate directly — not just through the lead.

- **Lead → Teammate**: assign tasks, approve plans, redirect work
- **Teammate → Lead**: report completion, ask for clarification, request approval
- **Teammate → Teammate**: share findings, challenge theories, coordinate

This is what separates agent teams from subagents — real peer-to-peer communication.
It enables the "competing hypotheses" debugging pattern where teammates actively argue.

Press `Shift+Down` to cycle through teammates and message them directly.

---

### 4. Plan Approval Gate

For risky work, require a teammate to plan before touching code:

```
Spawn a refactoring teammate for LoginPage.java.
Require plan approval before they make any changes.
```

Flow:
```
Teammate plans → sends plan to Lead → Lead reviews
      │
      ├── Approved → teammate implements
      └── Rejected with feedback → teammate revises and resubmits
```

The lead approves/rejects autonomously. You can influence its criteria:
```
"Only approve plans that preserve all existing test assertions"
```

---

### 5. CLAUDE.md Gives Every Agent Project Context

Teammates don't inherit the lead's conversation history.
But they **do** read `CLAUDE.md` automatically on spawn.

This project's `CLAUDE.md` tells every agent:
- How to run tests (`mvn test`)
- Project structure (where to put new files)
- Coding conventions (extend BasePage, use WebDriverWait)
- Available test pages and their URLs

This means teammates start with full project knowledge without you
having to repeat it in every prompt.

---

### 6. Two Display Modes

**In-process (default)** — all teammates in one terminal
- Works in any terminal (Windows CMD, PowerShell, VS Code terminal)
- Use `Shift+Down` to cycle through teammates
- No extra setup

**Split panes** — each teammate gets its own pane
- Requires tmux (macOS/Linux) or iTerm2
- See all teammates' output simultaneously
- Click into any pane to interact directly
- Best for YouTube demos — most visually impressive

Set in `.claude/settings.json`:
```json
{ "teammateMode": "tmux" }
```

---

### 7. Agent Teams vs Subagents

Both parallelize work — but they're different:

| | Subagents | Agent Teams |
|---|---|---|
| Communication | Report back to lead only | Direct peer-to-peer messaging |
| Coordination | Lead manages everything | Shared task list, self-coordinating |
| Best for | Quick focused tasks | Complex work needing collaboration |
| Token cost | Lower | Higher (each teammate = full Claude session) |
| Use when | Result is all that matters | Teammates need to share and challenge findings |

---

### 8. Hooks for Quality Gates

Use Claude Code hooks to enforce standards automatically:

- **`TeammateIdle`** — runs when a teammate goes idle. Exit code 2 = send feedback and keep them working.
- **`TaskCompleted`** — runs when a task is marked done. Exit code 2 = block completion and send feedback.

Example: block task completion if tests don't pass:
```bash
# .claude/hooks/TaskCompleted.sh
mvn test -q || exit 2
```

---

## Best Practices for This Project

| Practice | Why |
|---|---|
| One teammate per page/file | Prevents conflicts — teammates never edit the same file |
| 3-5 teammates max | Coordination overhead grows fast beyond 5 |
| Use CLAUDE.md for conventions | Teammates read it automatically — no need to repeat in every prompt |
| Require plan approval for refactors | Prevents teammates from making broad changes without review |
| Tell lead to wait before implementing | Lead sometimes starts doing work instead of delegating |

---

## What Agent Teams Cannot Do (Current Limitations)

- No session resumption with in-process teammates (`/resume` doesn't restore them)
- No nested teams (teammates can't spawn their own teams)
- One team per lead session at a time
- Split panes don't work in VS Code integrated terminal or Windows Terminal
- Task status can sometimes lag — nudge the lead if a task looks stuck

---

*Powered by Claude Code Agent Teams (experimental) | Selenium 4 | Java 17 | TestNG*
