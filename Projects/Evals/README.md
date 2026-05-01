# AI QA Assistant Evals Demo

Small Spring Boot demo used to evaluate an AI QA assistant with Promptfoo. The app accepts a feature description, generates QA coverage with an LLM, and then shows eval evidence in the same UI.

## What This Project Covers

- A local QA assistant web app
- Prompt-driven analysis for feature risk review
- Promptfoo-based evaluation against expected QA risks
- A failure-reveal style demo showing why polished output is not enough

## Stack

- Java 17
- Maven
- Spring Boot
- Promptfoo

## Quick Start

1. Configure local values in `src/main/resources/application.properties`
2. Start the app:

```bash
mvn spring-boot:run
```

3. Open `http://localhost:8080`
4. Run evals from a second terminal:

```powershell
.\run-promptfoo.cmd eval --output evaluations/latest-results.json --output evaluations/latest-report.html
```

## Important Files

- `steps.md` - demo flow and presenter notes
- `promptfooconfig.yaml` - Promptfoo test cases and assertions
- `src/main/resources/prompts/qa-assistant.txt` - assistant prompt
- `src/main/resources/application.properties` - local app configuration
- `evals/hidden-risks.md` - hidden reference risks for the demo

## Notes

- `application.properties` is ignored and should stay local only.
- You need an OpenAI-compatible API key configured locally before running the app.
- Promptfoo writes outputs into the `evaluations/` folder, which is also ignored.
