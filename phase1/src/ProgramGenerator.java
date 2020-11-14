
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProgramGenerator implements ActionListener{
    int count = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    public ProgramGenerator(){
        frame = new JFrame();

        JButton button = new JButton("log in");
        button.addActionListener(this);

        label = new JLabel("test");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30 , 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new ProgramGenerator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count ++;
        label.setText("test" + count);

    }
}