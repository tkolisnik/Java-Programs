//Assign5.java
//This is part of a program suite containing: Assign5.java, HashTable.java

//Assign5
//Version 16.07
//Created by: Tyler Kolisnik, Student ID: 10108327
//Created for: CPSC 319 Class at University of Calgary; Prof: Dr. Manzara, TA: Cory Bloor.

//DESCRIPTION:
//This is a program that takes in an input file which contains a list of words, and 
// Then uses a hash function to calculate a proper table address for each words and 
//inputs it to a hash table. It then outputs statistics about the hash table to an output file.


//ACKNOWLEDGEMENT:
//This program contains code taken from:
// Dr. Leonard Manzara and his CPSC 319 class, as well as the textbook Drozdek Data Structures and Algorithms in Java 2nd Edition 2010.
// Longsheng Zhou 
// https://github.com/lszhou 


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Assign5
{

	public static void main(String [] args) throws Exception
	{
		//Check for Incorrect input error
		if (args.length != 2) {
			System.out.println("Usage: java Assign5 input_text_file output_text_file");
			System.exit(0);
		}
	
		FileReader fr = null;
		BufferedReader br = null;
		String inputFileName = args[0];
		String fileLine = null;
		String outputFileName = args[1];
		
		//Get the number of lines in the input file
		Process p = Runtime.getRuntime().exec("wc -l "+inputFileName); //Use Unix process 'wc -l' to get # of lines in file
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String input = stdInput.readLine().replaceAll(inputFileName,""); //Remove the filename
		String input2 = input.replaceAll("\\s+",""); //Remove blank spaces
		int size = Integer.parseInt(input2); //Convert to integer
		
		//This (below) makes the hash table 1.42 times the size of the input file. A number found by trial an error to
		//maximize the hashing efficiency while maintaining a load size above 70%
		//This can be changed in order to manually assign a hash table size, which may be desired.
		int hashTableSize = (int) (size*1.42); 

		HashTable hashTable = new HashTable(hashTableSize);
		

		//Open a file reader to get data from the input file
		fr = new FileReader(inputFileName);
		br = new BufferedReader(fr);
		fileLine = br.readLine();
	
		//Check for empty input file error
		if (fileLine == null) {
			System.out.println("Error: Input File Empty ");
			System.exit(0);
		}


		//Fill the Hash Table
		while (fileLine != null) {
			hashTable.input(fileLine, fileLine);
			fileLine = br.readLine();
		}
		
		//Summary Statistic Variables Declaration

		double hashingEfficiency; // Load factor / Average number of reads per record
		double loadFactor;	// Number of words in Hash Table/Total Size of Hash Table
		double avgReadsRecord;	// Total number of reads to find all values in hash table / Total number of words in hash table
		double totalNumberOfReads = 0;
		double numberOfWords = 1;
		int longestChain = 0;
				
		//Reread the input file to search for each word in the table
		FileReader fr2 = null;
		BufferedReader br2 = null;
		fileLine = null;

		fr2 = new FileReader(inputFileName);
		br2 = new BufferedReader(fr2);
		fileLine = br2.readLine();

		while (fileLine != null) {
			int numberOfReads = 0;

			numberOfReads = hashTable.search(fileLine);

			if (numberOfReads > longestChain) {
				longestChain = numberOfReads;
			}

			totalNumberOfReads += numberOfReads;
			numberOfWords += 1;
			fileLine = br2.readLine();

		}


		//Calculating the Summary Statistic Information 
		avgReadsRecord = totalNumberOfReads/numberOfWords;
		loadFactor = numberOfWords/hashTableSize;
		hashingEfficiency = loadFactor/avgReadsRecord;
		

		//Print the Summary Statistics To the Output File:
            try {
                 File outputFile = new File(outputFileName);
                 FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
                 BufferedWriter bw = new BufferedWriter(fw);

			String avgReadsRecordText = String.valueOf(avgReadsRecord);
			String loadFactorText = String.valueOf(loadFactor);
			String hashingEfficiencyText = String.valueOf(hashingEfficiency);
			String longestChainText = String.valueOf(longestChain);

			bw.write("Summary Statistics:\n");
			bw.write("Average Number of Reads per Record: ");
			bw.write(avgReadsRecordText); bw.write("\n");
			bw.write("Load Factor: ");
			bw.write(loadFactorText); bw.write("\n");
			bw.write("Hashing Efficiency: ");
			bw.write(hashingEfficiencyText); bw.write("\n");
			bw.write("Size of Longest Chain: ");
			bw.write(longestChainText); bw.write("\n");

            bw.close();
            fw.close();
            
                } catch (IOException e) {
                		System.out.print("IO Exception Found: Unable to write to output file" );
                        e.printStackTrace();
                }

	}

}

