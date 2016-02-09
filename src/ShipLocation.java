/**
 * All ships' location are indicated by its top-left position
 * @author kevin
 *
 */
public class ShipLocation implements Location{
	private int x;
	private int y;
	private int[] position;
	private boolean isHorizontal;
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public boolean isShipHorizontal() {
		
		return this.isHorizontal;
	}

	
	public ShipLocation(int size){

		position = new int[size];
	}
	
	/**
	 * this method fills the position array with corresponding int value of ships' position
	 * direction can only be right or down (horizontal or vertical) given that x and y is the upper left position
	 * @param direction
	 */
	public void setLocation(boolean horizontal, int x, int y){
		int i = 0;
		this.x = x;
		this.y = y;
		int tempX = x;
		int tempY = y;
		if (!horizontal){
			while (i<this.position.length){
				position[i] = tempX + y*10;
				i++;
				tempX++;
			}
		} else {
			while (i<this.position.length){
				position[i] = x + tempY*10;
				i++;
				tempY++;
			}
		}
		this.isHorizontal = horizontal;
	}
	
	public int[] getPosition(){
		return position;
	}
	
	
}
