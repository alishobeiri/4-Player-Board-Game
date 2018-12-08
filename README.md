# TileO Board Game
The object of this game is for players to compete against each other to be the first player to land on the winning tile. The winning tile appears as a normal tile to the players and the first player to land on the tile wins. The game comes with several power ups that augment the playing field and allow for fun combinations and new challenges to be introduced into the game. 

The game supports two modes:
#### 1. Design mode:
This mode allows users to design and set up the board game to allow for them to vary difficulty and choose the board setup they wish to play with. A subset of features provided in this mode are:
 * Allows game creation with a number of players (4 max)
 * Design board state
   * Place tile
   * Remove tile
   * Denote tile type
   * Connect tiles together (create moveable paths for players)
   * Identify winning hidden tile
   * Identify player starting tiles
 * Choose up to 32 action cards that can modify game play state
 * Save designed game states
 * Load previously designed game states

#### 2. Play mode:
This mode allows users to select and play through a set of previously designed board configurations. Some of the features implemented in this mode include:
  * Start a game (shuffle the action cards, place players on board) 
  * Take a turn (roll the die, move to new position)
  * Land on a tile (basic behavior for hidden, regular, and action tiles)
  * Take the first card from the deck of cards
  * Perform different actions denoted by the card
  * Save and load game to continue playing at a later point
