/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 1 April 2024
 * MainFrame.java
 * This class is the home page of the GUI which leads to all other pages.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame {
    private JFrame frame;

    public MainFrame() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        this.frame.setTitle("Main Menu");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(500, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel welcomeLabel = new JLabel("Welcome to the Comic DMS!");
        panel.add(welcomeLabel);

        // Display all user's comics in a list when "My Comics" is pressed
        JButton myComicsButton = buildButton("My Comics");
        myComicsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ComicFrame comics = new ComicFrame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(myComicsButton);

        // Allow a user to add comics from a text file when "Add comics" is pressed
        JButton addComicButton = buildButton("Add Comics");
        addComicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFrame add = new AddFrame();
            }
        });
        panel.add(addComicButton);

        // Allow a user to edit a comic when "Edit comic" is pressed
        JButton editComicButton = buildButton("Edit Comic");
        editComicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditFrame edit = new EditFrame();
            }
        });
        panel.add(editComicButton);

        // Allow a user to remove a comic when "Remove comic" is pressed
        JButton removeComicButton = buildButton("Remove Comic");
        removeComicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveFrame edit = new RemoveFrame();
            }
        });
        panel.add(removeComicButton);

        // Close the GUI when "Exit" is pressed
        JButton exitButton = buildButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);

        this.frame.add(panel);
        this.frame.setVisible(true);
    }

    /*
     * Method Name: buildButton
     * Purpose: Helps make a button using just a name.
     * Parameters: String
     * Returns: JButton
     */
    private JButton buildButton(String name) {
        JButton button = new JButton(name);
        return button;
    }
}
