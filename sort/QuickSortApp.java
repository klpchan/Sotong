package com.sotong.sort;

import java.util.Arrays;


class QuickSort{
	static int count = 0;
	
	public static void qSort(int[] array,int low, int high) {
		int pivot;
		if (low < high) {
			pivot = partition(array,low,high);
			System.out.println("qSort start " + ++count + ": pivot is " + pivot);
			System.out.println("array : " + Arrays.toString(array));
			qSort(array, low, pivot - 1);
			qSort(array, pivot + 1, high);
		}
	}

	private static int partition(int[] array, int low, int high) {
		// TODO Auto-generated method stub
		int pivot = array[low];
		while (low < high) {
			while (low < high && array[high] >= pivot) {
				high--;
			}
			swap(array,low,high);
			while (low < high && array[low] <= pivot) {
				low++;
			}
			swap(array, low, high);
		}
		return low;
	}

	private static void swap(int[] array, int low, int high) {
		// TODO Auto-generated method stub
		if (low < 0 || high < 0 || low > high || low > array.length || high > array.length) {
			System.out.println("swap param error!");
			return ;
		}
		int tmp = array[low];
		array[low] = array[high];
		array[high] = tmp;
	}
	
	 public static void Qsort(int a[], int low, int high)
	 {
	    int i=low,j=high+1;
	
	    int key;
	
	    if(low >=high)
	 	   return;
	
	    while(true)
	    {
	 	   do{
	 		   i++;
	 	   }while(i<=high && a[i]<a[low]);
	
	 	   do{
	 		   j--;
	 	   }while(a[j]>a[low]);
	 	   if(i>j)
	 		   break;
	
	 	   key=a[i];
	 	   a[i]=a[j];
	 	   a[j]=key;
	
	    }
	
	        key=a[j];
	 	   a[j]=a[low];
	 	   a[low]=key;
	
	        Qsort(a,low,j-1);
	 	   Qsort(a,j+1,high);
	 }
}

class QuickSortApp
{
	static int tart[] = {20,50,10,90,30,70,40,80,60};
	public static void main(String[] args){
		QuickSort.qSort(tart, 0, tart.length - 1);
		System.out.println(Arrays.toString(tart));
    }  // end main()
}  // end class StackApp
