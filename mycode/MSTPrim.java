package com.sotong.mycode;

import java.util.LinkedList;

public class MSTPrim {
	public static final int [][]adjMax = {
		{0,6,1,5,0,0}, //1
		{6,0,5,0,3,0}, //2
		{1,5,0,5,6,4}, //3
		{5,0,5,0,0,2}, //4
		{0,3,6,0,0,6}, //5
		{0,0,4,2,6,0}};//6
	
/*	public static final int [][]adjMax = {
		{0,6,0,4,0,0}, //A
		{6,0,10,7,7,0}, //B
		{0,10,0,8,5,6}, //C
		{4,7,8,0,12,0}, //D
		{0,7,5,12,0,7}, //E
		{0,0,6,0,7,0}};//F
*/	
	public static final int DIM = adjMax.length;
	
	public static int[] lowcost;
	public static int[] mst;
	
	public static int startPoint;
	
	public static int minPathWeight = 0;
	public static LinkedList<Integer> minPathList = new LinkedList<Integer>();  
	
	public static void main(String args[]){
		
		initArray();
		
		int minvertex = Integer.MIN_VALUE;
		
		while ( (minvertex = findMinVertex()) != -1) {
			updateArrayByMinVertex(minvertex);
		}
		
		System.out.println("minPathWeight is " + minPathWeight + " PathList is " + minPathList.toString()) ;
	}

	private static void initArray() {
//		System.out.println("initArray:");
		// TODO Auto-generated method stub
		lowcost = new int[DIM + 1];
		mst = new int[DIM + 1];
		
		startPoint = 1;
		minPathList.add(startPoint);
		
		for (int i = 1; i < DIM + 1; i++) {
			if (i == startPoint) {
				lowcost[i] = 0;
				mst[i] = 0;
				continue;
			}
			
			lowcost[i] = adjMax[startPoint - 1][i - 1] == 0 ? Integer.MAX_VALUE:adjMax[startPoint - 1][i - 1];
			mst[i] = startPoint;
		}
		
//		displayArray();
	}
	
	private static int findMinVertex(){
		int result = -1;
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 1; i < lowcost.length; i++) {
			if (mst[i] == 0) {
				continue;
			}
			if (lowcost[i] < min) {
				min = lowcost[i];
				result = i;
			}
		}
		
		
		if (result > 0) {
			System.out.println("Find V" + result + " from V" + mst[result] + " distance " + lowcost[result]);
			minPathWeight += lowcost[result];
			minPathList.add(result);
			lowcost[result] = 0;
			mst[result] = 0;
		}
		
//		System.out.println("findMinVertex return " + result);
		
		return result;
	}
	
	//update array by minVertex such as 3;
	private static void updateArrayByMinVertex(int minVertex){
//		System.out.println("updateArrayByMinVertex : " + minVertex);
		for (int i = 1; i < DIM + 1; i++) {
			int distance;
			if (mst[i] != 0 && (distance = adjMax[minVertex - 1][i - 1]) > 0) {
				if (distance < lowcost[i]) {
					lowcost[i] = distance;
					mst[i] = minVertex;
				}
			}
		}
	}

/*	private static void displayArray() {
		// TODO Auto-generated method stub
		System.out.println("lowcost:");
		for (int i = 1; i < DIM + 1; i++) {
			System.out.print(" " + lowcost[i]);
		}
		System.out.println();
		System.out.println("mst:");
		for (int i = 1; i < DIM + 1; i++) {
			System.out.print(" " + mst[i]);
		}
		System.out.println();
	}*/
}
