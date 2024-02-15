import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        try (Socket Socket = new Socket("192.168.149.211", 1234)) {
            System.out.println("Connected to server on port " + Socket.getLocalPort());
            while (true) {

                BufferedReader in = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
                PrintWriter out = new PrintWriter(Socket.getOutputStream(), true);
                try (Scanner input = new Scanner(System.in)) {
                    System.out.println("Enter a message to send to server: ");
                    String message = input.nextLine();
                    out.println(message);
                }
                while (in.readLine() != null) {
                    System.out.println(in.readLine());
                }
                // System.out.println("massage sent to server: "+ message);

                // int charcount = Integer.parseInt(in.readLine());
                // System.out.println("Server Response: " + charcount);

                // Socket.close();
            }
        }

    }
}