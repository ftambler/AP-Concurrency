package runnables;

import models.Order;
import models.Status;

//Tercer paso
public class ProcessDelivery implements Runnable {
 
    private Order order;

    public ProcessDelivery(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        System.out.println("        Processing Delivery for " + order.getOwner());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        order.setStatus( Status.getNext(order.getStatus()) );


        System.out.println("        Order Delivered!");
    }
    
}