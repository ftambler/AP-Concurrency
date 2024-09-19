import resources.Order;
import resources.PipelineManager;

public class Main{
    public static void main(String[] args) {

        //Thread amounts
        int paymentProcessThreads = 5;
        int packagingProcessthreads = 5;
        int deliveryProcessThreads = 5;

        PipelineManager pipelineManager = new PipelineManager(paymentProcessThreads,packagingProcessthreads,deliveryProcessThreads);
        pipelineManager.start();

        for (int i = 0; i < 10; i++) {
            pipelineManager.pushOrder(new Order(100, 1, String.valueOf(i) ));
        }
        
        // pipelineManager.pushOrder(new Order(100, 1, "jorge" ));
    
    
    }
}