package AD_Practicals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPFileClient {
    private static final int PORT = 65535;
    private static final String SERVER_ADDRESS = "localhost";

    public static void main(String[] args) {
        try {
            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);
            DatagramSocket socket = new DatagramSocket();

            // Read the file to be sent
            File file = new File("E:\\java_programs\\Practicals\\src\\AD_Practicals\\file.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
                fileContent.append("\n");
            }
            reader.close();

            // Send the file name
            byte[] fileNameBytes = SERVER_ADDRESS.getBytes();
            DatagramPacket fileNamePacket = new DatagramPacket(fileNameBytes, fileNameBytes.length, serverAddress,
                    PORT);
            socket.send(fileNamePacket);

            // Send the file content
            byte[] fileContentBytes = fileContent.toString().getBytes();
            DatagramPacket fileContentPacket = new DatagramPacket(fileContentBytes, fileContentBytes.length,
                    serverAddress, PORT);
            socket.send(fileContentPacket);

            System.out.println("File sent.");
            socket.close();

        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + SERVER_ADDRESS);
        } catch (SocketException e) {
            System.err.println("Socket error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
