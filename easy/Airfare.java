package com.sotong.easy;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class Airfare {
	
	static int N;
	
	static int[][] array;
	
	static boolean[] visited;
	
//	static int[] testarray;
//	static int count;
	
	static int minExpense;
	
	public static final boolean DEBUG = false;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_airfare.txt"));

		int T = sc.nextInt();
		for(int tc = 0; tc < T; tc++) {

			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			initArray(sc);
			
			DFS(0, 0);
			
			// Print the answer to standard output(screen).
			if (minExpense == Integer.MAX_VALUE) {
				minExpense = 0;
			}
			System.out.println(minExpense);
		}
		
		sc.close();
	}
	
	public static void initArray(Scanner sc) {
		N = sc.nextInt();
		array = new int[N][N];
		visited = new boolean[N];
//		testarray = new int[N];//just for test
		minExpense = Integer.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (sc.hasNext()) {
					array[i][j] = sc.nextInt();
				}
			}
		}
		displayElem();
	}
	
	public static void DFS(int v, int path) {
		visited[v] = true;
//		testarray[count++] = v;
		for (int j = 1; j < array.length; j++) {
			if (j != v && array[v][j] > 0 && !visited[j]) {
				DFS(j, path + array[v][j]);
			}
		}
		if (allVisited() && array[v][0] > 0) {
			path += array[v][0];
//			displayArray();
//			System.out.println(" get " + path);
			minExpense = path < minExpense ? path : minExpense;
		}
//		count--; 
		visited[v] = false;
	}
	
	public static boolean allVisited() {
		if (visited == null) {
			return false;
		}
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == false) {
				return false;
			}
		}
		return true;
	}
	
	public static void displayElem() {
		if (DEBUG) {
			for (int i = 0; i < array.length; i++) {
				System.out.println(Arrays.toString(array[i]));
			}
			System.out.print("\n");
		}
	}	
	
/*	public static void displayArray(){
		if (testarray == null) {
			return;
		}
		for (int i = 0; i < count; i++) {
			System.out.print(testarray[i]);
		}
	}*/
}

/*

 * */
