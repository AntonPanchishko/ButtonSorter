package button.sorting;

import dto.SortingDto;
import exception.IncorrectInputException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import sort.util.ArrayQuickSorting;

public class ButtonsSorting extends JFrame implements ActionListener {
    private final SortingDto sortingDto;
    private JFrame frame;

    public ButtonsSorting() {
        super("Amount of elements");
        sortingDto = new SortingDto();

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(100,150,100, 30);
        enterButton.setBackground(Color.blue);
        enterButton.setForeground(Color.white);
        enterButton.addActionListener(this);
        sortingDto.setEnterButton(enterButton);
        add(enterButton);

        JTextField textArrayLength = new JTextField();
        textArrayLength.setBounds(100, 100, 100, 20);
        sortingDto.setTextArrayLength(textArrayLength);
        add(textArrayLength);

        JTextField textSpeedSort = new JTextField();
        textSpeedSort.setBounds(650, 250, 100, 20);
        sortingDto.setTextSpeedSort(textSpeedSort);

        JLabel labelArrayLength = new JLabel("How many numbers to display?");
        labelArrayLength.setBounds(60, 50, 180, 20);
        sortingDto.setLabelArrayLength(labelArrayLength);
        add(labelArrayLength);

        JLabel labelSpeedSort = new JLabel("<html>Enter speed <br/> show sort [1;30]</html>");
        labelSpeedSort.setBounds(650, 200, 200,50);
        sortingDto.setLabelSpeedSort(labelSpeedSort);

        setSize(300,300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == sortingDto.getEnterButton()) {
            try {
                int arrayLength = Integer.parseInt(sortingDto.getTextArrayLength().getText());
                if (arrayLength > 50 || arrayLength <= 0) {
                    throw new IncorrectInputException("You enter incorrect value. "
                            + "Please enter value bigger than 0 and less than 50");
                }
                setVisible(false);
                createFrame();
                sortingDto.setFrame(frame);
                generateRandomList(arrayLength);
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(ButtonsSorting.this,
                        "You enter incorrect value. "
                        + "Please enter value bigger than 0 and less than 50");
            }
        }

        if (actionEvent.getSource() == sortingDto.getSortButton()) {
            try {
                int speedSort = Integer.parseInt(sortingDto.getTextSpeedSort().getText());
                if (speedSort > 30 || speedSort <= 0) {
                    throw new IncorrectInputException("You enter incorrect value. "
                            + "Please enter value bigger than 0 and less than 30");
                }
                for (JButton button: sortingDto.getButtons()) {
                    button.setBackground(Color.GREEN);
                    button.setForeground(Color.white);
                }
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        ArrayQuickSorting.quickSortUtil(sortingDto.getButtonValues(),
                                0, sortingDto.getButtonValues().length - 1, sortingDto);
                    }
                };
                thread.start();
                sortingDto.getResetButton().setEnabled(false);
                sortingDto.getSortButton().setEnabled(false);
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(ButtonsSorting.this,
                        "You enter incorrect value. "
                        + "Please enter value bigger than 0 and less than 30");
            }
        }
        if (actionEvent.getSource() == sortingDto.getResetButton()) {
            frame.setVisible(false);
            sortingDto.getTextArrayLength().setText("");
            setVisible(true);
            frame.removeAll();
        }
    }

    private void createFrame() {
        frame = new JFrame();
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setVisible(true);

        JButton sortButton = new JButton("Sort");
        sortButton.setBounds(650,100,100, 30);
        sortButton.setForeground(Color.white);
        sortButton.setBackground(Color.GREEN);
        sortButton.addActionListener(this);
        sortingDto.setSortButton(sortButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(650,150,100, 30);
        resetButton.setForeground(Color.white);
        resetButton.setBackground(Color.GREEN);
        resetButton.addActionListener(this);
        sortingDto.setResetButton(resetButton);

        frame.add(sortButton);
        frame.add(resetButton);
        frame.add(sortingDto.getTextSpeedSort());
        frame.add(sortingDto.getLabelSpeedSort());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void generateRandomList(int arrayLength) {
        int [] buttonValues = new int[Integer.parseInt(sortingDto.getTextArrayLength().getText())];
        sortingDto.setButtonValues(buttonValues);

        List<JButton> buttons = new ArrayList<>();
        sortingDto.setButtons(buttons);
        int x = 20;
        int y = 20;
        int width = 100;
        int height = 30;
        for (int i = 0; i < arrayLength; i++) {
            if (i % 10 == 0 && i > 0) {
                x = x + 120;
                y = 20;
            }
            buttonValues[i] = (int) (Math.random() * 1000);
            JButton button = new JButton(String.valueOf(buttonValues[i]));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == button) {
                        if (Integer.parseInt(button.getText()) > 50) {
                            JOptionPane.showMessageDialog(ButtonsSorting
                                    .this,"This value bigger than 50");
                        } else {
                            frame.setVisible(false);
                            createFrame();
                            generateRandomList(Integer.parseInt(button.getText()));
                        }
                    }
                }
            });
            button.setBounds(x, y, width,height);
            button.setForeground(Color.RED);
            button.setBackground(Color.BLUE);
            button.setOpaque(true);
            frame.add(button);
            buttons.add(button);
            y += 40;
        }
        int number = (int) (Math.random() * arrayLength);
        buttonValues[number] = (int) (Math.random() * 29) + 1;
        buttons.get(number).setText(buttonValues[number] + "");
        frame.repaint();
    }
}
