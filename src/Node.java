
/*****
 * 
 * 用来存储路径的节点
 * 
 * ******/
public class Node implements Comparable<Node>{
	
	//路径的位置
	public Location location = new Location(0, 0);
	
	//已经发生的代价，是准确值，用来表示起点到当前结点的代价 
	public int G = 0;
	
	//剩余的代价，是估算值，用来表示当前节点走到终点的代价
	public int H = 0;
	
	//存储父节点，遍历完成后，确定最终路径的时候会用到
	public Node parent = null;
	
	
	public Node(Location loc){
		
		location.x = loc.x;
		location.y = loc.y;
	}
	
	public Node(Location loc, Node p, int g, int h) {
		location.x = loc.x;
		location.y = loc.y;
		parent = p;
		G = g;
		H = h;
	}

    /**
     * 返回值为正数，表明this中的值大于o中的值, 排序时将大的数移至后面
     * 返回值为0，表明this中的值等于o中的值
     * 返回值为负数，表明this中的值小于o中的值
     * 这里实现比较是用来后面排序提升查找性能用的，暂时不需要用到
     */
	@Override
	public int compareTo(Node o) {
		// 将节点按坐标位置升序排列
		
		if(null == o) {
			return -1;
		}
		if(G + H > o.G + o.H) {
			return 1;
		}else if(G + H < o.G + o.H){
			return -1;
		}
		return 0;
	}

}
