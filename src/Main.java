import java.util.Scanner;

import models.Order;
import resources.PipelineManager;

public class Main{
    public static void main(String[] args) {
        
        //Thread amounts
        int paymentProcessThreads = 35;
        int packagingProcessThreads = 35;
        int deliveryProcessThreads = 35;
        
        //Initialize Manager and Services
        PipelineManager pipelineManager = new PipelineManager(paymentProcessThreads,packagingProcessThreads,deliveryProcessThreads);
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
        
        //Submit Orders N orders (Priority 0-9)
         int n = 100;
         for (int i = 0; i < n; i++) {
             boolean pushedOrder = pipelineManager.pushOrder(new Order(100, i % 10, String.valueOf(i) ));
         }

        //Submit Custom Orders
//        pipelineManager.pushOrder(new Order(100, 10, "Antonio" ));
//        pipelineManager.pushOrder(new Order(100, 7, "Belen" ));
//        pipelineManager.pushOrder(new Order(100, 1, "Carlos" ));
//        pipelineManager.pushOrder(new Order(100, 3, "Daniel" ));
//        pipelineManager.pushOrder(new Order(100, 4, "Estefano" ));
//        pipelineManager.pushOrder(new Order(100, 9, "Federico" ));
//        pipelineManager.pushOrder(new Order(100, 1, "Gustavo" ));
//        pipelineManager.pushOrder(new Order(100, 3, "Hector" ));
//        pipelineManager.pushOrder(new Order(100, 5, "Ignacio" ));
//        pipelineManager.pushOrder(new Order(100, 9, "Juan Jose" ));
        
    }
}