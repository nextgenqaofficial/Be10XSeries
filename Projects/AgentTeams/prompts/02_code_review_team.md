# Prompt: Parallel Code Review Team

Use this prompt to spawn a team that reviews the existing Selenium tests
from three different angles simultaneously.

---

## The Prompt

```
Create an agent team to review the Selenium test code in this project.
Spawn 3 reviewer teammates, each with a different focus:

- Teammate 1 (Quality Reviewer): Review all files in src/test/java/ for
  code quality. Check: are locators resilient? Are waits correct?
  Are assertions meaningful? Are test methods independent?
  Score each file out of 10 and list specific improvements.

- Teammate 2 (Coverage Reviewer): Analyse LoginTest.java and identify
  missing test scenarios. What edge cases aren't covered?
  What negative scenarios are missing? What UI validations are skipped?
  Produce a prioritised list of gaps with suggested test case descriptions.

- Teammate 3 (Standards Reviewer): Check all code against Selenium best
  practices. Look for: hardcoded waits, magic strings, missing teardown,
  poor naming conventions, missing base class usage.
  Flag every violation with the file name and line number.

After all reviews are complete, compile a single consolidated report
with the top 5 most important improvements across all reviewers' findings.
Save it to output/consolidated-review.md
```

---

## What to expect

- 3 reviewers analyse the same codebase from different angles simultaneously
- Findings from all 3 are merged into one prioritised report
- Much more thorough than a single sequential review
- Total time: ~2-3 minutes

## Output

```
output/consolidated-review.md
```
