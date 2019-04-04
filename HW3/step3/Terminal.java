package step3;

/**
 * Enum containing several differentiators for terminal computations 
 * 
 * @see Computation
 * 
 * @author Joseph O'Connor (jgo2115)
 *
 */
public enum Terminal {
	/**
	 * For computing whether or not all items of a group up to a certain point have been the same
	 */
	ALLSAME, 
	/**
	 * For computing the total number of all items of a group up to a certain point
	 */
	COUNT, 
	/**
	 * For computing the minimum of all items of a group up to a certain point
	 */
	MIN, 
	/**
	 * For computing the maximum of all items of a group up to a certain point
	 */
	MAX, 
	/**
	 * For computing the sum of all numeric items of a group up to a certain point
	 */
	SUM;
}
