#!/bin/bash
# =====================================================
# cleanup-worktrees.sh — Remove Worktrees After Demo
# NextGenQA | Claude Code Git Worktree Tutorial
# =====================================================
#
# Run this after the tutorial demo to clean up
# the worktrees created during the session.
#
# Usage:
#   ./scripts/cleanup-worktrees.sh
# =====================================================

PROJECT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
cd "$PROJECT_DIR"

echo ""
echo "╔═══════════════════════════════════════════╗"
echo "║  🧹 Cleaning Up Worktrees                 ║"
echo "╚═══════════════════════════════════════════╝"
echo ""

# Remove feature/login-tests worktree
if [ -d ".claude/worktrees/feature/login-tests" ]; then
    echo "Removing feature/login-tests worktree..."
    git worktree remove .claude/worktrees/feature/login-tests --force 2>/dev/null || true
    echo "✅ feature/login-tests removed"
else
    echo "ℹ️  feature/login-tests worktree not found (already removed)"
fi

# Remove fix/checkout-tests worktree
if [ -d ".claude/worktrees/fix/checkout-tests" ]; then
    echo "Removing fix/checkout-tests worktree..."
    git worktree remove .claude/worktrees/fix/checkout-tests --force 2>/dev/null || true
    echo "✅ fix/checkout-tests removed"
else
    echo "ℹ️  fix/checkout-tests worktree not found (already removed)"
fi

# Prune any stale references
echo ""
echo "Pruning stale worktree references..."
git worktree prune
echo "✅ Pruned"

echo ""
echo "Active worktrees now:"
git worktree list
echo ""
