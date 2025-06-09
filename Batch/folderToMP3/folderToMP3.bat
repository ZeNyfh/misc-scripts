@echo off
for %%I in (*.*) do (
    if /I not "%%~xI"==".bat" (
        ffmpeg -i "%%I" "%%~nI.mp3"
    )
)