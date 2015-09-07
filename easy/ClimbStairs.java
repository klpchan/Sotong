package com.sotong.easy;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Scanner;
import java.io.FileInputStream;

class ClimbStairs {
static int MAXN = 300;
static int [][]R;// = new int[MAXN];
static int []S;// = new int[MAXN];
	public static void main(String args[]) throws Exception {
	//Scanner sc = new Scanner(System.in);
	Scanner sc = new Scanner(new FileInputStream("sample_input_climbstairs.txt"));
	
	int T = sc.nextInt();
	for(int tc = 0; tc < T; tc++) {
	
	/**********************************
	*  Implement your algorithm here. *
	***********************************/
		int n = sc.nextInt();
		R = new int[n][2];//0, consecutive, 1, not 
		S = new int[n];
		for (int i = 0; i < n; ++i) {
			S[i] = sc.nextInt();
		}
	
		R[0][0] = S[0];
		R[0][1] = S[0];
		R[1][0] = S[1] + S[0];
		R[1][1] = S[1];
		// Print the answer to standard output(screen).
		int answer1 = getRn(n-1, 0);
		int answer2 = getRn(n-1, 1);
		if (answer1 > answer2) {
		 System.out.println(answer1);
		} else {
		 System.out.println(answer2);
		}
	}
	
	sc.close();
}

	public static int getRn(int n, int type) {  
		if (R[n][type] != 0) {
			return R[n][type];
		}
		
		int r = 0;
		if (type == 0) {
			r = S[n] + getRn(n-1, (type+1)%2);
		} else {
			int r2 = S[n] + getRn(n-2, type);
			int r3 = S[n] + getRn(n-2, (type+1)%2);
			r = Math.max(r2, r3);
		}
		
		R[n][type] = r;
		return R[n][type];
	}
}

