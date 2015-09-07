package com.sotong.sort;

import java.util.Arrays;

class BubbleSort{
	//default start = 0; end = array.length - 1;
	public static void BSort(int[] array,int start,int end) {
		int i;
		int j;
		boolean needToSwap = true;
		for (i = start; i < end && needToSwap; i++) {
			needToSwap = false;
			for (j = end - 1; j >= i; j--) {
				if (array[j] > array[j+1]) {
					int content = array[j];
					array[j] = array[j+1];
					array[j+1] = content;
					needToSwap = true;
				}
			}
		}
	}
}


class BubbleSortApp
{
	static int tart[] = {20,50,10,90,30,70,40,80,60};
	public static void main(String[] args){
		BubbleSort.BSort(tart, 0, tart.length - 1);
		System.out.println(Arrays.toString(tart));
    }  // end main()
}  // end class StackApp
