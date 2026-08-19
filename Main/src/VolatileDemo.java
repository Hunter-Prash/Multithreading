public class VolatileDemo {
    static volatile boolean running=true;

    static void main(String[] args) throws InterruptedException {
        Thread worker=new Thread(()->{
           while(running){
                //simulate some task
           }
            System.out.println("Worker stopped");
        });

        worker.start();
        Thread.sleep(1000);
        System.out.println("Main stopped worker");
        running=false;
    }
}
