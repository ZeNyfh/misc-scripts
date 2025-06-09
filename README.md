# misc-scripts

Miscellaneous scripts, commands, notes, and code snippets by me, **ZeNyfh**.

> 🔗 **Click a badge to jump to that section below.**

[![Bash Script](https://img.shields.io/badge/bash_script-%23121011.svg?style=for-the-badge\&logo=gnu-bash\&logoColor=white)](#bash)
[![Batch File](https://img.shields.io/badge/batch_file-%23008080.svg?style=for-the-badge)](#batch)
[![PowerShell](https://img.shields.io/badge/PowerShell-%235391FE.svg?style=for-the-badge\&logo=powershell\&logoColor=white)](#powershell)
[![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge\&logo=openjdk\&logoColor=white)](#java)
[![C++](https://img.shields.io/badge/c++-%2300599C.svg?style=for-the-badge\&logo=c%2B%2B\&logoColor=white)](#c)
[![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge\&logo=javascript\&logoColor=%23F7DF1E)](#javascript)

---

## Bash

### `fullscreen.sh`

* Requires `wmctrl` (available via `apt`).
* Takes in an app name (e.g., "Firefox") and makes it borderless windowed.

> Useful for Minecraft 1.12.2, which doesn’t play nicely with fullscreen.

### `changeJava.sh`

* Uses `update-alternatives` for switching Java versions quickly.

> Useful when running multiple Minecraft server jars.

### `hotspot.sh`

* Arguments: `interface name` `SSID` `password`

> Originally made for getting my Wii online on university Wi-Fi, also nice for on-the-go LAN gaming.

### `appHelper.sh`

* Takes user input to create a `.desktop` menu shortcut for an app or script.

> Great for integrating AppImages or Bash scripts into Linux DE menus, with thumbnails.

---

## Java

### `VideoCompressor.java`

* Uses FFmpeg (2-pass filtering) to compress a file by changing its bitrate.
* Outputs 10MB files by default, the discord limit without nitro.

> Originated from Zenvibe, my Discord music bot, from a scrapped video downloading feature. Future plans: add resolution scaling.

### `DuplicateFileRemover.java`

* Scans and deletes duplicate files based on file hash.
* Uses hash caching for faster re-runs.

> Because sometimes you forget you already downloaded something 3 times (a me issue).

### `FileSorter.java`

* Predecessor to [`fileSorter.cpp`](#filesortercpp).
* Hardcoded paths (you’ll want to change them).

> Began as a Python project; rewritten in Java, Batch, C#, and C++. Might make it in Assembly next.

---

## C++

### `fileSorter.cpp`

* Hardcoded paths (you’ll want to change them).
* May need multiple runs (a bit buggy).

> Same origin story as `FileSorter.java`. File hoarding problems solved via automation. Future plans: add user config for paths and filetypes. Also fix multiple runs issue.

---

## Batch

### `folderToMP3.bat`

* Converts all non-BAT files in a folder to MP3 using FFmpeg.

> For programs that only support MP3 input or other use cases.

### `folderHalfPNGRes.bat`

* Halves resolution of all PNGs in a folder using FFmpeg.

> Saves space while keeping PNG format.

### `sudo.bat`

* Runs a passed command with UAC/admin privileges.

> Made to simulate Linux `sudo` experience on Windows.

---

## PowerShell

### `changeJava.ps1`

* Windows equivalent of `changeJava.sh`.

> Works similarly but is now unused by me as I daily drive Linux now. Could be useful to others though.

---

## JavaScript

### `cyrillicToPolish.js`

* **Tampermonkey Script**
* Transliterates Cyrillic text to Polish dynamically on web pages.
* Currently configured to use YouTube, Genius Lyrics, Lyrics Translate, and Online Notepad.

> Began as a Python project. Version 1.6 as of now. Not perfect due to pronunciation rules but does the job.

---
