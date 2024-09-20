package runnables;

import models.Order;

//Primer paso
public class ProcessPayment extends ProcessOrder {

    public ProcessPayment(Order order) {
        super(order);
    }

    @Override
    public void run() {
        System.out.println("Processing Payment for " + getOrder().getOwner());

        super.run();

        System.out.println("    Payment Success!");
    }
    
}
