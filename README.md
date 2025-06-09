# misc-scripts
Miscellaneous scripts, commands, notes, and code snippets by ZeNyfh.

## Bash

### fullscreen.sh

- Requires wmctrl, this can be acquired through apt.
- Takes in an app name, for example "Firefox" or "Github Desktop" and makes it borderless windowed, no need for quotes or fancy stuff, just pass in the name with spaces.

This exists because it works really well for minecraft versions which dont play nicely with fullscreen, like 1.12.2.

### changeJava.sh

This utilises the "update-alternatives" command to allow for quick switching of java versions that are installed. Great for people who use jars which require different JREs (Minecraft servers especially).

### hotspot.sh

- Takes in a network interface name, a chosen SSID, and a password for the hotspot.

I made this specifically so that my Wii can connect to the internet on my university accomodation Wi-Fi network.

### appHelper.sh

- Get's user input to create a menu shortcut for a script/application.

I made this a while ago to help me out with adding AppImages and bash scripts to my Menu in the Cinnamon desktop environment, should work with all DEs with a "Quick Access" menu though.

I could have just moved the executable files into the `.local/share/applications` directory but then it wouldn't have *thumbnails*.

## C++

### fileSorter.cpp

- Hardcoded, if you want to use this I recommend changing the paths this uses, perhaps someday I will add a .conf file for this to use.

- Broken(?), requires a couple runs sometimes, I haven't looked at this code in a while.

This originally started as one of my first python programming projects, but it has since been re-coded in java, I think batch at some point, C# for fun, and now C++, I want to attempt this in Assembly eventually.

The reason why this actually exists is because I am a hoarder of files, I like to click download on funny/cool media I see, but this takes up precious storage space on my OS drive, so I move stuff to my other drives using this script.

## Powershell

### changeJava.ps1

I remember this sort of worked, this is the Windows equivalent of changeJava.sh, I don't use Windows anymore however, this is here for archival purposes. Though, if you find this useful feel free to use it/add more to it with a PR. :)