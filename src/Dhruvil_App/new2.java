package Dhruvil_App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

public class new2 implements ActionListener {
    JTextField text;
    JPanel p2;
    static BufferedReader[] inFromClients;
    static PrintWriter[] outToClients;
    static int[] otherClients;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();

    new2() {

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 400, 60);
        f.add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("3 (1).png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(9, 15, 25, 25);
        p1.add(back);
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.exit(0);
            }
        });
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("profile1.png"));
        Image i5 = i4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 6, 50, 50);
        p1.add(profile);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("phone (1).png"));
        Image i8 = i7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel call = new JLabel(i9);
        call.setBounds(250, 14, 30, 30);
        p1.add(call);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("video (1).png"));
        Image i11 = i10.getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel video = new JLabel(i12);
        video.setBounds(300, 14, 30, 30);
        p1.add(video);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("3icon (1).png"));
        Image i14 = i13.getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel dots = new JLabel(i15);
        dots.setBounds(350, 14, 30, 30);
        p1.add(dots);

        JLabel U1 = new JLabel("NARUTO");
        U1.setBounds(100, 0, 200, 50);
        U1.setForeground(Color.WHITE);
        U1.setFont(new Font("SAN_SERIF", Font.BOLD, 13));
        p1.add(U1);

        JLabel status = new JLabel("Online");
        status.setBounds(100, 20, 200, 50);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 9));
        p1.add(status);

        p2 = new JPanel();
        p2.setBounds(4, 60, 378, 550);
        // p2.setBackground(Color.BLACK);
        f.add(p2);

        JButton send = new JButton("send");
        send.setBounds(310, 610, 70, 45);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        f.add(send);

        text = new JTextField();
        text.setBounds(3, 610, 300, 45);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));

        f.add(text);

        f.setLayout(null);
        f.setSize(400, 700);
        f.setLocation(10, 30);
        f.setVisible(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    // ghbjnmk,.

    public void actionPerformed(ActionEvent ae) {
        String str = text.getText();
        JPanel out = formatLabel(str);
        p2.setLayout(new BorderLayout());

        JPanel right = new JPanel(new BorderLayout());
        right.add(out, BorderLayout.LINE_END);

        for (int j = 0; j < 2; j++) {
            outToClients[otherClients[j]].println(str);
        }

        text.setText(" ");
        vertical.add(out);
        vertical.add(Box.createVerticalStrut(15));
        p2.add(vertical, BorderLayout.PAGE_END);
        f.repaint();
        f.validate();
        f.invalidate();
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel output = new JLabel(out);
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(1, 15, 15, 20));

        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }

    public static JPanel formatLabelr(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel output = new JLabel(out);
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(255, 100, 100));
        output.setBackground(Color.RED);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(1, 15, 15, 20));

        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        new new2();
        try {
            try (// Create a server socket
                    ServerSocket serverSocket = new ServerSocket(5000)) {
                // Wait for client connections
                System.out.println("Server waiting for clients...");

                // Arrays to store sockets, input streams, and output streams
                Socket[] clientSockets = new Socket[3];
                inFromClients = new BufferedReader[3];
                outToClients = new PrintWriter[3];

                // Accept three clients
                for (int i = 0; i < 3; i++) {
                    clientSockets[i] = serverSocket.accept();
                    System.out.println("Client " + (i + 1) + " connected");

                    // Create input and output streams for each client
                    inFromClients[i] = new BufferedReader(new InputStreamReader(clientSockets[i].getInputStream()));
                    outToClients[i] = new PrintWriter(clientSockets[i].getOutputStream(), true);
                }
            }

            // Start threads to forward messages using arrays
            for (int i = 0; i < 3; i++) {
                int t = i;
                otherClients = new int[2]; // Array for other two clients
                otherClients[0] = (i + 1) % 3; // Next client in the order
                otherClients[1] = (i + 2) % 3; // Client after the next one

                new Thread(() -> {
                    try {
                        String message;
                        while ((message = inFromClients[t].readLine()) != null) {
                            // Chat_database ob = new Chat_database();
                            // int k = Chat_database.lastOrder() + 1;
                            // Chat_database.insertData(k++, message, 1, 0, 0);
                            // Forward message to both other clients
                            JPanel panel2 = formatLabel(message);
                            JPanel left = new JPanel(new BorderLayout());
                            left.add(panel2);
                            left.add(panel2, BorderLayout.LINE_START);
                            vertical.add(left);
                            f.repaint();
                            f.validate();

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}