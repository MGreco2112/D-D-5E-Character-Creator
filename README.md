
# D&D 5e Dungeons Simulator

What started out as a character generator for 5th Edition Dungeons
& Dragons quickly took on a life of its own, becoming a CLI based
Text Adventure system for creating Dungeons and Adventures within them.

The systems at work within this project may be crude, however the majority 
was built as I was learning Java one week at a time. As I was learning new
ways to accomplish goals, such as method calls from within methods, I would impliment them.
The Combat Flow would really benefit from a while loop as opposed to running through
method calls, however touching this up would defeat the purpose of the code.

This project also has another contributor, a friend helped me refactor the Player Ability Modifiers into
a HashMap as opposed to hardcoding them in based on the chart in the 5e Player's Handbook. The fork
and merge of this project was my first time utilizing those features of Git.


## Run Locally

Clone the project

```bash
  git clone https://github.com/MGreco2112/D-D-5E-Character-Creator
```

Click the Play Button in the `Main Class` next to `private static void main(String args[])`
and the program will begin running in the CLI of the Editor.

First enter the prompted information for Character Creation, rerolling dice until
you have a desired set of Ability Scores. Then you place them into what Abilities you
wish them to pull from. Once a Character is built the first Dungeon begins and the menus
will allow you to navigate the two room Dungeon that is built into the Project.

### Note on Inputs:

There currently is no failsafe for incorrectly parsed data, thus the app will
crash in places where a character cannot be parsed as an Integer. This was left off before I
learned of such things. When I rebuild this to start fresh, I will include such an item to
prevent crashes

