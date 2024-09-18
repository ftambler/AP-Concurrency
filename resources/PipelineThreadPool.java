package resources;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import runnables.ProcessDelivery;
import runnables.ProcessPackaging;
import runnables.ProcessPayment;


public class PipelineThreadPool extends Thread{
    
    private Queue<Order> inQueue;
    private Queue<Order> outQueue;
    private ExecutorService executor; 

    public PipelineThreadPool(Queue<Order> inQueue, Queue<Order> outQueue, int threadAmount){
        this.inQueue = inQueue;
        this.outQueue = outQueue;
        executor = Executors.newFixedThreadPool(threadAmount);
    }

    @Override
    public void run() {
        while (true) {
            while (!inQueue.isEmpty()) {
                try {
                    runInputQueueOrder(inQueue.remove());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void runInputQueueOrder(Order currentOrder) throws InterruptedException{
        Runnable activity;


        switch (currentOrder.getStatus()) {
            case TOPROCESS:
                activity = new ProcessPayment(currentOrder);
                break;
            case TOPACKAGE:
                activity = new ProcessPackaging(currentOrder);
                break;    
            case TODELIVER:
                activity = new ProcessDelivery(currentOrder);
                break;
            default:
                activity = null;
                break;
        }
        
        Status oldStatus = currentOrder.getStatus();

        executor.execute(activity);
        
        while(currentOrder.getStatus() == oldStatus){
            wait(100);
        }

        outQueue.add( currentOrder );
    }

}
