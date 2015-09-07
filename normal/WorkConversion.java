package com.sotong.normal;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.FileInputStream;

class WorkConversion {
	
	public static int NumOfPairs; 
	public static final int MAX_SIZE = 100;
	public static HashMap<Integer, List<Integer>> adjhashMap;
	public static int[] arrayTool;
	public static int[] arrayPath;
	public static int startPoint ;
	
	public static int sum ;
	public static int count ;
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_workcomversation.txt"));

		arrayTool = new int[MAX_SIZE];
		arrayPath = new int[MAX_SIZE];
		
		int T = sc.nextInt();
//		T = 1; 
		for(int tc = 0; tc < T; tc++) {
			NumOfPairs = sc.nextInt();
			initAdjMap(sc);
			sum = 0;
			count = 0;
//			displayHashMap(adjhashMap);
			
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			float Answer = splitHashMap();
			// Print the answer to standard output(screen).
			
			System.out.println(new DecimalFormat("0.000").format(Answer));
		}
	}

	private static float splitHashMap() {
		Set<Integer> targetSet = adjhashMap.keySet();
		Iterator<Integer> it = targetSet.iterator();
		while (it.hasNext()) {
			Integer integer = (Integer) it.next();
//			System.out.println("keyset has " + integer);
			startPoint = integer;
			
			clearArray();
			
			//only from 1
//			if (startPoint == 4) {
				int toolIndex = 1;
				int currentPoint = 1;
				arrayTool[toolIndex++] = startPoint;
				
//				searchAdjVertex(startPoint);
				
				while (arrayTool[currentPoint] != 0) {
					int result;
					while ((result = searchAdjVertex(arrayTool[currentPoint])) != -1) {
						arrayPath[result] = arrayPath[arrayTool[currentPoint]] + 1;
						arrayTool[toolIndex++] = result;
						sum += arrayPath[result];
						count ++;
					}
//					displayArray(arrayTool);
//					displayArray(arrayPath);
					currentPoint++;
				}
//				System.out.println("keyset:1 sum = " + sum + " count = " + count);
//			}
		}
//		System.out.println("sum = " + sum + " count = " + count);
		return (float) (1.00 * sum / count);
	}
	
	public static void displayArray(int[] array) {
		for (int i = 0; i < 5; i++) {
			System.out.println("array[" + i + "] is " + array[i]);
		}
	}
	
	public static void initAdjMap(Scanner sc) {
		adjhashMap = new HashMap<>();
		for (int i = 0; i < NumOfPairs; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			if (adjhashMap.containsKey(start)) {
				adjhashMap.get(start).add(end);
			} else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(end);
				adjhashMap.put(start,list);
			}
		}
	}
	
	public static void displayHashMap(HashMap<Integer, List<Integer>> hashMap) {
		System.out.println(hashMap.toString());
	}
	
	public static int searchAdjVertex(int v) {
//		System.out.println("searchAdjVertex : " + v);
		int result = -1;
		List<Integer> list = adjhashMap.get(v);
		for (int i = 0; i < list.size(); i++) {
//			System.out.println("searchAdjVertex : test " + list.get(i));
			if (list.get(i) != v && list.get(i) != startPoint  && arrayPath[list.get(i)] == 0) {
//				System.out.println("searchAdjVertex : return " + list.get(i));
				return list.get(i);
			}
		}
//		System.out.println("searchAdjVertex : return -1");
		return result;
	}
	
	public static void clearArray(){
		for (int i = 0; i < MAX_SIZE; i++) {
			arrayPath[i] = 0;
			arrayTool[i] = 0;
		}
	}
}

