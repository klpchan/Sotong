package com.sotong.normal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.FileInputStream;

class Algorithm {
	
	static CustomVertex[] vertexes = new CustomVertex[5001];
	static Queue<Integer> queue = new LinkedBlockingQueue<Integer>();
	
	static int N,M;
	static long Answer;

	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);
		//sc = new Scanner(new FileInputStream("src/input.txt"));

		int T = sc.nextInt();
		for(int tc = 0; tc < T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			for(int i = 1; i <= N; vertexes[i] = new CustomVertex(i++, 0, 0, 0, 0, new ArrayList<Integer>(), new ArrayList<Integer>())) {}
			
			for(int i=1;i<=M;i++)
			{
				int s = sc.nextInt();
				int e = sc.nextInt();
				
				vertexes[s].out++;
				vertexes[s].children.add(e);
				vertexes[e].in++;
				vertexes[e].parent.add(s);
			}
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			queue.clear();
			for(int i = 1; i <= N; i++) {
				if(vertexes[i].in == 0) {
					queue.add(i);
					vertexes[i].dataIn = 1;
				}
			}
			
			while(!queue.isEmpty()) {
				int v = queue.poll();
				for(int i : vertexes[v].children) {
					
					vertexes[i].dataIn += vertexes[v].dataIn;
					vertexes[i].in --;
					if(vertexes[i].in == 0) { queue.add(i); }
				}
			}
			
			// cout reverse direction
			queue.clear();
			for(int i = 1; i <= N; i++) {
				if(vertexes[i].out == 0) {
					queue.add(i);
					vertexes[i].dataOut = 1;
				}
			}
			
			Answer = 0;
			while(!queue.isEmpty()) {
				int v = queue.poll();
				for(int i : vertexes[v].parent) {
					
					vertexes[i].dataOut += vertexes[v].dataOut;
					vertexes[i].out --;
					if(vertexes[i].out == 0) { 
						queue.add(i); 
					}
					
					if(Answer < vertexes[i].dataIn * vertexes[v].dataOut) { Answer = vertexes[i].dataIn * vertexes[v].dataOut; }
				}
				
			}
			
			// Print the answer to standard output(screen).
			System.out.println(Answer);
		}
	}
}

class CustomVertex implements Comparable<CustomVertex> {

	@Override
	public int compareTo(CustomVertex o) {
		return in - o.in;
	}
	
	public CustomVertex(int a, int i, int o, int dI, int dO, List<Integer> n, List<Integer> m) {
		id = a;
		in = i;
		out = o;
		dataIn = dI;
		dataOut = dO;
		children = n;
		parent = m;
	}
	
	int id;
	int in, out, dataIn, dataOut;
	List<Integer> children;
	List<Integer> parent;
}