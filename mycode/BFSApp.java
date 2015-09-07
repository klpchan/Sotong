package com.sotong.mycode;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.io.FileInputStream;

class BFSApp {
	
	public static int NumOfPairs; 
	public static final int MAX_ARRAY_SIZE = 100;
	public static HashMap<Integer, List<Integer>> adjhashMap;
	
	public static int[] arrayWeight;//表示每个顶点是否被访问及离初始点的距离，如0表示未被访问，N>0表示距离为N
	public static Queue<Integer> queueTool;
	
	public static int startPoint ;
	
	public static int sum ;
	public static int count ;
	
	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(new FileInputStream("sample_input_workcomversation.txt"));
		
		initParam();
		
		int T = sc.nextInt();
//		T = 1; 
		for(int tc = 0; tc < T; tc++) {
			NumOfPairs = sc.nextInt();
			initAdjMap(sc);
			sum = 0;
			count = 0;
			excuteBFS();
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			float Answer = sum * 1.0f / count;
			// Print the answer to standard output(screen).
			System.out.println(new DecimalFormat("0.000").format(Answer));
		}
	}

    private static void initParam() {
		// TODO Auto-generated method stub
		arrayWeight = new int[MAX_ARRAY_SIZE];
		queueTool = new LinkedList<>();
	}
    
    private static void excuteBFS(){

    	boolean DEBUG_KEY_excuteBFS = false;
    	Set<Integer> keys = adjhashMap.keySet();
    	Iterator<Integer> it = keys.iterator();
    	while (it.hasNext()) {
			startPoint = (Integer) it.next();//integer is 
			
    		if (DEBUG_KEY_excuteBFS) {
    			System.out.println("excuteBFS : Ready to Process key is " +  startPoint);
			}
			BFS(startPoint);
    		if (DEBUG_KEY_excuteBFS) {
    			System.out.println("excuteBFS : sum&count =  " +  sum + "&" + count);
			}
//			displayArray(arrayWeight);
		}
    }

	private static void BFS(int start) {
		int processValue;
		int processPoint;
		
		clearArray(); //clear weight array every time start from new start point.
		
		processPoint = start;
		processValue = arrayWeight[processPoint];
		
		queueTool.add(processPoint);//添加初始值
		
		int adjvalue = 0;
		
		while (!queueTool.isEmpty()) {
			processPoint = queueTool.poll();
			processValue = arrayWeight[processPoint];
			while ((adjvalue = findAdjVertex(processPoint))!= -1) {
				arrayWeight[adjvalue] = processValue + 1;
				sum += arrayWeight[adjvalue];
				count++;
				queueTool.add(adjvalue);
			}
		}
	}
    
    private static int findAdjVertex(int v){
    	int result = -1;
    	boolean DEBUG_KEY_findAdjVertex = false;
    	
    	List<Integer> list = adjhashMap.get(v);
    	
    	for (Integer integer : list) {
			if (integer != startPoint && arrayWeight[integer] == 0) {
				if (DEBUG_KEY_findAdjVertex) {
					System.out.println("findAdjVertex : input " + v + " get " +  integer);
				}
				return integer;
			}
		}
    	
		if (DEBUG_KEY_findAdjVertex) {
			System.out.println("findAdjVertex : input " + v + " get NONE");
		}
    	
    	return result;
    }
	
	public static void displayArray(int[] array) {
		for (int i = 1; i < 5; i++) {
			System.out.println("array[" + i + "] is " + array[i]);
		}
	}
	
	public static void initAdjMap(Scanner sc) {
		boolean DEBUG_KEY_initAdjMap = false;
		
		if (adjhashMap != null) {
			adjhashMap.clear();
		} else {
			adjhashMap = new HashMap<>();
		}
		
		for (int i = 0; i < NumOfPairs; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			if (DEBUG_KEY_initAdjMap) {
				System.out.println("initAdjMap : start&end is " + start + "&" + end);
			}
			if (adjhashMap.containsKey(start)) {
				adjhashMap.get(start).add(end);
			} else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(end);
				adjhashMap.put(start,list);
			}
		}
		if (DEBUG_KEY_initAdjMap) {
			displayHashMap(adjhashMap);
		}
	}
	
	public static void displayHashMap(HashMap<Integer, List<Integer>> hashMap) {
		System.out.println(hashMap.toString());
	}	
	
	public static void clearArray(){
		for (int i = 0; i < MAX_ARRAY_SIZE; i++) {
			arrayWeight[i] = 0;
		}
	}
}

/*1.833
2.255
1.971
1.758
1.886*/



