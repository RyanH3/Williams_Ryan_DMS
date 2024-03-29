import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveFrame {
    private JFrame frame;
    private JPanel panel;

    public RemoveFrame() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        frame.setTitle("Remove Comic");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        panel = new JPanel();

        JLabel inputLabel = new JLabel("Comic: ");
        panel.add(inputLabel);
        JTextField comicName = new JTextField(10);
        panel.add(comicName);
        JButton input = new JButton("Submit");
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeComic = comicName.getText();
                int comicIndex = -1;
                for (Comic comic : DatabaseManager.comics) {
                    if (comic.getTitle().equals(removeComic)) {
                        comicIndex = DatabaseManager.comics.indexOf(comic);
                    }
                }
                DatabaseManager.comics.remove(comicIndex);
                frame.dispose();
            }
        });
        panel.add(input);

        frame.add(panel);
        frame.setVisible(true);
    }
}
