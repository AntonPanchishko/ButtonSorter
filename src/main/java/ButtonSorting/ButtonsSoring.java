package ButtonSorting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonsSoring extends JFrame implements ActionListener {
    private JFrame frame;
    private JLabel labelArrayLength;
    private JLabel labelSpeedSort;
    private JButton buttonEnter;
    private JButton buttonSort;
    private JButton buttonReset;
    private JTextField arrayLength;
    private JTextField textSpeed;
    private List<Integer> numbers;
    private int speedOfSorting;

    public ButtonsSoring() {
        super("Write parameters");

        buttonEnter = new JButton("Enter");
        buttonEnter.setBounds(100, 140, 100, 30);
        add(buttonEnter);

        /*buttonSort = new JButton("Sort");
        buttonSort.setBounds(200, 200, 100, 30);
        add(buttonSort);

        buttonReset = new JButton("Reset");
        buttonReset.setBounds(400, 300, 100, 30);
        add(buttonReset);*/

        arrayLength = new JTextField();
        arrayLength.setBounds(100, 100, 100, 20);
        add(arrayLength);

        /*textSpeed = new JTextField();
        textSpeed.setBounds(200, 200, 100, 20);
        add(textSpeed);*/

        labelArrayLength = new JLabel("How many numbers to display?");
        labelArrayLength.setBounds(60, 50, 180, 20);
        add(labelArrayLength);

        labelSpeedSort = new JLabel("<html>Enter speed show sort [1;30]</html>");
        labelSpeedSort.setBounds(1200, 200, 100,40);

        setSize(300,300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == buttonEnter) {
            setVisible(true);
            createFrame();
        }
    }

    private void createFrame() {
        frame = new JFrame();
        frame.setSize(1200,1000);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(buttonSort);
        frame.add(buttonReset);
        frame.add(textSpeed);
        frame.add(labelSpeedSort);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ButtonsSoring();
    }
}
