# Claude Code Git Worktrees for QA Engineers
### Be10X AI Series — Next Gen QA

> **Run parallel AI automation tasks without merge conflicts.**
> This project demonstrates Claude Code's native Git Worktree feature
> using a real Selenium Java test automation project.

**Watch the full tutorial:**
[youtube.com/@Next.Gen.QA.Official](https://www.youtube.com/@Next.Gen.QA.Official)
| Part of the [Be10X AI Series](https://github.com/nextgenqaofficial/Be10XSeries)

---

## 📺 What This Tutorial Covers

You'll learn how to use **Claude Code's `--worktree` flag** to:
- Run two Claude Code sessions **simultaneously** on the same project
- Have one session write **new Selenium tests** (feature work)
- Have another session **fix a failing test** (bug fix) — at the same time
- Without the two sessions ever touching each other's files

---

## 🧰 Prerequisites

Before starting, make sure you have:

| Requirement | Version | Check |
|---|---|---|
| Java JDK | 11 or higher | `java -version` |
| Maven | 3.6+ | `mvn -version` |
| Google Chrome | Latest | — |
| Git | 2.x | `git --version` |
| Claude Code | 2.1.50+ | `claude update` → then `claude --version` |

> **Claude Code is required.** It's the CLI tool from Anthropic.
> Install it from: https://code.claude.ai/

---

## 📁 Project Structure

```
nextgenqa-claude-worktree-tutorial/
│
├── pom.xml                          ← Maven build file (Selenium + TestNG)
├── testng.xml                       ← TestNG suite configuration
├── README.md                        ← You are here
├── .gitignore
│
├── .claude/
│   └── agents/
│       └── qa-agent.md              ← Custom Claude Code agent (auto worktree isolation)
│
├── src/
│   ├── main/java/com/nextgenqa/pages/
│   │   ├── BasePage.java            ← Base class: reusable Selenium helpers
│   │   ├── LoginPage.java           ← Login page interactions
│   │   ├── HomePage.java            ← Secure area page interactions
│   │   └── CheckoutPage.java        ← Checkboxes page interactions
│   │
│   └── test/java/com/nextgenqa/tests/
│       ├── BaseTest.java            ← Browser setup/teardown (DO NOT modify)
│       ├── LoginPageTest.java       ← 🚧 INCOMPLETE — for worktree demo (Feature branch)
│       └── CheckoutPageTest.java    ← 🐛 HAS A BUG — for worktree demo (Bugfix branch)
│
└── scripts/
    ├── setup-git.sh                 ← Step 1: Run once to initialize git
    ├── start-login-feature.sh       ← Step 2a: Start Terminal 1 (feature work)
    ├── start-checkout-fix.sh        ← Step 2b: Start Terminal 2 (bug fix)
    ├── check-worktrees.sh           ← Optional: see all active worktrees
    └── cleanup-worktrees.sh         ← Step 3: Clean up after demo
```

---

## 🎬 Tutorial Walkthrough

### Step 0 — Open the Project in VS Code

```bash
# Clone or open the project
code nextgenqa-claude-worktree-tutorial
```

---

### Step 1 — Initialize Git (One-time Setup)

Git Worktrees **require** your project to have at least one git commit.
Run the setup script once:

```bash
chmod +x scripts/setup-git.sh
./scripts/setup-git.sh
```

This creates an initial commit and you're ready to go.

> **Windows users:** Use Git Bash or PowerShell and run:
> ```powershell
> git init
> git add .
> git commit -m "initial commit"
> ```

---

### Step 2 — The Problem (Before Worktrees)

Open `LoginPageTest.java` — notice the TODO methods.
Open `CheckoutPageTest.java` — notice the failing test with `BUG IS HERE` comment.

**Without worktrees**, you'd have to work on these one at a time:
1. Fix the bug → commit → switch to feature branch → write new tests
2. Any conflict between branches means stashing, switching, unstashing

That's the problem worktrees solve.

---

### Step 3 — Start Two Parallel Worktrees

Open **two terminal windows** in VS Code (`Ctrl+Shift+\`` twice).

**Terminal 1** — Feature work (new login tests):
```bash
./scripts/start-login-feature.sh
```
Or directly:
```bash
claude --worktree feature/login-tests
```

**Terminal 2** — Bug fix (checkout tests):
```bash
./scripts/start-checkout-fix.sh
```
Or directly:
```bash
claude --worktree fix/checkout-tests
```

Both Claude Code sessions are now running simultaneously on **separate branches**
in **separate working directories** — they cannot interfere with each other!

---

### Step 4 — Give Instructions to Each Claude Agent

**In Terminal 1** (feature/login-tests), type:
```
Complete all TODO test methods in LoginPageTest.java using the existing
LoginPage and HomePage page objects. Follow the same pattern as
testSuccessfulLogin(). The test site is https://the-internet.herokuapp.com/login
Valid credentials: tomsmith / SuperSecretPassword!
```

**In Terminal 2** (fix/checkout-tests), type:
```
There is a failing test in CheckoutPageTest.java. Read the file carefully,
identify what is wrong, explain the bug as if to a beginner QA engineer,
and fix it with a clear comment explaining the correction.
```

Watch both agents work in parallel — **zero conflicts!** 🎉

---

### Step 5 — Check Active Worktrees (Optional)

While both agents are working, open a **third terminal** and run:

```bash
./scripts/check-worktrees.sh
# Or manually:
git worktree list
```

You'll see all three working directories:
```
/path/to/project                  [main]
/path/to/project/.claude/worktrees/feature/login-tests   [feature/login-tests]
/path/to/project/.claude/worktrees/fix/checkout-tests    [fix/checkout-tests]
```

---

### Step 6 — Review & Merge Each Branch

When an agent finishes, review its changes:

```bash
# Review the login feature branch
git diff main..feature/login-tests

# Review the bugfix branch
git diff main..fix/checkout-tests
```

Merge independently when satisfied:
```bash
git merge feature/login-tests
git merge fix/checkout-tests
```

---

### Step 7 — Clean Up

After the demo:
```bash
./scripts/cleanup-worktrees.sh
```

Or manually:
```bash
git worktree remove .claude/worktrees/feature/login-tests
git worktree remove .claude/worktrees/fix/checkout-tests
git worktree prune
```

---

## 💡 Key Concepts Summary

| Concept | Explanation |
|---|---|
| **Git Worktree** | A second (or third) working folder linked to the same git repo, each on its own branch |
| **`claude --worktree <name>`** | Starts Claude Code in a new isolated worktree — one command does everything |
| **No conflicts** | Each agent edits files in its own folder; they never touch each other |
| **Resume sessions** | Run `claude --worktree feature/login-tests` again to resume exactly where you left off |
| **Cleanup** | `git worktree remove <path>` or `git worktree prune` |

---

## 🔧 Custom Agent Configuration

This project includes a pre-configured Claude agent at `.claude/agents/qa-agent.md`.

The key line is:
```yaml
isolation: worktree
```

This means any time you trigger this agent, Claude **automatically** creates a new
worktree for it — no `--worktree` flag needed. This is the professional setup
for larger projects where you want consistent worktree isolation by default.

---

## 🚀 Running Tests Manually

```bash
# Run all tests
mvn test

# Run only login tests
mvn test -Dtest=LoginPageTest

# Run only checkout tests
mvn test -Dtest=CheckoutPageTest
```

---

## 🌐 Test Application

This tutorial uses the **Herokuapp practice site** — it's free, always available,
and requires no account setup:

- **Login page**: https://the-internet.herokuapp.com/login
  - Username: `tomsmith` | Password: `SuperSecretPassword!`
- **Checkboxes page**: https://the-internet.herokuapp.com/checkboxes

---

## 📚 Further Reading

- [Claude Code Official Docs — Worktrees](https://code.claude.ai/docs/en/common-workflows)
- [Git Worktree Official Docs](https://git-scm.com/docs/git-worktree)
- [Next Gen QA YouTube Channel](https://www.youtube.com/@Next.Gen.QA.Official)

---

*Made with ❤️ by Next Gen QA | Subscribe for more AI-powered QA automation tutorials!*
