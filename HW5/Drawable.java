import java.awt.Graphics;

/**
 * Something which can be drawn given some {@link Graphics} object
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public interface Drawable {

	/**
	 * Draws this using the given {@link Graphics}
	 * 
	 * @param graphics
	 *            The Graphics object to use for drawing this
	 */
	public void draw(Graphics graphics);
}
