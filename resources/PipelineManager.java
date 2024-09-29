package resources;

import java.util.concurrent.PriorityBlockingQueue;

import models.Order;

public class PipelineManager {
    
    private PriorityBlockingQueue<Order> toProcess;
    private PriorityBlockingQueue<Order> toPackage;
    private PriorityBlockingQueue<Order> toDeliver;
    private PriorityBlockingQueue<Order> finalized;

    private PaymentProcessingService paymentProcessingService;
    private OrderPackagingService orderPackagingSerivice;
    private DeliveryService deliveryService;

    public PipelineManager(int paymentProcessThreads, int packagingProcessthreads, int deliveryProcessThreads){
        toProcess = new PriorityBlockingQueue<>();
        toPackage = new PriorityBlockingQueue<>();
        toDeliver = new PriorityBlockingQueue<>();
        finalized = new PriorityBlockingQueue<>();

        paymentProcessingService = new PaymentProcessingService(toProcess, toPackage, paymentProcessThreads);
        orderPackagingSerivice = new OrderPackagingService(toPackage, toDeliver, packagingProcessthreads);
        deliveryService = new DeliveryService(toDeliver, finalized, deliveryProcessThreads);
    }

    public void start(){
        paymentProcessingService.start();
        orderPackagingSerivice.start();
        deliveryService.start();
    }

    public boolean pushOrder(Order newOrder){
        try{
            toProcess.add(newOrder);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public void stop(){
        paymentProcessingService.getExecutor().shutdown();
        orderPackagingSerivice.getExecutor().shutdown();
        deliveryService.getExecutor().shutdown();
        System.out.println("Shutting down");
    }

}
