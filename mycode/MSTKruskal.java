package com.sotong.mycode;

import java.util.Arrays;

import com.sotong.utils.ScanUtils;

public class MSTKruskal {
/*	public static final int [][]adjMax = {
		{0,10,0,0,0,11,0,0,0}, //1
		{10,0,18,0,0,0,16,0,12},//2
		{0,0,0,22,0,0,0,0,8}, //3
		{0,0,22,0,20,0,0,16,21},//4
		{0,0,0,20,0,26,0,7,0}, //5
		{11,0,0,0,26,0,17,0,0}, //6
		{0,16,0,0,0,17,0,19,0},//7
		{0,0,0,16,7,0,19,0,0},//8
		{0,12,8,21,0,0,0,0,0}};//9
*/	
	public static float [][]adjMax;
	
	public static int DIM;
	
	public static int MAX_EDGE_NUM;
	
	public static Edge[] edges;
	
	public static float totalWeight;
	
	public static void main(String args[]){
		
		initArray();
		
		int[] parent = new int[DIM];
		
		for (int i = 0; i < parent.length; i++) {
			parent[i] = 0;
		}
		
		for (int i = 0; i < edges.length; i++) {
			if (edges[i] != null) {
				int m = findMark(parent, edges[i].start);
				int n = findMark(parent, edges[i].end);
				if (m != n) {
					parent[m] = n;
					totalWeight += edges[i].weight;
					System.out.println("Add edge to MST£º" + edges[i]);
				}
			}
		}
		
		System.out.println("totalWeight is " + totalWeight);
	}
	
	public static int findMark(int[] parent, int f) {
		while (parent[f] > 0) {
			f = parent[f];
		}
		return f;
	}

	private static void initArray() {
		// TODO Auto-generated method stub
//		ScanUtils.scan("tinyEWG.txt");
		ScanUtils.scan("mediumEWG.txt");
//		ScanUtils.scan("largeEWG.txt");
		adjMax = ScanUtils.adj;
		DIM = adjMax.length;
		MAX_EDGE_NUM = ScanUtils.M;
		edges = new Edge[MAX_EDGE_NUM];
		
		int count = 0;
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (adjMax[i][j] != 0) {
//					System.out.println("adding " + adjMax[i][j]);
					edges[count++] = new Edge(i, j, adjMax[i][j]);
				}
			}
		}
		Arrays.sort(edges, 0, count);
//		System.out.println(Arrays.toString(edges));
	}
}

class Edge implements Comparable<Edge>{
	int start;
	int end;
//	int weight;
	float weight;
	
	public Edge(int start, int end, float weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Edge info : from " + start + " to " + end + " Weight is " + weight;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		int result;
		if (this.weight == o.weight) {
			result = 0;
		} else {
			result =  this.weight - o.weight > 0 ? 1 : -1;
		}
		return result;
	}
}
