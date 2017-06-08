import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        int portNumber = 9911;

        try {
            ServerSocket ss = new ServerSocket(portNumber);

            Socket clientSocket = ss.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String messageLine;

            while (true) {

                if (stdIn.ready()) {
                    messageLine = stdIn.readLine();

                    if (messageLine.equals("stop_server")) {
                        System.exit(0);
                    }
                    out.println(messageLine);
                }

                if (in.ready()) {
                    System.out.println("--> " + in.readLine());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
