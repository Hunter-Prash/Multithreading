package miniProject_1;

import java.util.Queue;

public class JobConsumer implements Runnable{
    private final Queue<Job>queue;
    public JobConsumer(Queue<Job>queue){
        this.queue=queue;
    }

    @Override
    public void run(){

        for(int i=1;i<=10;i++){
            Job job=queue.remove();
            System.out.println(
                    Thread.currentThread().getName()
                            + " consumed Job-" + job.getId());
        }
    }
}
