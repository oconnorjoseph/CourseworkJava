import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/**
 * Icon featuring a {@link MovingDrawable} that requires Java's Graphics2D to be
 * drawn.
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class MovingIcon implements Icon {

	/**
	 * Constructs a new MovingIcon instance given a {@link MovingDrawable}
	 * object and reserves a space of {@code width} by {@code height}
	 * 
	 * @param subject
	 *            the {@link MovingDrawable} object to feature as the icon
	 * @param width
	 *            the width of the space to reserve for this MovingIcon
	 * @param height
	 *            the height of the space to reserve for this MovingIcon
	 * @throws IllegalArgumentException
	 *             if either {@code width} or {@code height} is negative
	 */
	public MovingIcon(MovingDrawable subject, int width, int height) {
		Asserter.assertIsNonNegative(width, "width",
				"the constructor for MovingIcon");
		Asserter.assertIsNonNegative(height, "height",
				"the constructor for MovingIcon");
		this.subject = subject;
		this.width = width;
		this.height = height;
	}

	/**
	 * Paints the icon featuring its {@link MovingDrawable} using the given
	 * Graphics2D {@code graphics2D}
	 * 
	 * @param c
	 *            ARGUMENTS FOR THIS PARAMETER ARE IGNORED
	 * @param graphics2D
	 *            the Graphics2D object to use for drawing the the icon
	 *            featuring its {@link MovingDrawable}
	 * @param x
	 *            ARGUMENTS FOR THIS PARAMETER ARE IGNORED
	 * @param y
	 *            ARGUMENTS FOR THIS PARAMETER ARE IGNORED
	 * @throws IllegalArgumentException
	 *             if the argument for parameter {@code graphics} is not an
	 *             instance of Graphics2D
	 */
	public void paintIcon(Component c, Graphics graphics, int x, int y) {
		Asserter.assertIsGraphics2D(graphics, "graphics",
				"MovingIcon#paintIcon");
		Graphics2D graphics2D = (Graphics2D) graphics;
		paintIcon(graphics2D);
	}

	/**
	 * Paints the icon featuring its {@link MovingDrawable} using the given
	 * Graphics2D {@code graphics2D}
	 * 
	 * @param graphics2D
	 *            the Graphics2D object to use for drawing the the icon
	 *            featuring its {@link MovingDrawable}
	 */
	public void paintIcon(Graphics2D graphics2D) {
		subject.draw(graphics2D);
	}
	
	/**
	 * Returns the width of space reserved for the icon
	 * @return width of the space reserved for the icon
	 */
	public int getIconWidth() {
		return width;
	}
	/**
	 * Returns the height of space reserved for the icon
	 * @return height of the space reserved for the icon
	 */
	public int getIconHeight() {
		return height;
	}

	private int width, height;
	private MovingDrawable subject;

}
