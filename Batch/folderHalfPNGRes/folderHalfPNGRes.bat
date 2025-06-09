@echo off
mkdir "smaller" 2>nul
for %%I in (*.png) do (
    ffmpeg -i "%%I" -vf "scale=iw/2:ih/2" "smaller\%%~nI.png"
)
