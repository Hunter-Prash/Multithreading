package miniProject_2;

import java.util.concurrent.*;

public class Main {
    static void main() throws InterruptedException {

        //initialize the queue (shared state)
        BlockingQueue<Integer>queue=new LinkedBlockingQueue<>(20);
        ExecutorService executor= Executors.newFixedThreadPool(5);//1 producer 4 workers
        CountDownLatch latch = new CountDownLatch(5);//latch to ensure all 4 consumers finish before main terminates
        Semaphore semaphore=new Semaphore(2);
        SharedDB s=new SharedDB();

        JobProducer jp=new JobProducer(queue,latch);
        JobConsumer jc=new JobConsumer(queue,latch,s,semaphore);

        executor.execute(jp);

        for(int i=1;i<=4;i++){
            executor.execute(jc);
        }

        latch.await();//main waits till all 5 worker threads have completed

        System.out.println("Jobs Processed: "+s.getProcessedJobs());

        //graceful shutdown
        executor.shutdown();//executor service itself shutdown

    }
}
