package com.sotong.mycode;

public class DFSApp{
	public static void main(String[] args){
	   Graph theGraph = new Graph();
/*	   theGraph.addVertex('A');    // 0  (start for dfs)
	   theGraph.addVertex('B');    // 1
	   theGraph.addVertex('C');    // 2
	   theGraph.addVertex('D');    // 3
	   theGraph.addVertex('E');    // 4
	
	   theGraph.addEdge(0, 1);     // AB
	   theGraph.addEdge(1, 2);     // BC
	   theGraph.addEdge(0, 3);     // AD
	   theGraph.addEdge(3, 4);     // DE
*/	   
	   theGraph.addVertex('A');    // 0  
	   theGraph.addVertex('B');    // 1
	   theGraph.addVertex('C');    // 2
	   theGraph.addVertex('D');    // 3
/*	   theGraph.addVertex('E');    // 4
	   theGraph.addVertex('F');    // 5  (start for dfs)
	   theGraph.addVertex('G');    // 6
	   theGraph.addVertex('H');    // 7
	   theGraph.addVertex('I');    // 8
*/	
	   theGraph.addEdge(0, 1);     // AB
	   theGraph.addEdge(0, 2);     // AC
	   theGraph.addEdge(1, 2);     // BC
	   theGraph.addEdge(2, 0);     // CA
	   theGraph.addEdge(2, 3);     // CD
	   theGraph.addEdge(3, 3);     // DD
/*	   theGraph.addEdge(3, 6);     // DG
	   theGraph.addEdge(6, 8);     // GI
*/	
	   System.out.println("Visits: ");
//	   theGraph.excuteDFS(0);             // depth-first search
	   theGraph.DFSTraverse();
	   System.out.println();
   }  // end main()
}  // end class DFSApp

class MyStack{
	public final int  SIZE = 30;
	public int top;
	public int st[];
	
	public MyStack(){
		st = new int[SIZE];
		top = -1;
	}
	
	public void push(int value) {
		if (top >= -1 && top < SIZE - 1) {
			st[++top] = value;
		} else {
			System.out.println("MyStack Push error top = " + top);
		}
	}
	
	public int pop() {
		return st[top--];
	}
	
	public int peek() {
		return st[top];
	}
	
	public boolean isEmpty() {
		return top <= -1;
	}
}

class Vertex{
	public char label;
	public boolean wasVisited;
	
	public  Vertex(char label) {
		this.label = label;
		wasVisited = false;
	}
}

class Graph{
	private final int MAX_VERTEX = 30;
	private Vertex vertexList[];
	private int currentVertexNum;
	private int adjmax[][];
	private MyStack myStack;
	private boolean visited[];
	
	public Graph(){
		vertexList = new Vertex[MAX_VERTEX];
		currentVertexNum = 0;
		adjmax = new int[MAX_VERTEX][MAX_VERTEX];
		
		for (int i = 0; i < MAX_VERTEX; i++) 
			for (int j = 0; j < MAX_VERTEX; j++) 
				adjmax[i][j] = 0;
		
		myStack = new MyStack();
	}
	
	public void addVertex(char name) {
		vertexList[currentVertexNum++] = new Vertex(name);
	}
	
	public void addEdge(int start, int end) {
		adjmax[start][end] = 1;
		adjmax[end][start] = 1;
	}
	
	public void displayVertex(int t) {
		System.out.println("The vertext of " + t + " is " + vertexList[t].label);
	}
	
	public int getadjVertex(int v) {
		int result = -1;
		for (int i = 0; i < currentVertexNum; i++) {
			if (adjmax[v][i] == 1 && vertexList[i].wasVisited == false) {
				return i;
			}
		}
		return result;
	}
	
	public void markAddPush(int v) {
		vertexList[v].wasVisited = true;
		myStack.push(v);
		displayVertex(v);
	}
	
	public void excuteDFS(int startVertex) {
		markAddPush(startVertex);  //put start point into stack mark
		
		
		while (!myStack.isEmpty()) {
			int nextPoint = getadjVertex(myStack.peek());
			if ( nextPoint == -1) { //if can't find adjVertex, pop up
				myStack.pop();
			} else {				//if can find adjVertex, push mark and display
				markAddPush(nextPoint);
			}
		}
		
		for (int i = 0; i < currentVertexNum; i++) {
			vertexList[i].wasVisited = false;
		}
	}
	
	public void DFSTraverse(){
		visited = new boolean[currentVertexNum];
		for (int i = 0; i < currentVertexNum; i++) {
			visited[i] = false;
		}
		DFS(2);
		for (int i = 0; i < currentVertexNum; i++) {
			if (! visited[i]) {
				DFS(i);
			}
		}
	}
	
	public void DFS(int v) {
		visited[v] = true;
		System.out.println("DFS " + vertexList[v].label);
		for (int i = 0; i < currentVertexNum; i++) {
			if (adjmax[v][i] == 1 && visited[i] == false) {
				DFS(i);
			}
		}
	}
}