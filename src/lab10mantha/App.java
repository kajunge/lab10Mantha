package lab10mantha;

import java.io.IOException;

/**
 *
 * @author kristinamantha
 * For this lab we were to create an application using the MVC architecture
 * that when a user clicks a JButton, it sends an HTTP Request and a JTextField
 * (or similar JComponent) displays the HTTP results. 
 */
public class App {

    public static void main(String[] args) throws IOException {

        HTTPAppModel model = new HTTPAppModel();
        HTTPAppView view = new HTTPAppView(model);
        HTTPAppController controller = new HTTPAppController(model, view);
        view.setVisible(true);
        
    
    }

}
