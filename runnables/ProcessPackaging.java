package runnables;

import resources.Order;
import resources.Status;

//Segundo paso
public class ProcessPackaging implements Runnable {

    private Order order;

    public ProcessPackaging(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        System.out.println("Packaging order for " + order.getOwner());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        order.setStatus( Status.getNext(order.getStatus()) );


        System.out.println("Finished Packaging!");
    }
    
}
