package resources;

import java.util.Queue;

public class PaymentProcessingService extends PipelineThreadPool{
    
    public PaymentProcessingService(Queue<Order> toProcess, Queue<Order> toPackage, int threadAmount){
        super(toProcess, toPackage, threadAmount);
    }

}
