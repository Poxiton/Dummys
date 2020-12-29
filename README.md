# Dummys
It is a minecraft plugin for creating dummies.

## Commands
- /dummy create
- /dummy delete
- /dummy reload
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

# True - auto reset total damage / False - dummy does not restart until deleted
DummyReset: true

# Initial damage value of the dummy
DummyDamage: 0

# Set /dummy help message
HelpMessage:
    - "&c=======================================\n"
    - "&e/dummy create - Use to create a dummy\n"
    - "&e/dummy delete - Use to delete a dummy\n"
    - "&e/dummy reload - Use to reload config\n"
    - "&c=======================================\n"

# Set message when dummy can't be placed
NotHere: "&cYou cannot place that here!"

# Set message when entity is not dummy
NotDummy: "&cThis is not a dummy!"

# Set (/dummy reload) message
DummyReload: "&aDummy configuration has been reloaded"
```

## License
This repo is licensed under the [MIT LICENSE](/LICENSE).
