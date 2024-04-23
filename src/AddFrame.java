/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 23 April 2024
 * AddFrame.java
 * This class creates a JFrame that lets the user add comics to their list.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


/**
 * Creates a JFrame that lets the user add comics to their list.
 */
public class AddFrame {
    private JFrame frame;
    private JPanel panel;

    /**
     * Builds the panel and adds it to the frame.
     */
    public AddFrame() {
        frame = new JFrame();
        this.frame.setTitle("Add Comics");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(500, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);

        panel = new JPanel();

        if (DatabaseManager.comics.isEmpty()) {
            getDatabaseComics();
        }
        else {
            getNewComic();
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Gets comics from a user-specified SQLite database.
     */
    public void getDatabaseComics() {
        JLabel databasePathLabel = new JLabel("Path to database:");
        panel.add(databasePathLabel);

        JTextField databasePathEntry = new JTextField(20);
        panel.add(databasePathEntry);

        JButton input = new JButton("Submit");
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseManager.url = "jdbc:sqlite:" + databasePathEntry.getText();
                    DatabaseManager.readComics();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error uploading new comics.");
                }
                frame.dispose();
            }
        });
        panel.add(input);
    }

    /**
     * Gets a user-submitted comic.
     */
    public void getNewComic() {
        JLabel newComicLabel = new JLabel("New comic info: ");
        panel.add(newComicLabel);

        JTextField newComicEntry = new JTextField("title,author,imagePath,id,rating,currentChapter," +
                "totalChapters,completed,pinned", 25);
        panel.add(newComicEntry);

        JButton input = new JButton("Submit");
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] comicItems = newComicEntry.getText().split(",");

                    Comic comic = new Comic(comicItems[0], comicItems[1], comicItems[2], Integer.parseInt(comicItems[3]),
                            Integer.parseInt(comicItems[4]), Integer.parseInt(comicItems[5]), Integer.parseInt(comicItems[6]),
                            Boolean.parseBoolean(comicItems[7]), Boolean.parseBoolean(comicItems[8]));

                    Connection con = DriverManager.getConnection(DatabaseManager.url);
                    Statement statement = con.createStatement();
                    statement.executeUpdate("INSERT INTO Comics VALUES ('" + comic.getTitle() + "', '" + comic.getAuthor() +
                            "', '" + comic.getImagePath() + "', " + comic.getId() + ", " + comic.getRating() + ", " +
                            comic.getCurrentChapter() + ", " + comic.getTotalChapters() + ", " + comic.getCompleted() +
                            ", " + comic.getPinned() + ");");
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error updating database.");
                }
                frame.dispose();
            }
        });
        panel.add(input);
    }
}
