import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mark Forster
 *
 *         The Decimal class.
 * 
 *         The attributes of the Decimal class are...
 * 
 *         decimalNumber The original string which represents the decimal
 *         number.
 *
 */
public class Decimal {
	private String decimalNumber;
	static final String HEX_VALUES = "0123456789ABCDEF";

	/**
	 * This constructor sets the initial values of the Decimal object attributes
	 * using a series of values provided by the developer.
	 * 
	 * @param decimalNumber The original string which represents the decimal number.
	 * 
	 */
	public Decimal(String number) {
		decimalNumber = number.trim();
	}

	/**
	 * This method returns the decimalNumber string.
	 * 
	 * @return decimalNumber Is the decimalNumber string.
	 */	
	public String getDecimalNumber() {
		return decimalNumber;
	}

	/**
	 * This method checks to see if the decimalNumber string has a valid decimal
	 * format.
	 * 
	 * @return valid Is the decimal number format valid or not?
	 */
	public boolean validString() {
		boolean valid = false;
		String regexPattern = "^[0-9]+$";

		Application.LOGGER.log(Level.INFO, "Validating the format of the decimal number " + decimalNumber + ".");

		Pattern regex = Pattern.compile(regexPattern);
		Matcher match = regex.matcher(decimalNumber);

		if (match.find()) {
			valid = true;
		}

		return (valid);
	}

	/**
	 * This method converts a decimal number to a binary number.
	 * 
	 * @return binaryNumber The binary number.
	 */
	public String convertToBinary() throws ArithmeticException {
		StringBuilder binaryNumber = new StringBuilder();
		ArrayList<String> remainderList = new ArrayList<String>();
		int quotient;
		int remainder;
		int decimal;

		//
		//	Make sure that the decimalNumber string can be successfully 
		//	be converted to an integer (i.e. check for integer overflow)...
		//
		try {
			Application.LOGGER.log(Level.INFO, "Converting the decimal number " + decimalNumber + " to binary.");
			decimal = Integer.parseInt(decimalNumber);
		}
		catch (ArithmeticException | NumberFormatException e) {
			String message = "Error: " + decimalNumber + " is too large to convert.";
			Application.LOGGER.log(Level.SEVERE, message);
			throw new ArithmeticException(message);
		}	
		
		//
		// Keep dividing the decimal value by 2 until the quotient
		// is equal 0 each time saving the remainder in a list...
		//
		do {
			quotient = decimal / 2;
			remainder = decimal % 2;

			remainderList.add(Integer.toString(remainder));
			decimal = quotient;
		} while (quotient > 0);

		//
		// Now traverse through the remainder list and add the
		// hex values to the hexNumber string...
		//
		ListIterator<String> remainderIterator = remainderList.listIterator(remainderList.size());
		while (remainderIterator.hasPrevious()) {
			binaryNumber.append(remainderIterator.previous());
		}

		Application.LOGGER.log(Level.INFO, "Converted binary number = " + binaryNumber.toString() + ".");
		return (binaryNumber.toString());
	}

	/**
	 * This method converts a decimal number to a hexadecimal number.
	 * 
	 * @return hexNumber The hexadecimal number.
	 */
	public String convertToHexadecimal() throws ArithmeticException {
		StringBuilder hexNumber = new StringBuilder();
		ArrayList<String> remainderList = new ArrayList<String>();
		String hexValues = HEX_VALUES;
		String hexString;
		int quotient;
		int remainder;
		int decimal;
		
		//
		//	Make sure that the decimalNumber string can be successfully 
		//	be converted to an integer (i.e. check for integer overflow)...
		//		
		try {
			Application.LOGGER.log(Level.INFO, "Converting the decimal number " + decimalNumber + " to hexadecimal.");
			decimal = Integer.parseInt(decimalNumber);
		}
		catch (ArithmeticException | NumberFormatException e) {
			String message = "Error: " + decimalNumber + " is too large to convert.";
			Application.LOGGER.log(Level.SEVERE, message);
			throw new ArithmeticException(message);
		}	

		//
		// Keep dividing the decimal value by 16 until the quotient
		// is equal 0 each time saving the remainder in a list...
		//
		do {
			quotient = decimal / 16;
			remainder = decimal % 16;

			hexString = hexValues.substring(remainder, remainder + 1);
			remainderList.add(hexString);
			decimal = quotient;
		} while (quotient > 0);

		//
		// Now traverse through the remainder list and add the
		// hex values to the hexNumber string...
		//
		ListIterator<String> remainderIterator = remainderList.listIterator(remainderList.size());
		while (remainderIterator.hasPrevious()) {
			hexNumber.append(remainderIterator.previous());
		}

		Application.LOGGER.log(Level.INFO, "Converted hexadecimal number = " + hexNumber.toString() + ".");
		return (hexNumber.toString());
	}
}
