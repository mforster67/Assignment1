import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mark Forster
 *
 *         The Hexadecimal class.
 * 
 *         The attributes of the Hexadecimal class are...
 * 
 *         hexNumber The original string which represents the hexadecimal
 *         number.
 *
 */
public class Hexadecimal {
	private String hexNumber;
	static final String HEX_VALUES = "0123456789ABCDEF";
	static final String[] BINARY_VALUES = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000",
			"1001", "1010", "1011", "1100", "1101", "1110", "1111" };

	/**
	 * This constructor sets the initial values of the Hexadecimal object attributes
	 * using a series of values provided by the developer.
	 * 
	 * @param hexNumber The original string which represents the hexadecimal number.
	 * 
	 */
	public Hexadecimal(String number) {
		hexNumber = number.trim().toUpperCase();
	}

	/**
	 * This method returns the hexNumber string.
	 * 
	 * @return hexNumber Is the hexNumber string.
	 */
	public String getHexNumber() {
		return hexNumber;
	}

	/**
	 * This method checks to see if the hexNumber string has a valid hexadecimal
	 * format.
	 * 
	 * @return valid Is the hexadecimal number format valid or not?
	 */
	public boolean validString() {
		boolean valid = false;
		String regexPattern = "^[0-9A-F]+$";

		Application.LOGGER.log(Level.INFO, "Validating the format of the hex number " + hexNumber + ".");

		Pattern regex = Pattern.compile(regexPattern);
		Matcher match = regex.matcher(hexNumber);

		if (match.find()) {
			valid = true;
		}

		return (valid);

	}

	/**
	 * This method converts a hexadecimal number to a binary number.
	 * 
	 * @return binaryNumber The binary number.
	 */
	public String convertToBinary() {
		StringBuilder binaryNumber = new StringBuilder();

		String validValues = HEX_VALUES;
		String[] binaryValues = BINARY_VALUES;
		int decimalValue;
		String hexChar;

		Application.LOGGER.log(Level.INFO, "Converting the hex number " + hexNumber + " to binary.");
		for (int i = hexNumber.length() - 1; i >= 0; i--) {
			hexChar = Character.toString(hexNumber.charAt(i));
			decimalValue = validValues.indexOf(hexChar);

			StringBuilder tempNumber = new StringBuilder();
			tempNumber.append(binaryValues[decimalValue]);
			tempNumber.append(binaryNumber);
			binaryNumber = tempNumber;
		}

		Application.LOGGER.log(Level.INFO, "Converted binary number = " + binaryNumber.toString() + ".");
		return (binaryNumber.toString());
	}

	/**
	 * This method converts a hexadecimal number to a decimal number.
	 * 
	 * @return decimalNumber The decimal number.
	 */
	public int convertToDecimal() throws ArithmeticException {
		String validValues = HEX_VALUES;
		int decimalNumber = 0;
		int digitValue = 1;
		String hexChar;

		//
		//  Convert the hexadecimal number to a decimal by working
		//  right to left and multiplying each hexadecimal character by a factor of 16
		//  each time adding the result to the existing decimal value.
		//	Also make sure that the digitValue integer does not overflow...
		//	
		Application.LOGGER.log(Level.INFO, "Converting the hex number " + hexNumber + " to decimal.");
		for (int i = hexNumber.length() - 1; i >= 0; i--) {
			try {
				hexChar = Character.toString(hexNumber.charAt(i));
				decimalNumber = decimalNumber + validValues.indexOf(hexChar) * digitValue;
				digitValue = digitValue * 16;	
				if (decimalNumber < 0 ) {
					throw new ArithmeticException();
				}
			}
			catch (ArithmeticException e) {
				String message = "Error: " + hexNumber + " is too large to convert.";
				Application.LOGGER.log(Level.SEVERE, message);
				throw new ArithmeticException(message);
			}	
		}

		Application.LOGGER.log(Level.INFO, "Converted decimal number = " + decimalNumber + ".");
		return(decimalNumber);
}

}
