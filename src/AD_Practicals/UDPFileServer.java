package AD_Practicals;

import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPFileServer {
    private static final int PORT = 65535;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);

            // Receive the file name
            byte[] fileNameBytes = new byte[256];
            DatagramPacket fileNamePacket = new DatagramPacket(fileNameBytes, fileNameBytes.length);
            socket.receive(fileNamePacket);
            String fileName = new String(fileNameBytes, 0, fileNamePacket.getLength());

            // Receive the file content
            byte[] fileContentBytes = new byte[4096];
            DatagramPacket fileContentPacket = new DatagramPacket(fileContentBytes, fileContentBytes.length);
            socket.receive(fileContentPacket);
            String fileContent = new String(fileContentBytes, 0, fileContentPacket.getLength());

            // Save the received file
            FileWriter writer = new FileWriter(fileName);
            writer.write(fileContent);
            writer.close();

            System.out.println("File received.");
            socket.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}