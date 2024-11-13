package models;

import java.util.concurrent.PriorityBlockingQueue;

import runnables.ProcessDelivery;
import runnables.ProcessOrder;
import runnables.ProcessPackaging;
import runnables.ProcessPayment;

public class Order implements Comparable<Order>{
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

    @Override
    public int compareTo(Order o) {
        return Integer.compare(this.priority, o.getPriority());
    }
   
    public static ProcessOrder createOrderThread(Order currentOrder, PriorityBlockingQueue<Order> outQueue){
        switch (currentOrder.getStatus()) {
            case TOPROCESS:
                return new ProcessPayment(currentOrder, outQueue);

            case TOPACKAGE:
                return new ProcessPackaging(currentOrder, outQueue);

            case TODELIVER:
                return new ProcessDelivery(currentOrder, outQueue);
            
            default:
                return null;
                
        }
    }
}
