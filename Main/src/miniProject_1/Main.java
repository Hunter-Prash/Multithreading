package miniProject_1;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    static Queue<Job> queue = new LinkedList<>();
    static void main() throws InterruptedException, ExecutionException {

        JobProducer jobProducer=new JobProducer(queue);
        JobConsumer jobConsumer=new JobConsumer(queue);

        ExecutorService executor= Executors.newFixedThreadPool(2);

        Future<?>f1=executor.submit(jobProducer);
        Future<?>f2=executor.submit(jobConsumer);

        f1.get();
        f2.get();
        System.out.println("-------------------");

        executor.shutdown();

    }
}
