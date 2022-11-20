package it.unibo.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final static int PROPORTION = 5;

    private final JFrame frame = new JFrame();

    private SimpleGUIWithFileChooser(Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My second java graphical interface");

        final JPanel panel1 = new JPanel();
        frame.setContentPane(panel1);
        panel1.setLayout(new BorderLayout());
        final JTextArea writingArea = new JTextArea();
        panel1.add(writingArea);
        final JButton save = new JButton("Save");
        panel1.add(save, BorderLayout.SOUTH);

        final JPanel panel2 = new JPanel();
        panel1.add(panel2, BorderLayout.NORTH);
        panel2.setLayout(new BorderLayout());
        final JTextField showFile = new JTextField("Current file path");
        showFile.setText(controller.getPath());
        showFile.setEditable(false);
        final JButton browse = new JButton("Browse...");
        panel2.add(showFile, BorderLayout.CENTER);
        panel2.add(browse, BorderLayout.LINE_END);

        //Listeners
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.saveOnFile(writingArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "an error occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                final JFileChooser chooser = new JFileChooser("Choose where to save");
                chooser.setSelectedFile(controller.getFile());
                final int result = chooser.showSaveDialog(frame);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newDest = chooser.getSelectedFile();
                        controller.setFileAsCurrent(newDest.getPath());
                        showFile.setText(newDest.getPath());
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, null, "An error occurred", JOptionPane.ERROR_MESSAGE);
                }
            }  
        });
    }

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int w = (int) screen.getWidth();
        final int h = (int) screen.getHeight();
        frame.setSize(w / PROPORTION, h / PROPORTION);
        frame.setVisible(true);
    }

    public static void main (String[] args) {
        final SimpleGUIWithFileChooser view = new SimpleGUIWithFileChooser(new Controller());
        view.display();
    }
}
