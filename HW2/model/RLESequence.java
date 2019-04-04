package model;

public interface RLESequence<T, N extends Number, S extends RLESequence<T, N, S>>
	extends Searchable<T, N>, Modifiable<T, N>, Retrievable<T, N> {
	
	public N length();
	
	public boolean containsSubsequence(RLESequence<?, ?, ?> sequence);
	
	public S subsequenceFrom(N startIndex);
	public S subsequenceTo(N endIndex);
	public S subsequenceBetween(N startIndex, N endIndex);
	
	public void addToHead(RLESequence<?, ?, ?> sequence);
	public void addToTail(RLESequence<?, ?, ?> sequence);
	
	public S copy();
}
