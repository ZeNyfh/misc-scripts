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
