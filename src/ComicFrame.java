/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 23 April 2024
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

        DatabaseManager.comics.clear();
        DatabaseManager.readComics();
        DatabaseManager.comics.sort(Comparator.comparing(Comic::getId));

        panel = new JPanel(new GridLayout(DatabaseManager.comics.size(), 1));

        // Print the pinned comic first
        for (Comic comic : DatabaseManager.comics) {
            if (comic.getPinned()) {
                panel.add(printComic(comic));
            }
        }
        // Print the unpinned comics
        for (Comic comic : DatabaseManager.comics) {
            if (!comic.getPinned()) {
                panel.add(printComic(comic));
            }
        }

        JScrollPane scroll = new JScrollPane(panel);
        frame.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));

        frame.add(scroll);
        this.frame.setVisible(true);
    }

    /**
     * Creates a GridBagConstraints object to position an element.
     * @param gridx Where on the x-axis of the grid the element is.
     * @param gridy Where on the y-axis of the grid the element is.
     * @param gridWidth How many rows the elements takes up.
     * @param gridHeight How many columns the elements takes up.
     * @return A GridBagConstraints object.
     */
    public static GridBagConstraints addConstraints(int gridx, int gridy, int gridWidth, int gridHeight) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;

        return gbc;
    }

    /**
     * Makes a JLabel with an image using the image's URL.
     * @param source URL of the image.
     * @param width Width of the image.
     * @param height Height of the image.
     * @return A JLabel with the image.
     * @throws IOException If the image does not load from the given URL.
     */
    public static JLabel loadImage(String source, int width, int height) throws IOException {
        URL url = new URL("https://qph.cf2.quoracdn.net/main-qimg-1a4bafe2085452fdc55f646e3e31279c-lq");
        try {
            url = new URL(source);
            BufferedImage image = ImageIO.read(url);
            Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            return new JLabel(new ImageIcon(resizedImage));
        } catch (IOException ioe) {
            throw new IOException();
        }
    }

    /**
     * Makes a JPanel with all the comics in it for the GUI to display.
     * @param comic Comic object to make the panel.
     * @return JPanel to be added to the Comic list.
     */
    public static JPanel printComic(Comic comic) {
        JPanel newPanel = new JPanel(new GridBagLayout());

        JLabel imageLabel = null;
        try {
            imageLabel = loadImage(comic.getImagePath(), 169, 300);
        } catch (IOException e) {
            imageLabel = new JLabel("Image not found");
        }
        GridBagConstraints gbc = addConstraints(1, 1, 1, 5);
        newPanel.add(imageLabel, gbc);

        JLabel titleLabel = new JLabel(comic.getTitle());
        gbc = addConstraints(2, 1, 1, 1);
        newPanel.add(titleLabel, gbc);

        JLabel authorLabel = new JLabel("by " + comic.getAuthor());
        gbc = addConstraints(2, 2, 1, 1);
        newPanel.add(authorLabel, gbc);

        String completedText = comic.getCompleted() ? "Completed" : "Not completed";
        JLabel completedLabel = new JLabel(completedText);
        gbc = addConstraints(2, 3, 1, 1);
        newPanel.add(completedLabel, gbc);

        JLabel ratingLabel = new JLabel("Rating: " + comic.getRating());
        gbc = addConstraints(3, 3, 1, 1);
        newPanel.add(ratingLabel, gbc);

        JLabel idLabel = new JLabel("ID: " + comic.getId());
        gbc = addConstraints(2, 5, 1, 1);
        newPanel.add(idLabel, gbc);

        JLabel chapterLabel = new JLabel("Currently on chapter " + comic.getCurrentChapter() +
                " / " + comic.getTotalChapters());
        gbc = addConstraints(2, 4, 1, 1);
        newPanel.add(chapterLabel, gbc);

        String pinPath = comic.getPinned() ? "https://static.vecteezy.com/system/resources/previews/021/494/531/original/push-pin-icon-in-gradient-colors-thumbtack-signs-illustration-png.png" : "https://upload.wikimedia.org/wikipedia/commons/5/59/Empty.png";
        JLabel pinnedLabel = null;
        try {
            pinnedLabel = loadImage(pinPath, 32, 32);
        } catch (IOException e) {
            pinnedLabel = new JLabel("N/A");
        }
        gbc = addConstraints(3, 1, 1, 1);
        newPanel.add(pinnedLabel, gbc);

        return newPanel;
    }
}
