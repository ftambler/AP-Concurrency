package runnables;

import java.util.concurrent.PriorityBlockingQueue;

import models.Order;

//Segundo paso
public class ProcessPackaging extends ProcessOrder {

    public ProcessPackaging(Order order, PriorityBlockingQueue<Order> outQueue) {
        super(order, outQueue);
    }

    @Override
    public void run() {
        System.out.println("\tPackaging order for " + getOrder().getOwner());

        super.run();

        System.out.println("\t » Finished Packaging!");
    }
    
}
