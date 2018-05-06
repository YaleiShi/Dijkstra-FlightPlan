package graph;

/** Custom implementation of a hash table using open hashing, separate chaining.
 *  Each key is a String (name of the city), each value is an integer (node id). */
public class HashTable {
	 // FILL IN CODE: add variables and methods
    HashNode[] nodes;
    final int A = 33;
    int size; /* the table size */

    /**
     * the constructor
     * @param size
     */
    public HashTable(int size){
        this.size = size;
        nodes = new HashNode[size];
    }

    /**
     * insert the node into the table
     * @param node
     */
    public void insert(HashNode node){
        int index = hash(node.getCity());
        HashNode head = nodes[index];
        node.setNext(head);
        nodes[index] = node;
    }

    /**
     * get the id by the name of the city
     * @param s the name of the city
     * @return the id of the city
     */
    public int getId(String s){
        int index = hash(s);
        HashNode cur = nodes[index];
        while(cur != null){
            if(cur.getCity().equals(s)){
                return cur.getid();
            }
            cur = cur.getNext();
        }
        return -1;
    }

    /**
     * give the index of the city by computing the name of the city
     * @param s the name of the city
     * @return the index of the city in the table
     */
    public int hash(String s) {
        int i;
        long r = 0;
        char c;
        for (i = 0; i < s.length(); i++)
        {
            c = s.charAt(i);
            r = (int) c + A*r;
        }
        return (int) (r % size);
    }

}