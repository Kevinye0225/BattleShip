import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer implements Player{
//	list of shots that has been fired
	private ArrayList<Integer> shotFired;

	
	@Override
	public Location placeShip(int size, boolean retry) {
		// TODO Auto-generated method stub
		
		Scanner in = null;
		int direction = 0;
		int x = 0;
		int y = 0;
		boolean horizontal = false;
		if (size == 5){
			System.out.println("Placing Carrier");
		}
		if (size == 4){
			System.out.println("Placing Battleship");
		}
		if (size == 3){
			System.out.println("Placing Submarine or Cruiser");
		}
		if (size == 2){
			System.out.println("Placing Destroyer");
		}
        try {
        	in = new Scanner(System.in);
        	System.out.println("Please enter x coordinate: " );
    		x = in.nextInt();
    		System.out.println("Please enter y coordinate: " );
    		y = in.nextInt();

    		System.out.println("Please indicate whether you want it to be horizontal 0 being Yes/1 being No: ");
    		direction = in.nextInt();
        } catch (InputMismatchException e){
        	e.printStackTrace();
        }
		
		if (direction == 0){
			horizontal = true;
		}
		
		ShipLocation result = new ShipLocation(size);
		result.setLocation(horizontal,x,y);
		int[] shipPos = result.getPosition();

		
		return result;
	}
	
	public ArrayList<Integer> getShotFired(){
		return this.shotFired;
	}

	@Override
	public Location getTarget() {
		// TODO Auto-generated method stub
		Scanner in = null;
		int pos;
		Shot target = null;
		try {
			in = new Scanner(System.in);
//			the pos equals to x + 10*y
			System.out.println("Please select a position from 0 to 100: ");
		    pos = in.nextInt();
			target = new Shot(pos);
			shotFired.add(pos);
		} catch (InputMismatchException e){
			e.printStackTrace();
		}


		return target;
	}

	@Override
	public void setResult(boolean hit, boolean sunk) {
		// TODO Auto-generated method stub
		if (hit){
			System.out.println("You hit a ship");
		}
		if (sunk){
			System.out.println("A ship has been sunk");
		}
		if (!hit && !sunk){
			System.out.println("oops, you did not hit anything");
		}
		
	}
	
	/*
	 * Constructor
	 */
	public HumanPlayer(){
		shotFired = new ArrayList<Integer>();
	}
	
	
	

}
