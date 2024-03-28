package AD_Practicals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class CharacterCountingClient {
    public static void main(String[] args) {
        String ip = "192.168.1.42";
        int port = 5000;
        try {
            try (Socket socket = new Socket(ip, port)) {
                System.out.println("Connected to server");
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                    String response = in.readLine();
                    System.out.println("Server response: " + response);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + ip);
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}