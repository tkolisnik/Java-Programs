import java.io.FileNotFoundException;

public class Node<T> {
		private T data;
		private Node<T> left, right;

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
}