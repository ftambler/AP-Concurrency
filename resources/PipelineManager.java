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

    private boolean isRunning;

    public PipelineManager(int paymentProcessThreads, int packagingProcessthreads, int deliveryProcessThreads){
        toProcess = new PriorityBlockingQueue<>();
        toPackage = new PriorityBlockingQueue<>();
        toDeliver = new PriorityBlockingQueue<>();
        finalized = new PriorityBlockingQueue<>();
        
        paymentProcessingService = new PaymentProcessingService(toProcess, toPackage, paymentProcessThreads);
        orderPackagingSerivice = new OrderPackagingService(toPackage, toDeliver, packagingProcessthreads);
        deliveryService = new DeliveryService(toDeliver, finalized, deliveryProcessThreads);
        
        isRunning = true;
    }
    
    public void start(){
        paymentProcessingService.start();
        orderPackagingSerivice.start();
        deliveryService.start();
    }
    
    public boolean pushOrder(Order newOrder){
        if(!isRunning){
            return false;
        }
        toProcess.add(newOrder);
        return true;        
    }
    
    public void stop(){
        this.isRunning = false;
        try {
            synchronized(paymentProcessingService.getMonitor()){
                paymentProcessingService.requestStop();
                paymentProcessingService.getMonitor().wait();
            }

            synchronized(orderPackagingSerivice.getMonitor()){
                orderPackagingSerivice.requestStop();
                orderPackagingSerivice.getMonitor().wait();
            }

            synchronized(deliveryService.getMonitor()){
                    deliveryService.requestStop();
                    deliveryService.getMonitor().wait();
            }
        } 
        catch (InterruptedException e) {
            System.err.println(e);
        }    
    }
}
