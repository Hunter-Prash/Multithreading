import java.util.concurrent.*;

public class ExecService {
    static void main() throws ExecutionException, InterruptedException {

        /*ExecutorService executor= Executors.newFixedThreadPool(3);
        //execute() is inherited from the Executor interface
        //ExecutorService extends Executor, so an ExecutorService can also use execute()

         ExecutorService executor=Executors.newFixedThreadPool(2);

        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " → Task 1");
        });

        executor.execute(() -> {
            System.out.println("Task 2");
        });

        executor.execute(() -> {
            System.out.println("Task 3");
        });

        executor.shutdown();*/

       /* ExecutorService executor =
                Executors.newFixedThreadPool(3);

        Future<?> future = executor.submit(() -> {
            System.out.println(
                    Thread.currentThread().getName() + " doing work"
            );
        });

        executor.shutdown();*/

        ExecutorService executor=Executors.newFixedThreadPool(2);

        Callable<Integer> task=()->{
            System.out.println("Worker started");

            Thread.sleep(3000);
            System.out.println("Worker finished");
            return 42;
        };

        Future<Integer> future=executor.submit(task);
        Integer result=future.get();

        System.out.println("Result: " + result);

        executor.shutdown();

    }
}
