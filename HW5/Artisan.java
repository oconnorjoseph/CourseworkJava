import java.applet.Applet;

/**
 * {@link Runnable} that draws each of an internal list of {@link Drawable}s
 * onto an {@link Applet}
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class Artisan extends Activator<Drawable> {

	/**
	 * Constructs an Artisan that can draw the given array of {@link Drawable}
	 * objects onto the given {@link Applet}
	 * 
	 * @param applet
	 *            The Applet onto which to draw the internal list of
	 *            {@link Drawable}s
	 * @param drawables
	 *            {@link Drawable} objects to add to the internal list of
	 *            {@link Drawable}s
	 */
	public Artisan(final Applet applet, Drawable... drawables) {
		super(drawables);
		this.applet = applet;
	}

	/**
	 * @see Runnable#run()
	 */
	public void run() {
		applet.paintAll(applet.getGraphics());
		for (Drawable drawable : observers) {
			drawable.draw(applet.getGraphics());
		}
	}

	private Applet applet;
}
