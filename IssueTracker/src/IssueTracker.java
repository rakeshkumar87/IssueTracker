

import javax.swing.SwingUtilities;

import controller.ApplicationController;
import model.ApplicationModel;
import view.ApplicationView;

/**
 * Application entrypoint. Instantiates model, view, controllers and kicks of the bindings
 * in the UI thread.
 */
public class IssueTracker {

	private static String RAILS_REPO = "Rails Repo";
	private static String RAILS_ISSUES_URL = "https://api.github.com/repos/rails/rails/issues";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater( new Runnable() {

        	@Override
        	public void run() {
        		try {
        		    ApplicationView view = new ApplicationView(RAILS_REPO);
        		    ApplicationModel model = new ApplicationModel(RAILS_ISSUES_URL);
        		    ApplicationController controller = new ApplicationController(model, view);
        		    controller.load();
        		} catch(Exception e) {
        			e.printStackTrace();
        		}
        	}
        } );
	}

}
