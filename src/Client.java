import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        String hostName = "localhost";
        int portNumber = 9911;

        try {
            Socket s = new Socket(hostName, portNumber);

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                out.println(stdIn.readLine());
                System.out.println("> " + in.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
