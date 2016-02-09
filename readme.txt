This is the readme file for battleship game, I would love to participate in the tournament.

Both computer and players place the ships in the same order, placing the Carrier first and placing the destroyer last, player will get an extra shot if hits a ship. The computer adopts a fixed pattern of placing the ships instead of randomizing it each time, all the ships collide with each other as a mean to trick the players. The shooting strategy for computer is to see the entire board as a chessboard, and it only shots the dark square until it hits a ship(1,3,5,7,9,10,12...).

ShipLocation Class:
It implements Location interface, it contains a integer array, which stores the position of a ship. The position is calculated as the following: x + 10*y, so it ranges from 0 to 99. The setLocation method fills the position array so that we could track where the ship is placed. 

Shot Class:
Similar to shipLocation class, it used an integer pos to represent its position.

HumanPlayer Class:
When human places ships, they pick the x and y coordinate first, and then indicates whether they want it horizontal or not. It has instance variable shotFired, which is an arraylist of position(integer) of the shots.

ComputerPlayer class:
Its strategy is explained on the top.


GamePlay class:
The way game checks whether a placement of ship is valid is checking if its the sum of x or y coordinate and size exceed 10 (if so, 
it is out of bound), and see if there is overlap by calculate the sum of the board, which should correspond to a value if it is placed correctly. It is done via fillboard method, the board is initially set to 1 for each box, whenever a ship is placed, the position in the board get switched to -1, and if placement is not successful, it get switched back. If a player hits a ship, the game will check whether the ship has been hit is sunk by checking all of its coordinates against the shots. 

GamePlayTester class:
Create an instance of both computerplayer and humanplayer, then the game will be initialized. 