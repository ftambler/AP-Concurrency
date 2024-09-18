package resources;

import java.util.Queue;

public class DeliveryService extends PipelineThreadPool {

    public DeliveryService(Queue<Order> toDeliver, Queue<Order> finalized, int threadAmount) {
        super(toDeliver, finalized, threadAmount);
        
    }
    
}
