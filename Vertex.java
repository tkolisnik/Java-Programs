//Vertex.java
//This is part of a program suite containing: Assign4.java, Vertex.java, Graph.java, Queue.java

public class Vertex
{

	//Used for the Queue
	private boolean isVisited = false;
	private Vertex child = null;
	private Vertex parent = null;

	private int edgeList[];
	private int numberOfNeighbours = 0;
	private int VXID = 0;


	public Vertex(int edges[], int totalVertexes, int currentVXNumber)
	{
		VXID = currentVXNumber;

		edgeList = new int[totalVertexes];

		for (int x = 0; x < totalVertexes; x++) {
			edgeList[x] = edges[x];
			if (edgeList[x] == 1) {
				numberOfNeighbours++;
			}
		}
	}

	public int getVXID()
	{
		return VXID;
	}

	public int getNumberOfNeighbours()
	{
		return numberOfNeighbours;
	}

	public boolean hasEdge(int toVertex)
	{
		if (edgeList[toVertex] == 1) {
			return true;
		}
		return false;
	}

	public void markVisited()
	{
		isVisited = true;
	}

	public void markUnvisited()
	{
		isVisited = false;
	}

	public boolean isVisited()
	{
		return isVisited;
	}


	public void setChild(Vertex newChild)
	{
		child = newChild;
	}

	public Vertex getChild()
	{
		return child;
	}

	public void setParent(Vertex newParent)
	{
		parent = newParent;
	}

	public Vertex getParent()
	{
		return parent;
	}
}
