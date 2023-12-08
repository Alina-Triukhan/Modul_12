import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Numbers {
    private final int n;
    private final BlockingQueue<String> fizzQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<String> buzzQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<String> fizzbuzzQueue = new LinkedBlockingQueue<>();

    public Numbers(int n) {
        this.n = n;
    }

    public void fizz() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                try {
                    fizzQueue.put("fizz");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    fizzQueue.put(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void buzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                try {
                    buzzQueue.put("buzz");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    buzzQueue.put(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void fizzbuzz() {
        for (int i = 1; i <= n; i++) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                try {
                    fizzbuzzQueue.put("fizzbuzz");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    fizzbuzzQueue.put(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void number() {
        String[] strArr = new String[n];
        for (int i = 1; i <= n; i++) {
            try {
                String fizzbuzzResult = fizzbuzzQueue.take();
                String fizzResult = fizzQueue.take();
                String buzzResult = buzzQueue.take();

                if ("fizzbuzz".equals(fizzbuzzResult)) {
                    strArr[i-1] = "fizzbuzz";
                    //System.out.println("fizzbuzz");
                } else if ("fizz".equals(fizzResult)) {
                    strArr[i-1] = "fizz";
                    //System.out.println("fizz");
                } else if ("buzz".equals(buzzResult)) {
                    strArr[i-1] = "buzz";
                    // System.out.println("buzz");
                } else {
                    strArr[i-1] = String.valueOf(i);
                    // System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("str: " + Arrays.toString(strArr));
    }

    public static void main(String[] args) {
        int n = 15;
        Numbers fizzBuzz = new Numbers(n);

        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzbuzz);
        Thread threadD = new Thread(fizzBuzz::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
