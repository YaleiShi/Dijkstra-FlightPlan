package graph;

/**
 * The hash node used in the hash table
 */
public class HashNode {
    private int id;
    private String city;
    private HashNode next;

    /**
     * the constructor
     * @param id the city id
     * @param city the city name
     * @param next the next node
     */
    public HashNode(int id, String city, HashNode next){
        this.id = id;
        this.city = city;
        this.next = next;
    }

    /**
     * the getters and setters
     * @return
     */
    public int getid() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public HashNode getNext() {
        return next;
    }

    public void setid(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNext(HashNode next) {
        this.next = next;
    }
}
