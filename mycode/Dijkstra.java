package com.sotong.mycode;

import com.sotong.utils.ScanUtils;

public class Dijkstra {
	
/*	s: Start vertex, A: Adjacent matrix, D: Distance
	V: Vertex set, U: Selected vertex set 

	Dijkstra(s, A, D) 
	U = {s};

	FOR every vertex v
		D[v] ¡û A[s][v]

	WHILE U ¡Ù V
		Select vertex w ¡Ê V-U wit minimum D[w]
		U ¡û U ¡È {w}

		FOR every vertex v adjacent to w
			D[v] ¡û min(D[v], D[w] + A[w][v])*/


/*	public static final int [][]adjMax = {
										{0,50,0,80,0},
										{0,0,60,90,0},
										{0,0,0,0,40},
										{0,0,20,0,70},
										{0,50,0,0,0}};*/
	
/*	public static final int [][]adjMax = { {0, 15, 80, 90},
        {0, 0, 40, 50},
        {0, 0, 0, 70},
        {0, 0, 0, 0}
       };*/
	
/*	public static final float[][]adjMax = {
		{0, 0, 0.26f, 0,0,0,0,0},
		{0, 0, 0, 0.29f,0,0,0,0},
		{0, 0, 0, 0,0,0,0,0},
		{0, 0, 0, 0,0,0,0.52f,0.39f},
		{0.38f, 0, 0, 0,0,0,0,0.37f},
		{0, 0.32f, 0, 0,0,0,0.28f,0.35f},
		{0.58f, 0, 0.40f, 0,0.93f,0,0,0},
		{0, 0, 0.34f, 0,0,0,0,0},
	};*/
	
/*	public static final int [][]adjMax = {
		{0,3,5,0,0,0},
		{0,0,2,6,0,0},
		{0,1,0,4,6,0},
		{0,0,0,0,2,3},
		{3,0,0,0,0,6},
		{0,0,0,0,0,0}};*/
	
	//A:0 B:1 C:2 D:3 E:4
	public static float [][]adjMax = null;
	
	public static int DIM = 0;
	
	public static VertexElem[] vertexElems;
	
	public static void main(String args[]){
		
		initStartArr(5);
		
		int delegate;
		
		while ((delegate = findDelegate()) != -1) {
			updateDelegate(delegate);
		}
	}
	
	private static void initStartArr(int startPoint) {
		// TODO Auto-generated method stub
		boolean DEBUG_initStartArr = false;
		if (DEBUG_initStartArr) {
			System.out.println("initStartArr start");
		}
		
//		ScanUtils.scan("tinyEWD.txt");
		ScanUtils.scan("tinyEWDAG.txt");
		adjMax = ScanUtils.adj;
		DIM = adjMax.length;
		vertexElems = new VertexElem[DIM];
		
		
		for (int i = 0; i < DIM; i++) {
			if (i == startPoint) {
				vertexElems[startPoint] = new VertexElem(0, startPoint, true);//init startpoint.
				continue;
			}
			
			if (adjMax[startPoint][i] > 0) {
				vertexElems[i] = new VertexElem(adjMax[startPoint][i], startPoint, false);
			} else {
				vertexElems[i] = new VertexElem(Float.MAX_VALUE, -1, false);
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
			float dis = 0;
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
		
		float min = Float.MAX_VALUE;
		
		for (int i = 0; i < vertexElems.length; i++) {
			if (vertexElems[i].marked) {
				continue;
			}
			
			if (vertexElems[i].distance < min) {
				min = vertexElems[i].distance;
				result = i;
			}
		}
		
		System.out.println("findDelete return " + result);
		
		return result;
	}
	
	public static void displayElem() {
//			System.out.println("displayElem :");
			for (int i = 0; i < vertexElems.length; i++) {
				System.out.print(vertexElems[i]);
			}
			System.out.print("\n");
	}
}

class VertexElem implements Comparable<VertexElem>{
//	int distance;
	float distance;
	int previousPoint;
	boolean marked;
	char label;
	
//	char[] nouse = {'A','B','C','D','E'};
//	char[] nouse = {'A','B','C','D','E','F','G','H'};
	
	public VertexElem(float distance, int previousPoint, boolean marked) {
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
