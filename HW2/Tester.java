import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.RLESequence;
import model.RLESequenceV1;
import model.RLESequenceV2;


public class Tester {
	private static final Integer[] TEST_PIXELS = {0, 255, 1, 2, 3, 4, 5, 5, 254, 253, 252, 251};
	private static final Integer[] TEST_PIXELS_SUBARRAY = {0, 255, 1, 2};
	private static final Integer[] DIFFERENT_TEST_PIXELS = {72, 73};
	
	public static void executeAllTests() {
		testEmptySequenceV1();
		testOnePixelSequenceV1();
		testBasicSequenceV1();
		
		testEmptySequenceV2();
		testOnePixelSequenceV2();
		testBasicSequenceV2();
	}
	
	private static void testBasicSequenceV2() {
		System.out.println("The following regards a sequence initialized to have one elements of " + TEST_PIXELS.toString() + ":");
		RLESequenceV2 sequence = new RLESequenceV2(TEST_PIXELS);
		testAllHappyCases(sequence, TEST_PIXELS[0], DIFFERENT_TEST_PIXELS[0], TEST_PIXELS_SUBARRAY, 
				DIFFERENT_TEST_PIXELS[0], DIFFERENT_TEST_PIXELS[1]);
		testRLESequenceV2HappyCases(sequence, new RLESequenceV2(TEST_PIXELS_SUBARRAY), new RLESequenceV2(DIFFERENT_TEST_PIXELS));
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void testOnePixelSequenceV2() {
		for (int pixel : TEST_PIXELS) {
			System.out.println("The following regards a sequence initialized to have one element of " + pixel + ":");
			RLESequenceV2 onePixelSequence = new RLESequenceV2(new Integer[] {pixel});
			testAllHappyCases(onePixelSequence, pixel, DIFFERENT_TEST_PIXELS[0], new Integer[] {pixel}, 
					DIFFERENT_TEST_PIXELS[0], DIFFERENT_TEST_PIXELS[1]);
			testRLESequenceV2HappyCases(onePixelSequence, new RLESequenceV2(), new RLESequenceV2(DIFFERENT_TEST_PIXELS));
			System.out.println();
			System.out.println();
			System.out.println();
		}
		
	}
	
	private static void testEmptySequenceV2() {
		System.out.println("The following regards a sequence initialized to be empty");
		RLESequenceV2 emptySequence = new RLESequenceV2();
		testAllHappyCases(emptySequence, 0, 0, new Integer[0], 72, 73);
		testRLESequenceV2HappyCases(emptySequence, new RLESequenceV2(), new RLESequenceV2(TEST_PIXELS));
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void testRLESequenceV2HappyCases(RLESequenceV2 sequence, RLESequenceV2 containedSubsequence, RLESequenceV2 nonContainedSubsequence) {
		testContainsSubsequenceV2(sequence, containedSubsequence);
		int length = sequence.length();
		
		System.out.println("That should have returned true");
		testContainsSubsequenceV2(sequence, nonContainedSubsequence);
		System.out.println("That should have returned false");
		
		testSubsequenceFromV2(sequence, 0);
		System.out.println("That should have returned the original sequence");
		if (length > 1) {
			testSubsequenceFromV2(sequence, 1);
			System.out.println("That should have returned a sequence missing the original sequence's first index");
			testSubsequenceFromV2(sequence, length - 1);
			System.out.println("That should have returned a sequence of just the last element");
		}
		
		testSubsequenceToV2(sequence, length);
		System.out.println("That should have returned the original sequence");
		if (length > 1) {
			testSubsequenceFromV2(sequence, length - 1);
			System.out.println("That should have returned a sequence missing the original sequence's last index");
			testSubsequenceFromV2(sequence, 1);
			System.out.println("That should have returned a sequence of just the first element");
		}
		
		testSubsequenceBetweenV2(sequence, 0, length);
		System.out.println("That should have returned the original sequence");
		if (length > 1) {
			testSubsequenceBetweenV2(sequence, 1, length);
			System.out.println("That should have returned a sequence missing the original sequence's first index");
			testSubsequenceBetweenV2(sequence, 0, length - 1);
			System.out.println("That should have returned a sequence missing the original sequence's last index");
			testSubsequenceBetweenV2(sequence, 0, 1);
			System.out.println("That should have returned a sequence of just the first element");
			testSubsequenceBetweenV2(sequence, length - 1, length);
			System.out.println("That should have returned a sequence of just the last element");
		}
		
		testCopyV2(sequence);
		testAddToHeadV2(sequence, nonContainedSubsequence);
		testAddToTailV2(sequence, nonContainedSubsequence);
		testCopyV2(sequence);
	}
	
	private static void testCopyV2(RLESequenceV2 sequence) {
		System.out.println("#copy returns the following sequence: ");
		System.out.println(sequence.copy().toString());
	}
	
	private static void testAddToTailV2(RLESequenceV2 sequence, RLESequenceV2 sequenceToAdd) {
		System.out.println("#addToTail with sequence of " + sequenceToAdd + " makes the sequence now: ");
		sequence.addToTail(sequenceToAdd);
		System.out.println(sequence.toString());
	}
	
	private static void testAddToHeadV2(RLESequenceV2 sequence, RLESequenceV2 sequenceToAdd) {
		System.out.println("#addToHead with sequence of " + sequenceToAdd + " makes the sequence now: ");
		sequence.addToHead(sequenceToAdd);
		System.out.println(sequence.toString());
	}
	
	private static void testSubsequenceBetweenV2(RLESequenceV2 sequence, Integer startIndex, Integer endIndex) {
		System.out.println("#subsequenceBetween with startIndex of " + startIndex + " and endIndex of " + endIndex + " returns the following: ");
		System.out.println(sequence.subsequenceBetween(startIndex, endIndex).toString());
	}
	
	private static void testSubsequenceToV2(RLESequenceV2 sequence, Integer endIndex) {
		System.out.println("#subsequenceTo with endIndex of " + endIndex + " returns the following: ");
		System.out.println(sequence.subsequenceTo(endIndex).toString());
	}
	
	private static void testSubsequenceFromV2(RLESequenceV2 sequence, Integer startIndex) {
		System.out.println("#subsequenceFrom with startIndex of " + startIndex + " returns the following: ");
		System.out.println(sequence.subsequenceFrom(startIndex).toString());
	}

	private static void testContainsSubsequenceV2(RLESequenceV2 sequence, RLESequenceV2 subsequence) {
		System.out.println("#containsSubsequence with sequence of " + subsequence + " returns the following: ");
		System.out.println(sequence.containsSubsequence(subsequence));
	}
	
	private static void testBasicSequenceV1() {
		System.out.println("The following regards a sequence initialized to have one elements of " + TEST_PIXELS.toString() + ":");
		RLESequenceV1 sequence = new RLESequenceV1(TEST_PIXELS);
		testAllHappyCases(sequence, TEST_PIXELS[0], DIFFERENT_TEST_PIXELS[0], TEST_PIXELS_SUBARRAY, 
				DIFFERENT_TEST_PIXELS[0], DIFFERENT_TEST_PIXELS[1]);
		testRLESequenceV1HappyCases(sequence, new RLESequenceV1(TEST_PIXELS_SUBARRAY), new RLESequenceV1(DIFFERENT_TEST_PIXELS));
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void testOnePixelSequenceV1() {
		for (int pixel : TEST_PIXELS) {
			System.out.println("The following regards a sequence initialized to have one element of " + pixel + ":");
			RLESequenceV1 onePixelSequence = new RLESequenceV1(new Integer[] {pixel});
			testAllHappyCases(onePixelSequence, pixel, DIFFERENT_TEST_PIXELS[0], new Integer[] {pixel}, 
					DIFFERENT_TEST_PIXELS[0], DIFFERENT_TEST_PIXELS[1]);
			testRLESequenceV1HappyCases(onePixelSequence, new RLESequenceV1(), new RLESequenceV1(DIFFERENT_TEST_PIXELS));
			System.out.println();
			System.out.println();
			System.out.println();
		}
		
	}
	
	private static void testEmptySequenceV1() {
		System.out.println("The following regards a sequence initialized to be empty");
		RLESequenceV1 emptySequence = new RLESequenceV1();
		testAllHappyCases(emptySequence, 0, 0, new Integer[0], 72, 73);
		testRLESequenceV1HappyCases(emptySequence, new RLESequenceV1(), new RLESequenceV1(TEST_PIXELS));
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void testAllHappyCases(RLESequence<Integer, Integer, ?> sequence, Integer containedElement, 
			Integer nonContainedElement, Integer[] containedElementSequence, Integer elementToInsert,
			Integer anotherElementToInsert) {
		testRetrievableHappyCases(sequence);
		testSearchableHappyCases(sequence, containedElement, nonContainedElement, containedElementSequence);
		testModifiableHappyCases(sequence, elementToInsert, anotherElementToInsert);
		
	}
	
	private static void testRLESequenceV1HappyCases(RLESequenceV1 sequence, RLESequenceV1 containedSubsequence, RLESequenceV1 nonContainedSubsequence) {
		testContainsSubsequenceV1(sequence, containedSubsequence);
		int length = sequence.length();
		
		System.out.println("That should have returned true");
		testContainsSubsequenceV1(sequence, nonContainedSubsequence);
		System.out.println("That should have returned false");
		
		testSubsequenceFromV1(sequence, 0);
		System.out.println("That should have returned the original sequence");
		if (length > 1) {
			testSubsequenceFromV1(sequence, 1);
			System.out.println("That should have returned a sequence missing the original sequence's first index");
			testSubsequenceFromV1(sequence, length - 1);
			System.out.println("That should have returned a sequence of just the last element");
		}
		
		testSubsequenceToV1(sequence, length);
		System.out.println("That should have returned the original sequence");
		if (length > 1) {
			testSubsequenceFromV1(sequence, length - 1);
			System.out.println("That should have returned a sequence missing the original sequence's last index");
			testSubsequenceFromV1(sequence, 1);
			System.out.println("That should have returned a sequence of just the first element");
		}
		
		testSubsequenceBetweenV1(sequence, 0, length);
		System.out.println("That should have returned the original sequence");
		if (length > 1) {
			testSubsequenceBetweenV1(sequence, 1, length);
			System.out.println("That should have returned a sequence missing the original sequence's first index");
			testSubsequenceBetweenV1(sequence, 0, length - 1);
			System.out.println("That should have returned a sequence missing the original sequence's last index");
			testSubsequenceBetweenV1(sequence, 0, 1);
			System.out.println("That should have returned a sequence of just the first element");
			testSubsequenceBetweenV1(sequence, length - 1, length);
			System.out.println("That should have returned a sequence of just the last element");
		}
		
		testCopyV1(sequence);
		testAddToHeadV1(sequence, nonContainedSubsequence);
		testAddToTailV1(sequence, nonContainedSubsequence);
		testCopyV1(sequence);
	}
	
	private static void testCopyV1(RLESequenceV1 sequence) {
		System.out.println("#copy returns the following sequence: ");
		System.out.println(sequence.copy().toString());
	}
	
	private static void testAddToTailV1(RLESequenceV1 sequence, RLESequenceV1 sequenceToAdd) {
		System.out.println("#addToTail with sequence of " + sequenceToAdd + " makes the sequence now: ");
		sequence.addToTail(sequenceToAdd);
		System.out.println(sequence.toString());
	}
	
	private static void testAddToHeadV1(RLESequenceV1 sequence, RLESequenceV1 sequenceToAdd) {
		System.out.println("#addToHead with sequence of " + sequenceToAdd + " makes the sequence now: ");
		sequence.addToHead(sequenceToAdd);
		System.out.println(sequence.toString());
	}
	
	private static void testSubsequenceBetweenV1(RLESequenceV1 sequence, Integer startIndex, Integer endIndex) {
		System.out.println("#subsequenceBetween with startIndex of " + startIndex + " and endIndex of " + endIndex + " returns the following: ");
		System.out.println(sequence.subsequenceBetween(startIndex, endIndex).toString());
	}
	
	private static void testSubsequenceToV1(RLESequenceV1 sequence, Integer endIndex) {
		System.out.println("#subsequenceTo with endIndex of " + endIndex + " returns the following: ");
		System.out.println(sequence.subsequenceTo(endIndex).toString());
	}
	
	private static void testSubsequenceFromV1(RLESequenceV1 sequence, Integer startIndex) {
		System.out.println("#subsequenceFrom with startIndex of " + startIndex + " returns the following: ");
		System.out.println(sequence.subsequenceFrom(startIndex).toString());
	}

	private static void testContainsSubsequenceV1(RLESequenceV1 sequence, RLESequenceV1 subsequence) {
		System.out.println("#containsSubsequence with sequence of " + subsequence + " returns the following: ");
		System.out.println(sequence.containsSubsequence(subsequence));
	}

	private static void testModifiableHappyCases(RLESequence<Integer, Integer, ?> sequence, Integer elementToInsert,
			Integer anotherElementToInsert) {
		int length = sequence.length();
		
		testInsert(sequence, elementToInsert, 0);
		testRemove(sequence, 0);
		if (length > 1) {
			testInsert(sequence, elementToInsert, 1);
			testReplaceAt(sequence, anotherElementToInsert, 1);
			testRemove(sequence, 1);
			
			
			testInsert(sequence, elementToInsert, sequence.length() - 1);
			testReplaceAt(sequence, anotherElementToInsert, sequence.length() - 1);
			testRemove(sequence, sequence.length() - 1);
		}
		
		testAppend(sequence, elementToInsert);
		testReplaceAt(sequence, anotherElementToInsert, sequence.length() - 1);
		
		testPrepend(sequence, elementToInsert);
		testReplaceAt(sequence, anotherElementToInsert, 0);
		
		testReplaceLast(sequence, anotherElementToInsert, elementToInsert);
		testReplaceFirst(sequence, anotherElementToInsert, elementToInsert);
		
		testRemoveLast(sequence, elementToInsert);
		testRemoveFirst(sequence, elementToInsert);
		
		testAppend(sequence, elementToInsert);
		testAppend(sequence, elementToInsert);
		testAppend(sequence, elementToInsert);
		testReplaceAll(sequence, elementToInsert, anotherElementToInsert);
		testRemoveAll(sequence, anotherElementToInsert);
		
	}
	
	private static void testReplaceAll(RLESequence<Integer, Integer, ?> sequence, Integer oldElement, Integer newElement) {
		System.out.println("#replaceAll with oldElement of " + oldElement + " and newElement of " + newElement + " makes the sequence now: ");
		sequence.replaceAll(oldElement, newElement);
		System.out.println(sequence.toString());
	}
	
	private static void testReplaceLast(RLESequence<Integer, Integer, ?> sequence, Integer oldElement, Integer newElement) {
		System.out.println("#replaceLast with oldElement of " + oldElement + " and newElement of " + newElement + " makes the sequence now: ");
		sequence.replaceLast(oldElement, newElement);
		System.out.println(sequence.toString());
	}
	
	private static void testReplaceFirst(RLESequence<Integer, Integer, ?> sequence, Integer oldElement, Integer newElement) {
		System.out.println("#replaceFirst with oldElement of " + oldElement + " and newElement of " + newElement + " makes the sequence now: ");
		sequence.replaceFirst(oldElement, newElement);
		System.out.println(sequence.toString());
	}
	
	private static void testReplaceAt(RLESequence<Integer, Integer, ?> sequence, Integer newElement, Integer index) {
		System.out.println("#replaceAt with index of " + index + " and element of " + newElement + " makes the sequence now: ");
		sequence.replaceAt(index, newElement);
		System.out.println(sequence.toString());
	}
	
	private static void testPrepend(RLESequence<Integer, Integer, ?> sequence, Integer newElement) {
		System.out.println("#prepend with element of " + newElement + " makes the sequence now: ");
		sequence.prepend(newElement);
		System.out.println(sequence.toString());
	}
	
	private static void testAppend(RLESequence<Integer, Integer, ?> sequence, Integer newElement) {
		System.out.println("#append with element of " + newElement + " makes the sequence now: ");
		sequence.append(newElement);
		System.out.println(sequence.toString());
	}
	
	private static void testRemoveAll(RLESequence<Integer, Integer, ?> sequence, Integer element) {
		System.out.println("#removeAll with element of " + element + " makes the sequence now: ");
		sequence.removeAll(element);
		System.out.println(sequence.toString());
	}
	
	private static void testRemoveLast(RLESequence<Integer, Integer, ?> sequence, Integer element) {
		System.out.println("#removeLast with element of " + element + " makes the sequence now: ");
		sequence.removeLast(element);
		System.out.println(sequence.toString());
	}
	
	private static void testRemoveFirst(RLESequence<Integer, Integer, ?> sequence, Integer element) {
		System.out.println("#removeFirst with element of " + element + " makes the sequence now: ");
		sequence.removeFirst(element);
		System.out.println(sequence.toString());
	}
	
	private static void testRemove(RLESequence<Integer, Integer, ?> sequence,  Integer index) {
		System.out.println("#remove with index of " + index + " makes the sequence now: ");
		sequence.remove(index);
		System.out.println(sequence.toString());
	}
	
	private static void testInsert(RLESequence<Integer, Integer, ?> sequence, Integer newElement, Integer index) {
		System.out.println("#insert with index of " + index + " and element of " + newElement + " makes the sequence now: ");
		sequence.insert(newElement, index);
		System.out.println(sequence.toString());
	}
	
	private static void testSearchableHappyCases(RLESequence<Integer, Integer, ?> sequence, Integer containedElement, 
			Integer nonContainedElement, Integer[] containedElementSequence) {
		testContainsElement(sequence, containedElement);
		System.out.println("That should be have been true (unless empty)");
		testContainsElement(sequence, nonContainedElement);
		System.out.println("That should be have been false");
		
		testContainsElementSequence(sequence, containedElementSequence);
		System.out.println("That should be have been true (unless empty)");
		Integer[] nonContainedElementSequence = generateNonContainedElementSequence(nonContainedElement, containedElementSequence);
		testContainsElementSequence(sequence, nonContainedElementSequence);
		System.out.println("That should be have been false");
		
		testContainsAll(sequence, containedElementSequence);
		System.out.println("That should be have been true (unless empty)");
		testContainsAll(sequence, nonContainedElementSequence);
		System.out.println("That should be have been false");
		
		List<Integer> containedElementSequenceList = Arrays.asList(containedElementSequence);
		testContainsElementSequenceList(sequence, containedElementSequenceList);
		System.out.println("That should be have been true (unless empty)");
		List<Integer> nonContainedElementSequenceList = Arrays.asList(nonContainedElementSequence);
		testContainsElementSequenceList(sequence, nonContainedElementSequenceList);
		System.out.println("That should be have been false");
		
		Collections.reverse(containedElementSequenceList);
		testContainsAllList(sequence, containedElementSequenceList);
		System.out.println("That should be have been true");
		Collections.reverse(nonContainedElementSequenceList);
		testContainsAllList(sequence, nonContainedElementSequenceList);
		System.out.println("That should be have been false");
	}
	
	private static void testContainsAll(RLESequence<Integer, Integer, ?> sequence, Integer[] elements) {
		System.out.println("#contains with (array) elements of " + elements.toString() + " returns the following: ");
		System.out.println(sequence.contains(elements));
	}
	
	private static void testContainsAllList(RLESequence<Integer, Integer, ?> sequence, List<Integer> elements) {
		System.out.println("#contains with (List) elements of " + elements.toString() + " returns the following: ");
		System.out.println(sequence.contains(elements));
	}
	
	private static void testContainsElementSequenceList(RLESequence<Integer, Integer, ?> sequence, List<Integer> elementSequence) {
		System.out.println("#contains with (List) elementSequence of " + elementSequence.toString() + " returns the following: ");
		System.out.println(sequence.contains(elementSequence));
	}
	
	private static Integer[] generateNonContainedElementSequence(Integer nonContainedElement, Integer[] containedElementSequence) {
		Integer[] tempSequence = new Integer[containedElementSequence.length + 1];
		for (int i = 0; i < containedElementSequence.length; i++) {
			tempSequence[i] = containedElementSequence[i];
		}
		tempSequence[containedElementSequence.length] = nonContainedElement;
		return tempSequence;
	}
	
	private static void testContainsElementSequence(RLESequence<Integer, Integer, ?> sequence, Integer[] elementSequence) {
		System.out.println("#contains with elementSequence of " + elementSequence.toString() + " returns the following: ");
		System.out.println(sequence.contains(elementSequence));
	}
	
	private static void testContainsElement(RLESequence<Integer, Integer, ?> sequence, Integer element) {
		System.out.println("#contains with element of " + element + " returns the following: ");
		System.out.println(sequence.contains(element));
	}
	
	private static void testRetrievableHappyCases(RLESequence<?, Integer, ?> sequence) {
		int length = sequence.length();
		if (!sequence.isEmpty()) {
			testGetAt(sequence, 0);
		}
		
		if (length > 1) {
			testGetAt(sequence, 1);
			testGetAt(sequence, sequence.length() - 1);
		}
		
		testGetAll(sequence);
		
		testGetFrom(sequence, 0);
		System.out.println("That should have returned the original sequence's elements");
		if (length > 1) {
			testGetFrom(sequence, 1);
			System.out.println("That should have returned elements without the original sequence's first index");
			testGetFrom(sequence, sequence.length() - 1);
			System.out.println("That should have returned just the last element");
		}
		
		testGetTo(sequence, sequence.length());
		System.out.println("That should have returned the original sequence's elements");
		if (length > 1) {
			testGetTo(sequence, sequence.length() - 1);
			System.out.println("That should have returned elements without the original sequence's last index");
			testGetTo(sequence, 1);
			System.out.println("That should have returned just the first element");
		}
		
		testGetBetween(sequence, 0, sequence.length());
		System.out.println("That should have returned the original sequence's elements");
		if (length > 1) {
			testGetBetween(sequence, 1, sequence.length());
			System.out.println("That should have returned elements without the original sequence's first index");
			testGetBetween(sequence, 0, sequence.length() - 1);
			System.out.println("That should have returned elements without the original sequence's last index");
			testGetBetween(sequence, 0, 1);
			System.out.println("That should have returned just the first element");
			testGetBetween(sequence, 1, sequence.length());
			System.out.println("That should have returned just the last element");
		}
	}
	
	private static void testGetAt(RLESequence<?, Integer, ?> sequence, Integer index) {
		System.out.println("#getAt with index of " + index + " returns the following elements: ");
		System.out.println(sequence.getAt(index));
	}
	
	private static void testGetAll(RLESequence<?, ?, ?> sequence) {
		System.out.println("#getAll returns a list of the following elements: ");
		for (Object obj : sequence.getAll()) {
			System.out.print(obj);
			System.out.print(", ");
		}
		System.out.println();
	}
	
	private static void testGetBetween(RLESequence<?, Integer, ?> sequence, Integer startIndex, Integer endIndex) {
		System.out.println("#getFrom with startIndex of " + startIndex + " and endIndex of " + endIndex + " returns a list of the following elements: ");
		for (Object obj : sequence.getBetween(startIndex, endIndex)) {
			System.out.print(obj);
			System.out.print(", ");
		}
		System.out.println();
	}
	
	private static void testGetFrom(RLESequence<?, Integer, ?> sequence, Integer startIndex) {
		System.out.println("#getFrom with startIndex of " + startIndex + " returns a list of the following elements: ");
		for (Object obj : sequence.getFrom(startIndex)) {
			System.out.print(obj);
			System.out.print(", ");
		}
		System.out.println();
	}
	
	private static void testGetTo(RLESequence<?, Integer, ?> sequence, Integer endIndex) {
		System.out.println("#getTo with endIndex of " + endIndex + " returns a list of the following elements: ");
		for (Object obj : sequence.getTo(endIndex)) {
			System.out.print(obj);
			System.out.print(", ");
		}
		System.out.println();
	}

}
