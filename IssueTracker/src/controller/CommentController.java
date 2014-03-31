package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.IssueItem;

import view.IssueView;

/**
 * Responsible for binding the model and view to display the comments when the user clicks on an issueId. Extends
 * the MouseAdapter class to listen on the mouse events.
 */
public class CommentController extends MouseAdapter {

	IssueItem _issueModel;
	IssueView _issueView;


	public CommentController(IssueItem issueModel, IssueView issueView) {
		_issueModel = issueModel;
		_issueView = issueView;
	    _issueView.getId().addMouseListener(this);
	}

	/**
	 * Displays the comments when the user clicks on an issue.
	 */
	public void mouseClicked(MouseEvent me)
    {
        _issueView.displayComments(_issueModel.getComments());
    }
}
