package resources;

import java.util.concurrent.PriorityBlockingQueue;

import models.Order;

public class OrderPackagingService extends PipelineThreadPool{

    public OrderPackagingService(PriorityBlockingQueue<Order> toPackage, PriorityBlockingQueue<Order> toDeliver, int threadAmount) {
        super(toPackage, toDeliver, threadAmount);
    }


    
}
