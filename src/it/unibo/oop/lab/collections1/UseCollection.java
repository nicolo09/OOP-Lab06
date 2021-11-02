package it.unibo.oop.lab.collections1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Example class using {@link java.util.List} and {@link java.util.Map}.
 * 
 */
public final class UseCollection {

    private UseCollection() {
    }

    /**
     * @param s unused
     */
    public static void main(final String... s) {
	/*
	 * 1) Create a new ArrayList<Integer>, and populate it with the numbers from
	 * 1000 (included) to 2000 (excluded).
	 */
	ArrayList<Integer> list = new ArrayList<>();
	for (int i = 1000; i < 2000; i++) {
	    list.add(i);
	}
	/*
	 * 2) Create a new LinkedList<Integer> and, in a single line of code without
	 * using any looping construct (for, while), populate it with the same contents
	 * of the list of point 1.
	 */
	LinkedList<Integer> linkedList = new LinkedList<>(list);
	/*
	 * 3) Using "set" and "get" and "size" methods, swap the first and last element
	 * of the first list. You can not use any "magic number". (Suggestion: use a
	 * temporary variable)
	 */
	int temp = list.get(list.size() - 1);
	list.set(list.size() - 1, list.get(0));
	list.set(0, temp);
	/*
	 * 4) Using a single for-each, print the contents of the arraylist.
	 */
	for (int a : list) {
	    System.out.println(a);
	}
	/*
	 * 5) Measure the performance of inserting new elements in the head of the
	 * collection: measure the time required to add 100.000 elements as first
	 * element of the collection for both ArrayList and LinkedList, using the
	 * previous lists. In order to measure times, use as example
	 * TestPerformance.java.
	 */
	long time = System.nanoTime();

	for (int i = 0; i < 100000; i++) {
	    list.add(0, i);
	}

	time = System.nanoTime() - time;

	System.out.println("Array List adding 100000 elements on head time:" + time);

	for (int i = 0; i < 100000; i++) {
	    linkedList.add(0, i);
	}

	time = System.nanoTime() - time;

	System.out.println("Linked List adding 100000 elements on head time:" + time);
	/*
	 * 6) Measure the performance of reading 1000 times an element whose position is
	 * in the middle of the collection for both ArrayList and LinkedList, using the
	 * collections of point 5. In order to measure times, use as example
	 * TestPerformance.java.
	 */

	time = System.nanoTime();

	for (int i = 0; i < 1000; i++) {
	    list.get(list.size() / 2);
	}

	time = System.nanoTime() - time;

	System.out.println("Array List reading 1000 elements time:" + time);

	time = System.nanoTime();

	for (int i = 0; i < 1000; i++) {
	    linkedList.get(list.size() / 2);
	}

	time = System.nanoTime() - time;

	System.out.println("Linked List reading 1000 elements time:" + time);

	/*
	 * 7) Build a new Map that associates to each continent's name its population:
	 * 
	 * Africa -> 1,110,635,000
	 * 
	 * Americas -> 972,005,000
	 * 
	 * Antarctica -> 0
	 * 
	 * Asia -> 4,298,723,000
	 * 
	 * Europe -> 742,452,000
	 * 
	 * Oceania -> 38,304,000
	 */

	HashMap<String, Long> map = new HashMap<>();

	map.put("Africa", 1110635000L);
	map.put("Americas", 972005000L);
	map.put("Antarctica", 0L);
	map.put("Asia", 4298723000L);
	map.put("Europe", 742452000L);
	map.put("Oceania", 38304000L);
	/*
	 * 8) Compute the population of the world
	 */
	long population = 0;

	for (Long a : map.values()) {
	    population += a;
	}

	System.out.println("Population of The World:" + population);
    }
}
