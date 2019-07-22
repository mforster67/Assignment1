import java.util.ArrayList;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Level;

/**
 * @author Mark Forster
 *
 *         The Binary class.
 * 
 *         The attributes of the Binary class are...
 * 
 *         binaryNumber The original string which represents the binary number.
 *
 */
public class Binary {
	private String binaryNumber;
	static final String HEX_VALUES = "0123456789ABCDEF";
	static final String[] BINARY_VALUES = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000",
			"1001", "1010", "1011", "1100", "1101", "1110", "1111" };

	/**
	 * This constructor sets the initial values of the Binary object attributes
	 * using a series of values provided by the developer.
	 * 
	 * @param binaryNumber The original string which represents the binary number.
	 * 
	 */
	public Binary(String number) {
		binaryNumber = number.trim();
	}

	/**
	 * This method returns the binaryNumber string.
	 * 
	 * @return binaryNumber Is the binaryNumber string.
	 */
	public String getBinaryNumber() {
		return binaryNumber;
	}

	/**
	 * This method checks to see if the binaryNumber string has a valid binary
	 * format.
	 * 
	 * @return valid Is the binaryNumber number format valid or not?
	 */
	public boolean validString() {
		boolean valid = false;
		String regexPattern = "^[01]+$";

		Application.LOGGER.log(Level.INFO, "Validating the format of the binary number " + binaryNumber + ".");

		Pattern regex = Pattern.compile(regexPattern);
		Matcher match = regex.matcher(binaryNumber);

		if (match.find()) {
			valid = true;
		}

		return (valid);

	}

	/**
	 * This method converts a binary number to a hexadecimal number.
	 * 
	 * @return hexNumber The hexadecimal number.
	 */
	public String convertToHexadecimal() throws ArithmeticException {
		StringBuilder hexNumber = new StringBuilder();
		ArrayList<String> remainderList = new ArrayList<String>();
		String hexValues = HEX_VALUES;
		int quotient;
		int remainder;
		int decimal;
		String hexString;

		//
		// Make sure that the decimalNumber string can be successfully
		// be converted to an integer (i.e. check for integer overflow)...
		//
		try {
			Application.LOGGER.log(Level.INFO, "Converting the binary number" + binaryNumber + " to hexadecimal.");
			decimal = this.convertToDecimal();
			if (decimal < 0 ) {
				throw new ArithmeticException();
			}
		} catch (ArithmeticException | NumberFormatException e) {
			String message = "Error: " + binaryNumber + " is too large to convert.";
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

	/**
	 * This method converts a binary number to a decimal number.
	 * 
	 * @return decimalNumber The decimal number.
	 */
	public int convertToDecimal() throws ArithmeticException {
		int decimalNumber = 0;
		int bitValue = 1;

		Application.LOGGER.log(Level.INFO, "Converting the binary number " + binaryNumber + " to decimal.");
		for (int i = binaryNumber.length() - 1; i >= 0; i--) {
			try {
				if (binaryNumber.charAt(i) == '1') {
					decimalNumber = decimalNumber + bitValue;
					if (decimalNumber < 0 ) {
						throw new ArithmeticException();
					}
				}

				bitValue = bitValue * 2;
			} catch (ArithmeticException | NumberFormatException e) {
				String message = "Error: " + binaryNumber + " is too large to convert.";
				Application.LOGGER.log(Level.SEVERE, message);
				throw new ArithmeticException(message);
			}
		}

		Application.LOGGER.log(Level.INFO, "Converted decimal number = " + decimalNumber + ".");
		return (decimalNumber);
	}
}
