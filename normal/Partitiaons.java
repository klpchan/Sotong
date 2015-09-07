package com.sotong.normal;

/*

In Practice, You should use the statndard input/output
in order to receive a score properly.
Do not use file input and output. Please be very careful. 

*/

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class Partitiaons{

	static int N;
	static int [] data = new int[10001];
	static int time;
	
	static boolean DEBUG = true;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_partitions.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int i;
			N = sc.nextInt();
			
			for(i=1; i<=N; i++) {
				data[i] = sc.nextInt();
			}
			
//			order(N);
			
			/**********************************
			 * Implement your algorithm here. *
			 **********************************/
			
			time = 0;
			
            for (i=2 ; i <=N ; i++) {
                Arrays.sort(data,i-1,N+1);
                data[i] = data[i] + data[i-1];
                time += data[i];
            }
			
			// Print the answer to standard output(screen).
			System.out.println(time);
		}
	}
	
/*	public static void order(int N) {
		int i,j,tmp;
		
		for (i = 1; i < N ; i++) {
			for (j = i + 1; j <= N ; j++) {
				if (data[i] < data[j]) {
					tmp = data[i];
					data[i] = data[j];
					data[j] = tmp;
				}
			}
		}
		
		printArray(N);

	}
	
	private static void printArray(int N){
		if (DEBUG) {
			for (int k = 1; k <= N; k++) {
				System.out.print(data[k]);
			}
			System.out.print('\n');
		}
	}
	
	*//****T从2开始到N-1****//*
	private static int addLastN(int T){
		int result = 0;
		
		for (int i = T ; i <= N ; i++) {
			result += data[i];
		}
		
		return result;
	}
	
	private static int addPart(int N){
		int result = 0;
		for (int i = 2; i < N ; i++) {
			result += addLastN(i);
		}
		return result;
	}
	
	private static int addAll(){
		int result = 0;
		
		for (int i = 1 ; i <= N ; i++) {
			result += data[i];
		}
		
		return result;
	}*/
}

