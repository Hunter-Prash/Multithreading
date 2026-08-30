package miniProject_2;

import java.util.concurrent.BlockingQueue;

public class JobConsumer implements Runnable{
    BlockingQueue<Integer> queue;

    public JobConsumer(BlockingQueue<Integer>queue){
        this.queue=queue;
    }
    @Override
    public void run(){

    }
}
