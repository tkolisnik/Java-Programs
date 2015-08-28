import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.UnknownFormatConversionException;
@SuppressWarnings(value = { "all" })

//Assign2
//Version 27.0 
//Created by: Tyler Kolisnik, Student ID: 10108327
//Created for: CPSC 319 Class at University of Calgary; Prof: Dr. Manzara, TA: Cory Bloor.

//Companion Classes: LinkedList.java, Node.java

//DESCRIPTION:
//This is a program that sorts an inputfile of anagrams including words, letters, numbers or characters.

//INSTRUCTIONS:
//This program takes command line input in the form: java Assign2 inputfile outputfile
//Sample input: java Assign2 anagramFile.txt outputAnagrams.txt
//The outputfile is created in the current directory
//The inputfile should contain one anagram per row, with no blank rows. 
//Sample inputfile:
//cat
//dog
//hat
//tah
//act
//tac

//ACKNOWLEDGEMENT:
//This program may contain segments of code or ideas from other sources. 
// Special Thanks to:
// Dr. Leonard Manzara

// Longsheng Zhou 
// https://github.com/lszhou

// Crunchify
// http://crunchify.com/how-to-implement-a-linkedlist-class-from-scratch-in-java/

// Cory Bloor
// http://pages.cpsc.ucalgary.ca/~cgbloor

//X. Wang
//http://www.programcreek.com/2012/11/quicksort-array-in-java/, by X. Wang

// http://www.java-fries.com/2014/05/check-if-two-strings-are-anagrams-or-not/

public class Assign2 {

	 public static void main(String args[]) throws Exception{
		 
	try{
		FileReader fr = null;
		BufferedReader br = null;
		
		String inputFile = args[0]; // Get the input file name
		String outputFile = args[1]; // Get the output file name
		
		fr = new FileReader(inputFile);
		br = new BufferedReader(fr);	 
		 
		String test;
		long startTime = System.nanoTime();
		 
		 //Get the number of lines in the File:
		int index = 0;
		
		while (br.readLine() != null){ 
			 index++;
		}
		 
		 LinkedList[] LLarr = new LinkedList[index]; //Create the initial array of linked lists.
		 
		 populateArray(LLarr, inputFile, index); //Fill the array with anagrams using insertion sort to put the anagrams in alphabetical order.
		 
		 quickSort(LLarr,0,counter); //Sort the array alphabetically by row
		
		 displayArray(LLarr, index, outputFile); //Output the array to the textfile outputFile.
		
		 long endTime = System.nanoTime(); //End timing algorithm
   		 long estimatedTime = endTime - startTime; //Calculate time difference
   		 double timeInSeconds = (estimatedTime * 1.0e-9);//Convert nanoseconds to seconds
   		 System.out.print("Sorting of Anagrams Complete, you can find them in: "+outputFile+"\n");
   		 System.out.print("\nThe algorithm took: "+ timeInSeconds+ " seconds to run\n");
		}
		
		//Error Handling
		catch(ArrayIndexOutOfBoundsException e){ //Catch errors due to incorrectly formatting the input 
			System.err.println("*******************************************************************");
			System.err.println("IndexOutOfBoundsException: Something went wrong with your input that caused an error.");
			System.err.println("IndexOutOfBoundsException: Please make sure there are two paramaters after the filename specifying an inputfile and an outputfile.");
			System.err.println("IndexOutOfBoundsException: Please make sure there are no words with spaces in your input file");
			System.err.println("Please ensure you are inputting an input file name and output file name");
			System.err.println("Correct input format: java Assign2 inputfile.txt outputfile.txt");
			System.err.println("*******************************************************************");	
		}
		catch(FileNotFoundException e){ //Catch the error that the inputFile is unable to be opened
			System.err.println("*******************************************************************");
			System.err.println("FileNotFoundException: The program could not find or correctly open your input file, please check the directory and try again, or specify the complete path.");
			System.err.println("Correct input format: java Assign2 inputfile.txt outputfile.txt");
			System.err.println("*******************************************************************");
		}
		catch(UnknownFormatConversionException e){ //Catch the error that someone tries to enter something that is not a .txt file as input (i.e. a pdf)
			System.err.println("*******************************************************************");
			System.err.println("UnknownFormatConversionException: The program could not correctly read your input file, please ensure it is a .txt file and try again.");
			System.err.println("Correct input format: java Assign2 inputfile.txt outputfile.txt");
			System.err.println("*******************************************************************");
		}
	 }
	 
	static int counter = 0; //This is used to keep track of how many different sets of anagrams there are.
	public static void counter(){ //Augments the counter by 1 each time it is called
		 counter++;
	 }
	 
	public static LinkedList[] populateArray(LinkedList[] array, String inputFile, int index) throws IOException{
		
		BufferedReader br = null;
		FileReader fr = null;
		fr = new FileReader(inputFile);
		br = new BufferedReader(fr);
		
		String newWord;
		int counter = 0;
		
		for (int x=0; x<index; x++){ //Fill the array with empty linked lists
			array[x]=new LinkedList();
		}
		
		 while ((newWord=br.readLine()) != null){ //While there is lines in our file,
			 
			 outerloop:
			 for (int i=0; i<index; i++){ 
				 if(array[i].isEmpty()==true){
					 array[i].addFirst(newWord); //If the LinkedList is empty it just adds it as the first node.
					 counter(); //This counts the total amount of linked lists created, so that the size of the final array can be determined
					// System.out.println("Is empty was true, adding "+newWord+" to a new linked list");
					 break outerloop;
				 }
				 else if ((isAnagram(array[i].getFirstData(), newWord))){
					 array[i].add(newWord); //If the LinkedLIst already has contents, it uses the function add which uses insertion sort to place it alphabetically.
					// System.out.println("They are anagrams, adding "+newWord+ " to a previous linked list");
					 break outerloop;
				 }
				 
			 }
				
		 }
		return array;
		
	}
	
	public static void displayArray(LinkedList[] array, int size, String outputfile) throws Exception{
		PrintStream originalStream = new PrintStream(System.out); 
		System.setOut(new PrintStream(new FileOutputStream(outputfile)));
		for (int x=0; x<size; x++){
			if ( array[x].isEmpty()==false){
				
				
				array[x].display();
				
			}
		}
		System.setOut(originalStream); //This allows it to continue printing to the command line after it has printed to the file.
	} 
	 private static boolean isAnagram(String str1, String str2) {
		 // Adapted from:
		 // http://www.java-fries.com/2014/05/check-if-two-strings-are-anagrams-or-not/
		 
		 	// If the strings are empty, they are not anagrams
		 	if (str1.length() == 0){
	            return false;
	        }
	        if (str2.length() == 0){
	            return false;
	        }
	        // If string lengths are not same, the strings are not anagrams.
	        if (str1.length() != str2.length()) {
	            return false;
	        }
	        // Sort characters of both the strings.
	        str1 = sortCharacters(str1);
	        str2 = sortCharacters(str2);
	 
	       // If after sorting if strings are equal then they are anagrams.
	       if (str1.equals(str2)) {
	            return true;
	        }
	        return false;
	        
	    }
	 
	 private static String sortCharacters(String str) {  //Sorts the characters
		 // Adapted from:
		 // http://www.java-fries.com/2014/05/check-if-two-strings-are-anagrams-or-not/
	        char[] charArray = str.toLowerCase().toCharArray();
	        Arrays.sort(charArray); 
	        return String.valueOf(charArray);
	    }
	 

	 
	 public static LinkedList[] quickSort(LinkedList[] arr, int low, int high) throws FileNotFoundException { 
			//Code adapted from: http://www.programcreek.com/2012/11/quicksort-array-in-java/, by X. Wang
				
				if (arr == null || arr.length == 0 || arr[low].getFirstData()==null)
					return arr;
		 
				if (low >= high)
					return arr;
		 
				//Pick the pivot
				int middle =  low + (high - low) / 2;
				int i = low, j = high;
				LinkedList pivot = arr[middle];
		
				//Make left < pivot and right > pivot
			
				while (i<=j) {
					while (arr[i].getFirstData().compareToIgnoreCase(pivot.getFirstData()) < 0) {
						i++;
					}
		 
					while (arr[j].getFirstData().compareToIgnoreCase(pivot.getFirstData()) > 0) {
						j--;
					}
		 
					if (i<=j) {
						LinkedList temp = arr[i];
						arr[i] = arr[j];
						arr[j] = temp;
						i++;
						j--;
					}
				}
		 
				//Recursively sort two sub parts
				if (low < j)
					quickSort(arr, low, j);
		 
				if (high > i)
					quickSort(arr, i, high);
				
				return arr;
	} // End Quick Sort Method
	 
 }    