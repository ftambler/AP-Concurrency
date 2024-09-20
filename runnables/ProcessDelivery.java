package runnables;

import models.Order;

//Tercer paso
public class ProcessDelivery extends ProcessOrder {

    public ProcessDelivery(Order order) {
        super(order);
    }

    @Override
    public void run() {
        System.out.println("        Processing Delivery for " + getOrder().getOwner());

        super.run();

        System.out.println("        Order Delivered!");
    }
    
}