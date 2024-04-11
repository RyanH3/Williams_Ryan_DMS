/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 10 April 2024
 * ComicFrame.java
 * This class creates a JFrame that displays all the comics in the user's list.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;

/**
 * Creates a JFrame that displays all the comics in the user's list.
 */
public class ComicFrame {
    private JFrame frame;
    private JPanel panel;
    private JLabel imageLabel, titleLabel, authorLabel, completedLabel, ratingLabel, idLabel, chapterLabel, pinnedLabel;

    /**
     * Builds the panel and adds it to the frame.
     * @throws IOException If the image does not load from the given URL.
     */
    public ComicFrame() throws IOException {
        frame = new JFrame();
        this.frame.setTitle("My Comics");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(500, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);
        panel = new JPanel();
        // Read the comics from the database and sort them
        DatabaseManager.comics.clear();
        DatabaseManager.readComics();
        DatabaseManager.comics.sort(Comparator.comparing(Comic::getId));
        // Print the pinned comic first
        for (Comic comic : DatabaseManager.comics) {
            if (comic.getPinned()) {
                imageLabel = loadImage(comic.getImagePath());
                panel.add(imageLabel);
                titleLabel = new JLabel(comic.getTitle());
                panel.add(titleLabel);
                authorLabel = new JLabel("by " + comic.getAuthor());
                panel.add(authorLabel);
                completedLabel = new JLabel("Completed: " + comic.getCompleted());
                panel.add(completedLabel);
                ratingLabel = new JLabel("Rating: " + comic.getRating());
                panel.add(ratingLabel);
                idLabel = new JLabel("ID: " + comic.getId());
                panel.add(idLabel);
                chapterLabel = new JLabel("Currently on chapter " + comic.getCurrentChapter() +
                        " / " + comic.getTotalChapters());
                panel.add(chapterLabel);
                pinnedLabel = new JLabel("Pinned: " + comic.getPinned());
                panel.add(pinnedLabel);
            }
        }
        // Print the unpinned comics
        for (Comic comic : DatabaseManager.comics) {
            if (!comic.getPinned()) {
                imageLabel = loadImage(comic.getImagePath());
                panel.add(imageLabel);
                titleLabel = new JLabel(comic.getTitle());
                panel.add(titleLabel);
                authorLabel = new JLabel("by " + comic.getAuthor());
                panel.add(authorLabel);
                completedLabel = new JLabel("Completed: " + comic.getCompleted());
                panel.add(completedLabel);
                ratingLabel = new JLabel("Rating: " + comic.getRating());
                panel.add(ratingLabel);
                idLabel = new JLabel("ID: " + comic.getId());
                panel.add(idLabel);
                chapterLabel = new JLabel("Currently on chapter " + comic.getCurrentChapter() +
                        " / " + comic.getTotalChapters());
                panel.add(chapterLabel);
                pinnedLabel = new JLabel("Pinned: " + comic.getPinned());
                panel.add(pinnedLabel);
            }
        }

        JScrollPane scroll = new JScrollPane(panel);
        frame.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));

        frame.add(scroll);
        this.frame.setVisible(true);
    }

    /**
     * Makes a JLabel with an image using the image's URL.
     * @param source URL of the image.
     * @return A JLabel with the image.
     * @throws IOException If the image does not load from the given URL.
     */
    public static JLabel loadImage(String source) throws IOException {
        URL url = new URL(source);
        try {
            BufferedImage image = ImageIO.read(url);
            JLabel comicImage = new JLabel(new ImageIcon(image));
            comicImage.setPreferredSize(new Dimension(169, 300));
            return comicImage;
        } catch (IOException ioe) {
            throw new IOException();
        }
    }
}
