//Graph.java
//This is part of a program suite containing: Assign4.java, Vertex.java, Graph.java, Queue.java

public class Graph
{
	private int numOfVXs;
	private Vertex vertexList[];
	

	public Graph (int adjacency_matrix[][], int numVXs)
	{

		numOfVXs = numVXs;
		vertexList = new Vertex[numVXs];

		for (int x = 0; x < numOfVXs; x++) {
			Vertex thisVertex = new Vertex(adjacency_matrix[x], numOfVXs, x);
			vertexList[x] = thisVertex;
		}
	}	


	//Used to 'reset' the graph
	public void setVertexesUnvisited ()
	{
		for (int x = 0; x < numOfVXs; x++) {
			vertexList[x].markUnvisited();
		}
	}

	public Vertex getVertex(int vertexNumber)
	{
		return vertexList[vertexNumber];
	}

	public int getNumVertexes ()
	{
		return numOfVXs;
	}


	public boolean allVXvisited()
	{
		for (int x = 0; x < numOfVXs; x++) {
			if (vertexList[x].isVisited()) {
				return false;
			}
		}
		return true;
	}

	public String DFS(Vertex currVX, int finalID, String currPath)
	{

		System.out.println("Current path is at: " + currPath);

		//Making a list of this vertex's neighbours
		Vertex neighbourList[] = new Vertex[currVX.getNumberOfNeighbours()];
		int currPosition = 0;

		for (int x = 0; x < numOfVXs; x++) {
			if (currVX.hasEdge(x)) {
				neighbourList[currPosition] = vertexList[x];
				currPosition++;
			}
		}

		//Marking this vertex as visited
		currVX.markVisited();

		for (int x = 0; x < currVX.getNumberOfNeighbours(); x++) {

			//If this is the vertex that we're looking for
			if (neighbourList[x].getVXID() == finalID) {
				System.out.println("final id found. current path is: " + currPath);
				return currPath;
			}

			//Update path with new neighbour, recursively call DFS
			if (!neighbourList[x].isVisited()) {
				currPath = currPath + ", " + neighbourList[x].getVXID();
				DFS(neighbourList[x], finalID, currPath);
			}
			
			else// if (this.allVXvisited()==true)
			{
				System.out.println("All vertexes visited. current path = -1");
				currPath = currVX.getVXID()+", -1";
				return currPath;
			}
			
		}
		
		return currPath;
	}

	

	public void BFS(Vertex startVX, int finalID, int[] path)
	{

		Vertex currVX = startVX;

		Queue myQueue = new Queue();
		myQueue.enqueue(startVX);

		int pathPosition = 0;


		currVX.markVisited();
		path[pathPosition] = currVX.getVXID();
		pathPosition++;

			//Create list of neighbouring vertices
                Vertex neighbourList[] = new Vertex[currVX.getNumberOfNeighbours()];
                int currPosition = 0;

                for (int x = 0; x < numOfVXs; x++) {
                        if (currVX.hasEdge(x)) {
                                neighbourList[currPosition] = vertexList[x];
                                currPosition++;

				if (vertexList[x].getVXID() == finalID) {
					path[pathPosition] = vertexList[x].getVXID();
					pathPosition++;
					return;
				}
                        }
                }

		//Filling Queue with neighbours
		for (int x = 0; x < currVX.getNumberOfNeighbours(); x++) {
			neighbourList[x].markVisited();
			myQueue.enqueue(neighbourList[x]);
		}



		while (!myQueue.isEmpty())
		{
			currVX = myQueue.dequeue();
	                currVX.markVisited();
	                path[pathPosition] = currVX.getVXID();
	                pathPosition++;
	
	                //Create list of neighbouring vertices
	                neighbourList = new Vertex[currVX.getNumberOfNeighbours()];
	        
	                for (int x = 0; x < numOfVXs; x++) {
	                        if (currVX.hasEdge(x)) {
	                                neighbourList[currPosition] = vertexList[x];
	                                currPosition++;
	                        }

					//If Found
	                                if (vertexList[x].getVXID() == finalID) {
	                                        path[pathPosition] = vertexList[x].getVXID();
	                                        pathPosition++;
	                                        return;
	                                }
	                        }
	                }

			//If not found, reduce path location by 1 and retry 
			path[pathPosition] = -1;
			pathPosition--;
	
	                //Adding neighbours to queue
	                for (int x = 0; x < currVX.getNumberOfNeighbours(); x++) {
	                        neighbourList[x].markVisited();
	                        myQueue.enqueue(neighbourList[x]);
	                }
	
		}
	
	
	
	public void djikstraSearch(Vertex startVX, int finalID, int[] path){
		
	}
	
}
