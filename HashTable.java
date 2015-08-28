//HashTable.java
//This is part of a program suite containing: Assign5.java, HashTable.java
//Created by: Tyler Kolisnik

public class HashTable
{

	//Create the hash table using an array.

	private String[] array;

	public HashTable (int arraySize)
	{

		array = new String[arraySize];

	}


	//Pairs the hash key with the value from the input file
	public void input(String key, String value)
	{

		int index;

		//Uses the hash function on the key 
		index = hashFunction(key);

		//Collisions are managed via linear probing
		int collisions = 0;
		while ( array[index] != null ) {

			collisions += 1;
			index = (index + 1) % array.length;
			
			//This should only occur if the hash table size is made smaller than the input file. 
			if (collisions == array.length) {
				System.out.println("The hash table is full, exiting program.");
				System.exit(0);
			}

		}

		array[index] = value;
		return;

	}


	//Locate the key value pair based on the hash
	//Return an int of the number of reads it took to locate it
	public int search(String key)
	{

		int numberOfReads = 0;
		int index;

		index = hashFunction(key);
		numberOfReads+=1;

		while ( !array[index].equals(key) ) {
			index = (index + 1) % array.length;
			numberOfReads+=1;
		}


		//When the key value pair is found, return the number of reads it took to locate it
		return numberOfReads;


	}
	
	//The main hash function that the above put and get functions depend upon
	private int hashFunction (String key)
	{

		int index = 0;
		long hashMultiplier = 31; //An arbitrary number to help randomize the hash values
		
		//Hash the key
		for (int x = 0; x < key.length(); x++) {

			char characterOfKey = key.charAt(x);
			int asciiValue = (int) characterOfKey;
			
			index = (int) (index * hashMultiplier);
			index ^= asciiValue;
			

		}
		//If the index is negative, make it positive:
		if (index < 1){
			index *= -1;
		}
			index = index % array.length;
		return index;
	}

}
