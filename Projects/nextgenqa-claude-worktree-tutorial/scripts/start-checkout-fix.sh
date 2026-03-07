#!/bin/bash
# =====================================================
# start-checkout-fix.sh — Open Claude Code Worktree
# NextGenQA | Claude Code Git Worktree Tutorial
# =====================================================
#
# DEMO STEP — Terminal 2 (Bug Fix Work)
#
# This script starts Claude Code in an isolated
# git worktree to fix the failing CheckoutPageTest.
#
# What it does:
#   1. Creates a new git branch: fix/checkout-tests
#   2. Creates a worktree at: .claude/worktrees/fix/checkout-tests/
#   3. Launches Claude Code scoped to that worktree
#
# Usage:
#   chmod +x scripts/start-checkout-fix.sh
#   ./scripts/start-checkout-fix.sh
#
# After running this, tell Claude:
#   "There is a failing test in CheckoutPageTest.java.
#    Investigate what is wrong, explain the bug clearly
#    (as if to a beginner), and fix it."
# =====================================================

set -e

PROJECT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
cd "$PROJECT_DIR"

echo ""
echo "╔═══════════════════════════════════════════════╗"
echo "║  🐛 Starting Worktree: fix/checkout-tests     ║"
echo "╚═══════════════════════════════════════════════╝"
echo ""
echo "What will happen:"
echo "  ✦ A new branch 'fix/checkout-tests' will be created"
echo "  ✦ Worktree created at: .claude/worktrees/fix/checkout-tests/"
echo "  ✦ Claude Code launches in this isolated environment"
echo "  ✦ The feature/login-tests worktree is NOT affected"
echo ""
echo "Starting Claude Code in worktree mode..."
echo ""

# The magic command — Claude Code native worktree support
claude --worktree fix/checkout-tests
