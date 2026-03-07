# Be10X AI Series — Video #02
## The Boris Cherny Workflow: 3 Claude Code Sessions in Parallel
### A QA Automation Project with Selenium Java + TestNG

---

## PART 1 — Running the Project

### Prerequisites

| Tool | Check Command | Minimum Version |
|------|--------------|-----------------|
| Java | `java -version` | 11+ |
| Maven | `mvn -version` | 3.8+ |
| Git | `git --version` | 2.30+ |
| Chrome | installed | Latest |
| Claude Code | `claude --version` | Latest |
| Claude Subscription | Pro or above | Opus 4.5 access |

### Clone and Build

```bash
git clone https://github.com/nextgenqaofficial/Be10XSeries.git
cd Be10XSeries/nextgenqa-boris-workflow
mvn clean compile
```

Expected output: `BUILD SUCCESS`

### Run Tests

```bash
# Run all tests
mvn clean test

# Run a specific test class
mvn test -Dtest=CheckoutPageTest

# Run a specific test method
mvn test -Dtest=CheckoutPageTest#test_checkout_page_title
```

### Project Structure

```
nextgenqa-boris-workflow/
├── CLAUDE.md                              <- Project rules for Claude
├── pom.xml                                <- Maven config (Selenium + TestNG)
├── testng.xml                             <- Test suite definition
├── .gitignore
├── .claude/
│   ├── settings.json                      <- Hooks configuration
│   └── commands/
│       ├── run-qa-suite.md                <- /run-qa-suite slash command
│       └── commit-push-pr.md              <- /commit-push-pr slash command
├── src/main/java/com/nextgenqa/pages/
│   ├── BasePage.java                      <- Base page with shared wait logic
│   ├── LoginPage.java                     <- Login page object
│   └── CheckoutPage.java                  <- Checkout page object
└── src/test/java/com/nextgenqa/tests/
    ├── BaseTest.java                      <- Shared driver setup/teardown
    ├── LoginPageTest.java                 <- INCOMPLETE — Tab 1 writes this
    └── CheckoutPageTest.java              <- HAS A BUG — Tab 2 fixes this
```

### What's Intentionally Broken

1. **LoginPageTest.java** — Only has TODOs, no actual test methods. Tab 1 (Feature Writer) will complete it.
2. **CheckoutPageTest.java** — `test_checkout_page_title` asserts title is `"The Internet - Login Page"` but the actual title is `"The Internet"`. Tab 2 (Bug Fixer) will find and fix this.

---

## PART 2 — YouTube Demo Steps (15 Minutes)

### Overview: 3-Tab Boris Workflow

| Tab | Role | Task |
|-----|------|------|
| 1 | Feature Writer | Complete LoginPageTest.java from TODOs |
| 2 | Bug Fixer | Find and fix the wrong assertion in CheckoutPageTest.java |
| 3 | CLAUDE.md + Rules | Review code, update CLAUDE.md with project conventions |

---

### SEGMENT 1 — Hook (0:00 - 0:30)

**What to show:** End result first — 3 terminals running, tests passing, hook log updating.

**Script:**
> "I'm going to run 3 AI agents in parallel on a real Selenium project. Each one gets its own task, its own copy of the code. By the end, all tests pass — and I barely typed any code. This is the Boris Cherny workflow."

---

### SEGMENT 2 — The Why (0:30 - 1:30)

**What to show:** You at the terminal, explaining the concept.

**Script:**
> "Boris Cherny is a staff engineer at Anthropic. He runs 5 Claude Code sessions at the same time — one per terminal tab. Each session works on a different task. He just reviews and approves. We're doing the same thing today with 3 sessions on a Selenium Java QA project."

**Show on screen:**
- A simple graphic: 3 terminal boxes, each labeled with its role

---

### SEGMENT 3 — Project Setup + Git Clones (1:30 - 3:00)

**What to do on camera:**

```bash
# Show the project
cd nextgenqa-boris-workflow
ls -la

# Build it
mvn clean compile
# Show BUILD SUCCESS

# Create isolated copies for Tab 2 and Tab 3
git clone . ../nextgenqa-tab2
git clone . ../nextgenqa-tab3
```

**Explain:**
> "Each tab gets its own full copy of the repo. Not a branch, not a worktree — a full clone. If one session edits a file, the others don't see it. Zero conflicts."

**Verify in each tab:**
```bash
# Tab 1: already in nextgenqa-boris-workflow
pwd

# Tab 2: switch to this tab, then:
cd ../nextgenqa-tab2
pwd

# Tab 3:
cd ../nextgenqa-tab3
pwd
```

---

### SEGMENT 4 — Plan Mode Deep Dive (3:00 - 6:00)

**Tab 1 — Feature Writer**

```bash
claude
```

Activate Plan Mode:
```
Press: Shift+Tab twice
# You should see [PLAN MODE] indicator
```

Paste this prompt:
```
Write complete Selenium Java TestNG tests for LoginPageTest.java.
The file already has TODOs marking what to implement.
Target: https://the-internet.herokuapp.com/login
Credentials: tomsmith / SuperSecretPassword!

Requirements:
- test_successful_login: valid login, assert flash message contains "You logged into a secure area!"
- test_invalid_username: wrong username, assert error message appears
- test_invalid_password: wrong password, assert error message appears
- test_empty_credentials: empty fields, assert validation error
- Use the existing LoginPage page object
- Use explicit waits, NEVER Thread.sleep
- Add @Test(description) annotations

Show me the plan before writing any code.
```

**Explain while Claude thinks:**
> "Plan Mode means Claude shows you the architecture BEFORE writing code. You review, correct, then approve. This is how you stay in control."

**When Claude returns the plan:**
- Read it on camera
- Add one correction: "Use BaseTest for setup instead of creating a new driver in this class"
- Approve: Switch to auto-accept with `Shift+Tab` once

> "Now Tab 1 is working. I don't touch it again until it's done."

---

### SEGMENT 5 — Launch Tab 2 + Tab 3 (6:00 - 8:00)

**Tab 2 — Bug Fixer**

Switch to Tab 2 (`Cmd+2` or `Ctrl+2`):

```bash
claude
```

```
Shift+Tab twice -> Plan Mode

Prompt:
There's a bug in CheckoutPageTest.java — one of the tests is failing.
Find the root cause, fix the assertion, and verify by running:
mvn test -Dtest=CheckoutPageTest

Show me what's wrong before fixing it.
```

**Tab 3 — CLAUDE.md + Slash Commands**

Switch to Tab 3 (`Cmd+3` or `Ctrl+3`):

```bash
claude
```

```
Shift+Tab twice -> Plan Mode

Prompt:
Review all test files and page objects in this project.
Then update CLAUDE.md with any additional rules or conventions
you observe. Focus on:
- Patterns that should be standardized
- Anti-patterns to warn about
- Any missing best practices

Also review the slash commands in .claude/commands/ and suggest
improvements if needed.

Show me your findings before making changes.
```

**After launching all 3:**
> "Three agents, three tasks, all running right now. My job is just to approve and review."

---

### SEGMENT 6 — Notification Workflow (8:00 - 10:00)

**What to show:** Real notification appearing, tab-switching, quick approvals.

**When a notification appears:**
1. Show the notification pop-up on screen
2. Switch to that tab: `Cmd+[number]`
3. Read what Claude is asking
4. Give a short approval: "Looks good, proceed" or "Yes, fix it that way"
5. Switch back

**Do this 2-3 times across tabs.**

**Explain:**
> "This is the actual workflow. I'm not writing code. I'm reviewing plans, approving changes, and context-switching between sessions. That's it."

**Quick approval phrases to use on camera:**
- "Looks good, proceed."
- "Yes, use that approach."
- "Fix it the way you described."

---

### SEGMENT 7 — Slash Command: /run-qa-suite (10:00 - 12:00)

**Back to Tab 1** (after Claude finishes writing LoginPageTest.java):

First, show the slash command file:
```bash
cat .claude/commands/run-qa-suite.md
```

**Explain:**
> "This is a reusable prompt saved as a markdown file. It tells Claude: run all tests, parse the output, and give me a summary. Anyone who clones this repo gets this command automatically."

**Run it:**
```
/run-qa-suite
```

**Show Claude:**
1. Running `mvn clean test`
2. Parsing the output
3. Reporting results

**Expected outcome:** All tests pass (Claude wrote them correctly + fixed the bug in Tab 2).

> "I didn't run a single test manually. Claude wrote the code, ran the tests, and told me the result. One command."

---

### SEGMENT 8 — Hooks: The Short Clip (12:00 - 14:00)

**Show the hooks file:**
```bash
cat .claude/settings.json
```

**Walk through each part on camera:**

```json
{
  "hooks": {
    "PostToolUse": [           // <- "fires after Claude uses any tool"
      {
        "matcher": "Write|Edit",  // <- "only when Claude writes or edits a file"
        "hooks": [
          {
            "type": "command",
            "command": "echo '[hook] File written at ...' >> .claude/hook.log"
                                  // <- "runs this shell command every time"
          }
        ]
      }
    ]
  }
}
```

**Demo it live:**

Open a second terminal pane (split screen) and run:
```bash
tail -f .claude/hook.log
```

Then in the Claude session, ask Claude to make a small edit to any file.

**Show:** Claude edits the file → hook fires → log updates in real-time on the right side.

> "Every time Claude touches a file, this hook fires. You can put anything here — a linter, a formatter, a Slack notification. Claude doesn't even know it's happening. It's guardrails for your AI."

**For the YouTube Short, capture this 45-second sequence:**
1. Show the settings.json (3 sec)
2. Split screen: Claude on left, `tail -f hook.log` on right (5 sec)
3. Ask Claude to edit a file (5 sec)
4. Hook fires, log updates live (5 sec)
5. "You can replace this with any command" (5 sec)
6. "Full video linked above" (3 sec)

---

### SEGMENT 9 — Wrap-up + CTA (14:00 - 15:00)

**Show:**
- Quick flash of the updated CLAUDE.md from Tab 3
- The final project structure in the terminal

**Script:**
> "Three sessions. Three tasks. All running in parallel. The tests are written, the bug is fixed, the project rules are documented. I typed maybe 10 lines total — and those were prompts, not code."

> "I used 3 tabs today. Boris uses 5. Scale it to whatever your project needs."

> "Next video: I'm going deep on CLAUDE.md — how to make Claude learn from its own mistakes across sessions. Subscribe so you don't miss it."

**Engagement CTA:**
> "Drop a comment: which of these 3 tasks would YOU give to Claude first — writing tests, fixing bugs, or documentation? I'll pin the best answer."

---

## PART 3 — YouTube Short Script (Hook Demo)

**Title:** "This hook runs EVERY time AI edits your code"

**Duration:** 45-60 seconds

| Timestamp | What's on screen | Voiceover |
|-----------|-----------------|-----------|
| 0-3s | Show .claude/settings.json | "Every time Claude edits a file..." |
| 3-8s | Highlight the matcher + command | "...this shell command fires automatically" |
| 8-15s | Split screen: Claude left, tail -f right | "Watch — I'll ask Claude to edit a test file" |
| 15-22s | Claude makes the edit | (silence, let it happen) |
| 22-27s | Hook fires, log updates live | "Boom. Logged automatically." |
| 27-35s | Type a new command replacing echo | "Swap this for a linter, formatter, or Slack webhook" |
| 35-42s | Show the new hook firing | "Guardrails for your AI assistant" |
| 42-45s | End card | "Full setup — link in comments" |

---

## PART 4 — Troubleshooting

### Tests fail with ChromeDriver error
Chrome and ChromeDriver versions must match. WebDriverManager handles this automatically via the pom.xml dependency. If it still fails:
```bash
# Check Chrome version
google-chrome --version   # Linux
# or check in Chrome -> Settings -> About Chrome

# WebDriverManager will auto-download the matching driver
```

### Plan Mode not activating
`Shift+Tab` must be pressed while the text input is empty. Clear any typed text first, then press `Shift+Tab` twice.

### Notifications not appearing
- **macOS:** System Settings -> Notifications -> iTerm2 -> Allow Notifications ON
- **Windows:** Settings -> System -> Notifications -> Windows Terminal -> ON

### Claude keeps asking for permission
Run `/permissions` inside the session and pre-allow common commands:
```
Bash(mvn *)
Bash(git *)
```

### Sessions editing each other's files
Verify each tab is in its own directory. Run `pwd` in each tab. They should be:
```
Tab 1: .../nextgenqa-boris-workflow
Tab 2: .../nextgenqa-tab2
Tab 3: .../nextgenqa-tab3
```

---

## PART 5 — What Each File Does

| File | Purpose | Who Creates It |
|------|---------|---------------|
| `pom.xml` | Maven config with Selenium + TestNG dependencies | Pre-built |
| `testng.xml` | Defines which test classes to run in the suite | Pre-built |
| `CLAUDE.md` | Project rules Claude loads at startup every session | Pre-built, Tab 3 updates |
| `.claude/settings.json` | Hook that fires after every file edit | Pre-built |
| `.claude/commands/run-qa-suite.md` | Slash command: run tests + summarize results | Pre-built |
| `.claude/commands/commit-push-pr.md` | Slash command: commit + push + PR | Pre-built |
| `BasePage.java` | Shared page object with wait helpers | Pre-built |
| `LoginPage.java` | Page object for the login page | Pre-built |
| `CheckoutPage.java` | Page object for the checkout page | Pre-built |
| `BaseTest.java` | Shared driver setup and teardown | Pre-built |
| `LoginPageTest.java` | INCOMPLETE — has TODOs, no test methods | Tab 1 completes |
| `CheckoutPageTest.java` | HAS A BUG — wrong title assertion | Tab 2 fixes |
