# Step-by-Step Guide — Claude Code Agent Teams + Selenium

> **YouTube Demo:** NextGenQA | Claude Code Agent Teams

---

## Prerequisites

| Tool | Version | Check |
|---|---|---|
| Node.js | 18+ | `node --version` |
| Claude Code CLI | 2.1.32+ | `claude --version` |
| Java JDK | 17+ | `java -version` |
| Maven | 3.8+ | `mvn -version` |
| Google Chrome | Latest | Chrome → Help → About |

---

## Step 1 — Install Claude Code

```bash
npm install -g @anthropic-ai/claude-code
claude --version   # should show 2.1.32 or later
```

---

## Step 2 — Get a Claude.ai Account

Claude Code authenticates through your **Claude.ai account** — no separate API key needed.

You need one of these plans (check [claude.com/pricing](https://claude.com/pricing)):
- **Claude Pro** — individual subscription
- **Claude Max** — higher usage limits
- **Claude for Teams / Enterprise** — for organisations

> If you already use claude.ai in your browser, you're already set. Just make sure you're on Pro or Max.

---

## Step 3 — Open the Project in Claude Code

```bash
cd D:\NextGenQA\NextGenQAProjects\AgentTeams
claude
```

The first time you run `claude`, it opens a **browser window** asking you to
log in with your Claude.ai account. Do that once — credentials are saved
securely in your system keychain. You won't need to log in again.

Claude Code starts in your terminal and automatically reads `CLAUDE.md`,
giving every agent context about this Selenium project.

---

## Step 5 — Verify Agent Teams Are Enabled

Agent teams are already enabled via `.claude/settings.json` in this project:

```json
{
  "env": {
    "CLAUDE_CODE_EXPERIMENTAL_AGENT_TEAMS": "1"
  }
}
```

You can confirm by asking Claude Code:
```
Are agent teams enabled in this project?
```

Or check your Claude Code version supports it:
```bash
claude --version   # needs 2.1.32+
```

---

## Step 6 — Run Your First Agent Team

Open `prompts/01_test_coverage_team.md`, copy the prompt text, paste it into
Claude Code and press Enter.

**What happens:**

```
You paste the prompt
       │
       ▼
Claude Code (Lead) analyses the task
       │
       ├──► Spawns Teammate 1 — writes CheckboxPage + CheckboxTest
       ├──► Spawns Teammate 2 — writes DropdownPage + DropdownTest
       └──► Spawns Teammate 3 — writes DynamicLoadingPage + DynamicLoadingTest
                    │
                    │  (all 3 work in parallel)
                    │
                    ▼
       Lead reviews all outputs and verifies consistency
```

**Watching teammates in the terminal:**

- All teammates appear in your terminal (in-process mode by default)
- Press `Shift+Down` to cycle through teammates and see what each is doing
- Type directly to message a specific teammate
- Press `Ctrl+T` to toggle the shared task list

---

## Step 7 — Try Each Demo Scenario

Work through the prompts in order for the best YouTube narrative:

### Demo 1 — Parallel Test Writing
```
prompts/01_test_coverage_team.md
```
Shows: 3 teammates writing independent tests simultaneously. No waiting.

### Demo 2 — Parallel Code Review
```
prompts/02_code_review_team.md
```
Shows: 3 reviewers covering quality, coverage gaps, and standards — all at once.

### Demo 3 — Competing Hypotheses Debug
```
prompts/03_debug_team.md
```
Shows: teammates actively arguing with each other to find the real root cause.
This is the most visually impressive demo.

### Demo 4 — Parallel Refactoring
```
prompts/04_refactor_team.md
```
Shows: teammates owning separate layers — no file conflicts, pure speedup.

---

## Step 8 — Interact With Teammates Directly

During any agent team run you can talk to individual teammates:

1. Press `Shift+Down` to cycle to a teammate
2. Type your message and press Enter
3. The teammate responds without going through the lead

Example — redirect a teammate mid-task:
```
Stop what you're doing. Focus only on the @FindBy locator strategy.
Use CSS selectors instead of XPath everywhere.
```

---

## Step 9 — Use Split Pane Mode (Optional)

To see all teammates side-by-side instead of cycling through them:

**Requires tmux (macOS/Linux):**
```bash
# Install tmux
brew install tmux          # macOS
sudo apt install tmux      # Ubuntu/Debian

# Start Claude Code inside a tmux session
tmux new-session -s agents
claude
```

Or set it permanently in `.claude/settings.json`:
```json
{
  "env": { "CLAUDE_CODE_EXPERIMENTAL_AGENT_TEAMS": "1" },
  "teammateMode": "tmux"
}
```

---

## Step 10 — Run the Selenium Tests After the Agent Team Finishes

After any test-writing demo, verify the generated tests actually pass:

```bash
mvn test
```

Expected output after Demo 1 (test coverage team):
```
LoginTest:         8 tests PASS
CheckboxTest:      3 tests PASS
DropdownTest:      3 tests PASS
DynamicLoadingTest: 2 tests PASS

Tests run: 16, Failures: 0, Errors: 0
```

---

## Step 11 — Clean Up the Team

When done, tell the lead:
```
Clean up the team
```

This removes shared team resources. Always clean up through the lead —
don't ask a teammate to do it.

---

## Troubleshooting

### "Agent teams not available"
Your Claude Code version is too old. Update:
```bash
npm update -g @anthropic-ai/claude-code
claude --version   # confirm 2.1.32+
```

### Teammates not appearing
Press `Shift+Down` — they may already be running but not visible.

### "Not logged in" or auth error
Run `claude` and follow the browser login prompt with your Claude.ai account.
To re-authenticate: type `/logout` inside Claude Code, then restart it.

### Tests fail after agent team writes them
Tell the lead: `Run mvn test and fix any compilation or test failures.`
The lead will delegate fixes to the appropriate teammate.

### Too many permission prompts from teammates
Pre-approve common operations before spawning. Tell Claude Code:
```
Allow teammates to read and write files in this project without prompting.
```

---

## Project Structure

```
AgentTeams/
├── .claude/
│   └── settings.json        ← Agent teams enabled here
├── CLAUDE.md                ← Project context loaded by every agent
├── prompts/                 ← Ready-made agent team prompts
│   ├── 01_test_coverage_team.md
│   ├── 02_code_review_team.md
│   ├── 03_debug_team.md
│   └── 04_refactor_team.md
├── pom.xml                  ← Maven: Selenium 4, TestNG, WebDriverManager
├── testng.xml               ← TestNG suite config
└── src/test/java/com/nextgenqa/
    ├── pages/
    │   ├── BasePage.java
    │   ├── LoginPage.java
    │   └── DashboardPage.java
    └── tests/
        ├── BaseTest.java
        └── LoginTest.java   ← 8 tests already written
```

---

*Made for YouTube — NextGenQA | Claude Code Agent Teams + Selenium Java*
