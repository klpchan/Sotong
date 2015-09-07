package com.sotong.normal;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ChristmasGiftsRight {

	private static int People = 0;
	private static int Gift = 0;
	private static boolean[][] Prefer = null;
	private static int[] Match = null;
	private static boolean[] isChoose = null;
	
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("sample_chris_gift.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int nTest = sc.nextInt();
		while (nTest-- > 0){
			People = sc.nextInt();
			Gift = sc.nextInt();
			Prefer = new boolean[People+1][Gift+1];
			for (int k=1; k<=People; k++){
				int nPrefer = sc.nextInt();
				while (nPrefer-- > 0){
					Prefer[k][sc.nextInt()] = true;
				}
			}
			int Answer = 0;
			//int Answer = 0;
			Match = new int[Gift+1];
			for (int i=1; i<=People; i++){
			isChoose = new boolean[People+1];
				if (search(i)){
					Answer++;
				}
			}
			System.out.println(Answer);
		}
	}
	private static boolean search(int i) {
		if (!isChoose[i]){
			isChoose[i] = true;
			for (int j=1; j<=Gift; j++){
				if (Prefer[i][j]){
					if (Match[j] == 0 || search(Match[j])){
						Match[j] = i;
						return true;
					}
				}
			}
		}
		return false;
	}
}
