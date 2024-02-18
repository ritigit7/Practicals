// import java.io.*;
// import java.net.*;
// import java.util.Scanner;

// public class MyClient {
//     public static void main(String[] args) {
//         try {
//             Socket s = new Socket("127.0.0.1", 6666);
//             DataOutputStream dout = new DataOutputStream(s.getOutputStream());
//             Scanner sc = new Scanner(System.in);
//             String str = sc.nextLine();
//             dout.writeUTF(str);
//             dout.flush();
//             dout.close();
//             sc.close();
//             s.close();
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//     }
// }
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.net.Socket;
// import java.net.UnknownHostException;

// public class MyClient {
//     public static void main(String[] args) throws UnknownHostException, IOException {
//         try (Socket s = new Socket("192.168.137.1", 4567)) {
//             BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//             String str = br.readLine();
//             System.out.println(str);
//         }

//     }
// }

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {

    public static void main(String[] args) throws UnknownHostException, IOException {

        try (Socket Socket = new Socket("192.168.137.1", 4567)) {
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