# Be10X AI Series — Video #02
## The Boris Cherny Workflow: 3 Claude Sessions in Parallel
### Windows + Cmder + Selenium Java

---

## What You Need Before Recording

| Tool | Check |
|------|-------|
| Cmder (Full) | cmder.net |
| Java 11+ | `java -version` |
| Maven | `mvn -version` |
| Claude Code | `claude --version` |
| Claude Pro subscription | Opus access |

---

## One-Time Setup (Do This Before Recording)

### 1. Open Cmder → Create 3 Tabs

```
Ctrl+T → select "Git bash" → repeat 3 times
```

Right-click each tab → Rename:
```
Tab 1  →  "1 - Feature Writer"
Tab 2  →  "2 - Bug Fixer"
Tab 3  →  "3 - CLAUDE.md"
```

### 2. Navigate Each Tab to Its Own Project Copy

In **Tab 1** (the main project):
```bash
cd /d/NextGenQA-Projects/Be10X\ Series/Projects/nextgenqa-boris-workflow
mvn clean compile
```

Clone it for the other tabs:
```bash
git clone . ../nextgenqa-tab2
git clone . ../nextgenqa-tab3
```

In **Tab 2**:
```bash
cd /d/NextGenQA-Projects/Be10X\ Series/Projects/nextgenqa-tab2
```

In **Tab 3**:
```bash
cd /d/NextGenQA-Projects/Be10X\ Series/Projects/nextgenqa-tab3
```

### 3. Enable Taskbar Flash in Cmder

```
Right-click title bar → Settings → Features → Task bar
→ Enable: Flash window when Console has new output
```

This notifies you when a tab needs your attention — no need to stare at all 3.

---

## On Camera: The Demo (15 Minutes)

### STEP 1 — Show the Project (1 min)

Open the project folder. Show:
- `LoginPageTest.java` — has TODOs, no test methods yet
- `CheckoutPageTest.java` — has a wrong assertion (deliberate bug)
- `CLAUDE.md` — project rules file

> *"Tab 1 writes the tests. Tab 2 fixes the bug. Tab 3 updates the project rules. All at the same time."*

---

### STEP 2 — Plan Mode on Tab 1 (3 min)

```bash
claude
```

Press `Shift+Tab` twice → you see **[PLAN MODE]**

Paste this prompt:claude

```
Complete LoginPageTest.java. The file has TODOs marking what to build.
Target: https://the-internet.herokuapp.com/login
Credentials: tomsmith / SuperSecretPassword!

Implement:
- test_successful_login → assert flash contains "You logged into a secure area!"
- test_invalid_username → assert error message appears
- test_invalid_password → assert error message appears
- test_empty_credentials → assert validation error appears

Use LoginPage page object. Extend BaseTest. No Thread.sleep.
Show me the plan first.
```

Claude returns a plan. Review it on camera. Approve:
```
Looks good, proceed.
```

Press `Shift+Tab` once → **auto-accept ON** → Claude starts writing.

> *"I don't touch Tab 1 again. It's working. I move on."*

---

### STEP 3 — Launch Tab 2 + Tab 3 (2 min)

**Tab 2** (`Ctrl+2`):
```bash
claude
```
`Shift+Tab` twice → Plan Mode, paste:
```
Find and fix the bug in CheckoutPageTest.java.
One test has a wrong assertion — it is failing.
Show me the root cause before fixing it.
Then verify with: mvn test -Dtest=CheckoutPageTest
```

**Tab 3** (`Ctrl+3`):
```bash
claude
```
`Shift+Tab` twice → Plan Mode, paste:
```
Review all test files and page objects in this project.
CLAUDE.md already has starter rules — do not remove them.
Add new rules based on patterns you observe in the actual code.
Focus on: wait strategy, assertion patterns, naming, driver setup.
Show me your findings before making any changes.
```

> Note: `CLAUDE.md` already has hand-written starter rules (wait strategy, naming, assertions). Tab 3 reads the real code and adds to those rules based on what it actually observes — like how `BaseTest` is used, how locators are structured, etc. Claude does not rewrite from scratch; it appends.

> *"Three agents. Three tasks. All running right now."*

---

### STEP 4 — Manage the Sessions (2 min)

Watch for the Cmder taskbar to flash.

When it does:
1. `Ctrl+[number]` → go to that tab
2. Read what Claude is asking
3. Reply in one line — `"Looks good, proceed."` or `"Yes, fix it that way."`
4. Back to what you were doing

> *"This is the whole job now. Not writing code. Approving."*

---

### STEP 5 — Slash Command: /run-qa-suite (2 min)

Once Tab 1 finishes writing tests:

```
/run-qa-suite
```

Claude runs `mvn clean test`, reads the output, fixes any failures, reruns.
You get a clean summary:

```
SUITE RESULTS
Total: 6 | Passed: 6 | Failed: 0 | Skipped: 0
Build: SUCCESS
```

> *"I didn't run a single test manually. One command."*


### STEP 6 — Wrap Up (1 min)

Switch to Tab 3. Show the updated CLAUDE.md.

> *"Tab 1 wrote the tests. Tab 2 fixed the bug. Tab 3 documented the rules. I used 3 tabs today — Boris uses 5. Scale it to whatever your project needs."*

CTA:
> *"Which of these 3 tasks would YOU give to Claude first? Comment below. Next video: CLAUDE.md deep dive — how to make Claude self-correct. Subscribe so you don't miss it."*

---

## Troubleshooting (Quick Reference)

| Problem | Fix |
|---------|-----|
| Ctrl+1/2/3 not switching tabs | Settings → Keys & Macro → bind Ctrl+1/2/3 to Switch console |
| Taskbar not flashing | Settings → Features → Task bar → Flash on new output |
| `tail -f` not working | Switch to Git bash shell, not cmd.exe |
| `mvn` not found | Add `C:\apache-maven\bin` to Windows PATH, restart Cmder |
| Plan Mode not activating | Clear input field first, then Shift+Tab twice |
| Sessions conflicting | Run `pwd` in each tab — must be in different folders |

---

### STEP 7 — Collect Results into Tab 1 (1 min)

Once all 3 sessions are done, copy the changed files back into the main project (Tab 1).

Tab 2 and Tab 3 are throwaway workspaces — you pick what's good and bring it in:

```bash
# Run these in Tab 1

# Bring in the bug fix from Tab 2
cp ../nextgenqa-tab2/src/test/java/com/nextgenqa/tests/CheckoutPageTest.java \
   src/test/java/com/nextgenqa/tests/

# Bring in the updated CLAUDE.md from Tab 3
cp ../nextgenqa-tab3/CLAUDE.md .
```

Review the copied files quickly, then commit everything from Tab 1:

```bash
/commit-push-pr
```

Claude writes the commit message, commits, and pushes. Done.

> *"No git merge. No conflicts. Tab 1 is the real repo — I pick what I want from the other sessions and pull it in manually. The separate checkouts are just workspaces."*

---

*Be10X AI Series — Video 2 of 10*
*Next: CLAUDE.md Deep Dive — Building a Self-Correcting QA Project*
