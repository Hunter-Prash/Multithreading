package miniProject_1;

import java.util.Queue;

public class JobProducer implements Runnable{

    private final Queue<Job>queue;

    public JobProducer(Queue<Job>queue){
        this.queue=queue;
    }

    @Override
    public void run(){
        for (int i=1;i<=10;i++){
            synchronized (queue){
                Job j=new Job(i);
                queue.add(j);
                queue.notify();
                System.out.println(Thread.currentThread().getName() + " created job-" +j.getId());
            }


        }
    }
}
