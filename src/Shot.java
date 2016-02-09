
public class Shot implements Location{
	private int x;
	private int y;
	private int pos;
	
	public Shot(int pos){
		this.pos = pos;
		this.y = pos/10;
		this.x = pos - this.y;
	}
	
	public int getPos(){
		return this.pos;
	}

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
		// TODO Auto-generated method stub
		return false;
	}
	




}
