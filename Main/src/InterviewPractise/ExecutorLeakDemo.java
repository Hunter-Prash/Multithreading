package InterviewPractise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
Write a program that:

creates a fixed pool of 3 threads,
builds a List<Callable<String>> of 6 tasks. Task n sleeps 1 second, then returns "Order n processed by " + Thread.currentThread().getName(),
submits them all with invokeAll(tasks),
prints each result in the original order,
prints the total time taken in milliseconds (use System.currentTimeMillis() before and after),
shuts the pool down properly.
 */
public class ExecutorLeakDemo {

    static void main() {
        ExecutorService ex= Executors.newFixedThreadPool(3);
        List<Callable<String>> list=new ArrayList<>();
        List<Future<String>>res=new ArrayList<>();

        long start=System.currentTimeMillis();

        try{
            for(int i=1;i<=6;i++){
                int num=i;
                Callable<String> c=()->{
                    Thread.sleep(1000);
                     return "Order"+num+"processed by"+ Thread.currentThread().getName();
                };
                list.add(c);
            }
            res=ex.invokeAll(list);

            for(Future<String> it : res){
                System.out.println(it.get());
            }
            long end=System.currentTimeMillis();

            System.out.println("Execution time="+(end-start)+"ms");

        }catch(InterruptedException e){
            Thread.currentThread().getStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        finally {
            ex.shutdown();
        }

    }
}
