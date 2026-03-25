# How To Run — Claude Code Channels (Telegram)
## NextGenQA Be10X AI Series — Video #5

---

## STEP 0 — Prerequisites

**Java & Maven:**
```bash
java -version   # must be Java 11 or later
mvn -version    # must be Maven 3.6 or later
```

**Claude Code:**
```bash
# Check version — must be 2.1.80 or later
claude --version

# Update if needed
npm update -g @anthropic-ai/claude-code

# Check Bun is installed
bun --version
```

**Install Bun if missing:**
```bash
curl -fsSL https://bun.sh/install | bash
```

> You must be logged in via claude.ai (Pro plan). API key auth does NOT work with Channels.

---

## STEP 1 — Build & Verify the Selenium Project

```bash
# Navigate to the project
cd "ClaudeCodeChannels"

# Download dependencies and compile
mvn clean compile

# Run all tests to confirm everything works
mvn test
```

Expected: 3 login tests pass against `https://the-internet.herokuapp.com/login`

---

## STEP 2 — Create Your Telegram Bot

1. Open Telegram — search for **@BotFather**
2. Send `/newbot`
3. Display name: `NextGenQA Claude Bot`
4. Username: `nextgenqa_claude_bot` (must end in `bot`, must be unique)
5. BotFather returns a token — **copy and save it**

Token looks like:
```
7123456789:AAF_example_token_here
```
BotToken : 8495888859:AAED9bhuzUV13tAdyyf6FaIbyXj-5CHCVrM
---

## STEP 3 — Install & Configure the Telegram Plugin

Open Claude Code in this project folder:
```bash
cd "ClaudeCodeChannels"
claude
```

Inside Claude Code terminal:
```
/plugin marketplace add anthropics/claude-plugins-official
/plugin install telegram@claude-plugins-official
/telegram:configure YOUR_BOT_TOKEN_HERE
```

---

## STEP 4 — Start Claude Code with Telegram

```bash
cd "ClaudeCodeChannels"
claude --channels plugin:telegram@claude-plugins-official
```

---

## STEP 5 — Pair Your Telegram Account

1. Find your bot on Telegram by username
2. Send any message (e.g. `hello`)
3. Bot replies with a 6-character pairing code e.g. `A7X2MN`

In Claude Code terminal:
```
/telegram:access pair A7X2MN
/telegram:access policy allowlist
```

The allowlist ensures only YOUR account can send commands to Claude.

---

## STEP 6 — Send Test Commands From Your Phone

Now send these from Telegram — Claude runs them against the project on your machine:

```
What files are in this project?
```
```
Run mvn test and tell me if anything is failing
```
```
How many tests do we have and what are they testing?
```
```
What was the last git commit?
```
```
The login test is failing — can you look at it and suggest a fix?
```

---

## STEP 7 — Keep Session Alive (Recommended)

Use tmux so the session persists when you close the terminal:

```bash
# Start named session
tmux new-session -s claude-qa

# Inside tmux
cd "ClaudeCodeChannels"
claude --channels plugin:telegram@claude-plugins-official

# Detach (keep running): Ctrl+B, then D

# Reattach later
tmux attach -session claude-qa
```

---

## Troubleshooting

| Problem                            | Fix                                                        |
|------------------------------------|------------------------------------------------------------|
| `claude --version` below 2.1.80    | `npm update -g @anthropic-ai/claude-code`                  |
| "Channels not available" error     | Log in via claude.ai — API key auth won't work             |
| Bot doesn't reply in Telegram      | Make sure `--channels` flag is on the startup command      |
| Pairing code doesn't work          | Codes are one-time use — DM the bot again for a new one    |
| Messages arrive but Claude ignores | Run `/telegram:access list` — check your ID is allowlisted |
| Session drops after 30 minutes     | Use tmux to keep session persistent                        |
| Maven build fails                  | Run `java -version` — must be Java 11+                     |

---

*NextGenQA Be10X AI Series — Claude Code Channels*
