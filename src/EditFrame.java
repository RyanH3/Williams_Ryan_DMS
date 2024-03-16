import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditFrame {
    private JFrame frame;
    private JPanel panel;

    public EditFrame() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        this.frame.setTitle("Edit Comic");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(500, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);

        panel = new JPanel();

        JLabel inputLabel = new JLabel("Comic: ");
        panel.add(inputLabel);
        JTextField comicName = new JTextField(10);
        panel.add(comicName);
        JButton input = new JButton("Submit");

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame = new JFrame();
                newFrame.setTitle("Edit Page");
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setSize(500, 400);
                newFrame.setLocationRelativeTo(null);
                newFrame.setResizable(true);

                JPanel newPanel = new JPanel();

                final String editComic = comicName.getText();
                frame.dispose();
                int comicIndex = -1;
                for (Comic comic : DatabaseManager.comics) {
                    if (comic.getTitle().equals(editComic)) {
                        comicIndex = DatabaseManager.comics.indexOf(comic);
                    }
                }
                JLabel imageLabel = null;
                try {
                    imageLabel = ComicFrame.loadImage(DatabaseManager.comics.get(comicIndex).getImagePath());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                newPanel.add(imageLabel);
                JLabel titleLabel = new JLabel(DatabaseManager.comics.get(comicIndex).getTitle());
                newPanel.add(titleLabel);
                JLabel authorLabel = new JLabel(DatabaseManager.comics.get(comicIndex).getAuthor());
                newPanel.add(authorLabel);
                JLabel completedLabel = new JLabel("Completed: ");
                newPanel.add(completedLabel);
                JTextField newCompleted = new JTextField(5);
                newPanel.add(newCompleted);
                JLabel ratingLabel = new JLabel("Rating:");
                newPanel.add(ratingLabel);
                JTextField newRating = new JTextField(3);
                newPanel.add(newRating);
                JLabel idLabel = new JLabel(Integer.toString(DatabaseManager.comics.get(comicIndex).getId()));
                newPanel.add(idLabel);
                JTextField newCurrentChapter = new JTextField(4);
                newPanel.add(newCurrentChapter);
                JLabel chapterLabel = new JLabel(" / ");
                newPanel.add(chapterLabel);
                JTextField newTotalChapters = new JTextField(4);
                newPanel.add(newTotalChapters);
                JLabel pinnedLabel = new JLabel("Pinned: ");
                newPanel.add(pinnedLabel);
                JTextField newPinned = new JTextField(5);
                newPanel.add(newPinned);
                JButton submit = new JButton("Submit");
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int comicIndex = -1;
                        for (Comic comic : DatabaseManager.comics) {
                            if (comic.getTitle().equals(editComic)) {
                                comicIndex = DatabaseManager.comics.indexOf(comic);
                            }
                        }
                        DatabaseManager.comics.get(comicIndex).setRating(Integer.parseInt(newRating.getText()));
                        DatabaseManager.comics.get(comicIndex).setCurrentChapter(Integer.parseInt(newCurrentChapter.getText()));
                        DatabaseManager.comics.get(comicIndex).setTotalChapters(Integer.parseInt(newTotalChapters.getText()));
                        DatabaseManager.comics.get(comicIndex).setCompleted(Boolean.parseBoolean(newCompleted.getText()));
                        DatabaseManager.comics.get(comicIndex).setPinned(Boolean.parseBoolean(newPinned.getText()));
                        newFrame.dispose();
                    }
                });
                newPanel.add(submit);

                newFrame.add(newPanel);
                newFrame.setVisible(true);
            }
        });

        panel.add(input);
        frame.add(panel);
        this.frame.setVisible(true);
    }
}
