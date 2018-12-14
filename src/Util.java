import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util {
	
	//用曼哈顿方法测量或者说估算 总体的代价值
	//曼哈顿的估算方法就是，只统计直线的距离，忽略斜线的距离
	public static int measureH(Location end, Location current) {
		int H = Math.abs(end.y - current.y) + Math.abs(end.x - current.x);
		return H;
	}
	
	//是否最终节点
	public static boolean isEndNode(Location end, Location current) {
		if(end.x == current.x && end.y == current.y) {
			return true;
		}
		return false;
	}
	
	//在原来到二维数组中打印出路径
	//可以直接输出节点
	public static void drawPath(int[][] map, Node end) {
		 
		System.out.println("路径总代价" + end.G);
		
		while(end != null) {
			
			Location loc = end.location;
			map[loc.y][loc.x] = 2;
			end = end.parent;
		}
	}
	
	//找到路径后，正序返回给外部
	public static List<Location> getFinalPath(Map myMap){
		
		if(myMap == null || myMap.end == null) {
			return null;
		}
		
		Node end = myMap.end;
		
		List<Location> pathList = new ArrayList<>();
		while(end != null) {	
			pathList.add(end.location);
			end = end.parent;
		}
		
		//倒序过来
		Collections.reverse(pathList);
		
		return pathList;
	}

}
