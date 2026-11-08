package InterviewPractise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SquareSumExecutor {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futures = new ArrayList<>();
        int sum = 0;

        try {
            for (int i = 1; i <= 5; i++) {
                int num = i;
                futures.add(executor.submit(() -> num * num));
            }

            for (Future<Integer> future : futures) {
                sum += future.get();
            }
            System.out.println("Sum: " + sum);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted");
        } catch (ExecutionException e) {
            System.out.println("Task execution failed: " + e.getCause());
        } finally {
            executor.shutdown();
        }
    }
}