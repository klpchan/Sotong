package com.sotong.mycode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * V0 : 11, 5, 4
 * V1 : 8,4,2
 * V2 : 9,6,5
 * V3 : 13,2
 * V4 : 7
 * V5 : 12,8
 * V6 : 5
 * V7 : 
 * V8 : 7
 * V9 : 11,10
 * V10: 13
 * V11: 
 * V12: 9
 * V13: 
 */

public class TopologyBase {
	
	public static final int [][]adjMax = {
	  // 0 1 2 3 4 5 6 7 8 9 0 1 2 3	
		{0,0,0,0,1,1,0,0,0,0,0,1,0,0}, //1
		{0,0,1,0,1,0,0,0,1,0,0,0,0,0}, //2
		{0,0,0,0,0,1,1,0,0,1,0,0,0,0}, //3
		{0,0,1,0,0,0,0,0,0,0,0,0,0,1}, //4
		{0,0,0,0,0,0,0,1,0,0,0,0,0,0}, //5
		{0,0,0,0,0,0,0,0,1,0,0,0,1,0}, //6
		{0,0,0,0,0,1,0,0,0,0,0,0,0,0}, //7
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //8
		{0,0,0,0,0,0,0,1,0,0,0,0,0,0}, //9
		{0,0,0,0,0,0,0,0,0,0,1,1,0,0}, //10
		{0,0,0,0,0,0,0,0,0,0,0,0,0,1}, //11
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //12
		{0,0,0,0,0,0,0,0,0,1,0,0,0,0}, //13
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0}};//14
	
	public static final int DIM = adjMax.length;

	public static TopoNode[] topoArray;
	
	public static void main(String args[]){
		initArray();
		
		int top = 0;
		int count = 0;
		int[] stack = new int[DIM];
		
		for (int i = 0; i < DIM; i++) {
			if (topoArray[i].in == 0) {
				stack[++top] = i;
			}
		}
		
		while (top != 0) {
			int popValue = stack[top--]; // pop 1
			System.out.println(" handle " + popValue);
			count++;
			ArrayList<Integer> list = topoArray[popValue].list;
			for (Integer integer : list) {
				TopoNode node = topoArray[integer];
				if (--node.in == 0) {
					stack[++top] = node.data;
				}
			}
		}
		
		System.out.println(" all handle count is  " + count);
	}

	private static void initArray() {
		// TODO Auto-generated method stub
		topoArray = new TopoNode[DIM];
		for (int i = 0; i < DIM; i++) {
			TopoNode node = new TopoNode();
//			List<Integer> list = new List()<Integer>;
			ArrayList<Integer> list = new ArrayList<>();

			for (int j = 0; j < DIM; j++) {
				if (adjMax[i][j] == 1) {
					list.add(j);
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
			if (adjMax[j][i] == 1) {
				result++;
			}
		}
		
		return result;
	}
	
}

class TopoNode{
	int in;
	int data;
	ArrayList<Integer> list;
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
	public ArrayList<Integer> getList() {
		return list;
	}
	public void setList(ArrayList<Integer> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TopoNode info in is " + in + " data is " + data + " list is " + list.toString() + "\n";
	}
}
