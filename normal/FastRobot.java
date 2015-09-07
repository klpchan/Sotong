package com.sotong.normal;


//In Practice, You should use the statndard input/output
//in order to receive a score properly.
//Do not use file input and output. Please be very careful. 

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.awt.Point;
import java.io.FileInputStream;

class FastRobot {
 
 static final int NOT_VISITED = Integer.MIN_VALUE;
 static final char WALL = '1';
 
 static int mazeWidth, mazeHeight;
 static Point startPosition, endPosition;
 static char[][] maze;
 static int[][] directionChanges;
 static Queue<Point> startPoints;
 
 public static void main(String args[]) throws Exception {
//     Scanner sc = new Scanner(System.in);
     Scanner sc = new Scanner(new FileInputStream("sample_input_fastRobot.txt"));

     int T = sc.nextInt();
     T = 1;
     for(int test_case = 0; test_case < T; test_case++) {
         /**********************************
          * Implement your algorithm here. *
          **********************************/
         initializeTestCase(sc);
         printMazeState();

         if (isStartEqualsEndPosition()) {
             System.out.println(0); // Print the answer to standard output(screen).
             continue;
         }
         
         int minDirectionChangesToEndPos = findMinimumDirectionChangesToEndPos();
         printDirectionChangesState();
         // Print the answer to standard output(screen).
         System.out.println(minDirectionChangesToEndPos); 
     }
     
     sc.close();
 }

 private static int findMinimumDirectionChangesToEndPos() {
     initializeFindingPath();
     
     while (!startPoints.isEmpty()) {
         Point currentPosition = startPoints.remove();

         if (goUp(currentPosition)) {
             return directionChanges[endPosition.x][endPosition.y];
         }
         
         if (goDown(currentPosition)) {
             return directionChanges[endPosition.x][endPosition.y];
         }
         
         if (goLeft(currentPosition)) {
             return directionChanges[endPosition.x][endPosition.y];
         }
         
         if (goRight(currentPosition)) {
             return directionChanges[endPosition.x][endPosition.y];
         }
     }
     
     return -1;
 }

 private static boolean goRight(Point currentPosition) {
     Point next = new Point(currentPosition.x + 1, currentPosition.y);
     while (next.x < mazeWidth && WALL != maze[next.x][next.y]) {
         if (NOT_VISITED == directionChanges[next.x][next.y]) {
             directionChanges[next.x][next.y] = directionChanges[currentPosition.x][currentPosition.y] + 1;
             if (next.x == endPosition.x && next.y == endPosition.y) {
                 return true;
             } else {
                 startPoints.add(next);
             }
         }
         next = new Point(next.x + 1, next.y);
     }
     return false;
 }

 private static boolean goLeft(Point currentPosition) {
     Point next = new Point(currentPosition.x - 1, currentPosition.y);
     while (next.x >= 0 && WALL != maze[next.x][next.y]) {
         if (NOT_VISITED == directionChanges[next.x][next.y]) {
             directionChanges[next.x][next.y] = directionChanges[currentPosition.x][currentPosition.y] + 1;
             if (next.x == endPosition.x && next.y == endPosition.y) {
                 return true;
             } else {
                 startPoints.add(next);
             }
         }
         next = new Point(next.x - 1, next.y);
     }
     return false;
 }

 private static boolean goDown(Point currentPosition) {
     Point next = new Point(currentPosition.x, currentPosition.y + 1);
     while (next.y < mazeHeight && WALL != maze[next.x][next.y]) {
         if (NOT_VISITED == directionChanges[next.x][next.y]) {
             directionChanges[next.x][next.y] = directionChanges[currentPosition.x][currentPosition.y] + 1;
             if (next.x == endPosition.x && next.y == endPosition.y) {
                 return true;
             } else {
                 startPoints.add(next);
             }
         }
         next = new Point(next.x, next.y + 1);
     }
     return false;
 }

 private static boolean goUp(Point currentPosition) {
     Point next = new Point(currentPosition.x, currentPosition.y - 1);
     while (next.y >= 0 && WALL != maze[next.x][next.y]) {
         if (NOT_VISITED == directionChanges[next.x][next.y]) {
             directionChanges[next.x][next.y] = directionChanges[currentPosition.x][currentPosition.y] + 1;
             if (next.x == endPosition.x && next.y == endPosition.y) {
                 return true;
             } else {
                 startPoints.add(next);
             }
         }
         next = new Point(next.x, next.y - 1);
     }
     return false;
 }

 private static void initializeFindingPath() {
     startPoints = new LinkedList<Point>();
     startPoints.add(startPosition);
     directionChanges[startPosition.x][startPosition.y] = -1;
 }

 private static boolean isStartEqualsEndPosition() {
     return startPosition.equals(endPosition);
 }

 private static void printMazeState() {
     System.out.println(String.format("START: (%d, %d)", startPosition.x + 1, startPosition.y + 1));
     System.out.println(String.format("END: (%d, %d)", endPosition.x + 1, endPosition.y + 1));
     
     for (int i = 0; i < mazeHeight; i++) {
         for (int j = 0; j < mazeWidth; j++) {
             System.out.print(maze[i][j]);
         }
         System.out.println("");
     }
 }
 
 private static void printDirectionChangesState() {
     System.out.println(String.format("START: (%d, %d)", startPosition.x + 1, startPosition.y + 1));
     System.out.println(String.format("END: (%d, %d)", endPosition.x + 1, endPosition.y + 1));
     
     for (int i = 0; i < mazeHeight; i++) {
         for (int j = 0; j < mazeWidth; j++) {
             System.out.print(directionChanges[i][j] == NOT_VISITED ? "X" : directionChanges[i][j]);
         }
         System.out.println("");
     }
 }

 private static void initializeTestCase(Scanner sc) {
     mazeWidth = sc.nextInt();
     mazeHeight = sc.nextInt();
     
     startPosition = new Point(sc.nextInt() - 1, sc.nextInt() - 1);
     endPosition = new Point(sc.nextInt() - 1, sc.nextInt() - 1);
     
     maze = new char[mazeWidth][mazeHeight];
     
     for (int i = 0; i < mazeHeight; i++) {
         String row = sc.next();
//         System.out.println(i + " Height row is " + row);
         for (int j = 0; j < mazeWidth; j++) {
             maze[j][i] = row.charAt(j);
//        	   maze[i][j] = row.charAt(j);
//             System.out.println(j + "/" + i + " is " + maze[j][i]);
         }
     }
     
     directionChanges = new int[mazeWidth][mazeHeight];
     
     for (int i = 0; i < mazeWidth; i++) {
         for (int j = 0; j < mazeHeight; j++) {
             directionChanges[i][j] = NOT_VISITED;
         }
     }
 }
}

class MazePoint {
 int x, y, counter;
}

