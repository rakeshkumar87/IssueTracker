package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;


public class IssueView extends JPanel {

	JTextArea _id;
	JTextArea _date;
	JTextArea _title;
	JTextArea _body;

	public IssueView(String id, String date, String title, String body) {
		initUI(id, date, title, body);
	}

	public IssueView() {
		initHeader("Bug-Id", "Update Time", "Title", "Body( First 140 Chars)");
	}

	private void initHeader(String id, String date, String title, String body){

		_id = newTextField(id, 1, 10, false);
		_date = newTextField(date, 1,  20, false);
		_title = newTextField(title, 1, 20, false);
		_body = newTextField(body, 1, 40, false);

		add(_id);
		add(_date);
		add(_title);
		add(_body);

		setBackground(Color.white);
		setLayout(new FlowLayout());
	}

	private void initUI(String id, String date, String title, String body){

		body = body.length() > 140 ? body.substring(0, 139) : body;

		_id = newTextField(id, 1, 10, true);
		_date = newTextField(date, 2,  20, false);
		_title = newTextField(title, 2, 20, false);
		_body = newTextField(body, 4, 40, false);

		add(wrapInScroll(_id));
		add(wrapInScroll(_date));
		add(wrapInScroll(_title));
		add(wrapInScroll(_body));

		setBackground(Color.white);
		setLayout(new FlowLayout());
	}

	private JScrollPane wrapInScroll(JTextArea textArea) {
		JScrollPane scroll = new JScrollPane ( textArea );
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    return scroll;
	}

	private JTextArea newTextField(String text, int rows, int cols, boolean selectable) {
		JTextArea textArea = new JTextArea(text, rows, cols);
		textArea.setEditable(false);
		textArea.setBorder(null);
		textArea.setForeground(UIManager.getColor("Label.foreground"));
		textArea.setFont(UIManager.getFont("Label.font"));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);


	    if (selectable) {
	        textArea.setForeground(Color.BLUE);
	    }

		return textArea;
	}


	public void displayComments(String comments) {
		CommentsView cView = new CommentsView(_title.getText(), comments);
		cView.setVisible(true);
	}

	public JTextArea getId() {
		return _id;
	}

}
