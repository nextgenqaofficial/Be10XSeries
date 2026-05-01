# Be10X AI Series - Next Gen QA

Practical demo projects for QA engineers and developers learning AI-assisted workflows. This repository is the companion codebase for the Next Gen QA Be10X series and now contains multiple self-contained tutorials under `Projects/`.

## What Is In This Repo

Each project focuses on a specific AI workflow for QA automation, agent collaboration, or eval-driven development. Most projects are Java + Maven based, with Selenium or Spring Boot depending on the tutorial.

## Project Index

| Folder | Focus | Stack | Start Here |
|---|---|---|---|
| `Projects/AgentTeams` | Claude Code agent teams working in parallel on a Selenium project | Java 17, Maven, Selenium, TestNG | `README.md`, `STEPS.md`, `prompts/` |
| `Projects/ClaudeCodeChannels` | Running Claude Code through Telegram channels while working on Selenium tests | Java 11, Maven, Selenium, TestNG | `README.md`, `HOW_TO_RUN.md` |
| `Projects/Evals` | Evaluating an AI QA assistant with Promptfoo and a Spring Boot web app | Java 17, Maven, Spring Boot, Promptfoo | `steps.md`, `promptfooconfig.yaml` |
| `Projects/nextgenqa-boris-workflow` | Boris-style multi-session Claude workflow using separate repo copies | Java 11, Maven, Selenium, TestNG | `instructions.md`, `steps.md`, `CLAUDE.md` |
| `Projects/nextgenqa-claude-worktree-tutorial` | Claude Code git worktrees for parallel QA automation tasks | Java 11, Maven, Selenium, TestNG | `README.md`, `steps.md`, `scripts/` |

## Repository Structure

```text
Be10XSeries/
|-- README.md
`-- Projects/
    |-- AgentTeams/
    |-- ClaudeCodeChannels/
    |-- Evals/
    |-- nextgenqa-boris-workflow/
    `-- nextgenqa-claude-worktree-tutorial/
```

## How To Use It

1. Clone the repository.
2. Open the project folder for the tutorial you want to follow.
3. Read that folder's `README.md`, `steps.md`, or `instructions.md` first.
4. Run Maven commands from inside that project folder, not from the repo root.

Example:

```bash
git clone https://github.com/nextgenqaofficial/Be10XSeries.git
cd Be10XSeries/Projects/AgentTeams
mvn test
```

## Common Requirements

Most projects expect:

- Java 11+ or 17+ depending on the folder
- Maven 3.6+ or newer
- Google Chrome for Selenium-based demos
- Claude Code for the Claude-focused workflows

The `Projects/Evals` demo also needs Node.js for Promptfoo and an OpenAI-compatible API key configured in its application properties.

## Notes

- These projects are intentionally independent rather than part of one parent build.
- Some tutorial folders include presentation assets such as `.pptx`, prompt files, or walkthrough notes used for the related videos.
- If you are unsure where to begin, start with `Projects/nextgenqa-claude-worktree-tutorial` or `Projects/AgentTeams`.

## Connect

- YouTube: [Next Gen QA](https://www.youtube.com/@Next.Gen.QA.Official)
- GitHub: [nextgenqaofficial](https://github.com/nextgenqaofficial)
