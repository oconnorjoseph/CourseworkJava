package model;

import java.util.ArrayList;
import java.util.List;

import func.RLEConverter;

public class RLESequenceV2 implements RLESequence<Integer, Integer, RLESequenceV2>{

	public RLESequenceV2() {
		this(0);
	}
	
	public RLESequenceV2(Integer inLength) {
		initFieldCodePairs(inLength);
	}

	public RLESequenceV2(Integer... inPixels) {
		initFieldCodePairs(RLEConverter.toSpace(inPixels));
	}
	
	public RLESequenceV2(List<Integer> inPixels) {
		initFieldCodePairs(RLEConverter.toSpace(inPixels));
	}
	
	private boolean isValid(Integer pixel) {
		return pixel >= 0 && pixel <= 255;
	}
	
	private void initFieldCodePairs(Integer length) {
		fieldCodePairs = new ArrayList<FieldCodePair<Integer>>(length);
	}
	
	private void initFieldCodePairs(List<FieldCodePair<Integer>> fieldCodePairs) {
		fieldCodePairs = new ArrayList<FieldCodePair<Integer>>();
		for (FieldCodePair<Integer> fieldCodePair: fieldCodePairs) {
			assertIsValid(fieldCodePair.getElement());
		}
		this.fieldCodePairs = new ArrayList<FieldCodePair<Integer>>(fieldCodePairs);
	}
	
	private void assertIsValid(Integer pixel) throws IllegalArgumentException {
		if (!isValid(pixel)) {
			throw new IllegalArgumentException("A pixel to be stored in a RLESSequence must be an integer value from 0 (inclusive) to 255 (inclusive).");
		}
	}
	
	// As instructed by Step 2, all the following methods just borrow the functionality of RLESequenceV1
	// (and as a result are terribly inefficient)
	
	public boolean contains(Integer pixel) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).contains(pixel);
	}

	public boolean containsAll(Integer... pixels) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).contains(pixels);
	}

	public boolean containsAll(List<Integer> pixels) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).containsAll(pixels);
	}

	public boolean contains(Integer... pixelSequence) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).contains(pixelSequence);
	}

	public boolean contains(List<Integer> pixelSequence) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).contains(pixelSequence);
	}

	public Integer indexOfFirst(Integer pixel) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).indexOfFirst(pixel);
	}

	public Integer indexOfLast(Integer pixel) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).indexOfLast(pixel);
	}

	public List<Integer> indexesOf(Integer pixel) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).indexesOf(pixel);
	}

	public void replaceAt(Integer index, Integer newPixel) {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.replaceAt(index, newPixel);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public void replaceFirst(Integer oldPixel, Integer newPixel) {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.replaceFirst(oldPixel, newPixel);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public void replaceLast(Integer oldPixel, Integer newPixel)  {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.replaceLast(oldPixel, newPixel);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public void replaceAll(Integer oldPixel, Integer newPixel) {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.replaceAll(oldPixel, newPixel);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public void insert(Integer newPixel, Integer index) {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.insert(newPixel, index);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public void append(Integer newPixel) {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.append(newPixel);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public void prepend(Integer newPixel) {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.prepend(newPixel);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public void remove(Integer index) {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.remove(index);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public void removeFirst(Integer pixel) {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.removeFirst(pixel);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public void removeLast(Integer pixel) {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.removeLast(pixel);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public void removeAll(Integer pixel) {
		RLESequenceV1 tempSequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSequence.removeAll(pixel);
		this.fieldCodePairs = RLEConverter.toSpace(tempSequence.getAll());
	}

	public boolean isEmpty() {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).isEmpty();
	}

	public Integer getAt(Integer index) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).getAt(index);
	}

	public List<Integer> getAll() {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).getAll();
	}

	public List<Integer> getFrom(Integer startIndex) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).getFrom(startIndex);
	}

	public List<Integer> getTo(Integer endIndex) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).getTo(endIndex);
	}

	public List<Integer> getBetween(Integer startIndex, Integer endIndex) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).getBetween(startIndex, endIndex);
	}

	public Integer length() {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).length();
	}

	@SuppressWarnings("rawtypes")
	public boolean containsSubsequence(RLESequence sequence) {
		return new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).containsSubsequence(sequence);
	}

	public RLESequenceV2 subsequenceFrom(Integer startIndex) {
		RLESequenceV1 tempSubsequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).subsequenceFrom(startIndex);
		return new RLESequenceV2(tempSubsequence.getAll());
	}

	public RLESequenceV2 subsequenceTo(Integer endIndex) {
		RLESequenceV1 tempSubsequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).subsequenceTo(endIndex);
		return new RLESequenceV2(tempSubsequence.getAll());
	}

	public RLESequenceV2 subsequenceBetween(Integer startIndex, Integer endIndex) {
		RLESequenceV1 tempSubsequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs)).subsequenceBetween(startIndex, endIndex);
		return new RLESequenceV2(tempSubsequence.getAll());
	}

	public void addToHead(RLESequence<?, ?, ?> sequence) {
		RLESequenceV1 tempSubsequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSubsequence.addToHead(sequence);
		fieldCodePairs = RLEConverter.toSpace(tempSubsequence.getAll());
	}

	public void addToTail(RLESequence<?, ?, ?> sequence) {
		RLESequenceV1 tempSubsequence = new RLESequenceV1(RLEConverter.toAPI(fieldCodePairs));
		tempSubsequence.addToTail(sequence);
		fieldCodePairs = RLEConverter.toSpace(tempSubsequence.getAll());
	}

	public RLESequenceV2 copy() {
		return new RLESequenceV2(RLEConverter.toAPI(fieldCodePairs));
	}

	private List<FieldCodePair<Integer>> fieldCodePairs;
	
	public boolean equals(Object anotherObj) {
		if (anotherObj instanceof RLESequenceV2) {
			RLESequenceV2 anotherRLESequenceV2 = (RLESequenceV2)anotherObj;
			return RLEConverter.toAPI(fieldCodePairs).equals(anotherRLESequenceV2.getAll());
		}
		return false;
	}
	
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuilder builder = new StringBuilder("[");
		for (FieldCodePair<Integer> fieldCodePair : fieldCodePairs) {
			builder.append("[" + fieldCodePair.getCount() + " " +  fieldCodePair.getElement() + "]");
			builder.append(" ");
		}
		int currentLength = builder.length();
		builder.replace(currentLength - 1, currentLength, "]");
		return builder.toString();
	}
}
