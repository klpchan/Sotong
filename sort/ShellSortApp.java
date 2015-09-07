package com.sotong.sort;

import java.util.Arrays;

class ShellSort{
	//default start = 0; end = array.length - 1;
	public static void ShSort(int[] array,int start,int end) {
		if (array == null || start < 0 || start >= array.length || end < 0 || end > array.length || end < start) {
			System.out.println("ShellSort parm error");
			return;
		}
		
		int i;
		int j;
		int guide;
		int increment = end - start + 1;
		
		do {
			increment = increment / 3 + 1;
//			System.out.println("increament is " + increment);
			for (i = increment; i <= end; i++) {
				if (array[i] < array[i-increment]) {
//					System.out.println("adjust " + i);
					guide = array[i];
					for (j = i - increment; j >= start && array[j] > guide; j-=increment) {
/*						System.out.println("j =  " + j);
						System.out.println("array[" + (j + increment ) + "] is " + array[j]);*/
						array[j+increment] = array[j];
					}
//					System.out.println("j =  " + j);
					array[j+increment] = guide;
//					System.out.println("array[" + (j + increment ) + "] is " + guide);
					
				}
			}
//			System.out.println("array is " + Arrays.toString(array));
		} while (increment > 1);
	}
}

class ShellSortApp
{
	static int tart[] = {20,50,10,90,30,70,40,80,60};
//	static int tart[] = {3,5,14,54,32,1,-3,0,23,11,11,29};
//	static int tart[] = {9,1,5,8,3,7,4,6,2};
	public static void main(String[] args){
		ShellSort.ShSort(tart, 0, tart.length - 1);
		System.out.println(Arrays.toString(tart));
    }  // end main()
}  // end class StackApp
