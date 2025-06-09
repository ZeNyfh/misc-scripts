#!/bin/bash

if [[ $3 == "" ]] then
	echo "Not all arguments given."
	echo "Usage: ./hotspot interface SSID password"
	exit 0
fi

nmcli dev wifi hotspot ifname "$1" ssid "$2" password "$3" 
