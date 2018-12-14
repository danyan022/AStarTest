

/**
 * 
 * 当前节点坐标
 * 
 * **/
public class Location {
	
	public int x;
	public int y;
	
	
	public Location(int x, int y){
		
		this.x = x;
		this.y = y;
	}
	
	//是否当前节点党坐标
	public boolean equel(Location loc){
		
		if(loc == null){
			return false;
		}
		
		if(loc.x == this.x && loc.y == this.y){
			return true;
		}
		return false;
	}
}
