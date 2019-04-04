import java.awt.Graphics2D;

/**
 * A figure that can be drawn with Java's Graphics2D and moved
 * (translated) from its current position
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public interface MovingDrawable extends Drawable, Movable {

	/**
	 * Draws the figure incorporating all previous shifts by the
	 * {@link #translate} function
	 * 
	 * @param graphics2D
	 *            the Graphics2D object to use for drawing the figure
	 */
	public void draw(Graphics2D graphics2D);

	/**
	 * Prepares the figure to be shifted xChange distance in the positive
	 * x-direction (rightward) and yChange distance in the positive y-direction
	 * (downward) for the next time the figure is drawn
	 * 
	 * @param xChange
	 *            the distance to shift the figure in the positive x-direction
	 *            (rightward)
	 * @param yChange
	 *            the distance to shift the figure in the positive y-direction
	 *            (downward)
	 */
	public void translate(int xChange, int yChange);
	
	/**
	 * @return the maximum y-value after which left-right wrapping, if any, will occur
	 */
	public int getMaxX();
	
	/**
	 * @return the maximum y-value after which up-down, if any, wrapping will occur
	 */
	public int getMaxY();
}
