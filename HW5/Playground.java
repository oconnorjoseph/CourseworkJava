import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Displays strings for the moves in a game of RPSLKH of different appearances
 * that move at different velocities as given by a corresponding Playground.HTML
 * document. These strings can collide with one another, and when they do, the
 * winner is displayed and the loser is eliminated.
 * 
 * Requires an HTML file of the form: <applet code="Playground.class"
 * width="xxx" height="yyy"> <param name="parameter1" value="value1"/> <param
 * name="parameter2" value="value2"/> < . . etc . . . /> </applet> Regarding how
 * each parameter should be formatted,
 * 
 * @see {@link AppletHTMLParser}
 * 
 * @author Joseph O'Connor (jgo2115) based on jrk, which is based on cay
 *         horstmann
 */
@SuppressWarnings("serial")
public class Playground extends Applet {

	/**
	 * Sets up the Applet window based on the parameters of the corresponding
	 * Playground.html document
	 */
	@Override
	public void init() {
		StringFigure[] figures = AppletHTMLParser.parseFigures(this);
		Mover mover = new Mover(this, figures);
		Artisan artisan = new Artisan(this, figures);
		RPSReferee referee = new RPSReferee(this, mover, artisan, figures);

		int tickLengthMillis = parseTickLength();
		clockTicker = new ClockTicker(tickLengthMillis, referee, mover, artisan);

	}

	/**
	 * Pauses whatever else is going on on this Applet's window and instead
	 * displays a given message for a given period of time at a given location
	 * 
	 * @param message 
	 * 			The String message to display
	 * @param messageLocation 
	 * 			The {@link Point} denoting the location at which to display the message
	 * @param delayMillis 
	 * 			How long in milliseconds to display the message before resuming 
	 * 			whatever else was going on on the Applet's window
	 */
	public void displayMessage(final String message,
			final Point messageLocation, int delayMillis) {
		this.message = message;
		this.messageLocation = messageLocation;
		clockTicker.interrupt(new Runnable() {
			public void run() {
				isDisplayingMessage = true;
				repaint();
			}
		}, new Runnable() {
			public void run() {
				isDisplayingMessage = false;
			}
		}, delayMillis);
	}

	/**
	 * Paints a message on the Applet's window, if need be
	 */
	@Override
	public void paint(Graphics graphics) {
		if (isDisplayingMessage) {
			graphics.drawString(message, messageLocation.x, messageLocation.y);
		}
	}

	private int parseTickLength() {
		String delayParam = getParameter("delay");
		delayParam = delayParam.trim();
		return Integer.parseInt(delayParam);
	}

	/**
	 * Continues playing the moving strings on the screen
	 */
	@Override
	public void start() {
		clockTicker.start();
	}

	/**
	 * Halts playing the moving string on the screen
	 */
	@Override
	public void stop() {
		clockTicker.stop();
	}

	private ClockTicker clockTicker;

	private boolean isDisplayingMessage;
	private String message = "";
	private Point messageLocation;
}
