@SuppressWarnings(value = { "all" })
//This LinkedList class goes along with Assign2.java  LinkedList.java 
//Please see Assign2.java for a more thorough description.

//Adapted from:
//Longsheng Zhou 
//https://github.com/lszhou

//Crunchify
//http://crunchify.com/how-to-implement-a-linkedlist-class-from-scratch-in-java/

//Cory Bloor
//http://pages.cpsc.ucalgary.ca/~cgbloor

public class LinkedList<AnyType> { 
	
	private Node head;	
	private Node current;
	private Node lastNode;
	
	// Constructs an empty list.
	public LinkedList() {
		head = null;
		
	}
	
	// Tells if the list is empty or not.
	public boolean isEmpty() {
		if (head==null) {
		return true;
		}
		else{
		return false;
		}
		
	}
	
	// Returns the value stored in the first node
	public String getFirstData() {
		if (head == null){
			return "null";
		}
		else {
		return (String) head.getData();
		}
	}
	
	// Adds a value to the first node
	public void addFirst(AnyType item) {
		head = new Node<AnyType>(item, head);
	}
	
	// Prints the list to the command line, putting a space after each element
	public void display() {
		Node tmp = head;
		while (tmp != null) {
			System.out.printf(tmp.getData()+ " ");
			tmp = tmp.getNext();
		}
		System.out.println();
	}
	
	// Adds information to the linked list using insertionSort to ensure correct alphabetical order
	public void add(String data) { 
		if (isEmpty()) { //If its empty, just add it as the first node
			head = new Node(data); 
			head.setNext(null);
			return; 
			} 
		
		if(((String) head.getData()).compareToIgnoreCase(data) >= 0) { //Put it infront if it is greater than the previous word alphabetically
			Node newNode = new Node(data);
			newNode.setNext(head); 
			head = newNode;
			return; 
			} 
		
		if(((String) head.getData()).compareToIgnoreCase(data) < 0) { //Put it behind if it is lesser than the previous word alphabetically
			Node newNode = new Node(data); 
			Node current = head;
			Node prev = head;
			Boolean isFinished = false;
			current=current.getNext();
			prev.setNext(current);
			
			while (isFinished==false && current !=null) { //Traverse the list to find the proper location to put the new data
				
			if(((String) current.getData()).compareToIgnoreCase(data) >= 0) { //Put it infront if it is greater than the previous word alphabetically
				newNode.setNext(current);
				current = newNode;
				prev.setNext(current);
				isFinished=true;  //Stop looping if we found where it goes
			}
				
			prev = current;
			current = current.getNext();
			prev.setNext(current);
			
			}
			
			if (current == null) { //If we have reached the end, put it at the end
				prev.setNext(newNode);
				isFinished = true; //Stop looping if we found where it goes
				
			}
			return;
		}
	}
}