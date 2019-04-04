package model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RLESequenceV1 implements RLESequence<Integer, Integer, RLESequenceV1> {
	
	public RLESequenceV1() {
		this(0);
	}
	
	public RLESequenceV1(Integer inLength) {
		initPixels(inLength);
	}

	public RLESequenceV1(Integer... inPixels) {
		initPixels(inPixels.length);
		for (int i = 0; i < inPixels.length; i++) {
			assertIsValid(inPixels[i]);
			pixels.add(inPixels[i]);
		}
	}
	
	public RLESequenceV1(List<Integer> inPixels) {
		for (Integer pixel : inPixels) {
			assertIsValid(pixel);
		}
		// Follows Clone-In Principle
		// Only need to perform shallow copy as each Integer pixel element is immutable
		this.pixels = new ArrayList<Integer>(inPixels);
	}
	
	private void initPixels(Integer length) {
		pixels = new ArrayList<Integer>(length);
	}
	
	private void assertIsValid(Integer pixel) throws IllegalArgumentException {
		if (!isValid(pixel)) {
			throw new IllegalArgumentException("A pixel to be stored in a RLESSequence must be an integer value from 0 (inclusive) to 255 (inclusive).");
		}
	}
	
	private boolean isValid(Integer pixel) {
		return pixel >= 0 && pixel <= 255;
	}

	public boolean contains(Integer pixel) {
		return pixels.contains(pixel);
	}
	
	public boolean containsAll(List<Integer> pixels) {
		return pixels.containsAll(pixels);
	}
	
	public boolean containsAll(Integer... pixels) {
		// The following is more computationally efficient than using #containsAll with Arrays#asList
		for (int i = 0; i < pixels.length; i++) {
			if (!contains(pixels[i])) {
				return false;
			}
		}
		return true;
	}

	public boolean contains(Integer... pixelSequence) {
		int indexInPixelSequence = 0;
		for (int i = 0; i < pixels.size(); i++) {
			if (pixels.get(i).equals(pixelSequence[indexInPixelSequence])) {
				indexInPixelSequence++;
			} else {
				indexInPixelSequence = 0;
			}
			if (indexInPixelSequence == pixelSequence.length) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(List<Integer> pixelSequence) {
		if (pixelSequence.isEmpty()) {
			return true;
		}
		int indexInPixelSequence = 0;
		for (int i = 0; i < pixels.size(); i++) {
			if (pixels.get(i).equals(pixelSequence.get(indexInPixelSequence))) {
				indexInPixelSequence++;
			} else {
				indexInPixelSequence = 0;
			}
			if (indexInPixelSequence == pixelSequence.size()) {
				return true;
			}
		}
		return false;
	}

	public Integer indexOfFirst(Integer pixel) {
		int index = pixels.indexOf(pixel);
		// The following is more computationally efficient than using #contains
		assertIndexFound(index, pixel);
		return index;
	}
	
	private void assertIndexFound(Integer index, Integer pixel) {
		if (index == UNFOUND_INDEX) {
			throwNoSuchElement(pixel);
		}
	}
	
	private void throwNoSuchElement(Integer pixel) throws NoSuchElementException {
		throw new NoSuchElementException(String.format("The RLESequence does not contain the given pixel " + pixel));
	}

	public Integer indexOfLast(Integer pixel) {
		int index = pixels.lastIndexOf(pixel);
		// The following is more computationally efficient than using #contains
		assertIndexFound(index, pixel);
		return index;
	}

	public List<Integer> indexesOf(Integer pixel) {
		
		List<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < pixels.size(); i++) {
			if (pixels.get(i) == pixel) {
				indexes.add(pixel);
			}
		}
		// The following is more computationally efficient than using #contains
		if (indexes.isEmpty()) {
			throwNoSuchElement(pixel);
		}
		return indexes;
	}

	public void insert(Integer newPixel, Integer index) throws IndexOutOfBoundsException, IllegalArgumentException {
		if (isEmpty()) {
			append(newPixel);
		} else {
			assertIsValid(newPixel);
			pixels.add(index, newPixel);
		}
	}

	public void append(Integer newPixel) throws IllegalArgumentException {
		assertIsValid(newPixel);
		pixels.add(newPixel);
	}

	public void prepend(Integer newPixel) throws IllegalArgumentException {
		insert(newPixel, 0);
	}

	public void remove(Integer index) {
		// Must force unboxing of argument passed to index parameter to call the correct List#remove method
		int unboxedIndex = index;
		pixels.remove(unboxedIndex);
	}

	public void removeFirst(Integer pixel) {
		Integer index = indexOfFirst(pixel);
		remove(index);
	}

	public void removeLast(Integer pixel) {
		Integer index = indexOfLast(pixel);
		remove(index);	
	}

	public void removeAll(Integer pixel) {
		// The following is more computationally efficient than using #indexesOf 
		Integer currentIndex = indexOfLast(pixel);
		while (currentIndex != UNFOUND_INDEX) {
			remove(currentIndex);
			currentIndex = pixels.lastIndexOf(pixel);
		}
	}

	public void replaceAt(Integer index, Integer newPixel) throws IndexOutOfBoundsException, IllegalArgumentException {
		assertIsValid(newPixel);
		pixels.set(index, newPixel);
	}

	public void replaceFirst(Integer oldPixel, Integer newPixel) throws IllegalArgumentException, NoSuchElementException {
		Integer index = indexOfFirst(oldPixel);
		replaceAt(index, newPixel);
	}
	
	public void replaceLast(Integer oldPixel, Integer newPixel) throws IllegalArgumentException, NoSuchElementException {
		Integer index = indexOfLast(oldPixel);
		replaceAt(index, newPixel);
	}
	
	public void replaceAll(Integer oldPixel, Integer newPixel) {
		// The following is more computationally efficient than using #indexesOf and #replaceAt
		Integer currentIndex = indexOfFirst(oldPixel);
		while (currentIndex != UNFOUND_INDEX) {
			replaceAt(currentIndex, newPixel);
			currentIndex = pixels.indexOf(oldPixel);
		}
	}

	public Integer getAt(Integer index) throws IndexOutOfBoundsException {
		return pixels.get(index);
	}
	
	public boolean isEmpty() {
		return length() == 0;
	}
	
	public List<Integer> getAll() {
		return new ArrayList<Integer>(pixels);
		// Only need to perform shallow copy as each Integer pixel element is immutable
	}

	public List<Integer> getBetween(Integer startIndex, Integer endIndex) throws IndexOutOfBoundsException {
		return pixels.subList(startIndex, endIndex);
	}
	
	public List<Integer> getFrom(Integer startIndex) throws IndexOutOfBoundsException {
		return getBetween(startIndex, pixels.size());
	}

	public List<Integer> getTo(Integer endIndex) throws IndexOutOfBoundsException {
		return getBetween(0, endIndex);
	}

	public Integer length() {
		return pixels.size();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean containsSubsequence(RLESequence sequence) {
		return contains(sequence.getAll());
	}
	
	public RLESequenceV1 subsequenceFrom(Integer startIndex) {
		return new RLESequenceV1(getFrom(startIndex));
	}

	public RLESequenceV1 subsequenceTo(Integer endIndex) {
		return new RLESequenceV1(getTo(endIndex));
	}

	public RLESequenceV1 subsequenceBetween(Integer startIndex, Integer endIndex) {
		return new RLESequenceV1(getBetween(startIndex, endIndex));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addToHead(RLESequence sequence) {
		pixels.addAll(0, sequence.getAll());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addToTail(RLESequence sequence) {
		pixels.addAll(sequence.getAll());
	}

	public RLESequenceV1 copy() {
		return new RLESequenceV1(pixels);
	}
	
	public boolean equals(Object anotherObj) {
		if (anotherObj instanceof RLESequenceV1) {
			RLESequenceV1 anotherRLESequenceV1 = (RLESequenceV1)anotherObj;
			return this.pixels.equals(anotherRLESequenceV1.pixels);
		}
		return false;
	}
	
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuilder builder = new StringBuilder("[");
		for(Integer pixel : pixels) {
			builder.append(pixel);
			builder.append(" ");
		}
		int currentLength = builder.length();
		builder.replace(currentLength - 1, currentLength, "]");
		return builder.toString();
	}
	
	private List<Integer> pixels;
	
	private static int UNFOUND_INDEX = -1;
}
