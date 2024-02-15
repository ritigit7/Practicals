import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleSwingProgram {

    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Simple Swing Program");

        // Create a JButton
        JButton button = new JButton("Click Me!");

        // Add an ActionListener to handle button clicks
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Button Clicked!");
            }
        });

        // Add the button to the JFrame's content pane
        frame.getContentPane().add(button);

        // Set frame properties
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
