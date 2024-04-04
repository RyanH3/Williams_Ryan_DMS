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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
        JLabel inputLabel = new JLabel("New comic info (title,author,imagePath,id,rating,currentChapter," +
                "totalChapters,completed,pinned): ");
        panel.add(inputLabel);
        JTextField comicEntry = new JTextField(50);
        panel.add(comicEntry);
        JButton input = new JButton("Submit");
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // Put all submitted values into a Comic object
                    String newComic = comicEntry.getText();
                    String[] comicItems = newComic.split(",");
                    Comic comic = new Comic(comicItems[0], comicItems[1], comicItems[2], Integer.parseInt(comicItems[3]),
                            Integer.parseInt(comicItems[4]), Integer.parseInt(comicItems[5]), Integer.parseInt(comicItems[6]),
                            Boolean.parseBoolean(comicItems[7]), Boolean.parseBoolean(comicItems[8]));
                    String update = "INSERT INTO Comics VALUES ('" + comic.getTitle() + "', '" + comic.getAuthor() +
                            "', '" + comic.getImagePath() + "', " + comic.getId() + ", " + comic.getRating() + ", " +
                            comic.getCurrentChapter() + ", " + comic.getTotalChapters() + ", " + comic.getCompleted() +
                            ", " + comic.getPinned() + ");";
                    // Add all submitted values to the database
                    Connection con = DriverManager.getConnection(DatabaseManager.URL);
                    Statement statement = con.createStatement();
                    statement.executeUpdate(update);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error submitting new comic.");
                }
                frame.dispose();
            }
        });
        panel.add(input);

        frame.add(panel);
        this.frame.setVisible(true);
    }
}
