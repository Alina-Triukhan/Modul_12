import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeGoOn {
    public static void main(String[] args) {
        Thread currentTime = new Thread(() -> {
            while (true) {
                displayElapsedTime();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        currentTime.start();
        Thread message = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (true) {
                System.out.println("Минуло 5 секунд.");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        message.start();
    }

    private static void displayElapsedTime() {
        long elapsedTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = sdf.format(new Date(elapsedTime));
        System.out.println("Поточний час: " + formattedTime);
    }
}
