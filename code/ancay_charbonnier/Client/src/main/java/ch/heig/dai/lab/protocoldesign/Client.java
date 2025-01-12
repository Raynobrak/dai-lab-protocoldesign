package ch.heig.dai.lab.protocoldesign;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {
    final String SERVER_ADDRESS = "localhost";
    final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        // Create a new client and run it
        Client client = new Client();
        client.run();
    }

    private void run() {

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in));)
        {
           //SENT REQUEST
            String line;
            while ((line = console.readLine()) != null) {
                //Send the line
                out.write(line + "\n");
                out.flush();

                //Print the return
                String returnString = in.readLine();
                System.out.println(returnString);
            }
        } catch (Exception e) {
            System.out.println("Client: exc.: " + e);
        }
    }
}