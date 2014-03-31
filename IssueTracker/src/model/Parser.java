package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Utility class to parse JSON responses.
 */
public class Parser {

	public static List<IssueItem> parseIssues(String data) {
		List<IssueItem> issuesList = new ArrayList<IssueItem>();

        try {
        	JSONParser parser = new JSONParser();
            Object obj= parser.parse(data);
		    JSONArray issuesArray = (JSONArray)obj;
		    int size = issuesArray.size();

		    for (int i = 0; i < size; i++ ) {
		    	JSONObject object = (JSONObject)issuesArray.get(i);

		    	IssueItem issue = new IssueItem();
		    	issue.setId(object.get("id").toString());
		    	issue.setTitle(object.get("title").toString());
		    	issue.setBody(object.get("body").toString());
		    	issue.setCommentsUrl(object.get("comments_url").toString());
                issue.setUpdateTime(object.get("updated_at").toString());
		    	issuesList.add(issue);
		    }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //Collections.sort(issuesList, Collections.reverseOrder());
        return issuesList;
    }

    public static List<IssueComment> parseComments(String data) {
        List<IssueComment> commentsList = new ArrayList<IssueComment>();

        try {
        	JSONParser parser = new JSONParser();
            Object obj= parser.parse(data);
		    JSONArray commentsArray = (JSONArray)obj;
		    int size = commentsArray.size();

		    for (int i = 0; i < size; i++ ) {
		    	JSONObject object = (JSONObject)commentsArray.get(i);

		    	IssueComment comment = new IssueComment();
		    	comment.setBody(object.get("body").toString());
		    	comment.setUpdateTime(object.get("updated_at").toString());

		    	if ( object.get("user") instanceof HashMap ) {
		    	    HashMap<String,String> userInfo = (HashMap<String,String>)object.get("user");
		    	    comment.setUser(userInfo.get("login").toString());
		    	}

		    	commentsList.add(comment);
		    }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Collections.sort(commentsList, Collections.reverseOrder());

        return commentsList;
	}

}
