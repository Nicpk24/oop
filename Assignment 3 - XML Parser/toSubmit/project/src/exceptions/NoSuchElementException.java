package exceptions;

/**
 * NoSuchElementException will be thrown when a ADT is trying trying to handle null data.
 * @author Nic Kelly
 *
 */
public class NoSuchElementException extends Exception {

	/**
	 * The no-argument constructor for a NoSuchElementException.
	 */
	public NoSuchElementException() {
		super("No element found");
	}
}
