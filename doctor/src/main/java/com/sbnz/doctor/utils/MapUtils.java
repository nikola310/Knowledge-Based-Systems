package com.sbnz.doctor.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtils {

	public static boolean ASC = true;
	public static boolean DESC = false;

	public static Map<String, Double> sortByComparator(Map<String, Double> unsortMap, final boolean order) {

		List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<String, Double>>() {
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		for (Entry<String, Double> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public static void printMap(Map<String, Double> map) {
		for (Entry<String, Double> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}
	}
	
	public static void main(String[] args) {

		// Creating dummy unsorted map
//		Map<String, Double> unsortMap = new HashMap<String, Double>();
//		unsortMap.put("B", 55.0);
//		unsortMap.put("A", 80.0);
//		unsortMap.put("D", 20.0);
//		unsortMap.put("C", 70.0);
//
//		System.out.println("Before sorting......");
//		printMap(unsortMap);
//
//		System.out.println("After sorting ascending order......");
//		Map<String, Double> sortedMapAsc = sortByComparator(unsortMap, ASC);
//		printMap(sortedMapAsc);
//
//		System.out.println("After sorting descending order......");
//		Map<String, Double> sortedMapDesc = sortByComparator(unsortMap, DESC);
//		printMap(sortedMapDesc);
		
		HashSet<Character> set = new HashSet<>();
		set.add('A');
		set.add('P');
		set.add('O');
		set.add('A');
		System.out.println(set.size());
		System.out.println(set.contains('A'));

	}

}
