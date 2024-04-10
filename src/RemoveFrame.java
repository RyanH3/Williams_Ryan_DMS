/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 1 April 2024
 * Comic.java
 * This class creates a JFrame that allows a user to remove a preexisting comic.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RemoveFrame {
    private JFrame frame;
    private JPanel panel;

    public RemoveFrame() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        frame.setTitle("Remove Comic");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        panel = new JPanel();

        JLabel inputLabel = new JLabel("Comic: ");
        panel.add(inputLabel);
        JTextField comicName = new JTextField(10);
        panel.add(comicName);
        JButton input = new JButton("Submit");

        // Removes the comic the user types in when "Submit" is pressed
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeComic = comicName.getText();
                int comicIndex = -1;
                for (Comic comic : DatabaseManager.comics) {
                    if (comic.getTitle().equals(removeComic)) {
                        comicIndex = DatabaseManager.comics.indexOf(comic);
                    }
                }
                if (comicIndex == -1) {
                    JOptionPane.showMessageDialog(frame, "Comic not found.");
                }
                else {
                    DatabaseManager.comics.remove(comicIndex);
                    try {
                        Connection con = DriverManager.getConnection(DatabaseManager.url);
                        Statement statement = con.createStatement();
                        statement.executeUpdate("DELETE FROM Comics WHERE Title = '" + removeComic + "';");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    frame.dispose();

                }
            }
        });
        panel.add(input);

        frame.add(panel);
        frame.setVisible(true);
    }
}
