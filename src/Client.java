import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        String hostName = "localhost";
        int portNumber = 9911;

        boolean first = true;

        while (true) {

            if (!first) {
                sleep(5000);
            }

            try {
                Socket s = new Socket(hostName, portNumber);

                System.out.println("Connection to server " + hostName + ":" + portNumber + " established");

                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                String messageLine;

                while (true) {

                    if (stdIn.ready()) {

                        messageLine = stdIn.readLine();

                        if (messageLine.equals("exit_chat")) {
                            System.exit(0);
                        }
                        out.println(messageLine);

                    }

                    if (in.ready()) {
                        System.out.println("--> " + in.readLine());
                    }

                }

            } catch (ConnectException e) {
                System.out.println("CONNECTION REFUSED. TRYING TO RECONNECT...");
                first = false;
            } catch (IOException e) {
                System.out.println("I/O ERROR");
                System.exit(1);
            }

        }
    }
}
