package resources;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import models.Order;

public class PipelineManager {
    
    private Queue<Order> toProcess;
    private Queue<Order> toPackage;
    private Queue<Order> toDeliver;
    private Queue<Order> finalized;

    private PaymentProcessingService paymentProcessingService;
    private OrderPackagingService orderPackagingSerivice;
    private DeliveryService deliveryService;

    public PipelineManager(int paymentProcessThreads, int packagingProcessthreads, int deliveryProcessThreads){
        toProcess = new ConcurrentLinkedQueue<>();
        toPackage = new ConcurrentLinkedQueue<>();
        toDeliver = new ConcurrentLinkedQueue<>();
        finalized = new ConcurrentLinkedQueue<>();

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
