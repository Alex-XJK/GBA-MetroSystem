package MetroSystem.src.metroSystem;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class AlgDijkstra implements Algorithm{
    @Override
    public ArrayList<Integer> findRoute(int startId, int endId, DataList data) {
        // Implement Dijkstra algorithm
    	// Initialize
        ArrayList<Integer> shortestRoute = new ArrayList<>();
        PriorityQueue<NodeEntry> dijkstraQueue = new PriorityQueue<NodeEntry>();
        int size = data.getSize() + 1;
        int parent[] = new int[size]; // ´ý¶¨
        boolean visited[] = new boolean[size];
        for (int i=0; i<size; i++) {
        	visited[i] = false;
        	parent[i] = -1;
        }
        
        shortestRoute.add(startId);
        visited[startId] = true;
        for (NodeEntry e : data.getNeighbors(startId)) {
        	dijkstraQueue.offer(e); // for the next possible nodes
        	parent[toInt(e.getKey())] = startId; // ´ý¶¨
        }
        
        while (!dijkstraQueue.isEmpty()) {
        	NodeEntry head = dijkstraQueue.poll(); // the shortest edge
        	int headId = toInt(head.getKey()); 
        	if (!visited[headId]) { // if haven't been visited yet
    			visited[headId] = true;
    			for (NodeEntry e : data.getNeighbors(headId)) {
    	        	dijkstraQueue.offer(e);
    	        }
    		} else {
    			continue;
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
