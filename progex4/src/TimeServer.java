import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TimeServer {
	private static int PORT = 27780;
	private ServerSocket serverSocket;

    /**
     * Starts a new TimeServer, listening on port 27780.
     *
     * Incoming requests are dispatched to NTPRequestHandler.
     */
	public TimeServer() {
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Server started on port: " + PORT);

            while (true) {
                // Block until new connection arises and handle new connections inside a separate thread
                Socket clientSocket = serverSocket.accept();
                long rcvRequest = System.currentTimeMillis();
                clientSocket.setTcpNoDelay(true);
                new Thread(new NTPRequestHandler(clientSocket, rcvRequest)).start();
            }
		} catch (IOException e) {
			e.printStackTrace();
			try {
				serverSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
		new TimeServer();
	}

	private class NTPRequestHandler implements Runnable {
		private Socket client;
        private long requestBegin;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;

		public NTPRequestHandler(Socket client, long rcv) {
			this.client = client;
            this.requestBegin = rcv;
		}

		@Override
		public void run() {
            try {
                ois = new ObjectInputStream(client.getInputStream());
                oos = new ObjectOutputStream(client.getOutputStream());

                NTPRequest ntpRequest = (NTPRequest) ois.readObject();
                ntpRequest.setT2(requestBegin);
                ntpRequest.setT3(System.currentTimeMillis());
                sendNTPAnswer(ntpRequest);

                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
		}

        /**
         * Sends the passed NTPRequest to the client after a random delay of 10-100 ms.
         *
         * @param request The NTPRequest reply.
         */
        private void sendNTPAnswer(NTPRequest request) {
            threadSleep(ThreadLocalRandom.current().nextLong(10, 101));

            try {
                oos.writeObject(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
