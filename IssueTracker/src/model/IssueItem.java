package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import rest.CommunicationService;

/**
 *
 * Represents the attributes of an issue.
 */
public class IssueItem implements Comparable<IssueItem>{

	private String _id;
    private String _title;
    private String _body;
	private String _user;
    private String _commentsUrl;
    private List<IssueComment> _comments;
    private Date _updateTime;


	public IssueItem() {
		super();
		this._comments = new ArrayList<IssueComment>();
		this._updateTime = new Date();
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		this._title = title;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		this._body = body;
	}

	public String getUser() {
		return _user;
	}

	public void setUser(String user) {
		this._user = user;
	}

	public String getCommentsUrl() {
		return _commentsUrl;
	}

	public void setCommentsUrl(String commentsUrl) {
		this._commentsUrl = commentsUrl;
	}

	public String getComments() {

		if ( _comments.size() == 0 ) {

			// Probably not the best way to do this.
			try {
		        CommunicationService service = new CommunicationService();
	            boolean connected = service.init(getCommentsUrl());
	            if ( connected ) {
	                String response = service.receiveResponse();
	                if ( response!= null && response.length() > 0 ) {
		                setComments(Parser.parseComments(response));
	                }
	            }
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		return _comments.toString();
	}

	public void setComments(List<IssueComment> comments) {
		this._comments = comments;
	}

    public Date getUpdateTime() {
		return _updateTime;
	}

	public void setUpdateTime(String updateTime) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			this._updateTime = formatter.parse(updateTime);

		} catch (Exception e) {
			this._updateTime = new Date();
		}
	}

	@Override
	public String toString() {
		return "IssueItem [_id=" + _id + ", _title=" + _title + ", _user="
				+ _user + ", _commentsUrl=" + _commentsUrl + ", _updateTime="
				+ _updateTime + "]";
	}


	@Override
	public int compareTo(IssueItem o) {
	    return getUpdateTime().compareTo(o.getUpdateTime());
	}

}
