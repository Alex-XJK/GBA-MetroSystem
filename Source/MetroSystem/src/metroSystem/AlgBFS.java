package metroSystem;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Implements {@link metroSystem.Algorithm}, and use Breadth-First Search to search for the route.
 * @see <a href="https://en.wikipedia.org/wiki/Breadth-first_search">BFS Algorithm</a>
 * @since Sept. 29, 2021
 * @version 1.0
 */
public class AlgBFS implements Algorithm{

	/**
	 * {@inheritDoc}
	 * <br>
	 * Use BFS algorithm to search for the shortest path.
	 */
    @Override
    public ArrayList<Integer> findRoute(int startId, int endId, DataList data) {
    	// Initialize
        ArrayList<Integer> shortestRoute = new ArrayList<>();
        Queue<Integer> bfsQueue = new LinkedList<>();
        int size = data.getSize() + 1;
        int[] parent = new int[size];
        boolean[] visited = new boolean[size];
        for (int i=0; i<size; i++) {
        	visited[i] = false;
        	parent[i] = -1;
        }
        
        bfsQueue.offer(startId);
        shortestRoute.add(startId);
        visited[startId] = true;
        
        while (!bfsQueue.isEmpty()) {
        	int headId = toInt(bfsQueue.poll());
        	for (NodeEntry e : data.getNeighbors(headId)) {
        		int nextId = toInt(e.getKey());
        		if (!visited[nextId]) { // if node [nextId] hasn't been visited yet
        			visited[nextId] = true;
        			bfsQueue.offer(nextId);
        			parent[nextId] = headId;
        		}
        		if (nextId == endId) {
        			Stack<Integer> backtracking = new Stack<>(); // for backtracking
        			int pathNode = endId;
        			while (pathNode != startId) {
        				backtracking.push(pathNode);
        				pathNode = parent[pathNode];
        			}
        			while (!backtracking.isEmpty()) {
        				shortestRoute.add(backtracking.pop());
        			}
            		return shortestRoute;
            	}
            }
        }
        return null;
    }

	/**
	 * Convert object to int.
	 * @param obj An java object
	 * @return The parsed integer
	 */
    private int toInt(Object obj) {
    	return Integer.parseInt(obj.toString());
    }
}
