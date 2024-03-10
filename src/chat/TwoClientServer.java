package chat;

import java.io.*;
import java.net.*;

public class TwoClientServer {
    public static void main(String[] args) {
        try {
            try (// Create a server socket
                    ServerSocket serverSocket = new ServerSocket(5000)) {
                // Accept first client
                Socket client1 = serverSocket.accept();
                System.out.println("Client 1 connected.");

                // Accept second client
                Socket client2 = serverSocket.accept();
                System.out.println("Client 2 connected.");

                // Start two threads to handle communication between clients
                Thread client1ToClient2Thread = new Thread(new ClientToClientHandler(client1, client2));
                client1ToClient2Thread.start();

                Thread client2ToClient1Thread = new Thread(new ClientToClientHandler(client2, client1));
                client2ToClient1Thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientToClientHandler implements Runnable {
    private Socket sourceClient;
    private Socket destinationClient;

    public ClientToClientHandler(Socket sourceClient, Socket destinationClient) {
        this.sourceClient = sourceClient;
        this.destinationClient = destinationClient;
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromSource = new BufferedReader(new InputStreamReader(sourceClient.getInputStream()));
            PrintWriter outToDestination = new PrintWriter(destinationClient.getOutputStream(), true);

            String message;
            while ((message = inFromSource.readLine()) != null) {
                outToDestination.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
