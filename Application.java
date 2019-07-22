import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Mark Forster
 *
 *         The Application class.
 * 
 *         The attributes of the Application class are...
 * 
 *         frame	The window frame.
 *         panel	The panel on which the other UI components are grouped.
 *
 *
 */	
public class Application implements ActionListener {
	static final Logger LOGGER = Logger.getLogger(Application.class.getName());
	static String logFileName = "logfile.txt";
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton bin2DecButton = new JButton("Bin to Dec");
	JButton bin2HexButton = new JButton("Bin to Hex");
	JButton dec2BinButton = new JButton("Dec to Bin");
	JButton dec2HexButton = new JButton("Dec to Hex");
	JButton hex2BinButton = new JButton("Hex to Bin");
	JButton hex2DecButton = new JButton("Hex to Dec");

	public static void main(String[] args) {

		FileHandler fh;
		try {
			//
			//	Setup the logging mechanism...
			//
			fh = new FileHandler(logFileName);
			LOGGER.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			LOGGER.info("Running number conversion program.");
			//
			//	Display the UI...
			//
			new Application().createUI();
			LOGGER.info("Closing number conversion program.");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void createUI() {
		frame.setVisible(true);
		frame.setTitle("Number Systems");
		frame.setLocationRelativeTo(null);

		frame.add(panel);
		panel.add(bin2DecButton);
		panel.add(bin2HexButton);
		panel.add(dec2BinButton);
		panel.add(dec2HexButton);
		panel.add(hex2BinButton);
		panel.add(hex2DecButton);
		frame.pack();	
		
		bin2DecButton.addActionListener(this);
		bin2HexButton.addActionListener(this);
		dec2BinButton.addActionListener(this);
		dec2HexButton.addActionListener(this);
		hex2BinButton.addActionListener(this);
		hex2DecButton.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ((e.getSource() == bin2DecButton) || (e.getSource() == bin2HexButton)) {
		    Menu popup = new Menu();
		    String binary = JOptionPane.showInputDialog(popup, "Enter a binary number...", "Input Dialog", JOptionPane.INFORMATION_MESSAGE);
		    
		    Binary binaryNumber = new Binary(binary);
		    boolean validNumber = binaryNumber.validString();
		    
		    if (validNumber) {
		    	try {
			    	if (e.getSource() == bin2DecButton) {	
			    		@SuppressWarnings("unused")
						AnswerPopup valuePopup = new AnswerPopup("Decimal Value", Integer.toString(binaryNumber.convertToDecimal()));	
			    	}
			    	else if (e.getSource() == bin2HexButton) {
			    		@SuppressWarnings("unused")
						AnswerPopup valuePopup = new AnswerPopup("Hexadecimal Value", binaryNumber.convertToHexadecimal());	
			    	}		    	
		    	}
				catch (ArithmeticException ex) {
					@SuppressWarnings("unused")
		    		ErrorPopup errorPopup = new ErrorPopup(ex.getMessage());					
				}		
		    }
		    else {
		    	@SuppressWarnings("unused")
	    		ErrorPopup errorPopup = new ErrorPopup(binary + " is an INVALID binary number !!!");		    	
		    }		    
		}
		else if ((e.getSource() == dec2BinButton) || (e.getSource() == dec2HexButton)) {
		    Menu popup = new Menu();
		    String decimal = JOptionPane.showInputDialog(popup, "Enter a decimal number...", "Input Dialog", JOptionPane.INFORMATION_MESSAGE);
		    
		    Decimal decimalNumber = new Decimal(decimal);
		    boolean validNumber = decimalNumber.validString();
		    
		    if (validNumber) {
		    	try {
			    	if (e.getSource() == dec2BinButton) {
			    		@SuppressWarnings("unused")
						AnswerPopup valuePopup = new AnswerPopup("Binary Value", decimalNumber.convertToBinary());			    		 		
			    	}
			    	else if (e.getSource() == dec2HexButton) {
			    		@SuppressWarnings("unused")
						AnswerPopup valuePopup = new AnswerPopup("Hexadecimal Value", decimalNumber.convertToHexadecimal());			    		  		
			    	}	    		
		    	}
				catch (ArithmeticException ex) {
					@SuppressWarnings("unused")
		    		ErrorPopup errorPopup = new ErrorPopup(ex.getMessage());						
				}			    			    	
		    }
		    else {
		    	@SuppressWarnings("unused")
	    		ErrorPopup errorPopup = new ErrorPopup(decimal + " is an INVALID decimal number !!!");		    	
		    }		    
		}
		else if ((e.getSource() == hex2BinButton) || (e.getSource() == hex2DecButton)) {
		    Menu popup = new Menu();
		    String hexadecimal = JOptionPane.showInputDialog(popup, "Enter a hexadecimal number...", "Input Dialog", JOptionPane.INFORMATION_MESSAGE);
		    
		    Hexadecimal hexNumber = new Hexadecimal(hexadecimal);
		    boolean validNumber = hexNumber.validString();
		    
		    if (validNumber) {
		    	try {
			    	if (e.getSource() == hex2DecButton) {
			    		@SuppressWarnings("unused")
						AnswerPopup valuePopup = new AnswerPopup("Decimal Value", Integer.toString(hexNumber.convertToDecimal()));
			    	}
			    	else if (e.getSource() == hex2BinButton) {
			    		@SuppressWarnings("unused")
						AnswerPopup valuePopup = new AnswerPopup("Binary Value", hexNumber.convertToBinary());			    		
					    System.out.println("Binary Value = " + hexNumber.convertToBinary());	    		
			    	}			    	
		    	}
				catch (ArithmeticException ex) {
					@SuppressWarnings("unused")
		    		ErrorPopup errorPopup = new ErrorPopup(ex.getMessage());	
				}	
		    }
		    else {
	    		@SuppressWarnings("unused")
	    		ErrorPopup errorPopup = new ErrorPopup(hexadecimal + " is an INVALID hexadecimal number !!!");		    	
		    }
		    
		}			
		
	}
}
