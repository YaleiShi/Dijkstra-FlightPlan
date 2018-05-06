package graph;

/**
 * the priority used in the priorityQueue
 * store the id of the city and the current cost of the city
 */
public class Priority {
    private int id;
    private int priority;

    /**
     * the constructor
     * @param id the city id
     * @param priority the current smallest cost from the original city to this city
     */
    public Priority(int id, int priority){
        this.id = id;
        this.priority = priority;
    }

    /**
     * get the city id
     * @return city id
     */
    public int getId() {
        return id;
    }

    /**
     * set the city id
     * @param id city id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get the current cost of the city
     * @return the integer stands for the city cost
     */
    public int getPriority() {
        return priority;
    }

    /**
     * set the priority which is the cost to a new priority
     * @param priority the new priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
