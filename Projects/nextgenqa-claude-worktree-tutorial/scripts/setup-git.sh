#!/bin/bash
# =====================================================
# setup-git.sh — Initialize Git Repository
# NextGenQA | Claude Code Git Worktree Tutorial
# =====================================================
#
# Run this ONCE before the tutorial demo.
# This sets up git and creates the initial commit
# that is REQUIRED for git worktrees to work.
#
# Usage:
#   chmod +x scripts/setup-git.sh
#   ./scripts/setup-git.sh
# =====================================================

set -e  # Exit on any error

PROJECT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
cd "$PROJECT_DIR"

echo ""
echo "╔═══════════════════════════════════════════╗"
echo "║   NextGenQA — Git Setup Script            ║"
echo "╚═══════════════════════════════════════════╝"
echo ""

# Step 1: Check if git is installed
if ! command -v git &> /dev/null; then
    echo "❌ Git is not installed. Please install Git first."
    echo "   https://git-scm.com/downloads"
    exit 1
fi
echo "✅ Git is installed: $(git --version)"

# Step 2: Check if already initialized
if [ -d ".git" ]; then
    echo "✅ Git repository already initialized."
else
    echo "📁 Initializing git repository..."
    git init
    echo "✅ Git repository initialized."
fi

# Step 3: Set up .gitignore if not present
if [ ! -f ".gitignore" ]; then
    echo "⚠️  No .gitignore found. Run from project root."
    exit 1
fi

# Step 4: Stage all files
echo ""
echo "📦 Staging all project files..."
git add .

# Step 5: Create initial commit (REQUIRED for worktrees!)
echo "💾 Creating initial commit..."
git commit -m "feat: initial project setup - NextGenQA Claude Worktree Tutorial

- Maven project with Selenium 4 + TestNG 7
- Page Object Model: BasePage, LoginPage, HomePage, CheckoutPage
- Test stubs: LoginPageTest (incomplete), CheckoutPageTest (has bug)
- Claude Code agent config for qa-automation-engineer
- Tutorial README with step-by-step worktree demo guide"

echo ""
echo "╔═══════════════════════════════════════════╗"
echo "║   ✅ Git setup complete!                  ║"
echo "╚═══════════════════════════════════════════╝"
echo ""
echo "You can now run the worktree scripts:"
echo "  ./scripts/start-login-feature.sh"
echo "  ./scripts/start-checkout-fix.sh"
echo ""
