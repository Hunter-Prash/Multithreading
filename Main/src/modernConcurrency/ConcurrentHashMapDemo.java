package modernConcurrency;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<Integer, String> users =
                new ConcurrentHashMap<>();

        Thread t1 = new Thread(() -> {

            for (int i = 1; i <= 1000; i++) {
                users.put(i, "User-" + i);
            }

        });

        Thread t2 = new Thread(() -> {

            for (int i = 1001; i <= 2000; i++) {
                users.put(i, "User-" + i);
            }

        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(users.size());
    }
}