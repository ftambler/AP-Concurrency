package runnables;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

import models.Order;
import models.Status;

public class ProcessOrder extends Thread{

    private Order order;
    private PriorityBlockingQueue<Order> outQueue;

    public ProcessOrder(Order order, PriorityBlockingQueue<Order> outQueue) {
        this.order = order;
        this.outQueue = outQueue;
    }

    @Override
    public void run() {
        try {
            Random rnd =  new Random();
            Thread.sleep(rnd.nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.order.setStatus( Status.getNext( this.order.getStatus()) );

        outQueue.add(order);

    }

    public Order getOrder() {
        return order;
    }
    
}
