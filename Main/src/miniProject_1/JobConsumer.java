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
            synchronized (queue) {
                if (queue.isEmpty()) {
                    try {
                        queue.wait();//after getting notified executions starts from the next line
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Job j = queue.remove();
                System.out.println("removed: " + j);
            }

        }
    }
}
