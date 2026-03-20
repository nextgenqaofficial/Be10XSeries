# Theory — What Is Claude Code Agent Teams?

> **YouTube Demo:** NextGenQA | Claude Code Agent Teams + Selenium Java

---

## Start Here — The Problem It Solves

When you ask a single AI to do a big, complex job, it does everything sequentially:

```
Single AI session:
  → Analyse the codebase
  → Plan the tests
  → Write the tests
  → Review the tests
  → Fix issues
  → Write the report
  [each step waits for the previous one to finish]
```

This works fine for small tasks. But it breaks down when:

- **The job is too big** for one context window
- **The work is parallelisable** — multiple independent things could happen at the same time
- **Specialisation matters** — a dedicated reviewer catches more than a reviewer who also wrote the code
- **Competing perspectives help** — one agent investigating a bug tends to anchor on the first plausible theory and stop

Agent Teams solves all four problems.

---

## The Core Idea

Instead of one AI doing everything, you have a **team of AIs** — each focused on a specific job, working in parallel, and communicating with each other.

```
Traditional approach:          Agent Teams approach:
────────────────────           ─────────────────────
One AI                         One Lead AI
Does everything                  │
Sequentially                     ├──► Teammate 1 (specialist)
One context window               ├──► Teammate 2 (specialist)
                                 └──► Teammate 3 (specialist)
                                 All working at the same time
                                 Each with their own context window
                                 Communicating peer-to-peer
```

This mirrors how **human teams** work. You wouldn't ask one person to design, build, test, review, and document a feature all by themselves. You split the work across specialists.

---

## What Is Claude Code?

Before understanding Agent Teams, you need to know what Claude Code is.

**Claude Code** is Anthropic's official CLI tool that runs Claude (the AI model) directly in your terminal. You authenticate with your **Claude.ai account** (Pro, Max, Teams or Enterprise) — no separate API key needed for individual use. It can:

- Read and write files in your project
- Run shell commands (`mvn test`, `git commit`, etc.)
- Search your codebase
- Browse the web
- Call external APIs

Think of it as Claude with hands — it doesn't just answer questions, it actually does the work in your codebase.

```
You type:   "Write a test for the login page"
Claude Code: reads LoginPage.java → writes LoginTest.java → runs mvn test → reports results
```

---

## What Are Agent Teams, Exactly?

**Claude Code Agent Teams** is a feature (experimental, as of early 2026) built into Claude Code CLI that lets you spawn multiple Claude Code instances — called **teammates** — that coordinate as a team.

Each teammate is a full, independent Claude Code session:
- Its own context window (its own "memory" for the conversation)
- Its own terminal view
- Full access to the filesystem and tools

What makes them a *team* rather than just isolated sessions:
- **Shared task list** — a live list of work items all agents can see, claim, and update
- **Mailbox** — direct messaging between any two agents (lead ↔ teammate, teammate ↔ teammate)
- **CLAUDE.md** — a shared project context file every agent reads automatically on startup

---

## The Three Key Concepts

### 1. Team Lead

The Claude Code session you're typing into. It:
- Receives your goal
- Breaks it into tasks
- Spawns teammate sessions
- Monitors progress
- Synthesises the final result

The lead is a coordinator, not a worker. It delegates.

### 2. Teammates

Independent Claude Code sessions spawned by the lead. They:
- Each receive a specific job via their spawn prompt
- Read `CLAUDE.md` for project context
- Claim tasks from the shared list
- Do the actual work (write code, review files, run tests)
- Message each other directly when they need to coordinate

### 3. Shared Task List + Mailbox

The glue that makes it a team:

- **Task List** — like a JIRA board. Lead creates tasks. Teammates claim and complete them. Dependencies are enforced (task B can't start until task A is done). File-locked to prevent race conditions.
- **Mailbox** — like Slack DMs. Any agent can send a message to any other agent directly. This enables peer-to-peer coordination without everything going through the lead.

---

## Why Multiple Context Windows Matter

A context window is the AI's working memory — everything it knows about the current conversation.

**Problem with one context window:**
- Has a size limit
- The more context, the slower and more expensive the responses
- One agent doing everything accumulates a huge context fast

**Advantage of multiple context windows:**
- Each teammate keeps only the context relevant to their job
- A code reviewer's context is just the code + review criteria — not the entire test planning conversation
- Teammates can work longer without running out of context

```
Single context window:              Multiple context windows:
──────────────────────              ─────────────────────────
[planning conversation]             Teammate 1: [only checkbox work]
[code generation conversation]      Teammate 2: [only dropdown work]
[review conversation]               Teammate 3: [only dynamic loading work]
[debug conversation]
[report conversation]               Each is lean and focused
[getting very large...]
```

---

## Agent Teams vs Subagents — What's the Difference?

Claude Code also has **subagents** — a simpler form of delegation. It's important to understand the difference:

### Subagents
- The main agent spawns a subagent, gives it a task, waits for the result, continues
- One-way communication: subagent reports back to main agent only
- Subagents never talk to each other
- Good for: isolated tasks where only the result matters

```
Main Agent
    │
    ├──► Subagent 1 ──── result ────► Main Agent
    │
    ├──► Subagent 2 ──── result ────► Main Agent
    │
    └──► Subagent 3 ──── result ────► Main Agent
```

### Agent Teams
- Teammates are fully independent sessions (not spawned and forgotten)
- Two-way, peer-to-peer communication
- Teammates can message each other directly — no lead required
- Shared task list for self-coordination
- Good for: complex work where teammates need to share findings and challenge each other

```
Lead
    │
    ├──► Teammate 1 ◄──────────► Teammate 2
    │         │                       │
    │         └──────────────────► Teammate 3
    │                    (direct messaging)
    └──► synthesises final result
```

**The practical difference:** if you're debugging a hard problem, subagents each investigate their theory and report back. Agent teams let them *argue with each other* — Teammate 2 can message Teammate 1 to say "your theory doesn't hold because I checked X and found Y." This debate surfaces the real root cause much faster.

---

## Why This Is a Big Deal for Test Automation

Test automation has always had parallelism built in — you run tests in parallel, you have teams of QA engineers working independently. Agent Teams brings that same parallelism to the *creation* of tests.

| Traditional test automation | With Agent Teams |
|---|---|
| One engineer writes tests for one page at a time | 3 agents write tests for 3 pages simultaneously |
| Sequential code review | 3 reviewers cover quality, coverage, and standards in parallel |
| One engineer investigates a flaky test | 3 agents investigate competing theories simultaneously |
| Refactoring done layer by layer | 3 agents refactor 3 independent layers at once |

The output quality also improves because of specialisation. An agent whose entire job is code review — and knows it won't be writing code — applies a more critical lens than one agent doing everything.

---

## The Human Team Analogy

The closest real-world analogy is a **Scrum sprint**:

| Scrum | Agent Teams |
|---|---|
| Sprint backlog | Shared task list |
| Scrum Master | Team Lead |
| Developers | Teammates |
| Daily standup | Mailbox messages |
| Definition of Done | TaskCompleted hook |
| Sprint Planning | Lead's delegation prompt |
| Sprint Review | Lead's synthesis at the end |

The difference: teammates work in minutes, not weeks.

---

## How CLAUDE.md Makes It Work

When you spawn a teammate, it doesn't know anything about your project. It has to be told.

You could put all project context in every spawn prompt — but that's repetitive and easy to forget.

`CLAUDE.md` solves this. Every Claude Code session (lead and teammates) reads it automatically on startup. It's the "onboarding document" for every agent.

In this project, `CLAUDE.md` tells every agent:
- How to run the tests (`mvn test`)
- Where to put new files
- What conventions to follow (extend BasePage, use WebDriverWait)
- What pages are available to test and their URLs
- What credentials to use for the test site

Result: every teammate starts with full project context without you having to repeat it.

---

## Limitations to Know About

Agent Teams is experimental (early 2026). Current limitations:

- **No session resumption** — if you close and reopen Claude Code, in-process teammates don't restore
- **No nested teams** — a teammate cannot spawn its own team
- **One team per lead** — can't run two teams simultaneously in the same session
- **Higher token cost** — each teammate is a full Claude session, tokens scale linearly
- **Split panes require tmux/iTerm2** — doesn't work in VS Code terminal or Windows Terminal

These are expected to improve as the feature matures.

---

## Summary

| Question | Answer |
|---|---|
| What is it? | A Claude Code CLI feature that coordinates multiple Claude instances as a team |
| Who makes it? | Anthropic — it's built into the Claude Code CLI |
| What do you need? | Claude Code v2.1.32+, Claude.ai account (Pro / Max / Teams) |
| How do you trigger it? | Enable in settings.json, then describe a team in plain English |
| What's the lead? | Your main Claude Code session — coordinates, doesn't do the work |
| What are teammates? | Independent Claude Code sessions — do the actual work in parallel |
| How do they coordinate? | Shared task list + direct peer-to-peer mailbox |
| What's CLAUDE.md? | Project context file auto-loaded by every agent on spawn |
| Best use case? | Parallel independent work: multi-page test writing, multi-lens code review, competing hypothesis debugging |
| Not worth using for? | Sequential tasks, single-file edits, simple questions |

---

*Powered by Claude Code Agent Teams (experimental) | Claude Code v2.1.32+ | Anthropic*
