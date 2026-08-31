package miniProject_2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class JobProducer implements Runnable{
    BlockingQueue<Integer> queue;

    //constructor
    public JobProducer(BlockingQueue<Integer> queue){
        this.queue=queue;
    }

    @Override
    public void run(){
        for(int i=1;i<=20;i++){
            try {
                Job j=new Job(i);
                queue.put(i);
                System.out.println("Produced: " + j.getId());
                if(i==20){
                    for(int x=1;x<=4;x++){
                        queue.put(-1);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
