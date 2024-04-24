/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 23 April 2024
 * Comic.java
 * This class creates a JFrame that allows a user to edit a preexisting comic.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a JFrame that allows a user to edit a preexisting comic.
 */
public class EditFrame {
    private JFrame frame;
    private JPanel panel;

    /**
     * Builds the panel and adds it to the frame.
     */
    public EditFrame() {
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

        // Brings up the comic the user types in when "Submit" is pressed
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame = new JFrame();
                newFrame.setTitle("Edit Page");
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setSize(500, 400);
                newFrame.setLocationRelativeTo(null);
                newFrame.setResizable(true);

                JPanel newPanel = new JPanel(new GridBagLayout());

                final String editComic = comicName.getText();
                frame.dispose();
                int comicIndex = -1;
                for (Comic comic : DatabaseManager.comics) {
                    if (comic.getTitle().equals(editComic)) {
                        comicIndex = DatabaseManager.comics.indexOf(comic);
                    }
                }
                if (comicIndex == -1) {
                    JOptionPane.showMessageDialog(frame, "Comic not found.");
                }
                else {
                    Comic editedComic = DatabaseManager.comics.get(comicIndex);
                    try {
                        JLabel imageLabel;
                        imageLabel = ComicFrame.loadImage(editedComic.getImagePath(),
                                169, 300);
                        GridBagConstraints gbc = ComicFrame.addConstraints(1, 1, 1, 6);
                        newPanel.add(imageLabel, gbc);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Image not found.");
                    }
                    JTextField newImagePath = new JTextField(editedComic.getImagePath(), 10);
                    GridBagConstraints gbc = ComicFrame.addConstraints(1, 8, 1, 1);
                    newPanel.add(newImagePath, gbc);

                    JLabel titleLabel = new JLabel(editedComic.getTitle());
                    gbc = ComicFrame.addConstraints(2, 1, 1, 1);
                    newPanel.add(titleLabel, gbc);

                    JLabel authorLabel = new JLabel("by " + editedComic.getAuthor());
                    gbc = ComicFrame.addConstraints(2, 2, 1, 1);
                    newPanel.add(authorLabel, gbc);

                    JLabel completedLabel = new JLabel("Completed: ");
                    gbc = ComicFrame.addConstraints(2, 3, 1, 1);
                    newPanel.add(completedLabel, gbc);

                    String completedText = editedComic.getCompleted() ? "true" : "false";
                    JTextField newCompleted = new JTextField(completedText, 5);
                    gbc = ComicFrame.addConstraints(3, 3, 1, 1);
                    newPanel.add(newCompleted, gbc);

                    JLabel ratingLabel = new JLabel("Rating:");
                    gbc = ComicFrame.addConstraints(2, 4, 1, 1);
                    newPanel.add(ratingLabel, gbc);

                    JTextField newRating = new JTextField(Integer.toString(editedComic.getRating()), 3);
                    gbc = ComicFrame.addConstraints(3, 4, 1, 1);
                    newPanel.add(newRating, gbc);

                    JLabel idLabel = new JLabel("ID: " + editedComic.getId());
                    gbc = ComicFrame.addConstraints(2, 5, 1, 1);
                    newPanel.add(idLabel, gbc);

                    JLabel chapterLabel = new JLabel("Chapter ");
                    gbc = ComicFrame.addConstraints(2, 6, 1, 1);
                    newPanel.add(chapterLabel, gbc);

                    JTextField newCurrentChapter = new JTextField(Integer.toString(editedComic.getCurrentChapter()), 4);
                    gbc = ComicFrame.addConstraints(3, 6, 1, 1);
                    newPanel.add(newCurrentChapter, gbc);

                    JLabel slashLabel = new JLabel(" / ");
                    gbc = ComicFrame.addConstraints(4, 6, 1, 1);
                    newPanel.add(slashLabel, gbc);

                    JTextField newTotalChapters = new JTextField(Integer.toString(editedComic.getTotalChapters()), 4);
                    gbc = ComicFrame.addConstraints(5, 6, 1, 1);
                    newPanel.add(newTotalChapters, gbc);

                    JLabel pinnedLabel = new JLabel("Pinned: ");
                    gbc = ComicFrame.addConstraints(2, 7, 1, 1);
                    newPanel.add(pinnedLabel, gbc);

                    String pinnedText = editedComic.getPinned() ? "true" : "false";
                    JTextField newPinned = new JTextField(pinnedText, 5);
                    gbc = ComicFrame.addConstraints(3, 7, 1, 1);
                    newPanel.add(newPinned, gbc);

                    JButton submit = new JButton("Submit");

                    // Edits the comic with the user-submitted values when "Submit" is pressed
                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int comicIndex = -1;
                            for (Comic comic : DatabaseManager.comics) {
                                if (comic.getTitle().equals(editComic)) {
                                    comicIndex = DatabaseManager.comics.indexOf(comic);
                                }
                            }

                            try {
                                DatabaseManager.editComics(editComic, "image", newImagePath.getText());
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Image not set.");
                            }

                            try {
                                DatabaseManager.editComics(editComic, "rating", newRating.getText());
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Rating not set.");
                            }

                            try {
                                DatabaseManager.editComics(editComic, "current chapter", newCurrentChapter.getText());
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Current chapter not set.");
                            }

                            try {
                                DatabaseManager.editComics(editComic, "total chapters", newTotalChapters.getText());
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Total chapters not set.");
                            }

                            try {
                                DatabaseManager.editComics(editComic, "completed", newCompleted.getText());
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Completed not set.");
                            }

                            try {
                                DatabaseManager.editComics(editComic, "pinned", newPinned.getText());
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Pinned not set.");
                            }
                            newFrame.dispose();
                        }
                    });
                    gbc = ComicFrame.addConstraints(2, 8, 1, 1);
                    newPanel.add(submit, gbc);

                    newFrame.add(newPanel);
                    newFrame.setVisible(true);
                }
            }
        });

        panel.add(input);
        frame.add(panel);
        this.frame.setVisible(true);
    }
}
