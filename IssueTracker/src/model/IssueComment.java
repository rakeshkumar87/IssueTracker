package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Represents the attributes of a comment on an issue.
 */
public class IssueComment implements Comparable<IssueComment>{

	private String _user;
	private Date _updateTime;
	private String _body;

	public String getUser() {
		return _user;
	}

	public void setUser(String user) {
		this._user = user;
	}

	public Date getUpdateTime() {
		return _updateTime;
	}

	public void setUpdateTime(String updateTime) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			this._updateTime = formatter.parse(updateTime);

		} catch (Exception e) {
			this._updateTime = new Date();
		}
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		this._body = body;
	}

	@Override
	public int compareTo(IssueComment o) {
		// TODO Auto-generated method stub
	    return getUpdateTime().compareTo(o.getUpdateTime());
	}

	@Override
	public String toString() {
		return "\nUser=" + _user + "\nupdateTime=" + _updateTime
				+ "\nbody=" + _body + "\n\n\n\n---------------------------------------------------\n\n";
	}

}
