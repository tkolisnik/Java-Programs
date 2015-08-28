
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayDeque;



/*This class is a generic type implementation of Binary Search Tree which
 * implements self-defined BST interface (BSTInterface.java). 
 * 
 * It is implemented by a private inner node class. In order to support
 * the binary search property, we require the data stored in each node is
 * comparable. Specially, BST class is of generic type T which is an ADT (class)
 * extends Comparable interface. Since all java basic ADTs (integer, String, etc) 
 * have implemented the compareTo() method, therefore for these data, this BST
 * could be used directly. For general object data (for example, self-defined 
 * student object), it should implements compareTo() method in advance, namely
 * it should implement comparable interface in advance. 
 *  
 * For more information of comparable interface, please refer to
 * "http://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html"
 * */

public class BST<T extends Comparable<T>> implements BSTInterface<T> {

	@SuppressWarnings("hiding")
////Imbedded Node Class Begins
	private class Node<T> {
		private T data;
		private Node<T> left, right;
		
		//private T data2;
		private Node<T> next;
		public Node(T data, Node<T> l, Node<T> r) {
			left = l;
			right = r;
			this.data = data;
		}

		public Node(T data) {
			this(data, null, null);
			
		}
	
		/**
		 * recursive depth-first InOrder traversal
		 * @throws FileNotFoundException 
		 */
		public void DFS_InOrder_Display() throws FileNotFoundException {
			if (this.left != null) {
				this.left.DFS_InOrder_Display();
			}
			
			String a=this.data.toString();
			String b = a.substring(a.indexOf("*")+1);
			System.out.print(b + "\n");

			if (this.right != null) {
				this.right.DFS_InOrder_Display();
			} 
		}

		/**
		 * recursive depth-first PostOrder traversal
		 */
		
	}//// Imbedded Node Class Ends
	

	private Node<T> root;
	
	/**
	 * Construct an empty BST;
	 */
	public BST() {
		root = null;
	}

	@Override
	/**
	 * Search the given data in the BST;
	 * @param toSearch the given data
	 */
	public boolean search(T toSearch) {
		return search(root, toSearch);
	}

	private boolean search(Node<T> p, T toSearch) {
		if (p == null)
			return false;
		else if (toSearch.compareTo(p.data) == 0)
			return true;
		else if (toSearch.compareTo(p.data) < 0)
			return search(p.left, toSearch);
		else
			return search(p.right, toSearch);
	}

	@Override
	public void insert(T toInsert) {
		root = insert(root, toInsert);
	}

	private Node<T> insert(Node<T> p, T toInsert) {
		if (p == null)
			return new Node<T>(toInsert);

		if (toInsert.compareTo(p.data) == 0)
			return p;

		if (toInsert.compareTo(p.data) < 0)
			p.left = insert(p.left, toInsert);
		else
			p.right = insert(p.right, toInsert);

		return p;
	}

	
	
	@Override
	/**
	 * Delete the data toDelete, considering the fllowing cases:
	 *  - is not in BST
	 *  - is a leaf (no children)
	 *  - has one child
	 *  - has two children
	 */
	public void delete(T toDelete) {
		root = delete(root, toDelete);
	}

	private Node<T> delete(Node<T> p, T toDelete) {
		if (p == null)
			throw new RuntimeException("cannot delete.");
		else if (toDelete.compareTo(p.data) < 0)
			p.left = delete(p.left, toDelete);
		else if (toDelete.compareTo(p.data) > 0)
			p.right = delete(p.right, toDelete);
		else {
			if (p.left == null)
				return p.right;
			else if (p.right == null)
				return p.left;
			else {
				p.data = retrieveData(p.left);
				p.left = delete(p.left, p.data);
			}
		}
		return p;
	}
	
	/**
	/**
	 * Get the smallest node data is the left subtree.
	 * @param p current node I am in.
	 * @return biggest data in the left subtree
	 */
	private T retrieveData(Node<T> p) {
		while (p.right != null)
			p = p.right;

		return p.data;
	}


	public void DFS_InOrder_Display() {
		try {
			this.root.DFS_InOrder_Display();
		} catch (FileNotFoundException e) {
			System.out.print("Unable to create output file.");
			e.printStackTrace();
		}
	}

	
	/**
	 * non-recursive Breadth-first traversal using stack;
	 */
	public void BFS_LevelOrder_Display() {
		
		if (root == null) {
			System.out.println("empty tree");
			return;
		}
		ArrayDeque<Node<T>> queue = new ArrayDeque<>();
		queue.add(root);
		//LinkedQueue<Node<Node>> queue = new LinkedQueue();
		//queue.enqueue(root);
		
		while (!queue.isEmpty()) {
			Node<T> node = queue.remove();
			//Node node = queue.remove();
			String a=node.data.toString();
			String b = a.substring(a.indexOf("*")+1);
			System.out.print(b + "\n");
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		System.out.print(" ");
		
		
	}

	@SuppressWarnings("hiding")
	public class LinkedQueue<T> {
		private LinkedList<T> list = new LinkedList<T>();
		public boolean isEmpty() {
			return list.isEmpty();
		}
		public Node<T> dequeue() {
			return  list.removeFirst();
		}
		public void enqueue(T el) {
			list.addLast(el);
		}
		}
	
	@SuppressWarnings("hiding")
	public class LinkedList<T> {
		private Node<T> head;
		private Node<T> blank;
		public LinkedList() {
			head = null;
		}
		public boolean isEmpty() {
			if (head==null) {
			return true;
			}
			else{
			return false;
			}
		}
			
		public void addLast(T item) {
			head = new Node<T>(item, head, blank);
		}
		public Node<T> removeFirst() {
			Node<T> tmp = null;
			head = tmp;
			head= head.next;
			return tmp;
			
			
		}
		
	}
	// begin
	
	
}
