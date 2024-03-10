package AppServer;


import java.io.*;
import java.net.*;

public class ServerTwoClientsMain {

    private ClientHandler client1Handler;
    private ClientHandler client2Handler;

    public static void main(String[] args) {
        ServerTwoClientsMain server = new ServerTwoClientsMain();
        server.start(5000);
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            Socket client1Socket = serverSocket.accept();
            System.out.println("Client 1 connected");
            Socket client2Socket = serverSocket.accept();
            System.out.println("Client 2 connected");
            // Accept first client
            client1Handler = new ClientHandler(client1Socket);
            new Thread(client1Handler).start();

            // Accept second client
            client2Handler = new ClientHandler(client2Socket);
            new Thread(client2Handler).start();
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received message: " + message);
                    // Forward message to the other client
                    if (this == client1Handler) {
                        client2Handler.sendMessage(message);
                    } else if (this == client2Handler) {
                        client1Handler.sendMessage(message);
                    }
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
