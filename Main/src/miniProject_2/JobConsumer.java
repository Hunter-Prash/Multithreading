package miniProject_2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class JobConsumer implements Runnable {

    BlockingQueue<Integer> queue;
    CountDownLatch latch;
    SharedDB s;
    Semaphore semaphore;

    public JobConsumer(
            BlockingQueue<Integer> queue,
            CountDownLatch latch,
            SharedDB s,
            Semaphore semaphore) {

        this.queue = queue;
        this.s = s;
        this.latch = latch;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        int value = 0;
        boolean permitAcquired = false;

        while (true) {

            try {

                value = queue.take();

                if (value == -1) {
                    break;
                }

                // Simulate processing failure
                if (value == 3) {
                    throw new InterruptedException();
                }

                // Acquire DB connection/resource
                semaphore.acquire();
                permitAcquired = true;

                // Access shared database
                s.setProcessedJobs();

                System.out.println(
                        "Consumed: " + value
                );

            } catch (InterruptedException e) {

                e.printStackTrace();

                System.out.println(
                        "Worker failed at: " + value
                );

            } finally {

                // Release ONLY if we actually acquired
                // a semaphore permit
                if (permitAcquired) {

                    semaphore.release();
                    permitAcquired = false;
                }
            }
        }
        // Worker has finished
        latch.countDown();
    }
}