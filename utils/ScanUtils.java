package com.sotong.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ScanUtils {

	public static int N;//Number of Vertex
	public static int M;//Number of edge
	
	public static float[][] adj;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		scan("tinyEWD.txt");
		display();
	}
	
	public static void display(){
		for (int i = 0; i < adj.length; i++) {
			System.out.println(Arrays.toString(adj[i]));
		}
	}

	public static void scan(String fileName) {
		// TODO Auto-generated method stub
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(fileName));
			N = sc.nextInt();
			M = sc.nextInt();
			adj = new float[N][N];
			for (int i = 0; i < M; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				float weight = sc.nextFloat();
				adj[start][end] = weight;
			}
//			display();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			sc.close();
		}
	}
}
