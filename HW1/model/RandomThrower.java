package model;

import java.util.Random;
import java.util.Set;

/**
 * @author Joseph O'Connor
 * Throws a {@link Move} chosen randomly from a provided set of MoveOptions to choose from
 */
public class RandomThrower implements Thrower {

	/**
	 * @param possibleMoveOptions Set of MoveOptions this RandomThrower can randomly choose from to make a Move
	 * @param throwerName the name of this RandomThrower
	 */
	public RandomThrower(Set<MoveOption> possibleMoveOptions, String throwerName) {
		random = new Random();
		this.throwerName = throwerName;
		this.possibleMoveOptions = possibleMoveOptions;
	}

	/**
	 * @see model.Thrower#getName()
	 */
	public String getName() {
		return throwerName;
	}

	/**
	 * @see model.Thrower#throwMove()
	 * The {@link Move} thrown will have been randomly chosen from the set of MoveOptions provided upon construction
	 */
	public Move throwMove() {
		MoveOption moveOption = randomMoveOption();
		return new Move(moveOption, throwerName);
	}

	private MoveOption randomMoveOption() {
		return possibleMoveOptions.stream()
				.skip(random.nextInt(possibleMoveOptions.size())).findFirst()
				.get();
	}

	private Random random;
	protected String throwerName;
	protected Set<MoveOption> possibleMoveOptions;
}
