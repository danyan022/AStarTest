
import java.util.List;

//这里是入口
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] maps = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } 
				};
		
		Location start = new Location(1, 5);
		Location end = new Location(10, 5);
		
		Map myMap=new Map(maps, new Node(start), new Node(end));
		Astar.start(myMap);
		
		//打印路径
		printPath(myMap);
		
		//打印地图
		printMap(myMap.map);
	}
	
	//打印最终地图
	public static void printMap(int[][] map) {
		
		System.out.println();
		System.out.println("描绘路径:");
		
		//遍历二维数组
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	//逆序输出需要的节点
	public static void printPath(Map myMap) {
		
		if(myMap == null || myMap.end == null) {
			return;
		}
		
		
		List<Location> pathList = Util.getFinalPath(myMap);
		
		System.out.println();
		System.out.println("输出路径:");
		for(int i=0; i<pathList.size(); i++) {
			
			Location loc = pathList.get(i);
			System.out.print("(" + loc.x + "," + loc.y + ")");
			System.out.print(" ");
		}
		
	
	}

}
