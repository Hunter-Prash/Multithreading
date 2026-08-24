package miniProject_1;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static Queue<Job> queue = new LinkedList<>();
    static void main() throws InterruptedException{

        JobProducer jobProducer=new JobProducer(queue);
        JobConsumer jobConsumer=new JobConsumer(queue);

        Thread producer= new Thread(jobProducer,"producer");
        Thread consumer=new Thread(jobConsumer,"consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
        System.out.println("-------------------");
    }
}
