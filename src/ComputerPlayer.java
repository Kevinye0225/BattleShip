import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer implements Player {
	public int[] board;
	private ArrayList<Integer> shotFired;
	// used to implement shot strategy
	private int shotCount;
	private int round;
	private boolean lastShot;
	private boolean lastSunk;
	private boolean secondLast;
	private boolean thirdLast;
	private boolean forthLast;
	/*
	 * Initialize all position in the board to be false till a ship is placed
	 */
	public ComputerPlayer() {
		board = new int[99];
		shotFired = new ArrayList<Integer>();
		shotCount = 1;
		round = 1;
		lastShot = false;
		lastSunk = false;
		secondLast = false;
		thirdLast = false;
		forthLast = false;
	}

	@Override
	public Location placeShip(int size, boolean retry) {
		// TODO Auto-generated method stub
		ShipLocation result = new ShipLocation(size);
		int i = 0;
		if (size == 5) {
			result.setLocation(true, 2, 2);
			for (i = 2; i < 7; i++) {
				int pos = 2 + i * 10;
				board[pos] = 1;
			}
		}
		if (size == 4) {
			result.setLocation(false, 3, 2);
			for (i = 3; i < 7; i++) {
				int pos = 20 + i;
				board[pos] = 1;
			}
		}
		if (size == 3) {
			if (board[33] == 1) {
				result.setLocation(false, 3, 4);
				for (i = 3; i < 6; i++) {
					int pos = i + 40;
					board[pos] = 1;
				}
			} else {
				result.setLocation(false, 3, 3);
				for (i = 3; i < 6; i++) {
					int pos = i + 30;
					board[pos] = 1;
				}
			}
		}
		if (size == 2) {
			result.setLocation(false, 3, 5);
			for (i = 3; i < 5; i++) {
				int pos = i + 50;
				board[pos] = 1;
			}
		}

		return result;

	}

	@Override
	public Location getTarget() {
		// TODO Auto-generated method stub
		int shotPos = 0;
		Random rand = new Random();
		int lastPos = 0;
		int numOfShots = shotFired.size();
		if (numOfShots != 0) {
			lastPos = shotFired.get(numOfShots - 1);
		}

		// if misses last shot or last shot sunk a ship, using the original
		// strategy
		if ((!lastShot && !secondLast && !thirdLast && !forthLast) || (lastShot && lastSunk)) {
			// keep finding the next available shot positions
			if (shotCount < 50) {
				do {
					if (shotCount % 5 == 0) {
						round++;
					}
					if (round % 2 == 0) {
						shotPos = shotCount * 2 - 2;
					} else {
						shotPos = shotCount * 2 - 1;
					}
					shotCount++;
				} while (shotFired.contains(shotPos));
			} else {
				do {
					shotPos = rand.nextInt(100);
				} while (shotFired.contains(shotPos));
			}
		} else {
			if (lastPos % 9 == 0) {
				if (lastPos >= 90) {
					shotPos = lastPos - 10;
				} else {
					shotPos = lastPos + 10;
				}
			} else {
				shotPos = lastPos + 1;
			}
		}

		Shot target = new Shot(shotPos);
		shotFired.add(shotPos);
		return target;
	}

	@Override
	public void setResult(boolean hit, boolean sunk) {
		// TODO Auto-generated method stub
		forthLast = thirdLast;
		thirdLast = secondLast;
		secondLast = lastShot;
		lastShot = hit;
		lastSunk = sunk;
		if (hit) {
			System.out.println("Computer hits a ship");
		}
		if (sunk) {
			System.out.println("Computer sunk a ship");
		}
		if (!hit) {
			System.out.println("Computer miss");
		}
	}

	public ArrayList<Integer> getShotFired() {
		// TODO Auto-generated method stub
		return this.shotFired;
	}

}
