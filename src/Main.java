import java.util.Scanner;

import models.Order;
import resources.PipelineManager;

public class Main{
    public static void main(String[] args) {
        
        //Thread amounts
        int paymentProcessThreads = 1;
        int packagingProcessthreads = 1;
        int deliveryProcessThreads = 1;
        
        //Initialize Manager and Services
        PipelineManager pipelineManager = new PipelineManager(paymentProcessThreads,packagingProcessthreads,deliveryProcessThreads);
        pipelineManager.start();
        
        //Check Stop Thread
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            boolean running = true;

            while(running){
                String input = sc.nextLine();
    
                if(input.toLowerCase().equals("s") ){
                    System.out.println("STOPPING");
                    
                    pipelineManager.stop();
                    running = false;
                    
                    System.out.println("STOPPED SUCCESSFULLY");
                    sc.close();
                }
            }
        }).start();
        
        //Submit Orders
        for (int i = 0; i < 10; i++) {
            try {
                boolean pushedOrder = pipelineManager.pushOrder(new Order(100, 1, String.valueOf(i) ));
                if(!pushedOrder) break;

                Thread.sleep(500);
            } catch (InterruptedException e){}
        }
        
        
    }
}