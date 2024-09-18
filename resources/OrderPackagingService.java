package resources;

import java.util.Queue;

public class OrderPackagingService extends PipelineThreadPool{

    public OrderPackagingService(Queue<Order> toPackage, Queue<Order> toDeliver, int threadAmount) {
        super(toPackage, toDeliver, threadAmount);
    }


    
}
