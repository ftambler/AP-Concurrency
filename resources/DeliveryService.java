package resources;

import java.util.concurrent.PriorityBlockingQueue;

import models.Order;

public class DeliveryService extends PipelineThreadPool {

    public DeliveryService(PriorityBlockingQueue<Order> toDeliver, PriorityBlockingQueue<Order> finalized, int threadAmount) {
        super(toDeliver, finalized, threadAmount);
        
    }
    
}
