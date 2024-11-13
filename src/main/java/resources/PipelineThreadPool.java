package resources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import models.Order;


public class PipelineThreadPool extends Thread{
    
    private PriorityBlockingQueue<Order> inQueue;
    private PriorityBlockingQueue<Order> outQueue;
    private ExecutorService executor; 

    private boolean isRunning = false;
    private boolean finished = false;
    private Object monitor;

    
    public PipelineThreadPool(PriorityBlockingQueue<Order> inQueue, PriorityBlockingQueue<Order> outQueue, int threadAmount){
        this.inQueue = inQueue;
        this.outQueue = outQueue;
        executor = Executors.newFixedThreadPool(threadAmount);
        monitor = new Object();
    }


    @Override
    public void run() {
        this.isRunning = true;
        runInputQueue();
    }

    /**
     * Consumes from input queue until it's empty
     * If it is empty and the thread has finished the monitor will be released
     */
    public void runInputQueue(){
        while (isRunning) {
            if (!inQueue.isEmpty()) {
                runOrder(inQueue.remove());
            }
        }
        
        while (!finished)

        synchronized(this.monitor){
            this.monitor.notify();
        }
    }

    /**
     * Run next process depending on order state
     * @param currentOrder Current order being processed
     */
    public void runOrder(Order currentOrder){
        Thread activity = Order.createOrderThread(currentOrder, outQueue);
        executor.submit(activity);
    }
    
    public void requestStop() {
        this.isRunning = false;
        //Finish Emptying Queue
        while (!inQueue.isEmpty()) {
            runOrder(inQueue.remove());
        }

        executor.shutdown();
        try {
            executor.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {              
            executor.shutdownNow();
        }
        
        this.finished = true;
    }

    public Object getMonitor() {
        return monitor;
    }
    
}
