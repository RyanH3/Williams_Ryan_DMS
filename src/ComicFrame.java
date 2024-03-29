import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ComicFrame {
    private JFrame frame;
    private JPanel panel;
    private JLabel imageLabel, titleLabel, authorLabel, completedLabel, ratingLabel, idLabel, chapterLabel, pinnedLabel;

    public ComicFrame() throws IOException {
        initialize();
    }

    public void initialize() throws IOException {
        frame = new JFrame();
        this.frame.setTitle("My Comics");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(500, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);
        panel = new JPanel();

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

    public static JLabel loadImage(String source) throws IOException {
        URL url = new URL(source);
        BufferedImage image = ImageIO.read(url);
        return new JLabel(new ImageIcon(image));
    }
}
