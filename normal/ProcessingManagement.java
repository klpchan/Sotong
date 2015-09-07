package com.sotong.normal;

/*

In Practice, You should use the statndard input/output
in order to receive a score properly.
Do not use file input and output. Please be very careful. 

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileInputStream;


class ProcessingManagement {
	static int N;
	static int [] Li = new int[5001];
	static int [] Wi = new int[5001];
	static int Answer;
	
	public static class SteelPlate implements Comparable<SteelPlate>{

		public int L;
		public int W;
		
		public SteelPlate(int L, int W) {
			// TODO Auto-generated constructor stub
			this.L = L;
			this.W = W;
		}
		
		public int compareTo(SteelPlate arg0) {
			// TODO Auto-generated method stub
			
			if (this.L == arg0.L) {
				return this.W - arg0.W;
			}
			
			return this.L - arg0.L;
		}

		public String toString() {
			// TODO Auto-generated method stub
			return "SteelPlate info : L = " + L + " W = " + W;
		}
	}

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("sample_input_ProcessManager.txt"));
		
		ArrayList<SteelPlate> steelPlates = new ArrayList<>();

		int T = sc.nextInt();
		T = 1;
		for(int test_case = 0; test_case < T; test_case++) {
			
			steelPlates.clear();
			
			N = sc.nextInt();
			for(int i=1;i<=N;i++)
			{
				Li[i] = sc.nextInt();
				Wi[i] = sc.nextInt();
//				System.out.println("create steelplate L = " + Li[i] + " W = " + Wi[i]);
				steelPlates.add(new SteelPlate(Li[i], Wi[i]));
			}
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			Answer = 0;
			
			if (steelPlates != null && steelPlates.size() > 0) {
				Collections.sort(steelPlates);
			}
			
			
/*			for (int i = 1; i < steelPlates.size(); i++) {
				if (steelPlates.get(i - 1).W > steelPlates.get(i).W) {
					Answer++;
				}
			}*/
			
			System.out.println(steelPlates.toString());
			
            while(steelPlates.size() > 0) {
                SteelPlate currentPair = new SteelPlate(0, 0);
                int n=0;
                while(n<steelPlates.size()) {
                    int h = steelPlates.get(n).L;
                    int w = steelPlates.get(n).W;
                    if( h >= currentPair.L && w >= currentPair.W) {
                        currentPair.L = h;
                        currentPair.W = w;
                        //韽暔霅橂姅 靷皝順曥潣 韥赴毵�雮波霊愱碃 鞝滉卑 頃滊嫟.
                        steelPlates.remove(n);
                    } else {
                        n++;
                    }
                }

                //攴鸽９ 頃橂倶臧�鞕勳劚 霅橃棃雼�
                Answer++;
            }
			
//			System.out.println(steelPlates.toString());
			
			// Print the answer to standard output(screen).
			System.out.println(Answer);
		}
	}
}

