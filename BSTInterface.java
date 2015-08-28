public interface BSTInterface<T> {
	
	void insert(T toInsert);
	
	boolean search(T toSearch);
	
	void delete(T toDelete);
	
	/*---------------------------*/
	void DFS_InOrder_Display();
	void BFS_LevelOrder_Display();

	
	
}
