#!/bin/bash

echo "This script will COPY the files to their destination."
echo "" # newline ig

input() {
    read -p "$1: " input
    echo "$input"
}

appimageFile=$(input "Enter the path to the AppImage/sh file")
imageFile=$(input "Enter the path to the image file")
appName=$(input "Enter the application name")

targetDir="$HOME/AppImages/$appName"
desktopFilePath="$HOME/.local/share/applications/${appName}.desktop"
appimageBasename=$(basename "$appimageFile")
imageBasename=$(basename "$imageFile")

mkdir -p "$targetDir"
cp "$appimageFile" "$targetDir"
cp "$imageFile" "$targetDir"

cat > "$desktopFilePath" << EOF
[Desktop Entry]
Name=$appName
Exec=$targetDir/$appimageBasename
Icon=$targetDir/$imageBasename
Type=Application
EOF

chmod +x "$targetDir/$appimageBasename"

echo "Application added to menu successfully."
echo "Desktop entry created at $desktopFilePath."

update-desktop-database "$HOME/.local/share/applications"