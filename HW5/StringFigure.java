import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A string that can move around, be drawn, and collide with other strings
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class StringFigure extends MoveableBoundary implements Drawable {

	/**
	 * Constructs a StringFigure with:
	 * 
	 * @param rectangle
	 *            {@link Rectangle} denoting the boundary of the moving string
	 * @param velocity
	 *            {@link Velocity} at which the moving string flies around
	 * @param value
	 *            Actual string to be displayed
	 * @param font
	 *            {@link Font} with which to display the string
	 * @param color
	 *            {@link Color} to display the string
	 */
	public StringFigure(Rectangle rectangle, Velocity velocity, String value,
			Font font, Color color) {
		super(rectangle, velocity);
		this.value = value;
		this.font = font;
		this.color = color;
	}

	/**
	 * Draws the string via a given {@link Graphics} object
	 * 
	 * @param graphics
	 *            {@link Graphics} to use for drawing the moving string
	 */
	public void draw(Graphics graphics) {
		graphics.setFont(font);
		graphics.setColor(color);
		graphics.drawString(value, getCurrentLocation().x,
				getCurrentLocation().y);
	}

	/**
	 * 
	 * @return The actual string
	 */
	public String getValue() {
		return value;
	}

	private String value;
	private Font font;
	private Color color;

}
