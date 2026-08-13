
class MyThread extends Thread{
    Synchronization s;
    public MyThread(Synchronization s){
        this.s=s;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            s.increment();
        }
    }
}

public class Synchronization {
    private int count=0;

    public synchronized void  increment(){
        count++;
    }

    public int getCount(){
        return count;
    }
    public static void main(String[] args) throws InterruptedException {

        Synchronization s=new Synchronization();

        //common resource between two thread(shared-mutable state)
        MyThread t1= new MyThread(s);
        MyThread t2=new MyThread(s);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(s.getCount());
    }

}
