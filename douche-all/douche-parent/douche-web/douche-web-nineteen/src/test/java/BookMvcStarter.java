import java.io.FileNotFoundException;
import java.net.URISyntaxException;




/**
 * @author ertuo
 */

public class BookMvcStarter {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JettyWebStarter webStarter = new JettyWebStarter();
        try {
			webStarter.start();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //webStarter.startWAR();
    }
   
}
