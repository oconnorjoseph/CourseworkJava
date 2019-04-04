import java.awt.Graphics2D;

/**
 * Something that can be drawn
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public interface Drawable {
	/**
	 * Draws this
	 * 
	 * @param graphics2D
	 *            the Graphics2D object to use for drawing this
	 */
	public void draw(Graphics2D graphics2D);

}
