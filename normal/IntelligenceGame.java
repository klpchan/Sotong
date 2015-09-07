package com.sotong.normal;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Scanner;
import java.io.FileInputStream;

class IntelligenceGame {
	
	public static int Dim;
	public static int [][]adj;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_IntelligenceGame.txt"));

		int T = sc.nextInt();
//		T = 2;
		for(int tc = 0; tc < T; tc++) {
			initadj(sc);
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			int Answer = search();
			System.out.println(Answer);
			// Print the answer to standard output(screen).

		}
	}
	
	private static int search(){
		int []match = new int[Dim];
		
		for (int i = 0; i < match.length; i++) {
			match[i] = -1;
		}
		
		int start;
		int result = 0;
		
		for (start = 0; start < Dim; start++) {
			boolean visited[] = new boolean[Dim];
			if (MBM(adj,start,visited,match)) {
				result++;
			}
		}
		
		return result;
	}
	
	private static boolean MBM(int[][] adj, int start,boolean[] visited,int[] match){
		
		int end;
		
		for (end = 0; end < Dim; end++) {
			if (adj[start][end] == 1 && !visited[end]) {
				visited[end] = true;
				if (match[end] < 0 || MBM(adj,match[end],visited,match)) {
					match[end] = start;
					return true;
				}
				
			}
		}
		
		return false;
	}
	

	private static void initadj(Scanner sc) {
		// TODO Auto-generated method stub
		boolean DEBUG_initadj = false;
		
		Dim = sc.nextInt();
		adj = new int[Dim][Dim];
		int NumOfPair = sc.nextInt();
		for (int i = 0; i < NumOfPair; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			adj[x-1][y-1] = 1;
		}
		
		if (DEBUG_initadj) {
			for (int i = 0; i < Dim; i++) {
				for (int j = 0; j < Dim; j++) {
					System.out.print(adj[i][j] + " ");
				}
				System.out.print("\n");
			}
		}
	}
}
