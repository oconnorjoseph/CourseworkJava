package model;

/**
 * @author Joseph G O'Connor III
 * Responsible for giving a {@link Move} upon calling {@link #throwMove()}
 */
public interface Thrower {
	/**
	 * @return The name of this Thrower
	 */
	public String getName();

	/**
	 * @return A Move made by this Thrower
	 */
	public Move throwMove();
}
