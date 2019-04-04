import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Service provider class for functions related to assertions and
 * argument-input-checking
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class Asserter {

	/**
	 * Throws an IllegalArgumentException if given {@code val} is negative with
	 * a description containing the name of the function and function's
	 * parameter in question
	 * 
	 * @param val
	 *            the actual int value to assert is non-negative
	 * @param paramName
	 *            the name of the function's parameter into which {@code val}
	 *            was originally passed
	 * @param functionName
	 *            the name of the function into which {@code val} was originally
	 *            passed
	 * @throws IllegalArgumentException
	 *             if {@code val} is negative
	 */
	public static void assertIsNonNegative(int val, String paramName,
			String functionName) {
		if (val < 0) {
			throw new IllegalArgumentException(
					String.format(
							"Argument for parameter \"%s\" of %s% must be non-negative.",
							paramName, functionName));
		}
	}

	/**
	 * Throws an IllegalArgumentException if given {@code val} is less than 1
	 * with a description containing the name of the function and function's
	 * parameter in question
	 * 
	 * @param val
	 *            the actual int value to assert is greater than 0
	 * @param paramName
	 *            the name of the function's parameter into which {@code val}
	 *            was originally passed
	 * @param functionName
	 *            the name of the function into which {@code val} was originally
	 *            passed
	 * @throws IllegalArgumentException
	 *             if {@code val} is less than 1
	 */
	public static void assertIsPositive(int val, String paramName,
			String functionName) {
		if (val < 0) {
			throw new IllegalArgumentException(
					String.format(
							"Argument for parameter \"%s\" of %s% must be non-negative.",
							paramName, functionName));
		}
	}

	/**
	 * Throws an IllegalArgumentException if given {@code graphics} is not
	 * actually a Graphics2D object with a description containing the name of
	 * the function and function's parameter in question
	 * 
	 * @param graphics
	 *            the Graphics object to assert is actually an instance of
	 *            Graphics2D
	 * @param paramName
	 *            the name of the function's parameter into which
	 *            {@code graphics} was originally passed
	 * @param functionName
	 *            the name of the function into which {@code graphics} was
	 *            originally passed @ throws IllegalArgumentException if
	 *            {@code graphics} is not actually an instance of Graphics2D
	 */
	public static void assertIsGraphics2D(Graphics graphics, String paramName,
			String functionName) {
		if (!(graphics instanceof Graphics2D)) {
			throw new IllegalArgumentException(
					String.format(
							"Argument for parameter \"%s\" of %s% must be of type Graphics2D.",
							paramName, functionName));
		}
	}
}
