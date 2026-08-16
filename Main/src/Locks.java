import java.util.concurrent.locks.ReentrantLock;

public class Locks {
    private int balance=100;//SHARED MUTABLE STATE
    private final ReentrantLock lock=new ReentrantLock();

    public  void withdraw(int amount) throws InterruptedException{
        lock.lock();// manually lock

        System.out.println(Thread.currentThread().getName() + " attempting to withdraw" + " "+ amount);
        if(balance>=amount){
            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
            try{
                Thread.sleep(300);
                balance-=amount;
                System.out.println("Remaining balance"+ balance + " "+ Thread.currentThread().getName());
            }catch (InterruptedException e){
                e.printStackTrace();
                System.out.println("Thread failed");
            }
            finally {
                lock.unlock();
            }
        }

        else{
            System.out.println(Thread.currentThread().getName() + " insufficient balance");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Locks obj=new Locks();

        Thread t1=new Thread(()->{
            try {
                obj.withdraw(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"Thread-1");

        Thread t2=new Thread(()->{
            try {
                obj.withdraw(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
