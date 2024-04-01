/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 1 April 2024
 * AddFrame.java
 * This class creates a JFrame that lets the user add comics to their list.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AddFrame {
    private JFrame frame;
    private JPanel panel;

    public AddFrame() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        this.frame.setTitle("Add Comics");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(500, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);

        panel = new JPanel();
        JLabel inputLabel = new JLabel("Comic list path: ");
        panel.add(inputLabel);
        JTextField path = new JTextField(20);
        panel.add(path);
        JButton input = new JButton("Submit");
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = path.getText();
                try {
                    DatabaseManager.addComics(filePath);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "File not found.");
                }
                frame.dispose();
            }
        });
        panel.add(input);

        frame.add(panel);
        this.frame.setVisible(true);
    }
}
