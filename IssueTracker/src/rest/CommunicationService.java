package rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 * Utility class to send Http requests. Please change the access token on 403 errors.
 */
public class CommunicationService {

	private URL _url;
	BufferedReader _inputStream;
	HttpsURLConnection _con;

	public boolean init(String url) {
	    try {
			_url = new URL(url);
			_con = (HttpsURLConnection)_url.openConnection();
			//_con.setRequestProperty("Authorization", "token ADD_YOUR_TOKEN");
			_inputStream = new BufferedReader(
		                new InputStreamReader(_con.getInputStream()));

	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// A Hack to display dialogs on http errors. Ideally needs to be propagated via the controller.
		   displayErrorDialog("Unable to connect to server. Verify Connection & Auth token");
		}
        return (_inputStream != null);

	}

	public String receiveResponse() {
		StringBuffer response = new StringBuffer();

	    try {
	    	String inputLine = null;
			while ((inputLine = _inputStream.readLine()) != null)
			    response.append(inputLine);

	        _inputStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// A Hack to display dialogs on http errors.Ideally needs to be propagated via the controller.
			displayErrorDialog("Error while fetching data from server. Verify Connection & Auth token");

		}

        return response.toString();
	}

	// A Hack to display dialogs on http errors. Ideally needs to be propagated via the controller.
	public void displayErrorDialog(final String text) {

        try {
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					try {
						JOptionPane.showMessageDialog(null, text);
				    } catch(Exception e) {
				        e.printStackTrace();
			        }
				}
			} );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
