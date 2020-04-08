package lab10mantha;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author kristinamantha
 * 
 * The view class.
 */
public class HTTPAppView extends JFrame {

    /**
     * These are all of my variables. I've got the text field for user input
     * the text area for the response, and I made the text area scroll-able 
     * since the amount of information returned was a lot. I've got the 
     * submit, exit, and clear buttons. And I made a color for the background
     * using the RGB color code I found on a Penn State site.
     */
    private final HTTPAppModel model;
    private JTextField httpField;
    private JTextArea textArea;
    private final JScrollPane scroll;
    private final JLabel label;
    private final int rows = 30;
    private final int cols = 40;
    private final JButton submitButton;
    private final JButton exitButton;
    private final JButton clearFields;
    private final Color PennSlate = new Color(49, 77, 100);

    HTTPAppView(HTTPAppModel model) throws IOException {
        this.model = model;

        label = new JLabel("Please enter HTTP address:");
        label.setForeground(Color.white);
        httpField = new JTextField("", 20);
        textArea = new JTextArea("", rows, cols);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scroll = new JScrollPane(textArea);
        submitButton = new JButton("SUBMIT");
        exitButton = new JButton("EXIT");
        clearFields = new JButton("Clear Text Fields");

        /**
         * everything is added to the panel
         */
        JPanel panel = new JPanel();
        panel.add(label);       
        panel.add(httpField);
        panel.add(submitButton);
        panel.add(clearFields);
        panel.add(scroll);
        panel.add(exitButton);
        panel.setBackground(PennSlate);
        panel.setOpaque(true);
        panel.setPreferredSize(new Dimension(500, 600));
        this.setContentPane(panel);
        this.setResizable(false);
        this.pack();
        this.setTitle("IST 411 Lab 10");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 
     * @param al 
     * 
     * all of the action listeners are here.
     */
    public void addSubmitButtonListener(ActionListener al) {
        submitButton.addActionListener(al);
    }

    public void addExitButtonListener(ActionListener al) {
        exitButton.addActionListener(al);
    }

    public void addClearFieldsButtonListener(ActionListener al) {
        clearFields.addActionListener(al);
    }

    public void setTextArea(String text) {
        getTextArea().setText(text);
    }

    /**
     * @return the httpField
     */
    public JTextField getHttpField() {
        return httpField;
    }

    /**
     * @param httpField the httpField to set
     */
    public void setHttpField(JTextField httpField) {
        this.httpField = httpField;
    }

    /**
     * @return the textArea
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * @param textArea the textArea to set
     */
    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

}
