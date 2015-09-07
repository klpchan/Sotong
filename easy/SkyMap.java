package com.sotong.easy;

/*
In Practice, You should use the statndard input/output
in order to receive a score properly.
Do not use file input and output. Please be very careful. 

*/

import java.io.FileInputStream;
import java.util.Scanner;

class SkyMap {

	static int N;
	static int [][] data = new int[26][26];
	static int maxVertexNum;
	static int maxNum;
	
	private static boolean[][] visisted;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_skymap.txt"));

		int T = sc.nextInt();
//		T = 1;
		for(int test_case = 0; test_case < T; test_case++) {
			int i,j;
			int S,C;
			N = sc.nextInt();
			
			for(i=1; i<=N; i++) {
				for(j=1; j<=N; j++) {
					data[i][j] = sc.nextInt();
				}
			}
			
			/**********************************
			 * Implement your algorithm here. *
			 **********************************/
			
			
			S = 0;
			C = 0;
			
			S = numIslands();
			C = maxNum;
			
			// Print the answer to standard output(screen).
			System.out.println(S+" "+C);
		}
		
		sc.close();
	}
	
    public static int numIslands() {
        // Write your code here
    	visisted = new boolean[N+1][N+1];
    	maxVertexNum = 0;
    	maxNum = Integer.MIN_VALUE;
    	int numOfisland = 0;
    	for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (data[i][j] == 1 && !visisted[i][j]) {
					dfsisland(i, j);
					numOfisland++;
					maxNum = maxVertexNum > maxNum ? maxVertexNum : maxNum; 
//					System.out.println("Find a Group maxVerNum is " + maxVertexNum);
					maxVertexNum = 0;
				}
			}
		}
    	return numOfisland;
    }
    
	public static void dfsisland(int x, int y){
    	visisted[x][y] = true;
    	maxVertexNum++;
//    	System.out.println("dfsisland island " + x +","+ y);
    	
    	if (x > 1 && !visisted[x - 1][y] && data[x-1][y] == 1) { 
			dfsisland(x-1, y); // left search
		}
    	
    	if (y > 1 && !visisted[x][y-1] && data[x][y-1] == 1) {
			dfsisland(x, y-1); // up search
		}
    	
    	if (x <= N - 1 && !visisted[x + 1][y] && data[x+1][y] == 1) {
			dfsisland(x+1, y); // right search
		}
    	
    	if (y <= N - 1 && !visisted[x][y+1] && data[x][y+1] == 1) {
			dfsisland(x, y+1); // down search
		}
    }
}
