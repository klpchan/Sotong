package com.sotong.normal;


//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Scanner;
import java.io.FileInputStream;

public class LongIncreaseSub {
	static int N;
	static int [][] L = new int[260][260];
	static int Answer;
	
//	static int[] L1 = {1,-1,2,-3,4,-5,6,-7};
	static int[][] MAX = new int[260][260];

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_LongestIncrease.txt"));

		int T = sc.nextInt();
//		T = 1;
		for(int tc = 0; tc < T; tc++) {
			N = sc.nextInt();
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					L[i][j] = sc.nextInt();
				}
			}
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			Answer = 0;

//			Answer = max(L1);
			Answer = max2dim(N, N);
			// Print the answer to standard output(screen).
			System.out.println(Answer);
		}
		sc.close();
	}
	
/*	private static int max(int[] array){
		int result = -1;
		int size = array.length;
		
		int[] Max = new int[size];
		
		for (int i = 0; i < Max.length; i++) {
			Max[i] = 1;
			for (int j = 0; j < i - 1; j++) {
				if (array[i] > array[j] && Max[i] < Max[j] + 1) {
					Max[i] = Max[j] + 1;
					
				}
			}
			
			result = Max[i] > result ? Max[i]:result;
		}
		System.out.println(Arrays.toString(Max));
		return result;
	} */
	
	private static int max2dim(int rowNum, int colNum){
		int result = -1;
		
//		System.out.println("max2dim :  row is " + rowNum);
		
		clearMax();
		
		int i = 1;
		int j = 1;
		for (i = 1; i < rowNum + 1; i++) {
			for (j = 1; j < colNum + 1; j++) {
//				MAX[i][j] = 1;
//				System.out.println("max2dim : start anay MAX:" + i + ";" + j);
				for (int k = 1; k < i; k++) {
					for (int l = 1; l < j; l++) {
/*						System.out.println("max2dim : compare: " + k + ";" + l);
						System.out.println("max2dim : array[i][j] is " + L[i][j] 
													+	"array[k][l] is " + L[k][l]
													+ "Max[i][j] is " + MAX[i][j]
													+ "Max[k][l] is " + MAX[k][l]);*/
						if (L[i][j] >= L[k][l] && MAX[i][j] < MAX[k][l] + 1) {
							 MAX[i][j] = MAX[k][l] + 1;
							 result = MAX[i][j] > result ? MAX[i][j]:result;
						}
					}
				}
//				System.out.println("max2dim : Max[" + i +"][" + j + "] is " + MAX[i][j]);
			}
		}
		
/*		for (int m = 0; m < rowNum; m++) {
			for (int n = 0; n < colNum; n++) {
				result = MAX[m][n] > result ? MAX[m][n]:result;
			}
		}*/
		
		return result;
	}
	
	private static void clearMax(){
		for (int i = 0; i < 260; i++) {
			for (int j = 0; j < 260; j++) {
				MAX[i][j] = 1;
			}
		}
	}
}



