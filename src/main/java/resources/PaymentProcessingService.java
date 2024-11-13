package resources;

import java.util.concurrent.PriorityBlockingQueue;

import models.Order;

public class PaymentProcessingService extends PipelineThreadPool{
    
    public PaymentProcessingService(PriorityBlockingQueue<Order> toProcess, PriorityBlockingQueue<Order> toPackage, int threadAmount){
        super(toProcess, toPackage, threadAmount);
    }

}
