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
        for(int i=1;i<=100;i++){
            try {
                Job j=new Job(i);
                queue.put(i);
                System.out.println("Produced: " + j.getId());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
