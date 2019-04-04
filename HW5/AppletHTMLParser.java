import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses {@link StringFigure} objects out of the HTML document corresponding to
 * a given Applet
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class AppletHTMLParser {

	/**
	 * Parses an array of {@link StringFigure} objects out of the HTML document
	 * corresponding to a given Applet
	 * 
	 * NOTE: HTML should be formatted so that each parameter for a figure is
	 * proceeded by // "figureN", where N denotes that it is a parameter for the
	 * Nth figure and // the first figure has N = 0, and has a suffix that is
	 * one of the following: "color", "fontname", "fontsize", "value",
	 * "location", "velocitymagnitude", "velocitydirection"
	 * 
	 * E.g. figure0color, figure11velocitydirection are valid parameter names
	 * 
	 * @param applet
	 *            the Applet to parse StringFigure objects out of its
	 *            corresponding HTML document
	 * @return Array of StringFigures as specified in the given Applet's HTML
	 *         document
	 */
	public static StringFigure[] parseFigures(Applet applet) {
		List<StringFigure> figures = new ArrayList<StringFigure>();
		for (int i = 0; existsParameterFor(i, applet); i++) {
			StringFigure figure = parseFigure(i, applet);
			figures.add(figure);
		}
		return figures.toArray(new StringFigure[figures.size()]);
	}

	private static StringFigure parseFigure(int index, Applet applet) {

		Velocity velocity = parseVelocity(index, applet);
		String value = parseValue(index, applet);
		Font font = parseFont(index, applet);
		Color color = parseColor(index, applet);
		Rectangle rectangle = parseRectangle(index, font, value, applet);
		return new StringFigure(rectangle, velocity, value, font, color);
	}

	private static Rectangle parseRectangle(int index, Font font, String value,
			Applet applet) {
		Point location = parseLocation(index, applet);
		Rectangle2D bounds = getBounds(font, value, applet);
		return new Rectangle(location.x, location.y, (int) bounds.getWidth(),
				(int) bounds.getHeight());
	}

	private static Rectangle2D getBounds(Font font, String value, Applet applet) {
		Graphics2D graphics2D = (Graphics2D) applet.getGraphics();
		return font.getStringBounds(value, graphics2D.getFontRenderContext());
	}

	private static Color parseColor(int index, Applet applet) {
		String colorParam = getParamFor(index, "color", applet);
		colorParam = colorParam.trim();
		return Color.decode(colorParam);
	}

	private static Font parseFont(int index, Applet applet) {
		String nameParam = getParamFor(index, "fontname", applet);
		String sizeParam = getParamFor(index, "fontsize", applet);
		String name = parseFontName(nameParam);
		int size = parseFontSize(sizeParam);
		return new Font(name, Font.BOLD, size);
	}

	private static int parseFontSize(String sizeParam) {
		sizeParam = sizeParam.trim();
		return Integer.parseInt(sizeParam);
	}

	private static String parseFontName(String nameParam) {
		return nameParam.trim().toLowerCase();
	}

	private static String parseValue(int index, Applet applet) {
		return getParamFor(index, "value", applet);
	}

	private static Velocity parseVelocity(int index, Applet applet) {
		String magnitudeParam = getParamFor(index, "velocitymagnitude", applet);
		String directionParam = getParamFor(index, "velocitydirection", applet);
		float magnitude = parseVelocityMagnitude(magnitudeParam);
		Direction direction = parseVelocityDirection(directionParam);
		return new Velocity(magnitude, direction);
	}

	private static Direction parseVelocityDirection(String directionParam) {
		directionParam = directionParam.replaceAll(" ", "").toLowerCase();
		return Directions.getDirection(directionParam);
	}

	private static float parseVelocityMagnitude(String magnitudeParam) {
		magnitudeParam = magnitudeParam.trim();
		return Float.parseFloat(magnitudeParam);
	}

	private static Point parseLocation(int index, Applet applet) {
		String locationParam = getParamFor(index, "location", applet);
		String[] coordinates = parseCoordinates(locationParam);
		int x = Integer.parseInt(coordinates[0]);
		int y = Integer.parseInt(coordinates[1]);
		return new Point(x, y);
	}

	private static String[] parseCoordinates(String locationParam) {
		locationParam = locationParam.trim().replaceAll("\\(", "")
				.replaceAll("\\)", "");
		String[] coordinates = locationParam.split(",");
		for (int i = 0; i < 2; i++) {
			coordinates[i] = coordinates[i].trim();
		}
		return coordinates;
	}

	private static String getParamFor(int index, String suffix, Applet applet) {
		return applet.getParameter(String.format("figure%d%s", index, suffix));
	}

	private static boolean existsParameterFor(int index, Applet applet) {
		return getParamFor(index, "location", applet) != null;
	}

}
