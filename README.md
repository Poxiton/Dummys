# Dummys
It is a minecraft plugin for creating dummies.

## Commands
- /dummy create
- /dummy delete
- /dummy help

## Installing
1. Download the newest release from the [releases page](https://github.com/Poxiton/Dummys/releases).

2. Put everything in your plugins folder.

3. Enjoy!

## Configuration
You can find the config file at \plugins\Dummys\config.yml. By default it should look like this:
```yml
#  ----------------------------------------------------
#   Dummys Configuration File
#  ----------------------------------------------------

# Time to restart total damage after last hit (seconds)
DummyRestartTime: 4

# Set /dummy help message
HelpMessage:
    - "&c=======================================\n"
    - "&e/dummy create - Use to create a dummy\n"
    - "&e/dummy delete - Use to delete a dummy\n"
    - "&c=======================================\n"

# Set message when dummy can't be placed
NotHere: "&cYou cannot place that here!"

# Set message when entity is not dummy
NotDummy: "&cThis is not a dummy!"
```

## License
This repo is licensed under the [MIT LICENSE](/LICENSE).
