

/**
 * 
 * 地图信息
 * 
 * **/
public class Map {
	
	//二维数组，用来表示地图坐标（x轴和y轴）
	int[][] map = null;
	
	//开始节点
	Node start = null;
	
	//结束节点
    Node end = null;

    
	public Map(int[][] map, Node start, Node end)
	{
		this.map = map;
		this.start = start;
		this.end = end;
	}
    
}
