# misc-scripts
Miscellaneous scripts, commands, notes, and code snippets by ZeNyfh.

- [Bash Section](#Bash)
- [Java Section](#Java)
- [C++ Section](#C++)
- [Batch Section](#Batch)
- [Powershell section](#Powershell)

## Bash

### fullscreen.sh

- Requires wmctrl, this can be acquired through apt.
- Takes in an app name, for example "Firefox" or "Github Desktop" and makes it borderless windowed, no need for quotes or fancy stuff, just pass in the name with spaces.

This exists because it works really well for minecraft versions which dont play nicely with fullscreen, like 1.12.2.
<br><br>

### changeJava.sh

This utilises the "update-alternatives" command to allow for quick switching of java versions that are installed. Great for people who use jars which require different JREs (Minecraft servers especially).
<br><br>

### hotspot.sh

- Takes in a network interface name, a chosen SSID, and a password for the hotspot.

I made this specifically so that my Wii can connect to the internet on my university accomodation Wi-Fi network.
<br><br>

### appHelper.sh

- Get's user input to create a menu shortcut for a script/application.

I made this a while ago to help me out with adding AppImages and bash scripts to my Menu in the Cinnamon desktop environment, should work with all DEs with a "Quick Access" menu though.

I could have just moved the executable files into the `.local/share/applications` directory but then it wouldn't have *thumbnails*.
<br><br>

## Java

### VideoCompressor.java

- Takes in a file and uses FFMpeg 2 pass filtering to lower the filsize by changing the bitrate

This is one of my nicer tools, this came from one of my projects (Zenvibe), which used to download videos and send them to discord, and of course files needed to be made smaller to be sent. Though that feature was too annoying to develop and was therefore scrapped, but worked really well as a standalone utility for compressing videos to be sent onto discord.

It really needs to be improved on by lowering resolution if the bitrate is too low, but that is effort and for a later date.
<br><br>

### DuplicateFileRemover.java

- Removes all duplicate files based on file hash.

- Caches all the hashes for faster comparisons when ran again.

Not everyone needs this, however I am a little daft sometimes and download the same files multiple times, or forget that I have downloaded them and have multiple copies of them, this is nice to remove them all.
<br><br>

### FileSorter.java

- this is the predecessor to [fileSorter.cpp](#filesortercpp).

- Hardcoded, if you want to use this I recommend changing the paths this uses, perhaps someday I will add a .conf file for this to use.	

This originally started as one of my first python programming projects, but it has since been re-coded in java, I think batch at some point, C# for fun, and now C++, I want to attempt this in Assembly eventually.

The reason why this actually exists is because I am a hoarder of files, I like to click download on funny/cool media I see, but this takes up precious storage space on my OS drive, so I move stuff to my other drives using this script.
<br><br>

## C++

### fileSorter.cpp

- Hardcoded, if you want to use this I recommend changing the paths this uses, perhaps someday I will add a .conf file for this to use.

- Broken(?), requires a couple runs sometimes, I haven't looked at this code in a while.

This originally started as one of my first python programming projects, but it has since been re-coded in java, I think batch at some point, C# for fun, and now C++, I want to attempt this in Assembly eventually.

The reason why this actually exists is because I am a hoarder of files, I like to click download on funny/cool media I see, but this takes up precious storage space on my OS drive, so I move stuff to my other drives using this script.
<br><br>

## Batch

### folderToMP3.bat

- attempts to convert all files (non-bat) in the directory to mp3 using FFMpeg.

I needed this because I was using a program which only took mp3s in, uses FFMpeg so it works with mp4s too, overall nice script that I used on Windows.
<br><br>

### folderHalfPNGRes.bat

- attempts to half the resolution of all PNG files in the directory using FFMpeg.

I think this was for saving file space because I had a BUNCH of large png files which I did not want to JPEG-ify due to PNG being required in that case. Could be useful for someone.
<br><br>

### sudo.bat

- attempts to run a passed in process with UAC/admin priveleges.

This was made because I wanted to have a more linux-like experience on windows, it was especially nice if I had a normal cmd window open and wanted to run something as admin. Quite useful overall, though I haven't used this in ages.
<br><br>

## Powershell

### changeJava.ps1

I remember this sort of worked, this is the Windows equivalent of changeJava.sh, I don't use Windows anymore however, this is here for archival purposes. Though, if you find this useful feel free to use it/add more to it with a PR. :)