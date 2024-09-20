package runnables;

import models.Order;
import models.Status;

//Primer paso
public class ProcessPayment implements Runnable {

    private Order order;

    public ProcessPayment(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        System.out.println("Processing Payment for " + order.getOwner());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        order.setStatus( Status.getNext(order.getStatus()) );

        System.out.println("Payment Success!");
    }
    
}
