package com.sotong.mycode;

import java.util.ArrayList;
import java.util.Arrays;

public class CriticalPath {
	
	public static final int [][]adjMax = {
	  // 0 1 2 3 4 5 6 7 8 9 
		{0,3,4,0,0,0,0,0,0,0}, //1
		{0,0,0,5,6,0,0,0,0,0}, //2
		{0,0,0,8,0,7,0,0,0,0}, //3
		{0,0,0,0,3,0,0,0,0,0}, //4
		{0,0,0,0,0,0,9,4,0,0}, //5
		{0,0,0,0,0,0,0,6,0,0}, //6
		{0,0,0,0,0,0,0,0,0,2}, //7
		{0,0,0,0,0,0,0,0,5,0}, //8
		{0,0,0,0,0,0,0,0,0,3}, //9
		{0,0,0,0,0,0,0,0,0,0}, //10
	};
	
	public static final int DIM = adjMax.length;
	
	public static int[] etv = new int[DIM];
	public static int[] ltv = new int[DIM];

	public static int[] stack2 = new int[DIM + 1];
	
	public static int top2;
	
	public static WTopoNode[] topoArray;
	
	public static void main(String args[]){
		initArray();
		
		TopologicalSort(); //make etv
		
		makeLtv();
		
		int ete;
		int lte;
		int pathResult = 0;
		
		for (int i = 0; i < DIM; i++) {
			ArrayList<adjListNode> list = topoArray[i].list; //´Óiµ½adjnode.vertexNum
			for (adjListNode adjnode : list) {
				ete = etv[i];
				lte = ltv[adjnode.vertexNum] - adjnode.weight;
				if (ete == lte) {
					pathResult += adjnode.weight;
					System.out.println("Put into critical path from " + i + " to " + adjnode.vertexNum);
				}
			} 
		}
		
		System.out.println("Critical path result is " + pathResult);
	}

	private static void makeLtv() {
		for (int i = 0; i < DIM; i++) {
			ltv[i] = etv[DIM - 1];
		}
		
		System.out.println("init ltv is " + Arrays.toString(ltv));
		
		while (top2 != 0) {
			int popvalue = stack2[top2--];
			ArrayList<adjListNode> list = topoArray[popvalue].list;
			for (adjListNode adjnode : list) {
				if (ltv[popvalue] > ltv[adjnode.vertexNum] - adjnode.weight) {
					ltv[popvalue] = ltv[adjnode.vertexNum] - adjnode.weight;
				}
			}
		}
		
		System.out.println(" ltv is " + Arrays.toString(ltv));
	}

	private static void TopologicalSort() {
		int top = 0;
		int count = 0;
		int[] stack = new int[DIM];
		
		for (int i = 0; i < DIM; i++) {
			if (topoArray[i].in == 0) {
				stack[++top] = i;
			}
			etv[i] = 0;
		}
		
		while (top != 0) {
			int popValue = stack[top--]; // pop 1
			stack2[++top2] = popValue;
			System.out.println(" handle " + popValue);
			count++;
			ArrayList<adjListNode> list = topoArray[popValue].list;
			for (adjListNode adjnode : list) {
				WTopoNode node = topoArray[adjnode.vertexNum];
				if (--node.in == 0) {
					stack[++top] = node.data;
				}
				if (etv[adjnode.vertexNum] < etv[popValue] + adjnode.weight) {
					etv[adjnode.vertexNum] = etv[popValue] + adjnode.weight;
				}
			}
		}
		
		System.out.println(" all handle count is  " + count);
		System.out.println(" etv is  " + Arrays.toString(etv));
	}

	private static void initArray() {
		// TODO Auto-generated method stub
		topoArray = new WTopoNode[DIM];
		for (int i = 0; i < DIM; i++) {
			WTopoNode node = new WTopoNode();
			ArrayList<adjListNode> list = new ArrayList<adjListNode>();

			for (int j = 0; j < DIM; j++) {
				if (adjMax[i][j] > 0) {
					list.add(new adjListNode(j,adjMax[i][j]));
				}
			}
			
			node.setData(i);
			node.setIn(queryIndegree(i));
			node.setList(list);
			topoArray[i] = node;
		}

		System.out.println(Arrays.toString(topoArray));
	}

	private static int queryIndegree(int i) {
		// TODO Auto-generated method stub
		int result = 0;
		
		for (int j = 0; j < DIM; j++) {
			if (adjMax[j][i] > 0) {
				result++;
			}
		}
		
		return result;
	}
	
}

class adjListNode{
	int vertexNum;
	int weight;
	public adjListNode(int vertexNum, int weight) {
		super();
		this.vertexNum = vertexNum;
		this.weight = weight;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "adjListNode " + vertexNum + ":" + weight;
	}
	
	
}

class WTopoNode{
	int in;
	int data;
	ArrayList<adjListNode> list;
	public int getIn() {
		return in;
	}
	public void setIn(int in) {
		this.in = in;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public ArrayList<adjListNode> getList() {
		return list;
	}
	public void setList(ArrayList<adjListNode> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TopoNode info in is " + in + " data is " + data + " list is " + list.toString() + "\n";
	}
}

