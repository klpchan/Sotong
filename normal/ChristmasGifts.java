package com.sotong.normal;


//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Scanner;
import java.io.FileInputStream;

class ChristmasGifts {
	
	private static int[][] BaseArray = null;
	private static int persons = 0;
	private static int gifts = 0;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_chris_gift.txt"));

		int Answer = 0;
		
		int T = sc.nextInt();
//		T = 2; 
		for(int tc = 0; tc < T; tc++) {
			
			persons = sc.nextInt();
			gifts = sc.nextInt();
//			System.out.println("person:gift is " + persons + ":" + gifts);
			
			BaseArray = new int[persons + 1][gifts + 1];
			
			for (int i = 1; i < persons + 1; i++) {
				int count = sc.nextInt();
				while (count-- > 0) {
					int y = sc.nextInt();
					BaseArray[i][y] = 1;
//					System.out.println("BaseArray[" + i + "][" + y + "] is set to true");
				}
			}
			
			Answer = 0;
			
			for (int i = 1; i < persons + 1; i++) {
				if(search(i) != Integer.MAX_VALUE){
					Answer++;
				}
			}
			
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			
			// Print the answer to standard output(screen).
			System.out.println("Answer is " + Answer);
			
			BaseArray = null;
		}
	}
	
	//find max Y
	public static int MatchY(int person,int y) {
		int result = 0;
		for (int i = person; i < persons + 1; i++) {
			if (BaseArray[i][y] == 1) {
				result++;
			}
		}
//		System.out.println("MatchY " + y + " : " + result);
		return result;
	}
	
	//search People,return minus gift number
	public static int search(int person) {
		int result = Integer.MAX_VALUE;
		int selectGift = -1;
		
		for (int i = 1; i < gifts + 1; i++) {
			if (BaseArray[person][i] == 1 && BaseArray[0][i] != 1) {
				int sub = MatchY(person,i);
				if (sub < result) {
					result = sub;
					selectGift = i;
				}
			}
		}
		
		if (selectGift != -1) {
			BaseArray[0][selectGift] = 1;
		}
		
//		System.out.println("search " + person + " selectGift/result is : " + selectGift + "/" + result);
		return result;
	}
}

