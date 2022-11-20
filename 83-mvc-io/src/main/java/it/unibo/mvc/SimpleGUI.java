package it.unibo.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;

    private final JFrame frame = new JFrame();

    private SimpleGUI(Controller control) {
        frame.setTitle("mvc-io");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(new BorderLayout());
        final JTextField field = new JTextField();
        panel.add(field, BorderLayout.NORTH);
        final JTextArea area = new JTextArea();
        area.setEditable(false);
        panel.add(area, BorderLayout.CENTER);
        final JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        final JButton print = new JButton("Print");
        buttons.add(print);
        final JButton show = new JButton("Show history");
        buttons.add(show);
        panel.add(buttons, BorderLayout.SOUTH);

        //Listeners
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                control.setNextToPrint(field.getText());
                control.printString();
            }
        });

        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                area.setText(control.getHistory().toString());
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int w = (int) screen.getWidth();
        final int h = (int) screen.getHeight();
        frame.setSize(w / PROPORTION, h / PROPORTION);
        frame.setVisible(true);
    }

    public static void main (String[] args) {
        final SimpleGUI view = new SimpleGUI(new SimpleController()); 
        view.display();
    }
}
