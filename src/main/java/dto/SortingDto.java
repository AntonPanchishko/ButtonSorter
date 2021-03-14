package dto;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SortingDto {
    private int [] buttonValues;
    private List<JButton> buttons;
    private Thread thread;
    private JButton enterButton;
    private JButton sortButton;
    private JButton resetButton;
    private JTextField textArrayLength;
    private JTextField textSpeedSort;
    private JLabel labelArrayLength;
    private JLabel labelSpeedSort;
    private JFrame frame;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getLabelArrayLength() {
        return labelArrayLength;
    }

    public void setLabelArrayLength(JLabel labelArrayLength) {
        this.labelArrayLength = labelArrayLength;
    }

    public JLabel getLabelSpeedSort() {
        return labelSpeedSort;
    }

    public void setLabelSpeedSort(JLabel labelSpeedSort) {
        this.labelSpeedSort = labelSpeedSort;
    }

    public JTextField getTextArrayLength() {
        return textArrayLength;
    }

    public void setTextArrayLength(JTextField textArrayLength) {
        this.textArrayLength = textArrayLength;
    }

    public JTextField getTextSpeedSort() {
        return textSpeedSort;
    }

    public void setTextSpeedSort(JTextField textSpeedSort) {
        this.textSpeedSort = textSpeedSort;
    }

    public JButton getEnterButton() {
        return enterButton;
    }

    public void setEnterButton(JButton enterButton) {
        this.enterButton = enterButton;
    }

    public JButton getSortButton() {
        return sortButton;
    }

    public void setSortButton(JButton sortButton) {
        this.sortButton = sortButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public void setResetButton(JButton resetButton) {
        this.resetButton = resetButton;
    }

    public int[] getButtonValues() {
        return buttonValues;
    }

    public void setButtonValues(int[] buttonValues) {
        this.buttonValues = buttonValues;
    }

    public List<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<JButton> buttons) {
        this.buttons = buttons;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
