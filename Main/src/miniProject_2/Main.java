package miniProject_2;

import java.util.concurrent.*;

public class Main {
    static void main() throws InterruptedException {

        //initialize the queue (shared state)
        BlockingQueue<Integer>queue=new LinkedBlockingQueue<>(20);
        ExecutorService executor= Executors.newFixedThreadPool(5);//1 producer 4 workers
        CountDownLatch latch = new CountDownLatch(4);//latch to ensure all 4 consumers finish before main terminates

        SharedDB s=new SharedDB();

        JobProducer jp=new JobProducer(queue);
        JobConsumer jc=new JobConsumer(queue,latch,s);

        executor.execute(jp);

        for(int i=1;i<=4;i++){
            executor.execute(jc);
        }

        latch.await();
        System.out.println(s.getJobs());
        executor.shutdown();

    }
}
