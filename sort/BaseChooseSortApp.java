package com.sotong.sort;

import java.util.Arrays;

class BaseChooseSort{
	//default start = 0; end = array.length - 1;
	public static void BCSort(int[] array,int start,int end) {
		if (array == null || start < 0 || start >= array.length || end < 0 || end > array.length || end < start) {
			System.out.println("BCSort parm error");
			return;
		}
		int i;
		int j;
		int min;
		
		for (i = start; i < end; i++) {
			min = i;
			for (j = i + 1; j <= end; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			if (min != i) {
				int tmp = array[i];
				array[i] = array[min];
				array[min] = tmp;
			}
		}
		
	}
}


class BaseChooseSortApp
{
	static int tart[] = {20,50,10,90,30,70,40,80,60};
	public static void main(String[] args){
		BaseChooseSort.BCSort(tart, 0, tart.length - 1);
		System.out.println(Arrays.toString(tart));
    }  // end main()
}  // end class StackApp
