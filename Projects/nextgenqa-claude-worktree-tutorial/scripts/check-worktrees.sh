#!/bin/bash
# =====================================================
# check-worktrees.sh — View All Active Worktrees
# NextGenQA | Claude Code Git Worktree Tutorial
# =====================================================
#
# Run this at any time to see all active worktrees.
# This is useful during the demo to confirm both
# worktrees are running in parallel.
#
# Usage:
#   ./scripts/check-worktrees.sh
# =====================================================

PROJECT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
cd "$PROJECT_DIR"

echo ""
echo "╔═══════════════════════════════════════════╗"
echo "║  📋 Active Git Worktrees                  ║"
echo "╚═══════════════════════════════════════════╝"
echo ""
git worktree list --porcelain
echo ""
echo "Tip: Each worktree is an isolated working copy"
echo "on its own branch. They share the same git history"
echo "but NEVER touch each other's files!"
echo ""
