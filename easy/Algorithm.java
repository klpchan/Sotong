package com.sotong.easy;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class Algorithm {
	
	static int N;
	
	static int[][] array;
	
	static boolean[] visited;
	
	static long minExpense;
	
	public static final boolean DEBUG = false;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_airfare.txt"));

		int T = sc.nextInt();
//		T = 2;
		for(int tc = 0; tc < T; tc++) {

			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			initArray(sc);
			
          DFS(0,0);
          
		   if (minExpense == Long.MAX_VALUE) {
				minExpense = 0;
			}
			// Print the answer to standard output(screen).
			System.out.println(minExpense);
		}
		
		sc.close();
	}
	
	public static void initArray(Scanner sc) {
		N = sc.nextInt();
		array = new int[N][N];
		visited = new boolean[N];
		minExpense = Long.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
              if(sc.hasNext()){
              	array[i][j] = sc.nextInt();
              }
			}
		}
		displayElem();
	}
	
	public static void displayElem() {
		if (DEBUG) {
			for (int i = 0; i < array.length; i++) {
				System.out.println(Arrays.toString(array[i]));
			}
			System.out.print("\n");
		}
	}
	
	public static void DFS(int v, long path) {
      if(path > minExpense){
      	return ;
      }      
		visited[v] = true;
		for (int j = 0; j < array.length; j++) {
			if (j != v && array[v][j] > 0 && !visited[j]) {
				DFS(j, path + array[v][j]);
			}
		}
		if (allVisited() && array[v][0] > 0) {
			path += array[v][0];
//			System.out.println("get " + path);
			minExpense = path < minExpense ? path : minExpense;
		}
		visited[v] = false;	
	}
	
	public static boolean allVisited() {
      if(visited == null){
          return false;
      }
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == false) {
				return false;
			}
		}
		return true;
	}
	
}
