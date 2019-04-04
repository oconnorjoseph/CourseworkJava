package model;

import java.util.List;

public interface Searchable<T, N extends Number> {
	
	public boolean contains(T element);
	
	public boolean containsAll(T... elements);
	public boolean containsAll(List<T> elements);
	
	public boolean contains(T... elementSequence);
	public boolean contains(List<T> elementSequence);
	
	public N indexOfFirst(T element);
	public N indexOfLast(T element);
	public List<Integer> indexesOf(T element);
}
