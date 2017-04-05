# Group01

### Umple
  * Make sure to copy any additional Java methods into the Umple code so that they persist across regeneration
  * Specifically:
    * All new model classes must be added to the Persistence ump
    * All new methods must be added to their respective classes ump
  * After regeneration 3 changes have to be made to the model: 
    * in ActionTile.java change the 'status' from private to public
    * in Game.java and Deck.java comment out the entire method with the error
  
### Design Mode (10 features)
  * Create a game with a number of players
  * Place a tile on the game board
  * Remove a tile from the game board
  * Connect two tiles with a connection piece
  * Remove connection between two tiles
  * Identify the hidden til e
  * Identify the starting tile of a player
  * Identify an action tile
  * Select 32 cards from predefined choices
   
### Play Mode (9 features)
  * Start a game (shuffle the action cards, place players on board) - Thomas
  * Take a turn (roll the die, move to new position) - Thomas
  * Land on a tile (basic behavior for hidden, regular, and action tiles) - Thomas
  * Take the first card from the deck of cards
  * Action card "Roll the die for an extra turn"
  * Action  card  "Connect  two  adjacent  tiles  with  a  connection  piece  from  the  pile  of  spare  connection pieces"
  * Action card "Remove a connection piece from the board and place it in the pile of spare connection pieces"
  * Action card "Move your playing piece to an arbitrary tile that is not your current tile"
  * Save and load game to continue playing at a later point
  
### Git Workflow
  * Before editing any code run `git pull` or `git pull origin master`
  * After editing code run `git status` to see what files have changed
  * Then use `git add <filename1> <filename2>` to add stage specific files or `git add .` to stage all files
  * Run `git status` again for good measure
  * Run `git commit -m "<you_commit_message>"` to commit to your local repository with a commit message in the present tense
  * Finally, to commit the the github repository, run `git push`
