/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 23 April 2024
 * MainFrame.java
 * This class is the home page of the GUI which leads to all other pages.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Loads ComicFrame, AddFrame, EditFrame, and RemoveFrame and exits the program.
 */
public class MainFrame {
    private JFrame frame;

    /**
     * Builds the panel and adds it to the frame.
     */
    public MainFrame() {
        frame = new JFrame();
        this.frame.setTitle("Main Menu");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(500, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Helvetica", Font.PLAIN, 36));
        panel.add(welcomeLabel);

        // Display all user's comics in a list when "My Comics" is pressed
        JButton myComicsButton = buildButton("My Comics");
        myComicsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ComicFrame();
                } catch (IOException ex) {
                    System.out.println("No image found.");
                }
            }
        });
        panel.add(myComicsButton);

        // Allow a user to add comics from a text file when "Add comics" is pressed
        JButton addComicButton = buildButton("Add Comics");
        addComicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFrame();
            }
        });
        panel.add(addComicButton);

        // Allow a user to edit a comic when "Edit comic" is pressed
        JButton editComicButton = buildButton("Edit Comic");
        editComicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditFrame();
            }
        });
        panel.add(editComicButton);

        // Allow a user to remove a comic when "Remove comic" is pressed
        JButton removeComicButton = buildButton("Remove Comic");
        removeComicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveFrame();
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

    /**
     * Helps make a button using just a name.
     * @param name Name of the button.
     * @return A button object.
     */
    private JButton buildButton(String name) {
        JButton newButton = new JButton(name);
        newButton.setBackground(Color.BLUE);
        newButton.setForeground(Color.WHITE);
        newButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        return newButton;
    }
}
