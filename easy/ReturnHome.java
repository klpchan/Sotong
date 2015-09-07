package com.sotong.easy;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Scanner;
import java.io.FileInputStream;


class ReturnHome {		
	public static int [][]adjMax = null;
	
	public static int DIM = 0;
	
	public static VertexElem[] vertexElems;
	
	public static final boolean DEBUG = false;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_returnhome.txt"));

		int T = sc.nextInt();
		for(int tc = 0; tc < T; tc++) {

			initStartArr(sc);
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			
			int delegate;
			
			while ((delegate = findDelegate()) != -1) {
				updateDelegate(delegate);
			}
			
			int Answer = vertexElems[0].distance;
			
			System.out.println(Answer);
			// Print the answer to standard output(screen).

		}
		sc.close();
	}
	
	private static void initStartArr(Scanner sc) {
		// TODO Auto-generated method stub
		boolean DEBUG_initStartArr = false;
		if (DEBUG_initStartArr) {
			System.out.println("initStartArr start");
		}
		
		int count = sc.nextInt();
		DIM = sc.nextInt();
		int startPoint = DIM - 1;
		adjMax = new int[DIM][DIM];

		while (count-- > 0) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int path = sc.nextInt();
			if (adjMax[start-1][end-1] == 0) {
				adjMax[start-1][end-1] = path;
				adjMax[end-1][start-1] = path;
			} else {
				adjMax[start-1][end-1] = path < adjMax[start-1][end-1] ? path : adjMax[start-1][end-1];
				adjMax[end-1][start-1] = path < adjMax[end-1][start-1] ? path : adjMax[end-1][start-1];
			}
			
		}
		
		displayAdj();
		vertexElems = new VertexElem[DIM];
		
		
		for (int i = 0; i < DIM; i++) {
			if (i == startPoint) {
				vertexElems[startPoint] = new VertexElem(0, startPoint, true);//init startpoint.
				continue;
			}
			
			if (adjMax[startPoint][i] > 0) {
				vertexElems[i] = new VertexElem(adjMax[startPoint][i], startPoint, false);
			} else {
				vertexElems[i] = new VertexElem(Integer.MAX_VALUE, -1, false);
			}
		}
		
		displayElem();
	}

	public static void updateDelegate(int deletePosition) {
		boolean DEBUG_updateDelete = false;
		if (DEBUG_updateDelete) {
			System.out.println("updateDelete start " + deletePosition);
		}
		
		vertexElems[deletePosition].marked = true;
		
		for (int i = 0; i < vertexElems.length; i++) {
			int dis = 0;
			if ( (dis = adjMax[deletePosition][i]) > 0 && !vertexElems[i].marked &&
					vertexElems[deletePosition].distance + dis < vertexElems[i].distance
					) {
				vertexElems[i].distance = vertexElems[deletePosition].distance + dis;
				vertexElems[i].previousPoint = deletePosition;
			}
		}
		
		displayElem();
	}
	
	public static final int findDelegate(){
		int result = -1;
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < vertexElems.length; i++) {
			if (vertexElems[i].marked) {
				continue;
			}
			
			if (vertexElems[i].distance < min) {
				min = vertexElems[i].distance;
				result = i;
			}
		}
		
//		System.out.println("findDelete return " + result);
		
		return result;
	}
	
	public static void displayElem() {
		if (DEBUG) {
			for (int i = 0; i < vertexElems.length; i++) {
				System.out.print(vertexElems[i]);
			}
			System.out.print("\n");
		}
	}
	
	public static void displayAdj() {
		if (DEBUG) {
			for (int i = 0; i < adjMax.length; i++) {
				for (int j = 0; j < adjMax.length; j++) {
					System.out.print(adjMax[i][j] + " ");
				}
				System.out.println("\n");
			}
		}
	}
}

class VertexElem implements Comparable<VertexElem>{
//	int distance;
	int distance;
	int previousPoint;
	boolean marked;
	char label;
	
//	char[] nouse = {'A','B','C','D','E'};
//	char[] nouse = {'A','B','C','D','E','F','G','H'};
	
	public VertexElem(int distance, int previousPoint, boolean marked) {
		super();
		this.distance = distance;
		this.previousPoint = previousPoint;
		this.marked = marked;
	}

	@Override
	public int compareTo(VertexElem o) {
		// TODO Auto-generated method stub
		return this.distance - o.distance > 0 ? 1 : -1 ;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  " " + distance + 
				(previousPoint == -1 ? "(NONE)":"(" + previousPoint+")")+ 
				(marked ? "*":" ");
	}
}	

