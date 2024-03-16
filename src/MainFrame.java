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

        // Display comics button
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

        // Add comics button
        JButton addComicButton = buildButton("Add Comics");
        addComicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFrame add = new AddFrame();
            }
        });
        panel.add(addComicButton);

        // Edit comic button
        JButton editComicButton = buildButton("Edit Comic");
        editComicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditFrame edit = new EditFrame();
            }
        });
        panel.add(editComicButton);

        // Remove comic button
        JButton removeComicButton = buildButton("Remove Comic");
        removeComicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveFrame edit = new RemoveFrame();
            }
        });
        panel.add(removeComicButton);

        // Exit button
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

    private JButton buildButton(String name) {
        JButton button = new JButton(name);
        return button;
    }
}
