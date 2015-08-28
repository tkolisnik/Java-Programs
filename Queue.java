//Queue.java
//This is part of a program suite containing: Assign4.java, Vertex.java, Graph.java, Queue.java

public class Queue 
{

	private Vertex head = null;
	private Vertex tail = null;

	public void setHead (Vertex newHead)
	{
		head = newHead;
	}

	public void setTail (Vertex newTail)
	{
		tail = newTail;
	}

	public Vertex getHead ()
	{
		return head;
	}

	public Vertex getTail ()
	{
		return tail;
	}

	public void enqueue (Vertex newVertex)
	{
		if (this.getHead() == null) {
			this.setHead(newVertex);
			this.setTail(newVertex);

			return;
		}

		newVertex.setChild(head);
		head.setParent(newVertex);
		this.setHead(newVertex);
		return;
	}

	public Vertex dequeue ()
	{
		Vertex returnVertex = this.getTail();

		if (returnVertex == this.getHead()) {

			head = null;
			tail = null;
			return returnVertex;
		}


		this.tail = this.tail.getParent();
                this.tail.setChild(null);
		
		return returnVertex;
	}


	public boolean isEmpty ()
	{
		if (tail == null) {
			return true;
		}
		return false;
	}

}
