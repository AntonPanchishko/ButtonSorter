/**
 * This application is able to sorting numbers.
 * App is using visualisation on Swing.
 * User is able to enter numbers of sort elements and
 * enter the speed of sorting
 */

package button.sorting;

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
import javax.swing.border.LineBorder;

public class ButtonsSorting extends JFrame implements ActionListener {
    private JFrame frame;
    private int[] buttonValues;
    private List<JButton> buttons;
    private Thread thread;
    private JButton enterButton;
    private JButton sortButton;
    private JButton resetButton;
    private JTextField textArrayLength;
    private JTextField textSpeedSort;
    private JLabel labelArrayLength;
    private JLabel labelSpeedSort;

    /**
     * This is constructor for the start display
     * where you able to enter number of elements
     */
    public ButtonsSorting() {
        super("Amount of elements");
        enterButton = new JButton("Enter");
        enterButton.setBounds(100, 150, 100, 30);
        enterButton.setForeground(Color.BLACK);
        enterButton.addActionListener(this);
        add(enterButton);

        textArrayLength = new JTextField();
        textArrayLength.setBounds(100, 100, 100, 20);
        add(textArrayLength);

        textSpeedSort = new JTextField();
        textSpeedSort.setBounds(650, 250, 100, 20);
        add(textSpeedSort);

        labelArrayLength = new JLabel("How many numbers to display?");
        labelArrayLength.setBounds(60, 50, 180, 20);
        add(labelArrayLength);

        labelSpeedSort = new JLabel("<html>Enter speed <br/> show sort [1;30]</html>");
        labelSpeedSort.setBounds(650, 200, 200, 50);

        setSize(300, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == enterButton) {
            try {
                int arrayLength = Integer.parseInt(textArrayLength.getText());
                if (arrayLength > 50 || arrayLength <= 0) {
                    throw new RuntimeException("You enter incorrect value. "
                            + "Please enter value bigger than 0 and less than 50");
                }
                setVisible(false);
                createFrame();
                generateRandomList(arrayLength);
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(ButtonsSorting.this,
                        "You enter incorrect value. "
                                + "Please enter value bigger than 0 and less than 50");
            }
        }

        if (actionEvent.getSource() == sortButton) {
            try {
                int speedSort = Integer.parseInt(textSpeedSort.getText());
                if (speedSort > 30 || speedSort <= 0) {
                    throw new RuntimeException("You enter incorrect value. "
                            + "Please enter value bigger than 0 and less than 30");
                }
                for (JButton button : buttons) {
                    button.setForeground(Color.BLACK);
                }
                thread = new Thread() {
                    @Override
                    public void run() {
                        quickSort(buttonValues,
                                0, buttonValues.length - 1);
                    }
                };
                thread.start();
                resetButton.setEnabled(false);
                sortButton.setEnabled(false);
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(ButtonsSorting.this,
                        "You enter incorrect value. "
                                + "Please enter value bigger than 0 and less than 30");
            }
        }
        if (actionEvent.getSource() == resetButton) {
            frame.setVisible(false);
            textArrayLength.setText("");
            setVisible(true);
            frame.removeAll();
        }
    }

    /**
     * This method create new display
     * where you able to enter speed of sorting
     * or return in previous display
     */
    private void createFrame() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setVisible(true);

        sortButton = new JButton("Sort");
        sortButton.setBounds(650, 100, 100, 30);
        sortButton.setForeground(Color.BLACK);
        sortButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.setBounds(650, 150, 100, 30);
        resetButton.setForeground(Color.BLACK);
        resetButton.addActionListener(this);

        frame.add(sortButton);
        frame.add(resetButton);
        frame.add(textSpeedSort);
        frame.add(labelSpeedSort);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method generate array of numbers
     * Every element of array it is value of buttons
     * @param arrayLength it is size of new array
     */
    private void generateRandomList(int arrayLength) {
        buttonValues = new int[arrayLength];
        buttons = new ArrayList<>();
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
                                    .this, "This value bigger than 50");
                        } else {
                            frame.setVisible(false);
                            createFrame();
                            generateRandomList(Integer.parseInt(button.getText()));
                        }
                    }
                }
            });
            button.setBounds(x, y, width, height);
            button.setForeground(Color.RED);
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

    /**
     * This method realize quick sort
     * @param buttonValues this is integer array with value of buttons which will be sorted
     * @param from starting index
     * @param to last index
     */
    public void quickSort(int[] buttonValues, int from, int to) {
        if (from < to) {
            int divideIndex = createPartition(buttonValues, from, to);
            quickSort(buttonValues, from, divideIndex - 1);
            quickSort(buttonValues, divideIndex, to);
        } else {
            for (int i = from; i <= to; i++) {
                buttons.get(i).setForeground(Color.BLACK);
            }
        }
        sortButton.setEnabled(true);
        resetButton.setEnabled(true);
    }

    /**
     * This is inner method of quick sorting using last element as pivot,
     * places the pivot element at its correct position in sorted array,
     * smaller than pivot places smaller to left of pivot all greater element to right
     * @param arr array which will be sort
     * @param from starting index
     * @param to ending index
     * @return value of last element
     */
    public int createPartition(int[] arr, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;
        int pivot = arr[from + (to - from) / 2];
        buttons.get(from + (to - from) / 2).setBorder(new LineBorder(Color.pink));

        while (leftIndex <= rightIndex) {
            buttons.get(rightIndex).setBorder(new LineBorder(Color.RED));
            while (arr[leftIndex] < pivot) {
                try {
                    buttons.get(leftIndex).setBorder(new LineBorder(Color.RED));
                    thread.sleep(1000 / Integer
                            .parseInt(textSpeedSort.getText()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buttons.get(leftIndex).setBorder(new LineBorder(Color.BLACK));
                leftIndex++;
            }
            buttons.get(leftIndex).setBorder(new LineBorder(Color.RED));

            while (arr[rightIndex] > pivot) {
                try {
                    buttons.get(rightIndex).setBorder(new LineBorder(Color.RED));
                    thread.sleep(1000 / Integer.parseInt(textSpeedSort.getText()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buttons.get(rightIndex).setBorder(new LineBorder(Color.BLACK));
                rightIndex--;
            }
            buttons.get(rightIndex).setBorder(new LineBorder(Color.RED));

            if (leftIndex <= rightIndex) {
                swapNumbers(arr, rightIndex, leftIndex);
                buttons.get(leftIndex).setBorder(new LineBorder(Color.BLACK));
                buttons.get(rightIndex).setBorder(new LineBorder(Color.BLACK));
                leftIndex++;
                rightIndex--;
            }
        }
        buttons.get(from + (to - from) / 2).setBorder(new LineBorder(Color.GREEN));
        if (leftIndex >= 0) {
            buttons.get(leftIndex).setBorder(new LineBorder(Color.BLACK));
        }
        if (rightIndex >= 0) {
            buttons.get(rightIndex).setBorder(new LineBorder(Color.BLACK));
        }
        return leftIndex;
    }

    /**
     * @param array this is array of swapping elements
     * @param firstIndex index of first element in array which will be swap
     * @param secondIndex index of second element in array which will be swap
     */
    private void swapNumbers(int[] array, int firstIndex, int secondIndex) {
        int tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
        buttons.get(firstIndex)
                .setText(String.valueOf(buttonValues[firstIndex]));
        buttons.get(secondIndex)
                .setText(String.valueOf(buttonValues[secondIndex]));
        buttons.get(firstIndex).setForeground(Color.red);
        buttons.get(secondIndex).setForeground(Color.red);
        try {
            thread.sleep(1000 / Integer.parseInt(textSpeedSort.getText()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buttons.get(firstIndex).setForeground(Color.BLACK);
        buttons.get(secondIndex).setForeground(Color.BLACK);
    }
}
