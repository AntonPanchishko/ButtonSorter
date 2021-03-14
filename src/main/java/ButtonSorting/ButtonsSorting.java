package ButtonSorting;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ButtonsSorting extends JFrame implements ActionListener {
    private JFrame frame;
    private JLabel labelArrayLength;
    private JLabel labelSpeedSort;
    private JButton buttonEnter;
    private JButton buttonSort;
    private JButton buttonReset;
    private JTextField arrayLength;
    private JTextField textSpeed;
    private List<JButton> buttons;
    private int speedOfSorting;
    private Thread innerSortThread;
    private List<Integer> valueOfButtons;

    public ButtonsSorting() {
        super("Write parameters");

        buttonEnter = new JButton("Enter");
        buttonEnter.setBounds(100, 150, 100, 30);
        buttonEnter.addActionListener(this);
        add(buttonEnter);

        arrayLength = new JTextField();
        arrayLength.setBounds(100, 100, 100, 20);
        add(arrayLength);

        textSpeed = new JTextField();
        textSpeed.setBounds(585, 150, 130, 20);
        add(textSpeed);

        labelArrayLength = new JLabel("How many numbers to display?");
        labelArrayLength.setBounds(60, 50, 180, 20);
        add(labelArrayLength);

        labelSpeedSort = new JLabel("<html>Enter speed show sort [1;30]</html>");
        labelSpeedSort.setBounds(600, 100, 100, 40);

        setSize(300, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == buttonEnter) {
            try {
                int newArrayLength = Integer.parseInt(arrayLength.getText());
                if (newArrayLength > 50 || newArrayLength <= 0) {
                    throw new RuntimeException();
                }
                setVisible(false);
                createFrame();
                generateRandomList(newArrayLength);
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(ButtonsSorting
                        .this,"Please enter the correct value!");
            }
        }
        if (actionEvent.getSource() == buttonSort) {
            try {
                speedOfSorting = Integer.parseInt(textSpeed.getText());
                if (speedOfSorting > 30 || speedOfSorting <= 0) {
                    throw new RuntimeException();
                }
                for (JButton button : buttons) {
                    button.setBackground(Color.GREEN);
                    button.setForeground(Color.white);
                }
                innerSortThread = new Thread() {
                    @Override
                    public void run() {
                        quickSort(valueOfButtons, 0, valueOfButtons.size() - 1);
                    }
                };
                innerSortThread.start();
                buttonReset.setEnabled(false);
                buttonSort.setEnabled(false);
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(ButtonsSorting
                        .this, "Please enter the correct value!");
            }
        }
    }

    private void generateRandomList(int listSize) {
        int x = 20;
        int y = 20;
        int width = 100;
        int height = 30;
        buttons = new ArrayList<>();
        valueOfButtons = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            if (i % 10 == 0 && i > 0) {
                x = x + 120;
                y = 20;
            }
            int numValue = (int) (Math.random() * 1000);
            valueOfButtons.add(numValue);
            JButton button = new JButton(String.valueOf(numValue));
            /*button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == button) {
                        if (Integer.parseInt(button.getText()) > 30) {
                            JOptionPane.showMessageDialog(ButtonsSorting
                                    .this,"Please enter the correct value!");
                        } else {
                            frame.setVisible(false);
                            createFrame();
                            generateRandomList(Integer.parseInt(button.getText()));
                        }
                    }
                }
            });*/
            button.setBounds(x, y, width,height);
            button.setForeground(Color.RED);
            button.setBackground(Color.white);
            button.setOpaque(true);
            buttons.add(button);
            frame.add(button);
            y = y + 40;
        }
    }

    public int partition(List<Integer> list, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;
        int pivot = list.get(from + (to - from) / 2);
        buttons.get(from + (to - from) / 2).setBackground(Color.pink);
        buttons.get(from + (to - from) / 2).setBorder(new LineBorder(Color.pink));

        while (leftIndex <= rightIndex) {
            buttons.get(rightIndex).setBorder(new LineBorder(Color.RED));

            while (list.get(leftIndex) < pivot) {
                try {
                    buttons.get(leftIndex).setBorder(new LineBorder(Color.RED));
                    innerSortThread.sleep(1000 / speedOfSorting);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buttons.get(leftIndex).setBorder(new LineBorder(Color.white));
                leftIndex++;
            }
            buttons.get(leftIndex).setBorder(new LineBorder(Color.RED));

            while (list.get(leftIndex) > pivot) {
                try {
                    buttons.get(rightIndex).setBorder(new LineBorder(Color.RED));
                    innerSortThread.sleep(1000 / speedOfSorting);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buttons.get(rightIndex).setBorder(new LineBorder(Color.white));
                rightIndex--;
            }
            buttons.get(rightIndex).setBorder(new LineBorder(Color.RED));

            if (leftIndex <= rightIndex) {
                buttonSwap(valueOfButtons, rightIndex, leftIndex);
                buttons.get(leftIndex).setBorder(new LineBorder(Color.white));
                buttons.get(rightIndex).setBorder(new LineBorder(Color.white));
                leftIndex++;
                rightIndex--;
            }
        }
        buttons.get(from + (to - from) / 2).setBackground(Color.GREEN);
        buttons.get(from + (to - from) / 2).setBorder(new LineBorder(Color.GREEN));
        if (leftIndex >= 0) {
            buttons.get(leftIndex).setBorder(new LineBorder(Color.white));
        }
        if (rightIndex >= 0) {
            buttons.get(rightIndex).setBorder(new LineBorder(Color.white));
        }
        return leftIndex;
    }

    public void quickSort(List<Integer> list, int from, int to) {
        if (from < to) {
            int divideIndex = partition(list, from, to);

            quickSort(list, from, divideIndex - 1);

            quickSort(list, divideIndex, to);
        } else {
            for (int i = from; i <= to; i++) {
                buttons.get(i).setBackground(Color.BLUE);
                buttons.get(i).setForeground(Color.white);
            }
        }
        if (buttons.get(valueOfButtons.size() - 1).getBackground() == Color.BLUE) {
            buttonReset.setEnabled(true);
            buttonSort.setEnabled(true);
        }
    }

    private void buttonSwap(List<Integer> list, int firstIndex, int secondIndex) {
        buttons.get(firstIndex).setText(String.valueOf(list.get(firstIndex)));
        buttons.get(secondIndex).setText(String.valueOf(list.get(secondIndex)));
        buttons.get(firstIndex).setForeground(Color.RED);
        buttons.get(secondIndex).setForeground(Color.RED);
        try {
            innerSortThread.sleep(1000/ speedOfSorting);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buttons.get(firstIndex).setForeground(Color.white);
        buttons.get(secondIndex).setForeground(Color.white);
    }

    private void createFrame() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setVisible(true);
        buttonSort = new JButton("Sort");
        buttonSort.setBackground(Color.GREEN);
        buttonSort.setBounds(600, 180, 100, 30);
        add(buttonSort);

        buttonReset = new JButton("Reset");
        buttonReset.setBounds(600, 230, 100, 30);
        buttonReset.setBackground(Color.GREEN);
        add(buttonReset);
        frame.add(buttonSort);
        frame.add(buttonReset);
        frame.add(textSpeed);
        frame.add(labelSpeedSort);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
