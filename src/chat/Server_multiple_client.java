package chat;

import java.io.*;
import java.net.*;
// import chat.Chat_database;

public class Server_multiple_client {

    public static void main(String[] args) {
        try {
            try (// Create a server socket
                    ServerSocket serverSocket = new ServerSocket(5000)) {
                // Wait for client connections
                System.out.println("Server waiting for clients...");

                // Arrays to store sockets, input streams, and output streams
                Socket[] clientSockets = new Socket[3];
                BufferedReader[] inFromClients = new BufferedReader[3];
                PrintWriter[] outToClients = new PrintWriter[3];

                // Accept three clients
                for (int i = 0; i < 3; i++) {
                    clientSockets[i] = serverSocket.accept();
                    System.out.println("Client " + (i + 1) + " connected");

                    // Create input and output streams for each client
                    inFromClients[i] = new BufferedReader(new InputStreamReader(clientSockets[i].getInputStream()));
                    outToClients[i] = new PrintWriter(clientSockets[i].getOutputStream(), true);
                }
                Chat_database.fetchData();
                // Start threads to forward messages using arrays
                for (int i = 0; i < 3; i++) {
                    int t = i;
                    int[] otherClients = new int[2]; // Array for other two clients
                    otherClients[0] = (i + 1) % 3; // Next client in the order
                    otherClients[1] = (i + 2) % 3; // Client after the next one

                    new Thread(() -> {
                        try {
                            String message;
                            while ((message = inFromClients[t].readLine()) != null) {

                                int k = Chat_database.lastOrder() + 1;
                                String[] msg = message.split(":");
                                int[] n = new int[3];
                                if (msg[0].equals("Vatsal")) {
                                    n[0] = 1;
                                } else if (msg[0].equals("Dhruvil")) {
                                    n[1] = 1;
                                } else {
                                    n[2] = 1;
                                }
                                Chat_database.insertData(k++, msg[1], n[0], n[1], n[2]);
                                // Forward message to both other clients
                                for (int j = 0; j < 2; j++) {
                                    outToClients[otherClients[j]].println(message);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
