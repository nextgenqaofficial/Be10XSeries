# Prompt: Parallel Debugging Team (Competing Hypotheses)

Use this prompt when a test is failing and you're not sure why.
Multiple teammates investigate different root causes simultaneously,
then challenge each other's findings.

---

## The Prompt (adapt the failing test name)

```
One of our Selenium tests is failing intermittently. The test is
LoginTest#tc01_successfulLogin — it passes 7 out of 10 times but
fails 3 times with "Element not interactable" on the login button.

Create an agent team with 3 teammates to investigate competing hypotheses.
Have them challenge each other's theories like a scientific debate:

- Teammate 1 (Timing Hypothesis): Investigate whether the failure is a
  timing/wait issue. Review BasePage.java and LoginPage.java for wait
  strategy problems. Look for races between page load and element interaction.
  Propose a fix.

- Teammate 2 (Locator Hypothesis): Investigate whether the CSS selector
  for the login button is fragile. Check if the button could be obscured,
  in a different state, or if the locator matches multiple elements.
  Propose a fix.

- Teammate 3 (Environment Hypothesis): Investigate whether the failure
  is environment-related — browser version, screen resolution, or
  JavaScript rendering timing. Review BaseTest.java Chrome options.
  Propose a fix.

Have teammates share and challenge each other's findings.
After debate, agree on the most likely root cause and implement the fix.
```

---

## Why competing hypotheses work better than single investigation

A single agent tends to find one plausible explanation and stop.
With 3 agents actively trying to disprove each other, the theory that
survives the debate is far more likely to be the real root cause.

## What to expect

- 3 teammates investigate in parallel (~2 minutes)
- They message each other to challenge findings
- The surviving theory gets implemented as a fix
