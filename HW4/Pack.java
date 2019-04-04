/**
 * Composite form of {@link MovingDrawable} in a Composite pattern
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public interface Pack extends MovingDrawable {
	/**
	 * Appends a {@link MovingDrawable} instance to the collection of children
	 * of this composite form of {@link MovingDrawable}
	 * 
	 * @param child
	 *            the {@link MovingDrawable} to add to this composite form of
	 *            {@link MovingDrawable}'s collection of children
	 */
	public void add(MovingDrawable child);

	/**
	 * Removes the {@link MovingDrawable} instance equal to the given argument
	 * from the collection of children of this composite form of
	 * {@link MovingDrawable} (if any of the children do match)
	 * 
	 * @param child
	 *            the {@link MovingDrawable} to remove from this composite form
	 *            of {@link MovingDrawable}'s collection of children
	 */
	public void remove(MovingDrawable child);

	/**
	 * Gets clones of all {@link MovingDrawable}s in the collection of this
	 * composite form of {@link MovingDrawable}
	 * 
	 * @return array of clones of all {@link MovingDrawable}s in the collection
	 *         of this composite form of {@link MovingDrawable}
	 */
	public MovingDrawable[] getChildren();
}
