//Assign1
//Version 12.0 
//Created by: Tyler Kolisnik, Student ID: 10108327
//Created for: CPSC 319 Class at University of Calgary; Prof: Dr. Manzara, TA: Cory Bloor.

//Instructions: 
//This program takes command line input in the form: java Assign1 order size algorithm outputfile
//sample input: java Assign1 descending 100 bubble bubbetest1.txt
//The outputfile is created in the current directory



//Java IO packages to import:
import java.io.FileNotFoundException; 
import java.io.IOException;
import java.io.PrintWriter;

public class Assign1 {
	
	
	public static void main(String[] args) throws IOException{ // Begin Main Block
	
	String initialSortType = args[0]; // Get ascending, descending, or random parameter for input array
	int[] intArray = new int[Integer.parseInt(args[1])]; // Get size parameter, and convert size parameter to integer.
	int[] temp = new int[Integer.parseInt(args[1])]; //Create temp array for merge sort
	String algorithmChoice = args[2]; //Get parameter that specifies the algorithm chosen bubble, insertion, merge, quick
	String filename = args[3];// Get parameter that specifies the output file name, should end in .txt
	
	//String relativePath = ("/Users/user/Desktop/CPSCA1/input"+ filename);//uncomment to enable printing of input array
	//PrintWriter out1 = new PrintWriter(relativePath);//uncomment to enable printing of input array
	
	int n = intArray.length; //get the length of the input array
	
	//Catch input errors
	if (n < 0) {
		System.out.println("Invalid size selection. The input must be a positive integer");
		System.exit(0);
	}

	if (!algorithmChoice.equals("bubble") && !algorithmChoice.equals("insertion") && !algorithmChoice.equals("merge") && !algorithmChoice.equals("quick")) {
		algorithmChoice = "quick"; //defaults to quick if they did not enter args[2] properly
		System.out.println("Invalid algorithm selection.\nChoices are: 'bubble', 'insertion', 'merge', 'quick'\nDefaulting to quick");
	}
	
    	if (initialSortType.equals("random")){
    		for (int i = 0; i < intArray.length; i++) { 
    			double x = Math.random() * Integer.MAX_VALUE;
    			intArray[i]= (int) x;
    			//out1.println((int) x); //uncomment to enable printing of input array
    		}
    	} else if (initialSortType.equals("ascending")){
    		double x = Math.random() * Integer.MAX_VALUE;
    		for (int i = 0; i < intArray.length; i++) {
    			intArray[i]= (int) x;
        		//out1.println((int) x); //uncomment to enable printing of input array
        		x++;
    		}
    	} else if (initialSortType.equals("descending")){
    		double x = Math.random() * Integer.MAX_VALUE;
    		for (int i = 0; i < intArray.length; i++) {
    			intArray[i]= (int) x;
        		//out1.println((int) x); //uncomment to enable printing of input array
        		x--;
    		}
    	} else { //else catch input errors
    		System.out.printf("Your input of: " +(args[0])+ " was not accepted.\nPlease input either ascending, descending or random for the first parameter.\n");
    		System.exit(0);
    	}
    		//out1.close();//uncomment to enable printing of input array
    
           //Begin Bubble Sort Block 
	    if (algorithmChoice.equals("bubble")){ 
	    //Code adapted From: www.programmingsimplified.com/java/source-code/java-program-to-bubble-sort
	    	
	    	//Begin Bubble Sort Algorithm
	    	long startTime1 = System.nanoTime(); //Begin timing algorithm
		    int c, d, swap;
		    for (c = 0; c <  n - 1; c++) {
		      for (d = 0; d < n - c - 1; d++) {
		        if (intArray[d] > intArray[d+1]) {
		          swap = intArray[d];
		          intArray[d]  = intArray[d+1];
		          intArray[d+1] = swap;
		     //End Bubble Sort Algorithm
		          
		        }
		      }
		    }
		   
			    long endTime1 = System.nanoTime(); //End timing algorithm
   	   		    long estimatedTime = endTime1 - startTime1; //Calculate time difference
   	   		    double timeInSeconds = (estimatedTime * 1.0e-9);// Convert nanoseconds to seconds

   	   		    System.out.print("Sorting Complete\n");
   	   		    System.out.print("The algorithm took: "+ timeInSeconds+ " seconds to run\n");//Print algorithm run time

         		printArray(intArray, filename); //Print the array to an outputfile
         		
         		//End Bubble Sort Block
	    } 
	    
	    //Begin Insertion Sort block
	    if (algorithmChoice.equals("insertion")){ 
	    //Code adapted from: http://www.java2novice.com/java-interview-programs/insertion-sort/
	    	
	    	//Begin insertion sort algorithm
	    	long startTime2 = System.nanoTime(); //Begin timing algorithm
	         for (int j = 1; j < n; j++) {
	             int key = intArray[j];
	             int i = j-1;
	             while ( (i > -1) && ( intArray [i] > key ) ) {
	                 intArray [i+1] = intArray [i];
	                 i--;
	             }
	            intArray[i+1] = key;
	         } // End insertion sort algorithm
	    		
	         		long endTime2 = System.nanoTime(); //End timing algorithm
	   	   		    long estimatedTime = endTime2 - startTime2; //Calculate time difference
	   	   		    double timeInSeconds = (estimatedTime * 1.0e-9);//Convert nanoseconds to seconds
	   	   		    System.out.print("Sorting Complete\n");
	   	   		    System.out.print("\nThe algorithm took: "+ timeInSeconds+ " seconds to run\n"); //Print algorithm run time
	   	   		    printArray(intArray, filename); //Print the array to an outputfile
	    	         
	    	     }
	    
	    //Begin Quick Sort block
	    if (algorithmChoice.equals("quick")){
	    	//Begin Quick Sort Algorithm
	    	long startTime = System.nanoTime(); //Begin timing algorithm
	    	int[] x = intArray;
			int low = 0;
			int high = x.length - 1;
			quickSort(x, low, high); //Pass input to method

		    long endTime = System.nanoTime(); //End timing algorithm
	   		long estimatedTime = endTime - startTime; //Calculate time difference
	   		double timeInSeconds = (estimatedTime * 1.0e-9); // Convert nanoseconds to seconds
	   		System.out.print("Sorting Complete\n");
	   		System.out.print("\nThe algorithm took: "+ timeInSeconds+ " seconds to run\n"); //Print algorithm run time
	   		printArray(x, filename); //Print the Array to an outputfile
		}
	    //End Quick Sort Block
	    
	    //Begin Merge Sort Block
	    if (algorithmChoice.equals("merge")){
	    	//Begin merge sort algorithm
	    	long startTime = System.nanoTime();//Begin timing algorithm
			mergeSort(intArray,temp, 0,intArray.length); //Pass input to method
			//End merge sort algorithm
			long endTime = System.nanoTime(); //End timing algorithm
	   		long estimatedTime = endTime - startTime; //Calculate time difference
	   		double timeInSeconds = (estimatedTime * 1.0e-9);// Convert nanoseconds to seconds
	   		System.out.print("Sorting Complete\n"); 
	   		System.out.print("\nThe algorithm took: "+ timeInSeconds+ " seconds to run\n"); //print algorithm run time
	   		printArray(intArray, filename); //Print the Array to an outputfile
	             
	    }	//End Merge Sort Block
	    
	    }   // End Main Block

		//Begin Quick Sort Method
		public static void quickSort(int[] arr, int low, int high) throws FileNotFoundException { 
		//Code adapted from: http://www.programcreek.com/2012/11/quicksort-array-in-java/, by X. Wang
			
			if (arr == null || arr.length == 0)
				return;
	 
			if (low >= high)
				return;
	 
			//pick the pivot
			int middle = low + (high - low) / 2;
			int pivot = arr[middle];
	 
			//make left < pivot and right > pivot
			int i = low, j = high;
			while (i <= j) {
				while (arr[i] < pivot) {
					i++;
				}
	 
				while (arr[j] > pivot) {
					j--;
				}
	 
				if (i <= j) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					i++;
					j--;
				}
			}
	 
			//recursively sort two sub parts
			if (low < j)
				quickSort(arr, low, j);
	 
			if (high > i)
				quickSort(arr, i, high);
			
		} // End Quick Sort Method
		
		//Begin Method to Print the sorted Arrays to an output file
		public static void printArray(int[] x, String filename) throws FileNotFoundException {	
			int i; 
			String relativePath2 = (filename);
			PrintWriter out2 = new PrintWriter(relativePath2);
			for (i = 0; i < x.length; i++) {
  	   		      out2.println((int) x[i]);
			}

   		    out2.close();
		
		} // End Print Method


		//Begin Merge Sort Method 
	    public static void mergeSort(int[] a,int[] temp, int low, int high) throws FileNotFoundException { 
	    //Code adapted from: http://www.sanfoundry.com/java-program-implement-merge-sort/ by: Manish Bhojasia
	    
	        int N = high - low;         

	        if (N <= 1) //if the number of elements in the list is 1 or 0, it does not need to be sorted

	            return; 

	        int mid = low + N/2; 

	        // recursively sort 

	        mergeSort(a, temp, low, mid); 
	        mergeSort(a, temp, mid, high); 

	        // merge two sorted subarrays


	        int i = low, j = mid;

	        for (int k = 0; k < N; k++)
	        {

	            if (i == mid)  

	                temp[k] = a[j++];

	            else if (j == high) 

	                temp[k] = a[i++];

	            else if (a[j]<a[i]) 

	                temp[k] = a[j++];

	            else 

	                temp[k] = a[i++];

	        }    

	        for (int k = 0; k < N; k++){
	            a[low + k] = temp[k];   }

	    } // End of mergeSort 
}
//End of Program
