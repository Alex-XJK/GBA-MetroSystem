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
        PriorityQueue<NodeEntry> dijkstraQueue = new PriorityQueue<>();
        int size = data.getSize() + 1;
        int[] parent = new int[size];
		int[] currentDis = new int[size]; // the total distance between start station and current station
        for (int i=0; i<size; i++) {
        	parent[i] = -1;
			currentDis[i] = 1000000;
        }
        
        shortestRoute.add(startId);
		currentDis[startId] = 0;
        for (NodeEntry e : data.getNeighbors(startId)) {
			NodeEntry<Object, Object> adjacent = new NodeEntry<>(e.getKey(), toInt(e.getValue())+currentDis[startId]);
        	dijkstraQueue.offer(adjacent); // for the next possible nodes
        }
        
        while (!dijkstraQueue.isEmpty()) {
        	NodeEntry head = dijkstraQueue.poll(); // the shortest path so far
        	int headId = toInt(((ArrayList) head.getKey()).get(1)); 
    		parent[headId] = toInt(((ArrayList) head.getKey()).get(0));
			currentDis[headId] = toInt(head.getValue());
    		for (NodeEntry e : data.getNeighbors(headId)) {
    			if (currentDis[toInt(((ArrayList) e.getKey()).get(1))] > currentDis[headId]+toInt(e.getValue())) {
    	        	dijkstraQueue.offer(e);
    	        } 
    		}
        	if (headId == endId) {
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
        return null;
    }
    
    // Convert object to int
    private int toInt(Object obj) {
    	return Integer.parseInt(obj.toString());
    }
}
