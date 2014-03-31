package model;

import java.util.ArrayList;
import java.util.List;

import rest.CommunicationService;

/**
 * Model class to fetch the data from github.
 */
public class ApplicationModel {

	private String _repoUrl;

	public ApplicationModel(String repoUrl) {
		_repoUrl = repoUrl;
	}

	/**
	 * Fetch the list of issues for the given page. All results from gthib are paginated and are
	 * returned 30 at a time.
	 * @param page - for which to fetch the results for.
	 * @return - List of issues.
	 */
	public List<IssueItem> getIssues(int page) {

		List<IssueItem> issues = new ArrayList<IssueItem>();
		CommunicationService service = new CommunicationService();
		boolean connected = service.init(_repoUrl+"?page="+page+"&sort=updated&state=open");
		if ( connected ) {
		    String response = service.receiveResponse();

		    if ( response!= null && response.length() > 0 ) {
		       issues = Parser.parseIssues(response);
		    }

		    System.out.println(issues.toString());
		}

		return issues;
	}

}
