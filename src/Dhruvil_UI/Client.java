package Dhruvil_UI;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("localhost", 5000);

            // Create input and output streams
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);

            // Create a thread to read messages from the server
            new Thread(() -> {
                try {
                    String message;
                    while ((message = inFromServer.readLine()) != null) {
                        System.out.println("Message from server: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Read messages from the console and send them to the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = reader.readLine()) != null) {
                outToServer.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
