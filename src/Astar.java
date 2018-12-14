import java.util.ArrayList;
import java.util.List;

/****
 * 
 *A星算法核心
 * 
 ******/
public class Astar {
	
	//不能走的节点
	public static final int CAN_NOT_GO_NODE = 1;
	
	//斜线价值  价值由来是根号2的值，根据单位为1的正方形的对角线的长度得来，精确是1.414，这里一般放大10倍取整数
	public static final int DIAGONAL_VALUE = 14;
	//直线价值  
	public static final int STRAIGHT_VALUE = 10;
	
	//需要从中选择比较的坐标点
	public static List<Node> openList = new ArrayList<Node>();
	//不需要再比较的坐标点
	public static List<Node> closeList = new ArrayList<Node>();
	
	public static void start(Map myMap) {
		
		if(myMap == null) {
			return;
		}
		
		openList.clear();
		closeList.clear();
		
		//增加开始节点
		openList.add(myMap.start);
		
		//查找路径
		searchPath(myMap);
		
		//暂时先不考虑效率，遍历完成
		//如果结束节点已经加入到closelist里面，说明已经找到最后了
		//查找完成打印出来
		if(isNodeInCloseList(myMap.end.location)) {
			Util.drawPath(myMap.map, myMap.end);
		}
	}
	
	
	//遍历map节点位置，查找最好的path
	public static void searchPath(Map myMap) {
		
		while(!openList.isEmpty()) {
			
			Node node = openList.get(0);
			openList.remove(0);
			
			closeList.add(node);
			addSideNode(myMap, node);
		}
	}

	//将旁边到节点加入到openlist
	//如果不允许走斜线，就把对角的相邻节点去掉
	public static void addSideNode(Map myMap, Node currentNode) {
		//上  下  左  右
		Location loc1 = new Location(currentNode.location.x, currentNode.location.y-1);
		Location loc2 = new Location(currentNode.location.x, currentNode.location.y+1);
		Location loc3 = new Location(currentNode.location.x-1, currentNode.location.y);
		Location loc4 = new Location(currentNode.location.x+1, currentNode.location.y);
		
		//左上  右上  左下  右下
		Location loc5 = new Location(currentNode.location.x-1, currentNode.location.y-1);
		Location loc6 = new Location(currentNode.location.x+1, currentNode.location.y-1);
		Location loc7 = new Location(currentNode.location.x-1, currentNode.location.y+1);
		Location loc8 = new Location(currentNode.location.x+1, currentNode.location.y+1);
		
		//直线代价
		calNodeValue(myMap, currentNode, loc1, STRAIGHT_VALUE);
		calNodeValue(myMap, currentNode, loc2, STRAIGHT_VALUE);
		calNodeValue(myMap, currentNode, loc3, STRAIGHT_VALUE);
		calNodeValue(myMap, currentNode, loc4, STRAIGHT_VALUE);
		
		//斜线代价
		calNodeValue(myMap, currentNode, loc5, DIAGONAL_VALUE);
		calNodeValue(myMap, currentNode, loc6, DIAGONAL_VALUE);
		calNodeValue(myMap, currentNode, loc7, DIAGONAL_VALUE);
		calNodeValue(myMap, currentNode, loc8, DIAGONAL_VALUE);
	}
	
	//计算节点价值
	public static void calNodeValue(Map myMap, Node currentNode, Location loc, int value) {
		
		if(isCanAddInOpenList(myMap, loc)) {
			//其实这里就是要比对一个问题：新加入的节点是否已经存在于openlist里面
			//如果不存在，就把新节点加入到openlist里面
			//如果存在，就把新节点计算出来的G值和openlist筛选出时，原始的G值比对
			//如果当前计算的G更小，就替换节点的G，同时更新该节点的parent为当前节点
			//否则不做改变
			Node newNode = findNodeFromOpenList(loc);//把要新加入节点的坐标位置在openlist里面查找
			int G = currentNode.G + value;
			if(newNode == null) {
				
				//新节点的H值
				int H = Util.measureH(myMap.end.location, loc);
				//是否是结束节点
				if(Util.isEndNode(myMap.end.location, loc)) {
					newNode = myMap.end;
					newNode.parent = currentNode;
					newNode.G = G;
					newNode.H = H;
				}else {
					newNode = new Node(loc, currentNode, G, H);
				}
				openList.add(newNode);
				
			}else if(newNode.G > G){
				//如果不是空的，意味着节点已经在openlist里面存在了，要做比对
				newNode.G = G;
				newNode.parent = currentNode;
				openList.add(newNode);
			}
		
		}
		
	}
	
	//判断节点是否合法
	public static boolean isCanAddInOpenList(Map myMap, Location loc) {
		
		if(loc.x < 0 || loc.x >= myMap.map[0].length) {
			return false;
		}
		
		if(loc.y<0 || loc.y >= myMap.map.length) {
			return false;
		}
				
		if(myMap.map[loc.y][loc.x] == CAN_NOT_GO_NODE) {
			return false;
		}
		
		if(isNodeInCloseList(loc)) {
			return false;
		}
		
		return true;
	}
	
	//当前节点是否已经加入了closelist
	public static boolean isNodeInCloseList(Location loc) {
		
		if(loc == null || closeList.isEmpty()) {
			return false;
		}
		
		for(int i=0; i<closeList.size(); i++) {
			
			if(loc.x == closeList.get(i).location.x && loc.y == closeList.get(i).location.y) {
				return true;
			}
		}
		
		return false;
	}
	
	//根据坐标位置返回openlist里面的节点
	public static Node findNodeFromOpenList(Location loc) {
		
		if(loc == null || openList.isEmpty()) {
			return null;
		}
		
		for(int i=0; i<openList.size(); i++) {
			
			Node node = openList.get(i);
			 
			if(node.location.x == loc.x && node.location.y == loc.y) {
				return node;
			}	
		}
		return null;
	}	
	
}
