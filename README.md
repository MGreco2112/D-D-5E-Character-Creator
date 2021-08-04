# D-D-5E-Character-Creator
A DnD 5th Edition text based simulation

### Features
Create your own character
Create your own dungeons
Allow players to explore your dungeons using a text based interface

### Technologies
Java 8

### Changelist 8/4/21
- Added a 'services' package to hold services and utils
  - These services will allow us to standardize the way we add mosnters, gear, spells, and more to the game. Think of it as building our own engine
  - You kinda had the same mentality with the WeaponsCollection.java class!
- Exctracted Game code from Main.java and placed into its own class
  - I changed the logic of the Main class to provide a single Scanner. No more Scanner Object bloat!
- Standardized dice roll logic in the DiceService.java class
  - This is a fairly large change BUT will reduce unecessary usage of memory since the dice are now singletons. see comments throughout code
