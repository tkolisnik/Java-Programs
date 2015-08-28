@SuppressWarnings(value = { "all" })
//This node class goes along with Assign2.java  LinkedList.java 
//Please see Assign2.java for a more thorough description.

//Adapted from:
//Longsheng Zhou 
//https://github.com/lszhou

//Crunchify
//http://crunchify.com/how-to-implement-a-linkedlist-class-from-scratch-in-java/

//Cory Bloor
//http://pages.cpsc.ucalgary.ca/~cgbloor

public class Node<AnyType> { 
	private AnyType data;
	private Node next;
	
	// Constructor with one argument. 
	public Node(AnyType data) {
		this.data = data;
		this.next = null;
		}
	
	// Constructor with two arguments. 
	public Node(AnyType data, Node next) {
			this.data = data;
			this.next = next;
	}
	
	public Node getNext() {
        return next;
    }
	
	public void setNext(Node nextValue) {
        next = nextValue;
    }
	
	 public AnyType getData() {
         return data;
     }

     public void setData(AnyType dataValue) {
         data = dataValue;
     }
	
     public void setNext(AnyType dataValue){
    	 next.data = dataValue;
     }
}