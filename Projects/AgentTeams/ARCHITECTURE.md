# Architecture — Claude Code Agent Teams

> **YouTube Demo:** NextGenQA | Claude Code Agent Teams + Selenium Java

---

## High-Level Architecture

```
┌─────────────────────────────────────────────────────────┐
│                  YOU (in the terminal)                  │
│         paste a prompt from prompts/ folder             │
└───────────────────────┬─────────────────────────────────┘
                        │
                        ▼
┌─────────────────────────────────────────────────────────┐
│              Claude Code  (Team Lead session)           │
│                                                         │
│  - Reads CLAUDE.md for project context                  │
│  - Analyses the prompt                                  │
│  - Creates shared task list                             │
│  - Spawns teammate sessions                             │
│  - Synthesises results at the end                       │
└────────────────────────┬────────────────────────────────┘
                         │  spawns
          ┌──────────────┼──────────────┐
          ▼              ▼              ▼
   ┌─────────────┐ ┌─────────────┐ ┌─────────────┐
   │ Teammate 1  │ │ Teammate 2  │ │ Teammate 3  │
   │             │ │             │ │             │
   │ Own context │ │ Own context │ │ Own context │
   │ window      │ │ window      │ │ window      │
   │             │ │             │ │             │
   │ Claims task │ │ Claims task │ │ Claims task │
   │ from list   │ │ from list   │ │ from list   │
   └──────┬──────┘ └──────┬──────┘ └──────┬──────┘
          │               │               │
          └───────────────┼───────────────┘
                          │  share
                          ▼
              ┌───────────────────────┐
              │    Shared Task List   │
              │  ~/.claude/tasks/...  │
              └───────────────────────┘
                          │
                          ▼
              ┌───────────────────────┐
              │       Mailbox         │
              │  Direct peer-to-peer  │
              │  messaging between    │
              │  any two agents       │
              └───────────────────────┘
```

---

## Components

| Component | What it is | Where it lives |
|---|---|---|
| **Team Lead** | Your Claude Code session. Creates and manages the team. | Your terminal |
| **Teammates** | Independent Claude Code instances spawned by the lead. | In-process or tmux panes |
| **Task List** | Shared work items that teammates claim and complete. | `~/.claude/tasks/{team-name}/` |
| **Mailbox** | Messaging system for direct agent-to-agent communication. | Managed by Claude Code |
| **Team Config** | Team membership, agent IDs, agent types. | `~/.claude/teams/{team-name}/config.json` |
| **CLAUDE.md** | Project context automatically loaded by every agent on spawn. | `AgentTeams/CLAUDE.md` |

---

## How This Differs From Subagents

```
SUBAGENTS                          AGENT TEAMS
─────────────────────────          ─────────────────────────
Main Agent                         Lead
    │                                  │
    ├─► Subagent 1 ─► result           ├─► Teammate 1 ◄──► Teammate 2
    ├─► Subagent 2 ─► result           │        │               │
    └─► Subagent 3 ─► result           │        └───────────────┘
                                       │          direct messaging
    (one-way: result flows up)         │        shared task list
                                       └─► Teammate 3
```

- Subagents only report back to the caller — no peer communication
- Agent teammates message each other directly — enables debate, challenge, coordination
- Agent teammates self-claim tasks from a shared list — lead doesn't micromanage

---

## What Happens When You Paste a Prompt

```
1. You paste prompt
        │
2. Lead reads CLAUDE.md (project context)
        │
3. Lead creates task list entries
        │
4. Lead spawns N teammate sessions
        │
5. Each teammate:
   a. Loads CLAUDE.md
   b. Receives spawn prompt from lead
   c. Claims first available task
   d. Does the work (reads/writes files)
   e. Messages other teammates if needed
   f. Marks task complete
   g. Claims next task or goes idle
        │
6. Lead receives idle notifications
        │
7. Lead synthesises all outputs
        │
8. Lead reports to you
```

---

## Context Isolation

Each teammate has its own context window. They do NOT share conversation history.

What they DO share:
- `CLAUDE.md` — loaded automatically on spawn (project context)
- Task list — read/write access
- Mailbox — send/receive messages
- The filesystem — they all work on the same codebase files

What they do NOT share:
- Lead's conversation history
- Other teammates' conversation history
- Each other's intermediate reasoning

---

## File Conflict Prevention

When multiple teammates could edit the same file:

1. **Task design**: structure prompts so each teammate owns different files
   - Teammate 1 → pages/CheckboxPage.java
   - Teammate 2 → pages/DropdownPage.java  ← different files = no conflict

2. **Task locking**: file locking prevents two teammates claiming the same task simultaneously

3. **Communication**: teammates can message each other to coordinate if overlap is discovered

---

## Token Cost Model

Each teammate is a full Claude session consuming tokens independently.

```
1 session:     1x tokens
3 teammates:   ~4x tokens  (lead + 3 teammates)
5 teammates:   ~6x tokens  (lead + 5 teammates)
```

Worth it for: research, parallel feature work, competing hypothesis debugging
Not worth it for: sequential tasks, single-file edits, simple Q&A

---

## Display Modes

```
IN-PROCESS MODE (default)           SPLIT PANE MODE (tmux/iTerm2)
────────────────────────           ────────────────────────────
┌─────────────────────┐            ┌──────────┬──────────┐
│                     │            │  Lead    │  TM 1    │
│  Lead terminal      │            │          │          │
│                     │            ├──────────┼──────────┤
│  Shift+Down →       │            │  TM 2    │  TM 3    │
│  cycles to TM 1     │            │          │          │
│                     │            └──────────┴──────────┘
└─────────────────────┘
Works in any terminal              Requires tmux or iTerm2
```

---

*Built on Claude Code Agent Teams (experimental) | Selenium 4 | Java 17 | TestNG*
