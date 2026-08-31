package miniProject_2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class JobConsumer implements Runnable{
    BlockingQueue<Integer> queue;
    CountDownLatch latch;
    SharedDB s;
    public JobConsumer(BlockingQueue<Integer>queue,CountDownLatch latch,SharedDB s){
        this.queue=queue;
        this.s=s;
        this.latch=latch;
    }

    @Override
    public void run(){
        while (true){
            try {
                int value=queue.take();
                if(value==-1)break;
                s.saveJob(value);
                System.out.println("Consumed: " + value);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        latch.countDown();
    }
}
