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
		int[] toVisit = new int[size];
		int[] currentDis = new int[size]; // the total distance between start station and current station
        for (int i=0; i<size; i++) {
        	parent[i] = -1;
			toVisit[i] = 1000000;
			currentDis[i] = 1000000;
        }
        
        shortestRoute.add(startId);
		toVisit[startId] = 0;
		currentDis[startId] = 0;
        for (NodeEntry e : data.getNeighbors(startId)) {
			NodeEntry<Object, Object> adjacent = new NodeEntry<>(e.getKey(), toInt(e.getValue())+currentDis[startId]);
        	dijkstraQueue.offer(adjacent); // for the next possible nodes
        }
        
        while (!dijkstraQueue.isEmpty()) {
			/*NodeEntry head = dijkstraQueue.poll();
			System.out.println("key-->");
			System.out.println(head.getKey());
			System.out.println("value-->");
			System.out.println(head.getValue());*/
        	NodeEntry head = dijkstraQueue.poll(); // the shortest path so far
//			System.out.println(head.getValue());
        	int headId = toInt(((ArrayList) head.getKey()).get(1));
			if (currentDis[headId] != 1000000 || toVisit[headId] < toInt(head.getValue())) { // the node that has been visited || the node that has been abandoned
				continue;
			}
    		parent[headId] = toInt(((ArrayList) head.getKey()).get(0));
			currentDis[headId] = toInt(head.getValue()); // mark visited and record the distance between start and current one
    		for (NodeEntry e : data.getNeighbors(headId)) {
				int nextId = toInt(((ArrayList) e.getKey()).get(1));
				if (currentDis[nextId] == 1000000 && // not been visited yet && the value need to be updated
						toVisit[nextId] > currentDis[headId] + toInt(e.getValue())) {
					NodeEntry<Object, Object> adjacent = new NodeEntry<>(e.getKey(), toInt(e.getValue()) + currentDis[headId]);
					dijkstraQueue.offer(adjacent);
					toVisit[nextId] = currentDis[headId]+toInt(e.getValue());
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
