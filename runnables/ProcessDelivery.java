package runnables;

import java.util.concurrent.PriorityBlockingQueue;

import models.Order;

//Tercer paso
public class ProcessDelivery extends ProcessOrder {

    public ProcessDelivery(Order order, PriorityBlockingQueue<Order> outQueue) {
        super(order, outQueue);
    }

    @Override
    public void run() {
        System.out.println("\t\tProcessing Delivery for " + getOrder().getOwner());

        super.run();

        System.out.println("\t\t » Order Delivered to " + getOrder().getOwner());
    }
    
}