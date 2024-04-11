/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 10 April 2024
 * Comic.java
 * This class creates a JFrame that allows a user to edit a preexisting comic.
 */

import javax.swing.*;
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

                JPanel newPanel = new JPanel();

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
                    try {
                        JLabel imageLabel;
                        imageLabel = ComicFrame.loadImage(DatabaseManager.comics.get(comicIndex).getImagePath());
                        newPanel.add(imageLabel);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Image not found.");
                    }

                    JLabel titleLabel = new JLabel(DatabaseManager.comics.get(comicIndex).getTitle());
                    newPanel.add(titleLabel);
                    JLabel authorLabel = new JLabel("by " + DatabaseManager.comics.get(comicIndex).getAuthor());
                    newPanel.add(authorLabel);
                    JLabel completedLabel = new JLabel("Completed: ");
                    newPanel.add(completedLabel);
                    JTextField newCompleted = new JTextField(5);
                    newPanel.add(newCompleted);
                    JLabel ratingLabel = new JLabel("Rating:");
                    newPanel.add(ratingLabel);
                    JTextField newRating = new JTextField(3);
                    newPanel.add(newRating);
                    JLabel idLabel = new JLabel("ID: " + DatabaseManager.comics.get(comicIndex).getId());
                    newPanel.add(idLabel);
                    JLabel chapterLabel = new JLabel("Chapter ");
                    newPanel.add(chapterLabel);
                    JTextField newCurrentChapter = new JTextField(4);
                    newPanel.add(newCurrentChapter);
                    JLabel slashLabel = new JLabel(" / ");
                    newPanel.add(slashLabel);
                    JTextField newTotalChapters = new JTextField(4);
                    newPanel.add(newTotalChapters);
                    JLabel pinnedLabel = new JLabel("Pinned: ");
                    newPanel.add(pinnedLabel);
                    JTextField newPinned = new JTextField(5);
                    newPanel.add(newPinned);
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
                    newPanel.add(submit);

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
