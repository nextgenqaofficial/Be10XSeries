# Agent Roles — Lead vs Teammates

> **YouTube Demo:** NextGenQA | Claude Code Agent Teams + Selenium Java

---

## The Two Roles

| Role | Who | Responsibilities |
|---|---|---|
| **Team Lead** | Your main Claude Code session | Creates team, assigns tasks, synthesises results, cleans up |
| **Teammate** | Claude Code session spawned by lead | Claims tasks, does the work, communicates with peers, reports completion |

---

## Team Lead

The session you're typing into is always the lead. You cannot change this.

**What the lead does:**
- Reads your prompt and decides if an agent team makes sense
- Creates the shared task list with all work items
- Spawns teammate sessions with specific spawn prompts
- Monitors teammate progress
- Approves/rejects teammate plans (when plan approval is required)
- Reassigns tasks if a teammate gets stuck
- Synthesises all outputs into a final answer for you
- Runs cleanup when you ask

**What the lead does NOT do:**
- Write the actual test code (it delegates that to teammates)
- Claim tasks from the task list (it manages the list, doesn't work from it)

---

## Teammates

Each teammate is a fully independent Claude Code session.

**What teammates do:**
- Read `CLAUDE.md` on spawn for project context
- Receive a spawn prompt from the lead describing their specific job
- Claim the first available unblocked task from the shared task list
- Do the work (read files, write code, run commands)
- Message other teammates directly if they need to coordinate
- Mark tasks complete
- Claim the next available task automatically
- Go idle and notify the lead when no tasks are left

**Key property:** teammates have their own context window.
They don't know what the lead said to you, or what other teammates are thinking.
They only know:
1. What's in `CLAUDE.md`
2. What their spawn prompt said
3. Messages other agents send them
4. What they've done themselves

---

## How Teammates Communicate

### Teammate → Lead
Used for: completion notifications, plan approval requests, asking for help
```
"I've finished writing CheckboxPage.java and CheckboxTest.java.
 Both files follow the BasePage pattern. Marking tasks complete."
```

### Lead → Teammate
Used for: task assignment, plan approval/rejection, redirection
```
"Your plan looks good but don't modify BasePage.java.
 Create a new CheckboxBasePage that extends it instead. Resubmit."
```

### Teammate → Teammate
Used for: sharing findings, challenging theories, coordinating on shared files
```
Teammate 1 → Teammate 2:
"I looked at the timing issue. The wait is 10 seconds — plenty of time.
 I think the problem might actually be a locator issue.
 Can you check if the button selector matches when the page first loads?"
```

Press `Shift+Down` in in-process mode to cycle through sessions and type directly.

---

## Task Life Cycle

```
Created (by lead)
       │
       ▼
   Pending  ←─── dependencies not met yet
       │
       ▼  (teammate claims it)
  In Progress
       │
       ├──► Completed  (teammate marks done → unblocks dependent tasks)
       │
       └──► Stuck      (tell lead: "nudge Teammate 2 on task X")
```

**File locking** prevents two teammates claiming the same task simultaneously.

**Dependencies**: you can define tasks that can't start until other tasks complete:
```
"Write the test class only after the page object is complete."
```

---

## How the Selenium Demos Map to Roles

### Demo 1 — Test Coverage Team

```
Lead:          Creates tasks for 3 pages, spawns 3 teammates
Teammate 1:    Owns /checkboxes — writes CheckboxPage + CheckboxTest
Teammate 2:    Owns /dropdown   — writes DropdownPage + DropdownTest
Teammate 3:    Owns /dynamic_loading — writes DynamicLoadingPage + DynamicLoadingTest
Lead (end):    Reviews all 3 for consistency, verifies mvn test passes
```

### Demo 2 — Code Review Team

```
Lead:          Assigns review lenses, spawns 3 reviewers
Teammate 1:    Reviews for code quality (locators, waits, assertions)
Teammate 2:    Reviews for coverage gaps (missing scenarios)
Teammate 3:    Reviews for standards violations (naming, structure)
Lead (end):    Merges findings into consolidated report
```

### Demo 3 — Competing Hypotheses Debug

```
Lead:          Describes the failing test, spawns 3 investigators
Teammate 1:    Investigates timing hypothesis
Teammate 2:    Investigates locator hypothesis
Teammate 3:    Investigates environment hypothesis
               ↕  teammates message each other to challenge findings
Lead (end):    Declares winning hypothesis, implements fix
```

### Demo 4 — Parallel Refactoring

```
Lead:          Splits refactoring by layer, spawns 3 teammates
Teammate 1:    Refactors page objects only (no test files)
Teammate 2:    Refactors test data only (no page objects)
Teammate 3:    Refactors infrastructure only (config, BaseTest)
               (no file overlap by design)
Lead (end):    Verifies mvn test still passes
```

---

## Controlling Teammates Mid-Run

You can redirect any teammate without going through the lead:

1. Press `Shift+Down` to cycle to the teammate you want
2. Type your instruction and press Enter

Examples:
```
Stop. The CSS selector you used won't work in Firefox. Use an ID locator instead.
```
```
Before you finish, also add a test for the 'Remember me' checkbox if it exists.
```
```
Your approach is correct but please add a @DataProvider for the invalid credential test cases.
```

---

## When NOT to Use Agent Teams

Agent teams add coordination overhead. Don't use them when:

- Tasks are sequential (each depends on the previous result)
- All tasks touch the same file
- The work is simple enough for one agent to do quickly
- You just need a quick answer or explanation

Use a single session (no team) for those cases.
Use agent teams when you have genuinely parallel, independent work.

---

*Powered by Claude Code Agent Teams (experimental) | Selenium 4 | Java 17 | TestNG*
