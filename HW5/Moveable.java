import java.awt.Point;

/**
 * Something which can be moved, put directly at a location, or gotten the
 * location of
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public interface Moveable {
	/**
	 * 
	 * @return The {@link Point} for the current location of this
	 */
	public Point getCurrentLocation();

	/**
	 * Moves this to the given location
	 * 
	 * @param location
	 *            the {@link Point} for the location to which to move this
	 */
	public void setLocation(Point location);

	/**
	 * Translates this some amount with the ability to wrap around a certain
	 * width and height
	 * 
	 * @param wrapWidth
	 *            The width around which this might need to have its location
	 *            "wrapped"
	 * @param wrapHeight
	 *            The height around which this might need to have its location
	 *            "wrapped"
	 */
	public void move(int wrapWidth, int wrapHeight);
}
