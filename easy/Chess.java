package com.sotong.easy;

//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.Scanner;
import java.io.FileInputStream;
import java.lang.reflect.Array;

class Chess {
	
	static int S,K; //defend
	static int R,C; //attack
	static int N,M; //N row M column
	static int result;
	public static  MyQueue<Point> queueTool;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input_Chess.txt"));

		int T = sc.nextInt();
		
		for(int tc = 0; tc < T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			S = sc.nextInt();
			K = sc.nextInt();
//			System.out.println("N:M:R:C:S:K = " + N + M + R + C + S + K);
			/**********************************
			*  Implement your algorithm here. *
			***********************************/
			result = -1;
			queueTool = new MyQueue<Point>(N * M);
			// Print the answer to standard output(screen).
			BFS(new Point(R, C, 0));
			System.out.println(result);
		}
		
		sc.close();
	}
	
	private static void BFS(Point startPoint) {
		boolean [][]visited = new boolean[N+1][M+1];
		
		queueTool.add(startPoint);//Ìí¼Ó³õÊ¼Öµ
		visited[startPoint.x][startPoint.y] = true;
		
		while (!queueTool.isEmpty()) {
			Point popPoint = queueTool.poll();
			if (popPoint.x == S && popPoint.y == K) {
				result = popPoint.path;
				return;
			}
			int x = popPoint.x;
			int y = popPoint.y;
			int path = popPoint.path;
			if ( x > 2 && y > 1 && !visited[x-2][y-1]) { 
				queueTool.add(new Point(x - 2, y - 1, path + 1)); 
				visited[x-2][y-1] = true;
			}
			if ( x > 1 && y > 2 && !visited[x-1][y-2]) { 
				queueTool.add(new Point(x - 1, y - 2, path + 1)); 
				visited[x-1][y-2] = true;
			}
			if ( x <= M - 1 && y > 2 && !visited[x+1][y-2]) { 
				queueTool.add(new Point(x + 1, y - 2, path + 1));
				visited[x+1][y-2] = true;
			}
			if ( x <= M - 2 && y > 1 && !visited[x+2][y-1]) { 
				queueTool.add(new Point(x + 2, y - 1, path + 1));
				visited[x+2][y-1] = true;
			}
			if ( x <= M - 2 && y <= N - 1 && !visited[x+2][y+1]) { 
				queueTool.add(new Point(x + 2, y + 1, path + 1));
				visited[x+2][y+1] = true;
			}
			if ( x <= M - 1 && y <= N - 2 && !visited[x+1][y+2]) { 
				queueTool.add(new Point(x + 1, y + 2, path + 1));
				visited[x+1][y+2] = true;
			}
			if ( x > 1 && y <= N - 2 && !visited[x-1][y+2]) { 
				queueTool.add(new Point(x - 1, y + 2, path + 1));
				visited[x-1][y+2] = true;
			}
			if ( x > 2 && y <= N - 1 && !visited[x-2][y+1]) { 
				queueTool.add(new Point(x - 2, y + 1, path + 1));
				visited[x-2][y+1] = true;
			}
		}
	}
}

class MyQueue<T>
{
	private int maxSize;
	private T[] queArray;
	private int front;
	private int rear;
	private int nItems;
	//--------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public MyQueue(int s)          // constructor
	   {
	   maxSize = s;
	   queArray = (T[]) Array.newInstance(Object.class,s); ;
	   front = 0;
	   rear = -1;
	   nItems = 0;
	   }
	//--------------------------------------------------------------
	public void add(T j)   // put item at rear of queue
	   {
	   if(rear == maxSize-1)         // deal with wraparound
	      rear = -1;
	   queArray[++rear] = j;         // increment rear and insert
	   nItems++;                     // one more item
	   }
	//--------------------------------------------------------------
	public T poll()         // take item from front of queue
	   {
	   T temp = queArray[front++]; // get value and incr front
	   if(front == maxSize)           // deal with wraparound
	      front = 0;
	   nItems--;                      // one less item
	   return temp;
	   }
	//--------------------------------------------------------------
	public T peekFront()      // peek at front of queue
	   {
	   return queArray[front];
	   }
	//--------------------------------------------------------------
	public boolean isEmpty()    // true if queue is empty
	   {
	   return (nItems==0);
	   }
	//--------------------------------------------------------------
	public boolean isFull()     // true if queue is full
	   {
	   return (nItems==maxSize);
	   }
	//--------------------------------------------------------------
	public int size()           // number of items in queue
	   {
	   return nItems;
	   }
	//--------------------------------------------------------------
} 

class Point{
	int x;
	int y;
	int path;
	public Point(int x, int y,int path) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.path = path;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getPath() {
		return path;
	}
	public void setPath(int path) {
		this.path = path;
	}
}

/*	public static void  move(int x, int y, int path) {
	if (x == S && y == K) {
		result = path;
		successFind = true;
		return;
	}
//	if (!successFind) {
		if (!successFind && x > 2 && y > 1) { move(x - 2, y - 1, path + 1); }
		if (!successFind && x > 1 && y > 2) { move(x - 1, y - 2, path + 1); }
		if (!successFind && x <= M - 1 && y > 2) { move(x + 1, y - 2, path + 1); }
		if (!successFind && x <= M - 2 && y > 1) { move(x + 2, y - 1, path + 1); }
		if (!successFind && x <= M - 2 && y <= N - 1) { move(x + 2, y + 1, path + 1); }
		if (!successFind && x <= M - 1 && y <= N - 2) { move(x + 1, y + 2, path + 1); }
		if (!successFind && x > 1 && y <= N - 2) { move(x - 1, y + 2, path + 1); }
		if (!successFind && x > 2 && y <= N - 1) { move(x - 2, y + 1, path + 1); }
//	}
}*/