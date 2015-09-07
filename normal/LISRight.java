package com.sotong.normal;
// In Practice, You should use the statndard input/output

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


class LISRight {
	static class Point {
		int v;
		int [] longest;
		int size;
		
		Point(int v, int size) {
			this.v = v;
			this.longest = new int[size];
			this.size = 0;
			Arrays.fill(this.longest, 0);
		}
		
		int get(int index) {
			return this.longest[index];
		}
		
		void set(int index, int data) {
			this.longest[index] = data;
		}
		
		void add(int data) {
			this.longest[this.size] = data;
			this.size++;
		}
		
		int size() {
			return this.size;
		}
		
		public String toString() {
			String ret = v + " " + Arrays.toString(this.longest);
			return ret;
		}
	}
	
	static void printData() {
		for(Point[] d: data) {
			for(Point p : d) {
				if(p != null) {
					System.out.print(p.toString());
					System.out.print("   ");
				}
			}
			System.out.println();
		}
	}
	
	static int N;

	static Point [][] data = null;
	static int Answer;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_LongestIncrease.txt"));

		int T = sc.nextInt();
		for(int tc = 0; tc < T; tc++) {
			N = sc.nextInt();
			data = new Point[N+1][N+1];

			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					int value = sc.nextInt();
					data[i][j] = new Point(value, N+1);
				}
			}
			
			data[1][1].add(data[1][1].v);
			
			for(int i=2; i<=N; i++) {
				data[1][i].add(Math.min(data[1][i-1].get(0), data[1][i].v));
				data[i][1].add(Math.min(data[i-1][1].get(0), data[i][1].v));
			}
			
			for(int i=2; i<=N; i++) {
				for(int j=2; j<=N; j++) {
					Point prev = data[i-1][j-1];
					Point up   = data[i-1][j];
					Point left = data[i][j-1];
					Point curr = data[i][j];

					int len = Math.min(i, j);
					
					int pos = -1;
					for(int k=prev.size()-1; k>=0; k--) {
						if(prev.get(k) <= curr.v) {
							pos = k+1;
							break;
						}
					}
					
					if(curr.v < prev.get(0)) {
						pos = 0;
					}

					for(int k=0; k<len; k++) {
						int data = 0;
						
						int upData = up.get(k);
						int leftData = left.get(k);
						
						if(upData==0 & leftData==0) {
							continue;
						}
						else if(upData==0) {
							data = leftData;
						}
						else if(leftData == 0) {
							data = upData;
						}
						else {
							data = Math.min(leftData, upData);
						}
						
						curr.add(data);
					}
					
					if(pos >= 0) {
						if(pos > curr.size()-1) {
							curr.add(curr.v);
						}
						else if(curr.v < curr.get(pos)) {
							curr.set(pos, curr.v);
						}
					}
				}
			}
			
			Answer = data[N][N].size();

			// Print the answer to standard output(screen).
			System.out.println(Answer);
		}
	}
}
