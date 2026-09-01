package miniProject_2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class JobProducer implements Runnable{
    BlockingQueue<Integer> queue;
    CountDownLatch latch;
    //constructor
    public JobProducer(BlockingQueue<Integer> queue,CountDownLatch latch){
        this.queue=queue;
        this.latch=latch;
    }

    @Override
    public void run(){
        for(int i=1;i<=10;i++){
            try {
                Job j=new Job(i);
                Random rand=new Random();
                queue.put(rand.nextInt(5)+1);
                System.out.println("Produced: " + j.getId());
                if(i==10){
                    for(int x=1;x<=4;x++){
                        queue.put(-1);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        latch.countDown();
    }
}
