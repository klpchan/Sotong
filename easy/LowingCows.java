package com.sotong.easy;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class LowingCows {
	
	static long N;
	static int array[];
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_lowcow.txt"));

		int T = sc.nextInt();
//		T = 2;
		for(int tc = 0; tc < T; tc++) {

			initArray(sc);
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			
			long Answer = 0;
			
			Answer = caculate();
			
			System.out.println(Answer);
			// Print the answer to standard output(screen).

		}
		
		sc.close();
	}
	
	public static void initArray(Scanner sc) {
		N = sc.nextInt();
		array = new int[(int) N];
		for (int i = 0; i < array.length; i++) {
			array[i] = sc.nextInt();
		}
		Qsort(array, 0, (int)N - 1);
		Arrays.sort(array);
//		System.out.println(Arrays.toString(array));
	}
	
	public static long caculate() {
		long result = 0;
		for (int i = 0; i < N; i++) {
			result += array[i] * i;
			result -= array[i] * (N - i -1);
		}
		return 2 * result;
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
	
/*	public static int displayInterval(int interval) {
		int result = 0;
		
		for (int i = 0; i < interval ; i++) {
			for (int j = i; j < N - interval; j+=interval) {
				result += Math.abs(array[j+interval]-array[j]);
			}
		}
		
		return result;
	}
	
	public static int CaculateSum() {
		int result = 0;
		
		for (int i = 1; i <= N - 1 ; i++) {
			result += displayInterval(i);
		}
		
		return 2 * result;
	}*/
}
