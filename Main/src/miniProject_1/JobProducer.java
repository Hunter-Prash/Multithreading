package miniProject_1;

import java.util.Queue;

public class JobProducer implements Runnable{

    private Queue<Job>queue;

    public JobProducer(Queue<Job>queue){
        this.queue=queue;
    }

    @Override
    public void run(){
        for (int i=1;i<=10;i++){
            Job j=new Job(i);
            queue.add(j);

            System.out.println(Thread.currentThread().getName() + " created job-" +j.getId());

        }
    }
}
