# Step-by-Step: Claude Code Git Worktrees Tutorial

---

## PHASE 1 — One-Time Setup

### Step 1: Initialize the Git Repository

```bash
git init
git add .
git commit -m "initial commit"
```

- Creates a git repo
- Makes the initial commit (required for worktrees to work)

---

## PHASE 2 — Open Two Terminals in Parallel

### Step 2: Terminal 1 — Start the Feature Worktree

```bash
claude --worktree feature/login-tests
```

- Creates branch: `worktree-feature/login-tests`
- Creates folder: `.claude/worktrees/feature/login-tests/`
- Launches Claude Code scoped to that folder

### Step 3: Terminal 2 — Start the Bugfix Worktree

```bash
claude --worktree fix/checkout-tests
```

- Creates branch: `worktree-fix/checkout-tests`
- Creates folder: `.claude/worktrees/fix/checkout-tests/`
- Launches Claude Code scoped to that folder



## PHASE 3 — Give Claude the Tasks

### Step 4: In Terminal 1, type this prompt to Claude:
```
Complete all TODO test methods in LoginPageTest.java
using the LoginPage and HomePage page objects.
The test site is https://the-internet.herokuapp.com/login
Valid credentials: tomsmith / SuperSecretPassword!


### Step 5: In Terminal 2, type this prompt to Claude:

There is a failing test in CheckoutPageTest.java.
Investigate what is wrong, explain the bug clearly, and fix it.




## PHASE 4 — Verify Claude's Work

### Step 6: In Terminal 1, run the login tests

mvn test -Dtest=LoginPageTest

Expected: `Tests run: 3, Failures: 0`

### Step 7: In Terminal 2, run the checkout tests

mvn test -Dtest=CheckoutPageTest

Expected: `Tests run: 3, Failures: 0`



## PHASE 5 — Commit Changes in Each Worktree

Claude makes changes but does NOT auto-commit. You must commit from each worktree before merging.

### Step 8: In Terminal 1, commit the login feature changes

```bash
cd .claude/worktrees/feature/login-tests
git add src/test/java/com/nextgenqa/tests/LoginPageTest.java
git commit -m "feat: implement TODO login tests"
```

### Step 9: In Terminal 2, commit the checkout fix changes

```bash
cd .claude/worktrees/fix/checkout-tests
git add src/test/java/com/nextgenqa/tests/CheckoutPageTest.java
git commit -m "fix: correct first checkbox assertion to assertFalse"
```

---

## PHASE 6 — Merge Back to Main

### Step 10: In the main project folder, merge both branches

```bash
git checkout master
git merge worktree-feature/login-tests
git merge worktree-fix/checkout-tests
```

No conflicts — each Claude session worked in isolation.



## PHASE 7 — Cleanup

### Step 11: Remove the worktree folders

```bash
git worktree remove .claude/worktrees/feature/login-tests
git worktree remove .claude/worktrees/fix/checkout-tests
git worktree prune
```

### Step 12: Verify the final git log

git log --oneline
git worktree list




## Quick Reference

| Terminal | Command | Branch | Task |
|---|---|---|---|
| 1 | `claude --worktree feature/login-tests` | worktree-feature/login-tests | Implement 2 TODO test methods |
| 2 | `claude --worktree fix/checkout-tests` | worktree-fix/checkout-tests | Fix wrong assertion on line 68 |

### The Bug in CheckoutPageTest.java (line 68):
```java
// WRONG — first checkbox is UNCHECKED by default
Assert.assertTrue(checkoutPage.isFirstCheckboxSelected(), ...)

// CORRECT fix:
Assert.assertFalse(checkoutPage.isFirstCheckboxSelected(), ...)
```

