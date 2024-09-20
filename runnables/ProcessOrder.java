package runnables;

import models.Order;
import models.Status;

public class ProcessOrder implements Runnable{

    private Order order;

    public ProcessOrder(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getOrder().setStatus( Status.getNext( getOrder().getStatus()) );


    }

    public Order getOrder() {
        return order;
    }
    
}
