package com.sotong.normal;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class LISBSTExample {
 static int N;
 static int [][] L = new int[260][260];
 static int Answer;
 static int[][] result = new int [260][260];
 static int[] save = new int [5];
 static int num;
 public static void main(String args[]) throws Exception {
//     Scanner sc = new Scanner(System.in);
     Scanner sc = new Scanner(new FileInputStream("sample_input_LongestIncrease.txt"));

     int T = sc.nextInt();
     T = 1;
     int i,j;
     for(int tc = 0; tc < T; tc++) {
         N = sc.nextInt();
         for( i = 1; i <= N; i++) {
             for( j = 1; j <= N; j++) {
                 L[i][j] = sc.nextInt();
                 if(i==N||j==N)
                     result[i][j]=1;
                 else 
                     result[i][j]=0;
             }
         }
         /**********************************
         *  Implement your algorithm here. *
         ***********************************/
         Answer = 0;
         for(i=N-1;i>=1;i--)
         {
             setSave(i);
             printSave();
             for(j=N-1;j>=1;j--)
             {

                    result[i][j]=search(i,j,L[i][j]);
                    
                    if(result[i][j]>Answer)
                       Answer=result[i][j];
             
             }
         
         }

         // Print the answer to standard output(screen).
         System.out.println(Answer);
     }
 }
 public static  int search(int i,int j,int v)
 {
	 //i = 3, j = 3,v = 2
     int m,n,temp;
     System.out.println("start search i =  " + i + " j = " + j + " v = " + v);
     for(m=i+1;m<=N;m++)
     {
    	 
         temp=result[m][j+1];
         System.out.println("search row m :  "  + m + " queue count is " + temp);
         if(L[m][j+1]>save[temp])
         {
             if(save[temp]==0){
                 num=result[m][j+1];
                 System.out.println(" Change num to be  " + num);
             }
             save[temp]=L[m][j+1];
             printSave();
         }
     
     }
     
     System.out.println("Look at a Save Array:");
     printSave();
     
     for(n=num;n>=1;n--)
         if(v<=save[n]){
        	 System.out.println("search Result[" + i + "][" + j + "] is " + (n + 1));
             return n+1;
         }
     return 1;


 }
 public static void setSave(int i)
 {
     int k;
     int max=0;
     for(k=i+1;k<=N;k++)
     {
         if(L[k][N]>max)
             max=L[k][N];

     }
     num=1;
     save[1]=max;
     for(k=2;k<=N;k++)
         save[k]=0;
 }
 
 public static void printSave(){
	 System.out.println(Arrays.toString(save));
 }
}
