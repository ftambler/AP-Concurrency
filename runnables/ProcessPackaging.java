package runnables;

import models.Order;

//Segundo paso
public class ProcessPackaging extends ProcessOrder {

    public ProcessPackaging(Order order) {
        super(order);
    }

    @Override
    public void run() {
        System.out.println("Packaging order for " + getOrder().getOwner());

        super.run();

        System.out.println("    Finished Packaging!");
    }
    
}
