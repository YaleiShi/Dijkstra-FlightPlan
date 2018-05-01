package graph;

/** Class Dijkstra. Implementation of Dijkstra's
 *  algorithm on the graph for finding the shortest path.
 *  Fill in code. You may add additional helper methods or classes.
 */

import java.util.*;
import java.awt.Point;

public class Dijkstra {
	private Graph graph; // stores the graph of CityNode-s and edges connecting them
    private List<Integer> shortestPath = null; // nodes that are part of the shortest path

    /** Constructor
	 *
	 * @param filename name of the file that contains info about nodes and edges
     * @param graph graph
	 */
	public Dijkstra(String filename, Graph graph) {
	    this.graph = graph;
		graph.loadGraph(filename);
	}

	/**
	 * Returns the shortest path between the origin vertex and the destination vertex.
	 * The result is stored in shortestPathEdges.
	 * This function is called from GUIApp, when the user clicks on two cities.
	 * @param origin source node
	 * @param destination destination node
     * @return the ArrayList of nodeIds (of nodes on the shortest path)
	 */
	public List<Integer> computeShortestPath(CityNode origin, CityNode destination) {
		// FILL IN CODE
		int oriId = graph.getId(origin);
		int destId = graph.getId(destination);
		int size = graph.numNodes();
        // Create and initialize Dijkstra's table
        int[] cost = new int[size];
		int[] parent = new int[size];
		boolean[] known = new boolean[size];
        // Create and initialize a Priority Queue
		PriorityQueue pq = new PriorityQueue(graph.numNodes());
		Edge[] edges = graph.getAdjacencyList();

		for(int i = 0; i < size; i++) cost[i] = Integer.MAX_VALUE;
		for(int i = 0; i < size; i++) parent[i] = -1;
		for(int i = 0; i < size; i++) known[i] = false;
        for(int i = 0; i < size; i++) pq.insert(i, cost[i]);

        // Run Dijkstra
        pq.reduceKey(oriId, 0);
        cost[oriId] = 0;
//        System.out.println("oriId is " + oriId);
//        System.out.println("destId is " + destId);
        while(!pq.isEmpty()){
            int min = pq.removeMin();
            known[min] = true;
            int dist = cost[min];
//            System.out.println("----------------------------------------");
//            System.out.println("min is " + min + " cost is " + cost[min]);

            Edge e = edges[min];
            while(e != null){
//                System.out.println("the edge " + e.getNeighbor() + " is " + known[e.getNeighbor()]);
                if(!known[e.getNeighbor()] && dist + e.getCost() < cost[e.getNeighbor()]){
//                    System.out.println("the cost now is " + cost[e.getNeighbor()] + " the dist is " + (dist + e.getCost()));
                    cost[e.getNeighbor()] = dist + e.getCost();
                    parent[e.getNeighbor()] = min;
                    pq.reduceKey(e.getNeighbor(), dist + e.getCost());
                }
                e = e.getNext();
            }
//            System.out.println("*********Priority Queue***********");
//            pq.print();
//            System.out.println("*****************");
        }

//        System.out.println("****************table****************");
//        System.out.println("id " + " cost " + " parent " + " known");
//        for(int i = 0; i < size; i++){
//            System.out.println(i + "  " + cost[i] + "  " + parent[i] + "  " + known[i]);
//        }
        // Compute the nodes on the shortest path by "backtracking" using the table
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(destId);
//        System.out.print("the shortest path from " + oriId + "to " + destId + "is: " + destId);
        int father = parent[destId];
        while(father != -1){
            System.out.print(" " + father);
            array.add(father);
            father = parent[father];
        }
//        System.out.println();
        // The result should be in an instance variable called "shortestPath" and
        // should also be returned by the method
        Collections.reverse(array);
        shortestPath = array;
	    return shortestPath; // don't forget to change it
    }

    /**
     * Return the shortest path as a 2D array of Points.
     * Each element in the array is another array that has 2 Points:
     * these two points define the beginning and end of a line segment.
     * @return 2D array of points
     */
    public Point[][] getPath() {
        if (shortestPath == null)
            return null;
        return graph.getPath(shortestPath); // delegating this task to the Graph class
    }

    /** Set the shortestPath to null.
     *  Called when the user presses Reset button.
     */
    public void resetPath() {
        shortestPath = null;
    }

}