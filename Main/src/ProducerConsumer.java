import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    static Queue<Integer> queue = new LinkedList<>(); // SHARED MUTABLE STATE

    public static void main(String[] args) throws InterruptedException {

        Thread producer = new Thread(() -> {

            for (int i = 1; i <= 5; i++) {

                synchronized (queue) {
                    queue.add(i);
                    System.out.println("Produced: " + i);
                    queue.notify();
                }
            }

        }, "Producer");


        Thread consumer = new Thread(() -> {

            for (int i = 1; i <= 5; i++) {

                synchronized (queue) {

                    while (queue.isEmpty()) {
                        try {
                            queue.wait();//pause here and release lock..after getting notified executions starts from the next line...thread remains suspended unless it is notified/interrupted
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    int value = queue.remove();
                    System.out.println("Consumed: " + value);
                }
            }

        }, "Consumer");


        consumer.start();
        producer.start();

        producer.join();
        consumer.join();
    }
}