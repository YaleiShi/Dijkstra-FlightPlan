package graph;

/**
 * A class that represents a graph where nodes are cities (of type CityNode)
 * and edges connect them and the cost of each edge is the distance between
 * the cities.
 * Fill in code in this class. You may add additional methods and variables.
 * You are required to implement a HashTable and a PriorityQueue from scratch.
 */
import java.util.*;
import java.io.*;
import java.awt.Point;

public class Graph {
    public final int EPS_DIST = 5;

    private CityNode[] nodes; // nodes of the graph
	private int numNodes;     // total number of nodes
	private int numEdges; // total number of edges
	private Edge[] adjacencyList; // adjacency list; for each vertex stores a linked list of edges
    // Your HashTable that maps city names to node ids should probably be here as well
	private  HashTable table;
	/**
	 * Read graph info from the given file, and create nodes and edges of
	 * the graph.
	 *
	 * @param filename name of the file that has nodes and edges
	 */
	public void loadGraph(String filename) {
		// FILL IN CODE
		numNodes = 0;
		numEdges = 0;
		try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
			String line = reader.readLine();
			line = reader.readLine();
			int size = Integer.parseInt(line);
			nodes = new CityNode[size];
			adjacencyList = new Edge[size];
			table = new HashTable(size);
			line = reader.readLine();
			while(!line.equals("ARCS")){
				String[] ss = line.split(" ");
				String name = ss[0];
				Double x = Double.parseDouble(ss[1]);
				Double y = Double.parseDouble(ss[2]);
				CityNode cn = new CityNode(name, x, y);
				addNode(cn);
				line = reader.readLine();
			}
			line = reader.readLine();
			while(line != null){
				String[] ss = line.split(" ");
				int id1 = table.getId(ss[0]);
				int id2 = table.getId(ss[1]);
				int cost = Integer.parseInt(ss[2]);
				Edge edge1 = new Edge(id2, cost, null);
				Edge edge2 = new Edge(id1, cost, null);
				addEdge(id1, edge1);
				addEdge(id2, edge2);
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Add a node to the array of nodes.
	 * Increment numNodes variable.
     * Called from loadGraph.
	 *
	 * @param node a CityNode to add to the graph
	 */
	public void addNode(CityNode node) {
		// FILL IN CODE
		nodes[numNodes] = node;
		String city = node.getCity();
		int id = numNodes;
		HashNode hn = new HashNode(id, city, null);
//		System.out.println("id: " + id + " city: " + city + " is added");
		table.insert(hn);
		numNodes++;
	}

	/**
	 * Return the number of nodes in the graph
	 * @return number of nodes
	 */
	public int numNodes() {
		return numNodes;
	}

	/**
	 * Adds the edge to the linked list for the given nodeId
	 * Called from loadGraph.
     *
	 * @param nodeId id of the node
	 * @param edge edge to add
	 */
	public void addEdge(int nodeId, Edge edge) {
		// FILL IN CODE
//		System.out.println(adjacencyList.length);
//		System.out.println(nodeId);
		Edge head = adjacencyList[nodeId];
		edge.setNext(head);
		adjacencyList[nodeId] = edge;
		numEdges++;
	}

	/**
	 * Returns an integer id of the given city node
	 * @param city node of the graph
	 * @return its integer id
	 */
	public int getId(CityNode city) {
		String name = city.getCity();
		int id = table.getId(name);
        return id; // Don't forget to change this
    }

	public Edge[] getAdjacencyList() {
		return adjacencyList;
	}

	/**
	 * Return the edges of the graph as a 2D array of points.
	 * Called from GUIApp to display the edges of the graph.
	 *
	 * @return a 2D array of Points.
	 * For each edge, we store an array of two Points, v1 and v2.
	 * v1 is the source vertex for this edge, v2 is the destination vertex.
	 * This info can be obtained from the adjacency list
	 */
	public Point[][] getEdges() {
		int i = 0;
		Point[][] edges2D = new Point[numEdges][2];
		// FILL IN CODE
		for(int j = 0; j < numNodes; j++){
			Point src = nodes[j].getLocation();
			Edge edge = adjacencyList[j];
			while(edge != null){
				int id = edge.getNeighbor();
				Point dest = nodes[id].getLocation();
				edges2D[i][0] = src;
				edges2D[i][1] = dest;
				i++;
				edge = edge.getNext();
			}
		}
		return edges2D;
	}

	/**
	 * Get the nodes of the graph as a 1D array of Points.
	 * Used in GUIApp to display the nodes of the graph.
	 * @return a list of Points that correspond to nodes of the graph.
	 */
	public Point[] getNodes() {
	    if (this.nodes == null) {
            System.out.println("Graph has no nodes. Write loadGraph method first. ");
            return null;
        }
		Point[] pnodes = new Point[this.nodes.length];
		// FILL IN CODE
		for(int i = 0; i < numNodes; i++){
			pnodes[i] = nodes[i].getLocation();
		}

		return pnodes;
	}

	/**
	 * Used in GUIApp to display the names of the airports.
	 * @return the list that contains the names of cities (that correspond
	 * to the nodes of the graph)
	 */
	public String[] getCities() {
        if (this.nodes == null) {
            System.out.println("Graph has no nodes. Write loadGraph method first. ");
            return null;
        }
		String[] labels = new String[nodes.length];
		// FILL IN CODE
		for(int i = 0; i < numNodes; i++){
			labels[i] = nodes[i].getCity();
		}

		return labels;

	}

	/** Take a list of node ids on the path and return an array where each
	 * element contains two points (an edge between two consecutive nodes)
	 * @param pathOfNodes A list of node ids on the path
	 * @return array where each element is an array of 2 points
	 */
	public Point[][] getPath(List<Integer> pathOfNodes) {
		int i = 0;
		Point[][] edges2D = new Point[pathOfNodes.size()-1][2];
        // Each "edge" is an array of size two (one Point is origin, one Point is destination)
        // FILL IN CODE
		for(i = 0; i < pathOfNodes.size() - 1; i++){
			edges2D[i][0] = nodes[pathOfNodes.get(i)].getLocation();
			edges2D[i][1] = nodes[pathOfNodes.get(i + 1)].getLocation();
		}
        return edges2D;
	}

	/**
	 * Return the CityNode for the given nodeId
	 * @param nodeId id of the node
	 * @return CityNode
	 */
	public CityNode getNode(int nodeId) {
		return nodes[nodeId];
	}

	/**
	 * Take the location of the mouse click as a parameter, and return the node
	 * of the graph at this location. Needed in GUIApp class.
	 * @param loc the location of the mouse click
	 * @return reference to the corresponding CityNode
	 */
	public CityNode getNode(Point loc) {
		for (CityNode v : nodes) {
			Point p = v.getLocation();
			if ((Math.abs(loc.x - p.x) < EPS_DIST) && (Math.abs(loc.y - p.y) < EPS_DIST))
				return v;
		}
		return null;
	}
}