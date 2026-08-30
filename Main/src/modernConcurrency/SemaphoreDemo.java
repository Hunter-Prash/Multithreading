package modernConcurrency;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(2);

        for (int i = 1; i <= 5; i++) {

            int id = i;

            Thread t = new Thread(() -> {

                try {
                    System.out.println("Thread " + id + " trying to enter");

                    semaphore.acquire();

                    System.out.println("Thread " + id + " ENTERED");

                    Thread.sleep(2000);

                    System.out.println("Thread " + id + " leaving");

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                } finally {
                    semaphore.release();
                }

            });

            t.start();
        }
    }
}