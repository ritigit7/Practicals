package chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerTwoClientsMain {

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    public static void main(String[] args) {
        ServerTwoClientsMain server = new ServerTwoClientsMain();
        server.start(5000);
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                ClientHandler clientHandler = new ClientHandler(socket, this);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    public void forwardMessage(String message, ClientHandler sender, ClientHandler receiver) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler != sender) {
                clientHandler.sendMessage(message);
            }
        }
    }
    

    private class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private ServerTwoClientsMain server;

        public ClientHandler(Socket socket, ServerTwoClientsMain server) {
            this.socket = socket;
            this.server = server;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received message: " + message);
                    server.forwardMessage(message, this, null);
                }
            } catch (IOException e) {
                System.err.println("Error in client handler: " + e.getMessage());
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }
}
