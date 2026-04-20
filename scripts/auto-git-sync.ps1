param(
    [string]$Branch = "main"
)

$ErrorActionPreference = "Stop"

$repoRoot = Split-Path -Parent $PSScriptRoot
Set-Location $repoRoot

$statusLines = git status --porcelain

if (-not $statusLines) {
    Write-Host "No changes to commit."
    exit 0
}

git add -A

$timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
$message = "Auto update $timestamp"

git commit -m $message
git push origin $Branch

Write-Host "Pushed: $message"
