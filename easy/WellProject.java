package com.sotong.easy;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

public class WellProject {
	
	public static int [][]adjMax;

	public static int DIM;

	public static int MAX_EDGE_NUM;

	public static Edge[] edges;

	public static int totalWeight;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_wellproject.txt"));

		int T = sc.nextInt();
		for(int tc = 0; tc < T; tc++) {

			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			
			initArray(sc);
			
			int[] parent = new int[DIM];
			
			for (int i = 0; i < parent.length; i++) {
				parent[i] = 0;
			}
			
			totalWeight = 0;
			
			for (int i = 0; i < edges.length; i++) {
				if (edges[i] != null) {
					int m = findMark(parent, edges[i].start);
					int n = findMark(parent, edges[i].end);
					if (m != n) {
						parent[m] = n;
						totalWeight += edges[i].weight;
//						System.out.println("Add edge to MST£º" + edges[i]);
					}
				}
			}
			
			System.out.println(totalWeight);
			// Print the answer to standard output(screen).

		}
	}

	public static int findMark(int[] parent, int f) {
		while (parent[f] > 0) {
			f = parent[f];
		}
		return f;
	}

	private static void initArray(Scanner sc) {
		// TODO Auto-generated method stub
		int numV = sc.nextInt();
		adjMax = new int[numV][numV];
		DIM = adjMax.length;
		MAX_EDGE_NUM = DIM * DIM / 2;
		edges = new Edge[MAX_EDGE_NUM];
		
		for (int i = 0; i < numV; i++) {
			for (int j = 0; j < numV; j++) {
				adjMax[i][j] = sc.nextInt();
			}
		}
		
		int count = 0;
		for (int i = 0; i < DIM; i++) {
			for (int j = i; j < DIM; j++) {
				if (adjMax[i][j] != 0) {
//					System.out.println("adding " + adjMax[i][j]);
					edges[count++] = new Edge(i, j, adjMax[i][j]);
				}
			}
		}
		Arrays.sort(edges, 0, count);
	//	System.out.println(Arrays.toString(edges));
	}

}

class Edge implements Comparable<Edge>{
int start;
int end;
int weight;
//float weight;

public Edge(int start, int end, int weight) {
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
