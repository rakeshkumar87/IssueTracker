package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

/**
 * Comments View - Displays all the comments for a given bug.
 */
public class CommentsView extends JFrame {

	public CommentsView(String title, String comments) {
        initUI(title, comments);
	}

	private void initUI(String title, String comments) {

		setTitle(title);

		JTextArea textArea = new JTextArea(comments, 20, 60);
		textArea.setEditable(false);
		textArea.setBorder(null);
		textArea.setForeground(UIManager.getColor("Label.foreground"));
		textArea.setFont(UIManager.getFont("Label.font"));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scroll = new JScrollPane ( textArea );
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

	    add(scroll);

	    pack();
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}
