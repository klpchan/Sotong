package com.sotong.sort;

import java.util.Arrays;

class StraightInsertSort{
	//default start = 0; end = array.length - 1;
	public static void SISort(int[] array,int start,int end) {
		if (array == null || start < 0 || start >= array.length || end < 0 || end > array.length || end < start) {
			System.out.println("SISort parm error");
			return;
		}
		int i;
		int j;
		int guide;
		
		for (i = start + 1; i <= end; i++) {
			if (array[i] < array[i-1]) {
				guide = array[i];
				for (j = i - 1; j >= start && array[j] > guide; j--) {
					array[j+1] = array[j];
				}
				array[j+1] = guide;
			}
		}
	}
}

class StraightInsertSortApp
{
//	static int tart[] = {20,50,10,90,30,70,40,80,60};
	static int tart[] = {3,5,14,54,32,1,-3,0,23,11,11,29};
	public static void main(String[] args){
		StraightInsertSort.SISort(tart, 0, tart.length - 1);
		System.out.println(Arrays.toString(tart));
    }  // end main()
}  // end class StackApp
