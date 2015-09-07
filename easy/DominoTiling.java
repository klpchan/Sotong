package com.sotong.easy;

/*

In Practice, You should use the statndard input/output
in order to receive a score properly.
Do not use file input and output. Please be very careful. 

*/

import java.util.Scanner;
import java.io.FileInputStream;

class DominoTiling {
	static int N,M;
	static long Answer;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_dominotrilling.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			Answer = 0;
//			Answer = calculateSum(N) % M;
			Answer = calculateSum(N,M);
			// Print the answer to standard output(screen).
			System.out.println(Answer);
		}
		
		sc.close();
	}
	
	public static int calculateSum(int N,int M){
		if (N <= 0) {
			return 0;
		}
		if (N == 1) {
			return 1 % M;
		}
		if (N == 2) {
			return 3 % M;
		}
		int[] array = new int[N + 1];
		array[1] = 1 % M;
		array[2] = 3 % M;
		for (int i = 3; i < N + 1; i++) {
			int tmp = array[i-1] + 2 * array[i-2];
			array[i] = tmp % M;
		}
		return array[N];
	}
}

