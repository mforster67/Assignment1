import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Mark Forster
 *
 *         The AnswerPopup class.
 * 
 *         The attributes of the AnswerPopup class are...
 * 
 *         frame	The window frame.
 *         button	The button on which the answer is printed.
 *
 */
public class AnswerPopup extends JFrame {

	private static final long serialVersionUID = 7417313117026940257L;

	AnswerPopup(String answerLabel, String answer) {
		JFrame frame = new JFrame("Conversion Answer");
		JButton button = new JButton();

		button.setText(answerLabel + ": " + answer);
		frame.add(button);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}
	
}
