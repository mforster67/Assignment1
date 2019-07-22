import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Mark Forster
 *
 *         The ErrorPopup class.
 * 
 *         The attributes of the ErrorPopup class are...
 * 
 *         frame	The window frame.
 *         button	The button on which the answer is printed.
 *
 */
public class ErrorPopup extends JFrame {

	private static final long serialVersionUID = 4591740470243521201L;

	ErrorPopup(String errorMessage) {
		JFrame frame = new JFrame("Conversion Error");
		JButton button = new JButton();

		button.setText("Error: " + errorMessage);
		frame.add(button);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}
	
}
