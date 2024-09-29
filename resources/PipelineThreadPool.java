package resources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import models.Order;


public class PipelineThreadPool extends Thread{
    
    private PriorityBlockingQueue<Order> inQueue;
    private PriorityBlockingQueue<Order> outQueue;
    private ExecutorService executor; 

    private boolean isRunning = false;

    public PipelineThreadPool(PriorityBlockingQueue<Order> inQueue, PriorityBlockingQueue<Order> outQueue, int threadAmount){
        this.inQueue = inQueue;
        this.outQueue = outQueue;
        executor = Executors.newFixedThreadPool(threadAmount);
    }

    @Override
    public void run() {
        this.isRunning = true;
        
        while (isRunning) {
            if (!inQueue.isEmpty()) {
                try {
                    runInputQueueOrder(inQueue.remove());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void runInputQueueOrder(Order currentOrder) throws InterruptedException{
        Thread activity = Order.createOrder(currentOrder, outQueue);
        
        executor.submit(activity);

    }

    public ExecutorService getExecutor() {
        return executor;
    }

}
