package metroSystem;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Implements {@link metroSystem.Algorithm}, and use Dijkstra's Algorithm to search for the route.
 * @see <a href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">Dijkstra's Algorithm</a>
 * @version 1.0
 */
public class AlgDijkstra implements Algorithm{

	/**
	 * {@inheritDoc}.
	 * <br>
	 * Use Dijkstra's Algorithm to search for the least cost path.
	 */
    @Override
    public ArrayList<Integer> findRoute(int startId, int endId, DataList data) {
        // Implement Dijkstra algorithm
    	// Initialize
        ArrayList<Integer> shortestRoute = new ArrayList<>();
        PriorityQueue<NodeEntry> dijkstraQueue = new PriorityQueue<NodeEntry>();
        int size = data.getSize() + 1;
        int parent[] = new int[size];
        boolean visited[] = new boolean[size];
        for (int i=0; i<size; i++) {
        	visited[i] = false;
        	parent[i] = -1;
        }
        
        shortestRoute.add(startId);
        visited[startId] = true;
        for (NodeEntry e : data.getNeighbors(startId)) {
        	dijkstraQueue.offer(e); // for the next possible nodes
        }
        
        while (!dijkstraQueue.isEmpty()) {
        	NodeEntry head = dijkstraQueue.poll(); // the shortest edge
        	int headId = toInt(((ArrayList) head.getKey()).get(1)); 
    		visited[headId] = true;
    		parent[headId] = toInt(((ArrayList) head.getKey()).get(0)); 
    		for (NodeEntry e : data.getNeighbors(headId)) {
    			if (!visited[toInt(((ArrayList) e.getKey()).get(1))]) { // if haven't been visited yet
    	        dijkstraQueue.offer(e);
    	        } 
    		}
        	if (headId == endId) {
        		Stack<Integer> backtracking = new Stack<Integer>(); // for backtracking
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
        return null;
    }
    
    // Convert object to integer
    private int toInt(Object obj) {
    	return Integer.parseInt(obj.toString());
    }
}
