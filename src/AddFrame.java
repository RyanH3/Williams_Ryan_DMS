import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddFrame {
    private JFrame frame;
    private JPanel panel;

    public AddFrame() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        this.frame.setTitle("Add Comics");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(500, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);

        panel = new JPanel();
        JLabel inputLabel = new JLabel("Comic list path: ");
        panel.add(inputLabel);
        JTextField path = new JTextField(20);
        panel.add(path);
        JButton input = new JButton("Submit");
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = path.getText();
                DatabaseManager.addComics(filePath);
                frame.dispose();
            }
        });
        panel.add(input);

        frame.add(panel);
        this.frame.setVisible(true);
    }
}
