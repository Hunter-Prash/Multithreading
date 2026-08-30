package miniProject_2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    static void main() {

        //initialize the queue (shared state)
        BlockingQueue<Integer>queue=new LinkedBlockingQueue<>(100);

        ExecutorService executor= Executors.newFixedThreadPool(5);//1 producer 4 workers

        JobProducer jp=new JobProducer(queue);
        JobConsumer jc=new JobConsumer(queue);

        executor.execute(jp);

        for(int i=1;i<=4;i++){
            executor.execute(jc);
        }

        executor.shutdown();

    }
}
