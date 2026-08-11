public class ThreadMethods extends Thread {
    public ThreadMethods(String name){
        super(name);
    }
    @Override
    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName() + "-Priority"+ Thread.currentThread().getPriority() + "-count:" + i);
            Thread.yield();
            try{
                //simulate some work
                Thread.sleep(500);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args)throws InterruptedException {
        ThreadMethods t1=new ThreadMethods("low");
        ThreadMethods t2=new ThreadMethods("med");
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);

        t1.start();
        t2.start();
    }
}
