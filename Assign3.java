import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

//Assign3
//Version 17.1 
//Created by: Tyler Kolisnik, Student ID: 10108327
//Created for: CPSC 319 Class at University of Calgary; Prof: Dr. Manzara, TA: Cory Bloor.

//Companion Classes: BST.java BSTInterface.java

//DESCRIPTION:
//This is a program that sorts an input file of student records by building
// a binary search tree.
// It then traverses the tree using a depth-first in order traversal and prints the output to outputfile1
// and then it traverses the tree using a breadth-first traversal and prints the output to outputfile2.
/*
Example input file lines:
I8534534McKay                    0251CT  1
I8400342LaPorte                  0045JA  1
D8499120Black                    0341RST 1
 */


//ACKNOWLEDGEMENT:
//This program contains code taken from:
// Dr. Leonard Manzara and his CPSC 319 class, aswell as the textbook Drozdek Data Structures and Algorithms in Java 2nd Edition 2010.
// Longsheng Zhou 
// https://github.com/lszhou 


public class Assign3 {
	/*-----------------------BST Test-----------------------*/
	public static void main(String[] args) throws Exception {
		int[] arrTest = { 1, 4, 2, 3, 6, 5 };
		BST<Integer> tree = new BST<>();
		String filename = args[0];
		String output1 = args[1];
		String output2 = args[2];
		FileReader fr = null;
		BufferedReader br = null;
		
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String fileLine = br.readLine();
			boolean isFinished = false;

			if (args.length != 3) {
				
				System.out.println("Usage: java Assign2 inputfile.txt outputfile1.txt(order traversal) outputfile2.txt(breadth first traversal)");
				System.exit(0);
			}
			if (fileLine == null) {
				System.out.println("Input file empty");
				isFinished = true;
			}
			String indel;
			String lastName;
			String studentInfo;
			BST<String> newTree = new BST<>();
			while (!isFinished) {
				if (fileLine == null) {
					isFinished = true;
					break;
				}
				indel = fileLine.substring(0,1);
				lastName = fileLine.substring(8, fileLine.indexOf(" "));
				studentInfo = fileLine.substring(1);
				String info = lastName+"*"+studentInfo;

				if (indel.equals("I")) {

					//Insert the node
					newTree.insert(info);

				}
				else if(indel.equals("D")) {

					//Delete the node
					newTree.delete(info);
				}

				//Check next line
				fileLine = br.readLine();
			}
			
		//Print
		PrintStream originalStream = new PrintStream(System.out); 
		System.setOut(new PrintStream(new FileOutputStream(output1)));
		//Print In-order traversal
		newTree.DFS_InOrder_Display();
		System.setOut(originalStream);

		System.setOut(new PrintStream(new FileOutputStream(output2)));
		//Print Breadth-First-Search Traversal
		newTree.BFS_LevelOrder_Display();
		System.setOut(originalStream);
	}
		
	}



