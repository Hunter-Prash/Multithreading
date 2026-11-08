package InterviewPractise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BoundedPoolDemo {

    static void main() {
        ExecutorService ex=new ThreadPoolExecutor(2,4,30,TimeUnit.SECONDS,new ArrayBlockingQueue<>(2),new ThreadPoolExecutor.CallerRunsPolicy());
        long s=System.currentTimeMillis();
        List<Callable<String>> list=new ArrayList<>();
        List<Future<String>>res=new ArrayList<>();

        try{
            for(int i=1;i<=10;i++){
                int num=i;
                list.add(()->{
                    return ("Task "+num+"running on "+ Thread.currentThread().getName());
                });
            }

            res=ex.invokeAll(list);

            for(Future<String> it : res){
                System.out.println(it.get());
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }catch (ExecutionException e){
            System.out.println(e.getCause());
        }finally {
            ex.shutdown();
        }
        System.out.println(System.currentTimeMillis()-s);

    }
}
