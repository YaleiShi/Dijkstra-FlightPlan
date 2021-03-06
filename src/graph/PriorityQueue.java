package graph;

/** A priority queue: implemented using a min heap.
 *  You may not use any Java built-in classes, you should implement
 *  PriorityQueue yourself. You may use/modify the MinHeap code posted
 *  by the instructor under Examples, as long as you understand it. */
public class PriorityQueue {
	Priority[] minPri;
	int count;
	int[] map;

	public PriorityQueue(int size){
		minPri = new Priority[size + 1];
		minPri[0] = new Priority(-1, Integer.MIN_VALUE);
		count = 1;
		map = new int[size];
	}

	/** Insert a new element (nodeId, priority) into the heap.
     *  For this project, the priority is the current "distance"
     *  for this nodeId in Dikstra's algorithm. */
	public void insert(int nodeId, int priority) {
		// FILL IN CODE
		int index = count;
		map[nodeId] = index;
		minPri[index] = new Priority(nodeId, priority);
		count++;
	}

    /**
     * Remove the element with the minimum priority
     * from the min heap and return its nodeId.
     * @return nodeId of the element with the smallest priority
     */
	public int removeMin() {
		// FILL IN CODE
		int id = minPri[1].getId();
		swap(1, count - 1);
		count--;
		pushDown(1);
		return id; // don't forget to change it
	}

    /**
     * Reduce the priority of the element with the given nodeId to newPriority.
     * You may assume newPriority is less or equal to the current priority for this node.
     * @param nodeId id of the node
     * @param newPriority new value of priority
     */
	public void reduceKey(int nodeId, int newPriority) {
		int index = map[nodeId];
		minPri[index].setPriority(newPriority);
		pushUp(index);
	}

	/**
	 * push the element in the index position up to the right place
	 * finally build a min heap
	 * @param index the position of the element
	 */
	public void pushUp(int index){
		int child = index;
		int father = child / 2;
		while(minPri[child].getPriority() < minPri[father].getPriority()){
			swap(child, father);
			child = father;
			father = child / 2;
		}
	}

	/**
	 * push down the elment to the right place
	 * if one of the children is smaller than the parent, swap them and keep going
	 * @param index the position of the element
	 */
	public void pushDown(int index){
		int father = index;
		while(!isLeaf(father)){
			int smallestChild = father * 2;
			if(smallestChild + 1 < count && minPri[smallestChild].getPriority() > minPri[smallestChild + 1].getPriority()){
				smallestChild = smallestChild + 1;
			}
			if(minPri[father].getPriority() < minPri[smallestChild].getPriority()){
				return;
			}
			swap(father, smallestChild);
			father = smallestChild;
		}

	}

	/**
	 * swap the position of the two element and change the
	 * record in the position array
	 * @param i the first element position
	 * @param j the second element position
	 */
	public void swap(int i, int j){
		Priority pi = minPri[i];
		Priority pj = minPri[j];

		int idi = pi.getId();
		int idj = pj.getId();

		map[idi] = j;
		map[idj] = i;

		minPri[i] = pj;
		minPri[j] = pi;
	}

	/**
	 * return true if the the element in the position is a leaf
	 * @param index the position of the element
	 * @return true if is a leaf, false if not
	 */
	public boolean isLeaf(int index){
		return index > ((count - 1) / 2) && index < count;
	}

	/**
	 * return true if the number of element is only 2
	 * @return true or false
	 */
	public boolean isEmpty(){
		return count == 2;
	}

	/**
	 * print out the priority queue
	 */
	public void print(){
		for(Priority p: minPri){
			System.out.print(p.getId() + "|" + p.getPriority() + ", ");
		}
	}

}

