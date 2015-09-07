package com.sotong.normal;
//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Scanner;
import java.io.FileInputStream;

class PickUpJew {
	static int N;
	static int [][] maze = new int[11][11];
	static int [][] road = new int[11][11];
	static int jewels_count;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
;
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_pickupjwe.txt"));

		int T = sc.nextInt();
		for(int tc = 0; tc < T; tc++) {
			maze = new int[11][11];
			road = new int[11][11];
			N = sc.nextInt();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					maze[i][j] = sc.nextInt();
				}
			}
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			jewels_count = 0;
			pickJewels(0,0,0);
			
			// Print the answer to standard output(screen).
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					System.out.print(road[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println(jewels_count+"\n");
		}
	}
	public static void pickJewels(int x, int y, int count) {
		int u,v = 0;
		int temp = maze[x][y];
		if (maze[x][y] == 1 || maze[x][y] == 3 ) {
			return;
		}
		
		if (maze[x][y] == 2) {
			count++;
		}
		maze[x][y] = 3;
		if (x == N-1 && y == N-1) {
			if (count >= jewels_count) {
				jewels_count = count;
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						road[i][j] = maze[i][j];
					}
				}
			}
			maze[x][y] = temp;
			return;
		}
		for (int i=0;i<4;i++) {
			u = x + dx[i];
			v = y + dy[i];
			if (u>=0 && u<N && v>=0 && v<N) {
				pickJewels(u,v,count);
			}
		}
		maze[x][y] = temp;
	    
	}
}


// In Practice, You should use the statndard input/output
// in order to receive a score properly.
// Do not use file input and output. Please be very careful. 

/*import java.util.Scanner;
import java.io.FileInputStream;

class PickUpJew {
	static int N;
	static int [][] maze = new int[11][11];
	static int [][] passage = new int[11][11];
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	static int count;

	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(new FileInputStream("sample_input_pickupjwe.txt"));

		int T = sc.nextInt();
		for(int tc = 0; tc < T; tc++) {
			N = sc.nextInt();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					maze[i][j] = sc.nextInt();
				}
			}
			*//**********************************
			*  Implement your algorithm here. *
			***********************************//*
			count = 0;
			jesearch(0, 0 , 0);
			// Print the answer to standard output(screen).
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					System.out.print(passage[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println(count+"\n");
		}
	}
	public static void jesearch(int x, int y, int count){
		if (x < 0 || x >= N || y < 0 || y >= N){
			return;
		}
		
		if (maze[x][y] == 1 || maze[x][y] == 3){
			return;
		}
		int temp = maze[x][y];
		if (temp == 2){
			count++;
		}
		maze[x][y] = 3;
		if (x == N -1 && y == N -1){
			if (count >= count){
				count = count;
				for (int i = 0; i < N; i++){
					for (int j = 0; j < N; j++){
						passage[i][j] = maze[i][j];
					}
				}
			}
			maze[x][y] = temp;
			return;
		}
		
		for (int i = 0; i < 4; i++){
			jesearch(x + dx[i], y + dy[i], count);
		}
		
		
		maze[x][y] = temp;
	}
}
*/
