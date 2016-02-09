import java.util.ArrayList;

public class GamePlay implements Game{
	private Location[] human;
	private Location[] computer;
	private int[] playerBoard;
	private int playerShipSunk;
	private int computerShipSunk;
	private HumanPlayer p1;
	private ComputerPlayer p2;

	
	@Override
	public void initialize(Player p1, Player p2) {
		// TODO Auto-generated method stub
		this.p1 = (HumanPlayer) p1;
		this.p2 = (ComputerPlayer) p2;
		boolean retry = false;
		ShipLocation temp = null;

		do {
			human[0] = p1.placeShip(CARRIER, retry);
			temp = (ShipLocation) human[0];
			if (outOfBoard(temp, CARRIER, temp.isShipHorizontal())){
				retry = true;
			} else {
				fillBoard(temp);
				if (sumBoard() != 90) {
					retry = true;
				} else {
					retry = false;
				}
			}
			
		} while (retry);
		
        do {
        	human[1] = p1.placeShip(BATTLESHIP, retry);
        	temp = (ShipLocation) human[1];
			fillBoard(temp);
			if (outOfBoard(temp, BATTLESHIP, temp.isShipHorizontal()) || sumBoard() != 82) {
				retry = true;
//				unfill the board if it is not a valid ship position
				fillBoard(temp);
			} else {
				retry = false;
			}
        } while (retry);
        
        do {
        	human[2] = p1.placeShip(SUBMARINE, retry);
        	temp = (ShipLocation) human[2];
			fillBoard(temp);
			if (outOfBoard(temp, SUBMARINE, temp.isShipHorizontal()) || sumBoard() != 76) {
				retry = true;
//				unfill the board if it is not a valid ship position
				fillBoard(temp);
			} else {
				retry = false;
			}
        } while (retry);
        
        do {
        	human[3] = p1.placeShip(CRUISER, retry);
        	temp = (ShipLocation) human[3];
			fillBoard(temp);
			if (outOfBoard(temp, CRUISER, temp.isShipHorizontal()) || sumBoard() != 70) {
				retry = true;
				fillBoard(temp);
			} else {
				retry = false;
			}
        } while (retry);
        
        do {
        	human[4] = p1.placeShip(DESTROYER, retry);
        	temp = (ShipLocation) human[4];
			fillBoard(temp);
			if (outOfBoard(temp, DESTROYER, temp.isShipHorizontal()) || sumBoard() != 66) {
				retry = true;
				fillBoard(temp);
			} else {
				retry = false;
			}
        } while (retry);
		
		
		
//		computer place ships at the exact same locations, so there is no need to retry.
		computer[0] = p2.placeShip(CARRIER, false);
		computer[1] = p2.placeShip(BATTLESHIP, false);
		computer[2] = p2.placeShip(SUBMARINE, false);
		computer[3] = p2.placeShip(CRUISER, false);
		computer[4] = p2.placeShip(DESTROYER, false);
		
		
	}

	@Override
	public Player playGame() {
		// TODO Auto-generated method stub
		Shot humanShot = null;
		Shot computerShot = null;
		boolean humanHit = false;
		boolean humanSunk = false;
		boolean computerHit = false;
		boolean computerSunk = false;
		while (playerShipSunk < 5 && computerShipSunk < 5){
			do {
				System.out.println("Computer has " + (5-this.computerShipSunk) + " ships remaining");
				humanShot = (Shot) p1.getTarget();
				humanHit = false;
				humanSunk = false;
				if (this.validShot(humanShot, computer) != -1){
					humanHit = true;
					if (this.sunkShip(humanShot, computer, true)){
						humanSunk = true;
						computerShipSunk ++;
					}
				}
				p1.setResult(humanHit, humanSunk);
			} while (humanHit);
			
//			computer turn
            do {
            	computerShot = (Shot) p2.getTarget();
    			computerHit = false;
    			computerSunk = false;
    			if (this.validShot(computerShot, human) != -1) {
    				computerHit = true;
    				if (this.sunkShip(computerShot, human, false)) {
    					computerSunk = true;
    					playerShipSunk++;
    				}
    			} 
    			p2.setResult(computerHit, computerSunk);
            } while (computerHit);
			

			
			
		}
		if (this.playerShipSunk == 5){
			System.out.println("Computer wins");
			return p2;
		} else {
			System.out.println("Player wins");
			return p1;
		}
	}

	
	/**
	 * Constructor, initialized both player
	 */
	public GamePlay(){

		human = new Location[5];
		computer = new Location[5];
	    playerBoard = new int[100];
//	    initialized player board so that it could used to keep track of how many ships have been placed
		for (int i = 0; i < 100; i++){
			playerBoard[i] = 1;
		}
		
	}
	
	/*
	 * this method checks whether the ship was placed outside of the board
	 */
	public boolean outOfBoard(ShipLocation ship, int size, boolean horizontal){
		int x = ship.getX();
		int y = ship.getY();
		if (horizontal){
			if (y+size > 10){
				return true;
			}
		} else {
			if (x+size > 10){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * update the board when placing a ship
	 */
	public void fillBoard(ShipLocation ship){
		int[] shipPos = ship.getPosition();
		for (int j = 0; j < shipPos.length; j++) {
			playerBoard[shipPos[j]] = playerBoard[shipPos[j]] * -1;
		}
	}
	
//	helper method
	public int sumBoard(){
		int sum = 0;
		for (int i = 0; i < 100; i++){
			sum += playerBoard[i];
		}
		return sum;
	}
	
    /*
     * This method checks whether a shot hits a ship
     * returns the corresponding index number in player shiplocation array or -1 if not found
     */
	private int validShot(Shot shoot, Location[] player){
		int shotPos = shoot.getPos();
		int result = -1;
		ShipLocation temp = null;
		boolean found = false;
		for (int i = 0; i<player.length && !found; i++){
			temp = (ShipLocation) player[i];
			int[] shipPos = temp.getPosition();
			for (int j = 0; j<shipPos.length && !found; j++){
				if (shipPos[j] == shotPos){
					found = true;
					result = i;
				}
			}
		}
		return result;
	}
	
	/*
	 * check whether a ship has been sunk after a hit
	 * boolean human indicates whoes turn it is
	 */
	private boolean sunkShip(Shot shot, Location[] player, boolean human){
		ArrayList<Integer> pastShots = new ArrayList<Integer>();
        if (human){
        	pastShots = p1.getShotFired();
        } else {
        	pastShots = p2.getShotFired();
        }
		
//		total shots fire
		int numOfShots = pastShots.size();
		int index = validShot(shot, player);
//		the ship that was hit
		ShipLocation shipHit = (ShipLocation) player[index];
//		list of the damaged ship's location
		int[] shipPos = shipHit.getPosition();
		
//		if total number of shots is lower than the ship size, it cannot be sunk
		if (numOfShots < shipPos.length){
			return false;
		}
//		loop through the ship position, if one of the positions is not hit, return false
		for (int i = 0; i < shipPos.length; i++){
			if (!pastShots.contains(shipPos[i])) {
				return false;
			}
		}
		
		
		return true;
	}
	

}
