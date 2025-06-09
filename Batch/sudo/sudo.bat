@echo off

:: if user does not have admin, run the program as admin.
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo Requesting UAC...
    powershell -Command "Start-Process '%~f0' -Verb runAs"
    exit /b
)

echo Running as administrator: %*
%*
