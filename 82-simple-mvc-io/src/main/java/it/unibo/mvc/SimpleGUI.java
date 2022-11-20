package it.unibo.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();

    private SimpleGUI(final Controller controller) {
        frame.setTitle("My first java graphical interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JTextArea text = new JTextArea();
        panel.add(text);
        final JButton save = new JButton("Save");
        panel.add(save, BorderLayout.SOUTH);
        frame.setContentPane(panel);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.saveOnFile(text.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "an error occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) screen.getWidth();
        final int height = (int) screen.getHeight();
        frame.setSize(width / PROPORTION, height / PROPORTION);
        //frame.pack();

        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main (String[] args) {
        final Controller controller = new Controller();
        final SimpleGUI view = new SimpleGUI(controller);
        view.display();
    }
}
