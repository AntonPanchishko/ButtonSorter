import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

public class Main {
    public static void main(String[] args) {
        JFrame frame = getFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        JButton button = new JButton("enter");
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.add(new MyComponent());
            }
        });
        JTextField field = new JTextField();
        frame.add(new MyComponent());
    }

    static class MyComponent extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            List<Integer> list = generateRandomList(17);
            double x = 10;
            double y = 10;
            double w = 70;
            double h = 30;
            int valueForNumberX = 38;
            int valueForNumberY = 30;
            Graphics2D graphics2D = (Graphics2D) g;
            for (int i = 0; i < list.size(); i++) {
                if (i % 10 == 0 && i > 1){
                    x = x + 100;
                    valueForNumberX = valueForNumberX + 100;
                    y = 10;
                    valueForNumberY = 30;
                }
                Rectangle2D rectangle2D = new Rectangle2D.Double(x, y, w, h);
                graphics2D.drawString(String.valueOf((int) (Math.random() * 1000)),
                        valueForNumberX, valueForNumberY);

                y = y + 40;
                valueForNumberY = valueForNumberY + 40;
                graphics2D.draw(rectangle2D);
            }
        }
    }

    static JFrame getFrame() {
        JFrame frame = new JFrame() {
        };
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        return frame;
    }

    public static List<Integer> generateRandomList(int amountOfElement) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < amountOfElement; i++) {
            list.add((int) (Math.random()*1000));
        }
        return list;
    }
}