package graph;

/** Custom implementation of a hash table using open hashing, separate chaining.
 *  Each key is a String (name of the city), each value is an integer (node id). */
public class HashTable {
	 // FILL IN CODE: add variables and methods
    HashNode[] nodes;
    final int A = 33;
    int size;

    public HashTable(int size){
        this.size = size;
        nodes = new HashNode[size];
    }

    public void insert(HashNode node){
        int index = hash(node.getCity());
        HashNode head = nodes[index];
        if(head == null){
            head = node;
            return;
        }
        node.setNext(head);
        head = node;
    }

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

    public int hash(String s) {
        int i;
        int r = 0;
        char c;
        for (i = 0; i < s.length(); i++)
        {
            c = s.charAt(i);
            r = (int) c + A*r;
        }
        return r % size;
    }

}