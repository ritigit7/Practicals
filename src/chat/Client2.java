package chat;

import java.io.*;
import java.net.*;

public class Client2 {
    public static void main(String[] args) {
        try {
            try (// Connect to the server
                    Socket socket = new Socket("192.168.1.42", 5000)) {
                // Create input and output streams
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);

                // Create a thread to read messages from the server
                new Thread(() -> {
                    try {
                        String message;
                        while ((message = inFromServer.readLine()) != null) {
                            System.out.println(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                // Read messages from the console and send them to the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String[] message = new String[2];
                message[0] = "Dhruvil";
                while ((message[1] = reader.readLine()) != null) {
                    outToServer.println(message[0] + ":" + message[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
