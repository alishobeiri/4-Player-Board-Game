# Group01

### Design Mode (10 features)
  * Create a game with a number of players
  * Place a tile on the game board
  * Remove a tile from the game board
  * Connect two tiles with a connection piece
  * Remove connection between two tiles
  * Identify the hidden til e
  * Identify the starting tile of a player
  * Identify an action tile (inactivity period not required for this iteration)
  * Select 32 cards from predefined choices
   
### Play Mode (9 features)
  * Start a game (shuffle the action cards, place players on board) - Thomas (partially implemented)
  * Take a turn (roll the die, move to new position) - Thomas (roll die implemented, moving not)
  * Land on a tile (basic behavior for hidden, regular, and action tiles) - Thomas
  * Take the first card from the deck of cards
  * Action card "Roll the die for an extra turn"
  * Action  card  "Connect  two  adjacent  tiles  with  a  connection  piece  from  the  pile  of  spare  connection pieces"
  * Action card "Remove a connection piece from the board and place it in the pile of spare connection pieces"
  * Action card "Move your playing piece to an arbitrary tile that is not your current tile"
  * Save and load game to continue playing at a later point
