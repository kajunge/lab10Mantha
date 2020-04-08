package lab10mantha;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author kristinamantha
 *
 * This is the controller class.
 */
public class HTTPAppController {

    private HTTPAppModel model;
    private HTTPAppView view;
    protected File file;
    URL url;

    HTTPAppController(HTTPAppModel model, HTTPAppView view) throws IOException {
        this.model = model;
        this.view = view;
        file = new File("src/File/HTMLFile");

        /**
         * This is the action listener that I created for when the user clicks
         * the submit button. When the user clicks submit, my action listener
         * takes the user's input from the view class and transforms it into a
         * URL. It then opens a new HTTPS URL Connection as well as a buffered
         * writer to write the response to my file, which I called HTMLFile. The
         * InputStreamReader takes the input from the HTTPURLConnection
         * inputStream. While there are lines from the HTTP response, it writes
         * them to the file. Finally, I created a FileReader and a
         * BufferedReader to read my file and to set my JTextArea text to the
         * file contents.
         */
        class SubmitButtonListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                try {
                    url = new URL(view.getHttpField().getText());
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    InputStream input = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(input);
                    BufferedReader in = new BufferedReader(isr);
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        bw.write(inputLine);
                    }
                    in.close();
                    bw.close();

                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    view.getTextArea().read(br, null);
                    br.close();
                    view.getTextArea().requestFocus();

                    /**
                     * Here is where I tried to catch any potential errors
                     * caused by the user. For example, if the user types in a
                     * string of characters it would result in the malformed URL
                     * exception, if they entered an address with the HTTP
                     * protocol when it would require the HTTPS protocol, it
                     * would also throw that error.
                     */
                } catch (MalformedURLException ex) {
                    System.out.println("\n*******************************\n"
                            + "Error! You must enter a"
                            + " properly formatted URL.\n"
                            + "\n" + ex.getMessage()
                            + "\n*******************************\n");
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                } catch (ClassCastException ce) {
                    System.out.println("\n*******************************\n"
                            + "\nYou have entered an HTTP URL, but"
                            + " this URL should show HTTPS.\n"
                            + "\n*******************************\n");
                }
                /**
                 * Here I just put in a print to the output to show the user
                 * input.
                 */
                System.out.println("You typed: " + view.getHttpField().getText());

            }
        }
        /**
         * I also decided to create an action listener to exit on a button
         * click.
         */
        class ExitButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }

        /**
         * I also wanted to make an option for the user to easily make another
         * request, so I created a button that clears the user input in the HTTP
         * field as well as the JTextArea response field.
         */
        class ClearFieldsButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTextArea().setText("");
                view.getHttpField().setText("");
            }
        }
        view.addExitButtonListener(new ExitButtonListener());
        view.addSubmitButtonListener(new SubmitButtonListener());
        view.addClearFieldsButtonListener(new ClearFieldsButtonListener());
    }

}
