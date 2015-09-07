package com.sotong.easy;
//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Scanner;
import java.io.FileInputStream;

class HiringNewEmployees {
	static int N;
	static int [] Si = new int[300005];
	
	static int [] marked = new int[32001]; 
	
	static int MaxSum;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_hireemp.txt"));

		int T = sc.nextInt();
		for(int tc = 0; tc < T; tc++) {
			MaxSum = 0;
			N = sc.nextInt();
			for (int i = 0; i < 32001; i++) {
				marked[i] = 0;
			}
			for(int i=1;i<=N;i++)
			{
				Si[i] = sc.nextInt();
				if (Si[i] > MaxSum) {
					MaxSum = Si[i];
				}
				marked[Si[i]]++;
			}
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			
			for (int i = 1; i <= N; i++) {
				System.out.print(findOrderofNum(Si[i])+" ");
			}
			// Print the answer to standard output(screen).
			
			System.out.println();
		}
		
		sc.close();
	}
	
	public static int findOrderofNum(int num) {
		int result = 0;
		for (int i = num + 1; i <= MaxSum; i++) {
			result += marked[i];
		}
		return result + 1;
	}
}

