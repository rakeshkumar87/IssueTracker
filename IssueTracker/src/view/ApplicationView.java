package view;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Main Application view - Displays the repo name and the list of open issues on the repo.
 */
public class ApplicationView extends JFrame {

	JPanel _issuesPanel;

	public ApplicationView(String repoName) {
		_issuesPanel = new JPanel();
		initUI(repoName);
		setVisible(true);
	}

	private void initUI(String repoName) {
		setTitle("Issue Tracker");

		JScrollPane scrollFrame = new JScrollPane(_issuesPanel);
		_issuesPanel.setAutoscrolls(true);
		_issuesPanel.setBackground(Color.black);

        BoxLayout bLayout = new BoxLayout(_issuesPanel, BoxLayout.Y_AXIS);
		_issuesPanel.setLayout(bLayout);

		// Text Field for the repo name
		JTextField repoLabel = new JTextField("List of Issues for " + repoName);
		repoLabel.setEditable(false);
		repoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        repoLabel.setHorizontalAlignment(JTextField.CENTER);

        // Add the repo name and bug attribute(id, updated time, title, body) labels.
		_issuesPanel.add(repoLabel);
		addToIssuePanel(new IssueView());

		add(scrollFrame);

		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		setSize(xSize,ySize);

		setBackground(Color.black);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public IssueView addIssue(String id, String date, String title, String body){
	    IssueView view = new IssueView(id, date, title, body);
	    addToIssuePanel(view);
		return view;
	}

	private void addToIssuePanel(IssueView view) {
		view.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        _issuesPanel.add(view);

	}

}
