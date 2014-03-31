package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.SwingUtilities;

import view.ApplicationView;
import model.ApplicationModel;
import model.IssueItem;

/**
 * Controller object to bind model and view. This controller object is responsible for loading the
 * issues in the background and propagating upto the view to display.
 *
 */
public class ApplicationController {

	private ApplicationModel _model;
	private ApplicationView _view;

	/**
	 * Instantiates the controller object.
	 * @param model
	 * @param view
	 */
	public ApplicationController(ApplicationModel model, ApplicationView view) {
        _model = model;
        _view = view;
	}

	/**
	 * Loads the first 30 issues on the event thread and the rest of them in the
	 * background.
	 */
	public void load() {

		// Load the first 30 issues in the foreground
		List<IssueItem> issuesList = _model.getIssues(1);
		for ( IssueItem issue : issuesList ) {
	        new CommentController(issue, _view.addIssue(issue.getId(), issue.getUpdateTime().toString(), issue.getTitle(), issue.getBody()));
	    }

		// Load the rest of the issues in the background
		Thread loadThread = new Thread(new Runnable() {
	         public void run()
	         {
	        	 List<IssueItem> issuesList;
	        	 int page = 2;

                 while ( (issuesList = _model.getIssues(page)).size() > 0 ) {
                	 addIssues(issuesList);
                	 page++;

                 }

             }
	    });

		loadThread.setDaemon(true);
		loadThread.start();
	}

	/**
	 * Adds the issuesList on to the issue panel from the event thread.
	 * @param issuesList - List of issues to add to the view.
	 */
	public void addIssues(final List<IssueItem> issuesList) {

        try {
			SwingUtilities.invokeAndWait( new Runnable() {
				@Override
				public void run() {
					try {
						    for ( IssueItem issue : issuesList ) {
					    	    new CommentController(issue, _view.addIssue(issue.getId(), issue.getUpdateTime().toString(), issue.getTitle(), issue.getBody()));
					        }
						} catch(Exception e) {
						    e.printStackTrace();
					}
				}
			} );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
