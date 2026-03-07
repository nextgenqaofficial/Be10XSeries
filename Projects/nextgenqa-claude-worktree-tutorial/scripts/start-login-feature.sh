#!/bin/bash
# =====================================================
# start-login-feature.sh — Open Claude Code Worktree
# NextGenQA | Claude Code Git Worktree Tutorial
# =====================================================
#
# DEMO STEP — Terminal 1 (Feature Work)
#
# This script starts Claude Code in an isolated
# git worktree for the Login feature tests.
#
# What it does:
#   1. Creates a new git branch: feature/login-tests
#   2. Creates a worktree at: .claude/worktrees/feature/login-tests/
#   3. Launches Claude Code scoped to that worktree
#
# Usage:
#   chmod +x scripts/start-login-feature.sh
#   ./scripts/start-login-feature.sh
#
# After running this, tell Claude:
#   "Complete all TODO test methods in LoginPageTest.java
#    using the LoginPage and HomePage page objects.
#    The test site is https://the-internet.herokuapp.com/login
#    Valid credentials: tomsmith / SuperSecretPassword!"
# =====================================================

set -e

PROJECT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
cd "$PROJECT_DIR"

echo ""
echo "╔═══════════════════════════════════════════════╗"
echo "║  🌿 Starting Worktree: feature/login-tests    ║"
echo "╚═══════════════════════════════════════════════╝"
echo ""
echo "What will happen:"
echo "  ✦ A new branch 'feature/login-tests' will be created"
echo "  ✦ Worktree created at: .claude/worktrees/feature/login-tests/"
echo "  ✦ Claude Code launches in this isolated environment"
echo "  ✦ Your main branch is UNTOUCHED"
echo ""
echo "Starting Claude Code in worktree mode..."
echo ""

# The magic command — Claude Code native worktree support
claude --worktree feature/login-tests
