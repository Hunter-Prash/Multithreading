public class Main {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                int temp = count;

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                count = temp + 1;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                int temp = count;

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                count = temp + 1;
            }
        });

        t1.start();
        t2.start();

        //t1.join();
        //t2.join();
        Thread.sleep(10);
        System.out.println(count);
    }
}