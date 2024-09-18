package resources;

public class Order {
    private String owner;
    private double cost;
    private Status status;
    private int priority;
    
    public Order(double cost, int priority, String owner) {
        this.cost = cost;
        this.status = Status.TOPROCESS;
        this.priority = priority;
        this.owner = owner;
    }

    public double getCost() {
        return cost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public String getOwner() {
        return owner;
    }
   
}
