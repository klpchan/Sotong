package com.sotong.mycode;

public class Floyd {
	
	public static final int MAXNUM = 100;
	
	public static int[][] D;
	
	public static int[][] P;
	
	public static final int [][]adjMax = {
		{0,1,5,MAXNUM,MAXNUM,MAXNUM,MAXNUM,MAXNUM,MAXNUM}, //1
		{1,0,3,7,5,MAXNUM,MAXNUM,MAXNUM,MAXNUM}, //2
		{5,3,0,MAXNUM,1,7,MAXNUM,MAXNUM,MAXNUM}, //3
		{MAXNUM,7,MAXNUM,0,2,MAXNUM,3,MAXNUM,MAXNUM},
		{MAXNUM,5,1,2,0,3,6,9,MAXNUM}, //4
		{MAXNUM,MAXNUM,7,MAXNUM,3,0,MAXNUM,5,MAXNUM},
		{MAXNUM,MAXNUM,MAXNUM,3,6,MAXNUM,0,2,7},//6
		{MAXNUM,MAXNUM,MAXNUM,MAXNUM,9,5,2,0,4},//6
		{MAXNUM,MAXNUM,MAXNUM,MAXNUM,MAXNUM,MAXNUM,7,4,0}};//6
	
/*	public static final int [][]adjMax = {
		{0,14,4,10,20}, //1
		{14,0,7,8,7}, //2
		{4,5,0,7,16}, //3
		{11,7,9,0,2},
		{18,7,17,4,0}};//6
*/	
	public static final int DIM = adjMax.length;
	
	public static void main(String args[]){
		
		initArray();
		
		int k,m,n;
		for (k = 0; k < DIM; k++) {
			for (m = 0; m < DIM; m++) {
				for (n = 0; n < DIM; n++) {
					if (D[m][n] > D[m][k] + D[k][n]) {
						System.out.println("k = " + k + " m = " + m + " n = " + n);
						D[m][n] =  D[m][k] + D[k][n];
						P[m][n] = P[m][k];
					}
				}
			}
		}
		
		displayArray(D);
		displayArray(P);
	}

	private static void initArray() {
		// TODO Auto-generated method stub
		D = new int[DIM][DIM];
		P = new int[DIM][DIM];
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				D[i][j] = adjMax[i][j];
				P[i][j] = j;
			}
		}
//		displayArray(D);
	}
	
	private static void displayArray(int[][] array){
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				System.out.print(" " + array[i][j]);
			}
			System.out.print("\n");
		}
	}
	
}
