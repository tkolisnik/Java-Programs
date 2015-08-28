//Assign4.java
//This is part of a program suite containing: Assign4.java, Vertex.java, Graph.java, Queue.java

//Assign4
//Version 14.4 
//Created by: Tyler Kolisnik, Student ID: 10108327
//Created for: CPSC 319 Class at University of Calgary; Prof: Dr. Manzara, TA: Cory Bloor.

//DESCRIPTION:
//This is a program that takes in an input file which contains an adjacency matrix, and a query file that 
// contains queries about node paths. 
// The program reads in the adjacency matrix, then reads the query file and uses Breadth-First Traversal
// and Depth-First Traversal in order to find that path, and writes each to a separate output file. 
// In this output file, if no path is found it is listed by -1. 


//ACKNOWLEDGEMENT:
//This program contains code taken from:
// Dr. Leonard Manzara and his CPSC 319 class, aswell as the textbook Drozdek Data Structures and Algorithms in Java 2nd Edition 2010.
// Longsheng Zhou 
// https://github.com/lszhou 

//IMPORTANT NOTE:
// This program does not function correctly. The breadth-first traversal produces output, the depth-first does not. 
// The beginning workings of Djikstras were implemented but do not function. 

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.UnknownFormatConversionException;

public class Assign4
{

	public static void main(String [] args) 
	{
	try{

		FileReader fr = null;
		BufferedReader br = null;

		//Take in command line inputs 
		String filename = args[0];
		String queryname = args[1];
		String outputname1 = args[2];
		String outputname2 = args[3];
		//String outputname3 = args[4]; //input for Djikstras
		
		String fileLine = null;
		fr = new FileReader(filename);
		br = new BufferedReader(fr);
		fileLine = br.readLine();

		if (fileLine == null) {
			System.out.println("Empty input file");
			System.exit(0);
		}


		//Recreate the input file as a data structure in Java
		String trimmedFileLine = fileLine.replaceAll("\\s+"," ");
		String[] splitFileLine = trimmedFileLine.split(" ");
		
		int graphSize = (trimmedFileLine.length()/2) ;
		System.out.print("graph size:"+graphSize+"\n");
		
		//Create Adjacency Matrix
				int[][] adjMatrix = new int[graphSize+1][graphSize+1]; 
				int row = 0;
				
				//Read in the file and Fill the Matrix
				while (fileLine != null) {
					trimmedFileLine = fileLine.replaceAll("\\s+"," ");
					splitFileLine = trimmedFileLine.split(" ");
					row++;

					for (int x = 0; x < graphSize; x++) {
						int this_element = Integer.parseInt(splitFileLine[x]);
						adjMatrix[row][x] = this_element;
						
					}

					fileLine = br.readLine();
				}
		//PRINT THE INPUTTED GRAPH TO ENSURE IT WAS INPUTTED PROPERLY
	/*	System.out.printf("Matrix Graph:\n");
		for (int i = 1; i <= graphSize; i++) { //For each row value
			for (int j = 0; j <= graphSize; j++) //For each column value
				System.out.printf("%d ", adj_matrix[i][j]);
			System.out.println();
		}
		*/
				
		//Initializing the graph using the adjacency matrix read in from input file
		Graph newGraph = new Graph(adjMatrix, graphSize);

		//Open new file for queries
                //Opening a file reader to parse through the input file
                fr = new FileReader(queryname);
                br = new BufferedReader(fr);
                //fileLine = br.readLine();

               


		int startVX;
		int endVX;
		
		
		

// BEGIN DEPTH-FIRST SEARCH HERE
              
                        File outputFile = new File(outputname1);
                        FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
                        BufferedWriter bw = new BufferedWriter(fw);
                        
			while ((fileLine = br.readLine()) != null) {
	
				trimmedFileLine = fileLine.replaceAll("\\s+"," ");
				splitFileLine = trimmedFileLine.split(" ");
	
				startVX = Integer.parseInt(splitFileLine[0]);
				//System.out.println("Start Vertex ID:"+startVX);
				endVX = Integer.parseInt(splitFileLine[1]);			
				//System.out.println("End Vertex ID:"+endVX);
				
				
				//Start of DFS
				Vertex initialVertex = newGraph.getVertex(startVX);
				String path = String.valueOf(startVX);
				path = newGraph.DFS(initialVertex, endVX, path);
				path = path + ", " + String.valueOf(endVX);
				//System.out.println(path+"\n");
				bw.write(path);
				bw.write("\n");	
		
			}
			bw.close();
			fw.close();	

//// BEGIN BREADTH FIRST SEARCH HERE
                
                //Reread the query file 
                fr = new FileReader(filename);
                br = new BufferedReader(fr);
                fileLine = br.readLine();

                if (fileLine == null) {
                        System.out.println("Empty input query file");
                        System.exit(0);
                }


                while ((fileLine = br.readLine()) != null) {

	        	 	  trimmedFileLine = fileLine.replaceAll("\\s+"," ");
	        	 	  splitFileLine = trimmedFileLine.split(" ");
	
	                 startVX = Integer.parseInt(splitFileLine[0]);
	                 endVX = Integer.parseInt(splitFileLine[1]);
	         
	
	                //BFS
                    Vertex initialVertex = newGraph.getVertex(startVX);
	
	                //Reset the graph
	                newGraph.setVertexesUnvisited();
	                int pathArr[] = new int[graphSize];
	
	                //Initializing the path Array to -1   
	                for (int x = 0; x < graphSize; x++) {
	                        pathArr[x] = -1;
	                }
	                newGraph.BFS(initialVertex, endVX, pathArr);
	                
	                /*System.out.println("Printing path");
	                for (int x = 0; x < graphSize; x++) {
	                        System.out.println(pathArr[x]);
	                }
	                */
                }

                }
        		catch(ArrayIndexOutOfBoundsException e){ //Catch errors due to incorrectly formatting the input 
        			System.err.println("*******************************************************************");
        			System.err.println("IndexOutOfBoundsException: Something went wrong with your input that caused an error.");
        			System.err.println("IndexOutOfBoundsException: Please make sure there are four paramaters after the filename specifying an inputfile, a queryfile, and two outputfiles.");
        			System.err.println("Please ensure you are inputting an input file name and two output file names");
        			System.err.println("Correct input format: java Assign4 inputfile.txt queryfile.txt outputfile1.txt outputfile2.txt");
        			System.err.println("*******************************************************************");	
        		}
        		catch(FileNotFoundException e){ //Catch the error that the inputFile is unable to be opened
        			System.err.println("*******************************************************************");
        			System.err.println("FileNotFoundException: The program could not find or correctly open your input file, please check the directory and try again, or specify the complete path.");
        			System.err.println("Correct input format: java Assign4 inputfile.txt queryfile.txt outputfile1.txt outputfile2.txt");
        			System.err.println("*******************************************************************");
        		}
        		catch(UnknownFormatConversionException e){ //Catch the error that someone tries to enter something that is not a .txt file as input (i.e. a pdf)
        			System.err.println("*******************************************************************");
        			System.err.println("UnknownFormatConversionException: The program could not correctly read your input file, please ensure it is a .txt file and try again.");
        			System.err.println("Correct input format: java Assign4 inputfile.txt queryfile.txt outputfile1.txt outputfile2.txt");
        			System.err.println("*******************************************************************");
        		}
        		catch(StringIndexOutOfBoundsException e){ //Catch errors due to incorrect input file.
        			System.err.println("*******************************************************************");
        			System.err.println("StringIndexOutOfBoundsException: Something went wrong with your input that caused an error.");
        			System.err.println("StringIndexOutOfBoundsException: Please make sure your input file has contents and is a plain text file.");
        			System.err.println("Correct input format: java Assign4 inputfile.txt queryfile.txt outputfile1.txt outputfile2.txt");
        			System.err.println("*******************************************************************");	
        		} catch (IOException e) { //Catch errors due to incorrect input file or unwritable outputfiles.
        			System.err.println("*******************************************************************");
        			System.err.println("IOException: Something went wrong with your input that caused an error.");
        			System.err.println("IOException: Please make sure your input file has contents and is a plain text file.");
        			System.err.println("IOException: Please make sure your input file has contents in the specified format.");
        			System.err.println("IOException: Please make sure you are not trying to write to an unwritable file.");
        			System.err.println("Correct input format: java Assign4 inputfile.txt outputfile1.txt outputfile2.txt");
        			System.err.println("*******************************************************************");	
        			e.printStackTrace();
        		}
		}
	}
		
        