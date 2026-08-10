public class LifeCycle {
    public static void main(String[] args)throws InterruptedException{
        Thread t1=new Thread(()->{
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
        Thread.sleep(500);
        System.out.println(t1.getState());
        t1.join();
        System.out.println(t1.getState());
    }
}
