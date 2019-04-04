import java.applet.Applet;

/**
 * {@link Runnable} that moves each of an internal list of {@link Moveable}s 
 * around an {@link Applet}
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class Mover extends Activator<Moveable> {

	/**
	 * Constructs a Mover that can move the given array of {@link Moveable}
	 * around the given {@link Applet}
	 * 
	 * @param applet
	 *            The Applet around which to draw the internal list of
	 *            {@link Moveable}s
	 * @param moveables
	 *            {@link Moveable} objects to add to the internal list of
	 *            {@link Moveable}s
	 */
	public Mover(final Applet applet, Moveable... moveables) {
		super(moveables);
		this.applet = applet;
	}

	/**
	 * @see Runnable#run()
	 */
	public void run() {
		int wrapWidth = applet.getWidth();
		int wrapHeight = applet.getHeight();
		for (Moveable moveable : observers) {
			moveable.move(wrapWidth, wrapHeight);
		}
	}

	private Applet applet;
}
