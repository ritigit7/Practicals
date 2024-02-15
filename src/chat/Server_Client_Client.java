package chat;

import java.io.*;
import java.net.*;

public class Server_Client_Client {
    public static void main(String[] args) {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(5000);

            // Wait for client connections
            System.out.println("Server waiting for clients...");

            // Accept the first client
            Socket clientSocket1 = serverSocket.accept();
            System.out.println("First client connected");

            // Accept the second client
            Socket clientSocket2 = serverSocket.accept();
            System.out.println("Second client connected");

            // Create input and output streams for both clients
            BufferedReader inFromClient1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
            PrintWriter outToClient1 = new PrintWriter(clientSocket1.getOutputStream(), true);

            BufferedReader inFromClient2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
            PrintWriter outToClient2 = new PrintWriter(clientSocket2.getOutputStream(), true);

            // Start a thread to forward messages from client 1 to client 2
            new Thread(() -> {
                try {
                    String message;
                    while ((message = inFromClient1.readLine()) != null) {
                        outToClient2.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Start a thread to forward messages from client 2 to client 1
            new Thread(() -> {
                try {
                    String message;
                    while ((message = inFromClient2.readLine()) != null) {
                        outToClient1.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
