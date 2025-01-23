/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node current = getFirst(); // Start at the first node
		for (int i = 0; i < index; i++) {
			current = current.next; // Traverse to the next node
		}
		return current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node insert = new Node(block);
		if (index == 0) {
			insert.next = first;
			first = insert;
			if (size == 0) {
				last = insert;
			}
		} else if (index == size) {
			last.next = insert;
			last = insert;
		} else {
			Node prev = getNode(index - 1);
			insert.next = prev.next;
			prev.next = insert;
		}
		size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		add (size, block);
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		add (0, block);
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		Node current = getNode(index);
		return current.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		Node current = first;
		for ( int i = 0; i< size; i++){
			if ((block == null && current.block == null) || 
            (block != null && block.equals(current.block))){
				return i;
			}
			current = current.next;
		 }
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	//public void remove(Node node) {
		//if (node == null) {
		//	throw new NullPointerException("NullPointerException!");
		//}
		//if (node == first) {
			//first = first.next;
			//if (first == null) { // List becomes empty
			//	last = null;
			//}
			//size--;
		//}
	//	Node current = first;
   // while (current != null && current.next != node) {
      //  current = current.next;
   // }
    
  //  if (current == null || current.next == null) {
    //    throw new IllegalArgumentException("Node does not belong to this list.");
 //   }
    
    // Remove the node
   // current.next = node.next;
   // if (node == last) { // If the node is the last node
    //    last = current;
  //  }
   //size--;
	//}
	public boolean remove(Node node) {
		// Check if the node exists in the list
		Node current = first;
		boolean nodeFound = false;
	
		while (current != null) {
			if (current == node) {
				nodeFound = true;
				break;
			}
			current = current.next;
		}
	
		if (!nodeFound) {
			throw new IllegalArgumentException("Node does not belong to this list.");
		}
	
		// Handle removing the head node
		if (first == node) {
			first = first.next; // Update head
			if (first == null) {
				last = null; // If list is now empty, update tail
			}
			return true;
		}
	
		// Handle removing a middle or last node
		Node prev = null;
		current = first;
	
		while (current != null) {
			if (current == node) {
				if (prev != null) {
					prev.next = current.next; // Skip the node being removed
				}
				if (current == last) {
					last = prev; // Update tail if the last node is removed
				}
				return true;
			}
			prev = current;
			current = current.next;
		}
	
		return false; // If somehow the node isn't found (shouldn't reach here)
	}
	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		
		if (index == 0){
			first = first.next;
			if (first == null) { // List becomes empty
				last = null;
			}
			size--;
			return;
		 }
     Node prev = getNode(index-1);
	 Node r = prev.next;
    // Remove the node
    prev.next = r.next;
		 if (r == last){ // If the node is the last node
			last = prev;
		 }
		 size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int index = indexOf(block);
		if (index == -1) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		remove(index);
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		Node current = first;
		String sb = "[";
		for ( int i = 0; i < size-1; i++){
		   sb += current.block + ", ";
		   current = current.next;
		}
		sb += current.block + "]";
		return sb;
	}
}