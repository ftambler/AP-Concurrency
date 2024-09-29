package runnables;

import java.util.concurrent.PriorityBlockingQueue;

import models.Order;

//Primer paso
public class ProcessPayment extends ProcessOrder {

    public ProcessPayment(Order order, PriorityBlockingQueue<Order> outQueue) {
        super(order, outQueue);
    }

    @Override
    public void run() {
        System.out.println("Processing Payment for " + getOrder().getOwner());

        super.run();

        System.out.println(" » Payment Success on " + getOrder().getOwner());
    }
    
}
