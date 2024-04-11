/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 10 April 2024
 * Comic.java
 * This class creates a JFrame that allows a user to remove a preexisting comic.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Creates a JFrame that allows a user to remove a preexisting comic.
 */
public class RemoveFrame {
    private JFrame frame;
    private JPanel panel;

    /**
     * Builds the panel and adds it to the frame.
     */
    public RemoveFrame() {
        frame = new JFrame();
        frame.setTitle("Remove Comic");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        panel = new JPanel();

        JLabel titleInputLabel = new JLabel("Comic title: ");
        panel.add(titleInputLabel);
        JTextField comicName = new JTextField(9);
        panel.add(comicName);
        JLabel idInputLabel = new JLabel("OR Comic ID: ");
        panel.add(idInputLabel);
        JTextField comicId = new JTextField(9);
        panel.add(comicId);
        JButton input = new JButton("Submit");

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeComicTitle = comicName.getText();
                int removeComicId = -1;
                try {
                    removeComicId = Integer.parseInt(comicId.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "No integer found.");
                }
                int comicIndex = -1;

                for (Comic comic : DatabaseManager.comics) {
                    if (comic.getTitle().equals(removeComicTitle) || comic.getId() == removeComicId) {
                        comicIndex = DatabaseManager.comics.indexOf(comic);
                    }
                }

                if (comicIndex == -1) {
                    JOptionPane.showMessageDialog(frame, "Comic not found.");
                }
                else {
                    DatabaseManager.comics.remove(comicIndex);
                    try {
                        if (removeComicTitle.isEmpty()) {
                            DatabaseManager.removeComic("ID", removeComicTitle, removeComicId);
                        }
                        else {
                            DatabaseManager.removeComic("title", removeComicTitle, removeComicId);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error updating database.");
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
