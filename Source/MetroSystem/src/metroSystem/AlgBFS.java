package metroSystem;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class AlgBFS implements Algorithm{

    @Override
    public ArrayList<Integer> findRoute(int startId, int endId, DataList data) {
        // Implement BFS algorithm
    	// Initialize
        ArrayList<Integer> shortestRoute = new ArrayList<>();
        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        int size = data.getSize() + 1;
        int parent[] = new int[size];
        boolean visited[] = new boolean[size];
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
        		if (!visited[nextId]) { // if haven't been visited yet
        			visited[nextId] = true;
        			bfsQueue.offer(nextId);
        			parent[nextId] = headId;
        		}
        		if (nextId == endId) {
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
        }
        return null;
    }
    
    // Convert object to integer
    private int toInt(Object obj) {
    	return Integer.parseInt(obj.toString());
    }
}
