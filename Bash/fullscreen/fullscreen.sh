if [[ $1 == "" ]] then
	echo "No argument given."
	exit 0
fi

wmctrl -r "$@" -b add,below,fullscreen
