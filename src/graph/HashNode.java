package graph;

public class HashNode {
    private int id;
    private String city;
    private HashNode next;

    public HashNode(int id, String city, HashNode next){
        this.id = id;
        this.city = city;
        this.next = next;
    }

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
