import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

public class TimeClient {
    private static String hostUrl = "127.0.0.1";
    private static int PORT = 27780;
    private Double minD = Double.MAX_VALUE;
    private NTPRequest minNTPrequest;
    private Socket socket;

    public TimeClient() {
        try {
            for (int i = 0; i < 10; i++) {
                // Setup connection
                socket = new Socket(InetAddress.getByName(hostUrl), PORT);
                socket.setTcpNoDelay(true);

                // Send first message with local timestamp
                NTPRequest ntpRequest = new NTPRequest();
                ntpRequest.setT1(System.currentTimeMillis());
                sendNTPRequest(ntpRequest);

                // Receive response with send/receive timestamp of remote system
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ntpRequest = (NTPRequest) ois.readObject();

                // Denote receive time as local timestamp
                ntpRequest.setT4(System.currentTimeMillis());

                // Calculate offset to remote clock and network delay.
                ntpRequest.calculateOandD();
                System.out.println(String.format("Round %d: Est. offset: %.4f ms, Delay: %.4f ms", i + 1, ntpRequest.o, ntpRequest.d));

                // Remember the NTP Request with the lowest delay
                if (ntpRequest.d < minD) {
                    minD = ntpRequest.d;
                    minNTPrequest = ntpRequest;
                }

                socket.close();
                // Wait 300ms between two measurements.
                this.threadSleep(300);
            }

            System.out.println("Time difference (est. offset): " + minNTPrequest.o + ", " +
                    "Accuracy: " + (minNTPrequest.o - minNTPrequest.d / 2) +
                    " <= o (real offset) <= " +
                    (minNTPrequest.o + minNTPrequest.d / 2));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendNTPRequest(NTPRequest request) {
        // In addition, implement a random delay between 10ms and 100ms on server and client side to simulate the
        // communication more realistically (this is the case, when both client and server are started on the same computer).
        threadSleep(ThreadLocalRandom.current().nextLong(10, 101));

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TimeClient();
    }
}
