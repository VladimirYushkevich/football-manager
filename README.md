Football Manager
-----------
### Simple CLI application with the following features:
* User can select a team to play for.
* Before each match day user can switch a position within the team.
* To start a match the team and position should be selected.
* After match day user gains (or not) some experience points.
* Explore user profile is possible via corresponding menu item.
* Navigation is just via valid items in menu.
* **Exit** menu terminates the game.
* After two rounds (home and away games) by trying to play next game you will see the result and the game terminates automatically.

### Technical details:
All scripts to build and run application are located in *documentation* folder:
* Run `package.bat` first. In the case of success build the executable `football-manager.jar` will be in *delivery* folder. If *delivery* is not exists just create it.
* Run `run.bat`. Just to start a game.
* XML files are in *data* folder. Please note that the execution units tests resets any previously stored data.
* Application has been tested in Windows CL so far. 