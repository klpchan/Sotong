package com.sotong.normal;


//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

//Algorithm
class CompanyTrackMeet {
	
	private static int difference = 0;
	private static int targetcase = 1;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_CompanyTrackMeet.txt"));

		int numberOfEmp;
		int[] rank = new int[11];
		
		int T = sc.nextInt();
//		T = 1;
				
		for(int tc = 0; tc < T; tc++) {
			numberOfEmp = sc.nextInt();
			difference = 0;
			targetcase = 1;
			
			for (int i = 0; i < rank.length; i++) {
				
			}
			
			for (int i = 1; i <= numberOfEmp; i++) {
				rank[i] = sc.nextInt();
			}
			
//			System.out.println(Arrays.toString(rank));
			
			SearchWinner(rank, 1, numberOfEmp);
			
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			System.out.println(difference+"\n"+targetcase);
			// Print the answer to standard output(screen).
		}
	}
	
	public static int SearchWinner(int[] array,int start, int end) {
		int min = Integer.MAX_VALUE;
		int index = -1;
		int path = 1;
		
		for (int i = start; i <= end; i++) {
			if (array[i] < min) {
				min = array[i];
				index = i;
			}
		}
		
//		System.out.println("SearchWinner: min is " + min + " index is " + index);
		
		if (index > start) {
			int dif = (SearchWinner(array, start, index - 1) - min);
//			System.out.println("difference + " + dif);
			difference += dif;
		}
		
		if (index < end) {
			int dif = SearchWinner(array, index + 1, end) - min;
//			System.out.println("difference + " + dif);
			difference += dif;
		}
		
		if (index > start && index < end) {
			path++;
			if (path == 2) {
				targetcase *= 2;
			}
		}
		
		return min;
	}

}

