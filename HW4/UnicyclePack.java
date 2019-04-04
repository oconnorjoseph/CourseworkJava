import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Composite form of {@link Unicycle} in a Composite pattern with the added
 * functionality of being able to set a multiplier on the velocity of all of its
 * children (which adds to other velocity multipliers if they are present)
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class UnicyclePack implements Pack {

	/**
	 * Constructs a new UnicyclePack with no {@link MovingDrawable} children and the
	 * given {@code velocityMultiplier}
	 * 
	 * @param velocityMultiplier
	 *            multiplier applied to the velocities of all of the MovingDrawable
	 *            children children
	 */
	public UnicyclePack(double velocityMultiplier) {
		this.velocityMultiplier = velocityMultiplier;
		children = new ArrayList<MovingDrawable>();
	}

	/**
	 * Draws all child {@link MovingDrawable}s of this composite form, where all
	 * previous shifts by the {@link #translate} function are incorporated
	 * 
	 * @param graphics2D
	 *            the Graphics2D object to use for drawing the MovingDrawable children
	 */
	public void draw(Graphics2D graphics2D) {
		for (MovingDrawable child : children) {
			child.draw(graphics2D);
		}
	}

	/**
	 * Prepares all {@link MovingDrawable} children to be shifted xChange distance in
	 * the positive x-direction (rightward) and yChange distance in the positive
	 * y-direction (downward) for the next time each {@link MovingDrawable} child is
	 * drawn and performs any left-right or up-down wrapping if necessary
	 * 
	 * The velocityMultiplier is applied to both xChange and yChange
	 * 
	 * @param xChange
	 *            the distance to shift the MovingDrawable children in the positive
	 *            x-direction (rightward)
	 * @param yChange
	 *            the distance to shift the MovingDrawable children in the positive
	 *            y-direction (downward)
	 */
	public void translate(int xChange, int yChange) {
		xChange *= velocityMultiplier;
		yChange *= velocityMultiplier;
		
		for (MovingDrawable child : children) {
			child.translate(xChange, yChange);
		}
	}

	/**
	 * @return the maximum x-value after which left-right wrapping will occur for
	 * the first child of this composite form, or 0 if there are no children
	 */
	public int getMaxX() {
		if (children.isEmpty()) {
			return 0;
		} else {
			return children.get(0).getMaxX();
		}
	}

	/**
	 * @return the maximum y-value after which up-down wrapping will occur for
	 * the first child of this composite form, or 0 if there are no children
	 */
	public int getMaxY() {
		if (children.isEmpty()) {
			return 0;
		} else {
			return children.get(0).getMaxY();
		}
	}

	/**
	 * Appends a {@link MovingDrawable} instance to the collection of children
	 * of this composite form of {@link MovingDrawable}
	 * 
	 * @param child
	 *            the {@link MovingDrawable} to add to this composite form of
	 *            {@link MovingDrawable}'s collection of children
	 */
	public void add(MovingDrawable child) {
		children.add(child);
	}

	/**
	 * Removes the {@link MovingDrawable} instance equal to the given argument
	 * from the collection of children of this composite form of
	 * {@link MovingDrawable} (if any of the children do match)
	 * 
	 * @param child
	 *            the {@link MovingDrawable} to remove from this composite form
	 *            of {@link MovingDrawable}'s collection of children
	 */
	public void remove(MovingDrawable child) {
		children.remove(child);
	}

	/**
	 * Gets clones of all {@link MovingDrawable}s in the collection of this
	 * composite form of {@link MovingDrawable}
	 * 
	 * @return array of clones of all {@link MovingDrawable}s in the collection
	 *         of this composite form of {@link MovingDrawable}
	 */
	public MovingDrawable[] getChildren() {
		Object cloneObj = ((ArrayList<MovingDrawable>)children).clone();
		@SuppressWarnings("unchecked")
		ArrayList<MovingDrawable> clone = (ArrayList<MovingDrawable>)cloneObj;
		return clone.toArray(new MovingDrawable[clone.size()]);
	}

	private double velocityMultiplier;
	private List<MovingDrawable> children;

}
