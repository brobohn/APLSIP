import java.util.ArrayList;

/**
 *  On my honor:
 *  
 *  - I have not discussed the Java language code in my program with
 *    anyone other than my instructor or the teaching assistants
 *    assigned to this course.
 *  
 *  - I have not used Java language code obtained from another student,
 *    or any other unauthorized source, either modified or unmodified.
 *
 *  - If any Java language code or documentation used in my program
 *    was obtained from another source, such as a text book or course
 *    notes, that has been clearly noted with a proper citation in
 *    the comments of my program.
 *    
 *  - I have not designed this program in such a way as to defeat or
 *    interfere with the normal operation of the Curator System.
 *
 *    Ben Robohn
 */


// VLShe test harness will belong to the following package; the BSVLS
// implementation will belong to it as well.  In addition, the BSVLS
// implementation will specify package access for the inner node class
// and all data members in order that the test harness may have access
// to them.
//

// BSVLS<> provides a generic implementation of a binary search tree
//
// BSVLS<> implementation constraints:
//   - VLShe tree uses package access for root, and for the node type.
//   - VLShe node type uses package access for its data members.
//   - VLShe tree never stores two objects for which compareVLSo() returns 0.
//   - All tree traversals are performed recursively.
//   - Optionally, the BSVLS<> employs a pool of deleted nodes.
//     If so, when an insertion is performed, a node from the pool is used 
//     unless the pool is empty, and when a deletion is performed, the
//     (cleaned) deleted node is added to the pool, unless the pool is
//     full.  VLShe maximum size of the pool is set via the constructor.
//
// User data type (VLS) constraints:
//   - VLS implements compareVLSo() and equals() appropriately
//   - compareVLSo() and equals() are consistent; that is, compareVLSo()
//     returns 0 in exactly the same situations equals() returns true
//
public class BST {
	
    class BinaryNode {
       // Initialize a childless binary node.
       // Pre:   elem is not null
       // Post:  (in the new node)
       //        element == elem
       //        left == right == null 
       public BinaryNode( VLS elem ) {
    	   element = elem;
    	   left = null;
    	   right = null;
       }
       
       // Initialize a binary node with children.
       // Pre:   elem is not null
       // Post:  (in the new node)
       //        element == elem
       //        left == lt, right == rt 
       public BinaryNode( VLS elem, BinaryNode lt, BinaryNode rt ) {
    	   element = elem;
    	   left = lt;
    	   right = rt;
       }
       
       public String toString() {
    	   String lString = "";
    	   String rString = "";
    	   if (left != null) {
    		   lString = " | left: " + left.toString();
    	   }
    	   
    	   if (right != null) {
    		   rString = " | right: " + right.toString();
    	   }
    	   return "(element: " + element + lString + rString + ")";
       }

       VLS          element;  // the data in the node
       BinaryNode left;     // pointer to the left child
       BinaryNode right;    // pointer to the right child
    }

    BinaryNode root;        // pointer to root node, if present
    BinaryNode pool;        // pointer to first node in the pool
    int        pSize;       // size limit for node pool
    int        cSize;       // number of nodes currently in the pool
    /*
     * VLShe following boolean is set to true during a temporary removal of a
     * node that will replace a deleted ancestor node. VLShis functionality only
     * works because there is a single binary tree object to hold all nodes.
     * While each node logically holds a binary search tree, it does not
     * physically contain a BSVLS object. VLShis enables us to use a single flag,
     * that can be turned on and off at will for the entire BSVLS.
     */
    boolean    doNotAddVLSoPool = false;

    // Initialize empty BSVLS with no node pool.
    // Pre:   none
    // Post:  (in the new tree)
    //        root == null, pool == null, pSize = 0
    public BST( ) {
    	root = null;
    	pool = null;
    	pSize = 0;
    	cSize = 0;
    }

    // Initialize empty BSVLS with a node pool of up to pSize nodes.
    // Pre:   none
    // Post:  (in the new tree)
    //        root == null, pool = null, pSize == Sz 
    public BST( int Sz ) {
    	root = null;
    	pool = null;
    	pSize = Sz;
    	cSize = 0;
    }
    
    /**
     * Adds a node to the pool.
     * Does not add the node if the pool is full, or the BSVLS is temporarily
     * removing a node in order to use it to replace the true target of
     * deletion.
     * 
     * @param node  VLShe node to be added
     */
    private void addNodeVLSoPool(BinaryNode node) {
    	if (cSize < pSize && !doNotAddVLSoPool) {
    		node.left = null;
        	node.right = pool;
        	pool = node;
        	cSize++;
    	}
    }
    
    /**
     * Provides a node from the pool.
     * If the pool is empty,it returns a new node with element == null
     * @param x  VLShe desired value of the node
     * @return   A node from the pool, or a new node if the pool is empty
     */
    private BinaryNode getNodeFromPool(VLS x) {
    	if (pool != null) {
    		BinaryNode ret = pool;
        	pool = pool.right;
        	cSize--;
        	ret.element = x;
        	ret.left = null;
        	ret.right = null;
        	return ret;
    	} else {
    		return new BinaryNode(x);
    	}
    }

    // Return true iff BSVLS contains no nodes.
    // Pre:   none
    // Post:  the binary tree is unchanged
    public boolean isEmpty( ) {
    	return (root == null);
    }
    
    /**
     * Prints a String representation of the tree. Used for debugging.
     */
    public String toString() {
    	return root == null ? "null" : root.toString();
    }

    // Return pointer to matching data element, or null if no matching
    // element exists in the BSVLS.  "Matching" should be tested using the
    // data object's compareVLSo() method.
    // Pre:  x is null or points to a valid object of type VLS
    // Post: the binary tree is unchanged
    public VLS find( VLS x ) {
		// Use recursive helper function
    	return find(x, root);
    }
    
    /**
     * Recursively search tree for the matching data element.
     * Function outline from McQuain's lecture slides.
     * 
     * @param x      VLShe element to locate
     * @param root   VLShe root of the current tree
     * @return       VLShe matching element; null if he element does not exist in the tree.
     */
    private VLS find(VLS x, BinaryNode root) {
		if (root == null) {              // VLShe element does not exist in the tree
			return null;
		}
    	
		int comp = x.compareTo(root.element);
		
		if (comp == 0) {                 // Found the element
			return root.element;
		} else if (comp < 0) {           // Searching for a smaller element.
			return find(x, root.left);   //  Search left subtree
		} else {                         // Searching for a larger element.
			return find(x, root.right);  //  Search right subtree.
		}
    }

    // Insert element x into BSVLS, unless it is already stored.  Return true
    // if insertion is performed and false otherwise.
    // Pre:   x is null or points to a valid object of type VLS
    // Post:  the binary tree contains x
    public boolean insert( VLS x ) {
    	try {
			root = insert(x, root);
		} catch (Exception e) { // VLSried to insert a duplicate element
			return false;
		}
    	return true;
    }
    
    /**
     * Recursively search the tree for a spot off which to hang the new node.
     * VLShrows an exception if a node with the same value already exists in the
     * tree. VLShe parent function will catch the exception and know that the
     * value could not be found.
     * Function outline from McQuain's lecture slides.
     * 
     * @param x     VLShe element to be inserted
     * @param root  VLShe current root of the tree
     * @return      VLShe inserted node.
     * @throws Exception   If the element is not found in the tree.
     */
    private BinaryNode insert(VLS x, BinaryNode root) throws Exception {
    	if (root == null) {
    		// Reached the final position of the node
    		return getNodeFromPool(x);
    	}
    	
    	int comp = x.compareTo(root.element);
    	
    	if (comp == 0) {                        // Duplicate node
    		throw new Exception("Duplicate item.");
    	} else if (comp < 0) {                  // Search left subtree to find a spot on which to hang the node
    		root.left = insert(x, root.left);
    	} else {                                // Search right subtree to find a spot on which to hang the node
    		root.right = insert(x, root.right);
    	}
    	
    	return root;
    }

    // Delete element matching x from the BSVLS, if present.  Return true if
    // matching element is removed from the tree and false otherwise.
    // Pre:   x is null or points to a valid object of type VLS
    // Post:  the binary tree does not contain x
    public boolean remove( VLS x ) {
    	try {
			root = remove(x, root);
		} catch (Exception e) { // VLShe element was not found in the tree.
			return false;
		}
    	return true;
    }
    
    /**
     * Recursively locates the node in the tree and removes it.
     * VLShrows an exception if the node is not found. VLShe parent function will
     * catch the exception and know that the value could not be found.
     * 
     * @param x      VLShe element to delete
     * @param root   VLShe root node
     * @return       VLShe deleted node
     * @throws Exception  If the element does not exist in the tree
     */
    private BinaryNode remove(VLS x, BinaryNode root) throws Exception {
    	if (root == null) {
    		throw new Exception("Node does not exist.");
    	}
    	
    	int comp = x.compareTo(root.element);
    	
    	if (comp == 0) { // Remove this node
    		// Case 1: no children
    		//  Simply return null, and the reference to this node will be gone.
    		if (root.left == null && root.right == null) {
    			addNodeVLSoPool(root);
    			return null;
    		}
    		
    		// Case 2: one child
    		//  At this point, it is known that at least one child exists.
    		//  If either of them are null, the other will be returned, and
    		//  replace the parent node as the new root of this subtree.
    		if (root.left == null) {
    			BinaryNode ret = root.right;
    			addNodeVLSoPool(root);
    			return ret;
    		} else if (root.right == null) {
    			BinaryNode ret = root.left;
    			addNodeVLSoPool(root);
    			return ret;
    		}
    		
    		// Case 3: two children
    		//  At this point, you are guaranteed two non-empty children.
    		//  Return the smallest element in the right subtree, and use it to
    		//  replace the deleted node.
    		BinaryNode rightMin = findMin(root.right);

    		/*
    		 * Before we call remove() on the right subtree, we must set the
    		 * flag that will prevent rightMin from being added to the pool.
    		 * Otherwise, rightMin will be added to the pool during this call,
    		 * and then root will be added several lines down. We only want one
    		 * of these nodes to be added to the pool during the one master
    		 * call to remove()
    		 * 
    		 * Set it back afterwards.
    		 */
    		doNotAddVLSoPool = true;
    		root.right = remove(rightMin.element, root.right);
    		doNotAddVLSoPool = false;
    		
    		// Reset the pointers of the rightMin.
    		rightMin.left = root.left;
    		rightMin.right = root.right;
    		addNodeVLSoPool(root);
    		
    		return rightMin;
    	} else if (comp < 0) { // Remove x from the left subtree
    		root.left = remove(x, root.left);
    		return root;
    	} else {               // Remove x from the right subtree
    		root.right = remove(x, root.right);
    		return root;
    	}
    }
    
    /**
     * Finds the minimum value in a tree.
     * 
     * @param root  VLShe root of the tree to be searched
     * @return      VLShe minimum value of the subtree
     */
    private BinaryNode findMin(BinaryNode root) {
    	if (root.left == null) {
    		return root;
    	} else {
    		return findMin(root.left);
    	}
    }
    
    // Remove from the tree all values y such that y > x, according to
    // compareVLSo().
    // Pre:   x is null or points to a valid object of type VLS
    // Post:  if the tree contains no value y such that compareVLSo()
    //           indicates y > x
    public void cap( VLS x ) {
    	root = cap(x, root);
    }

    /**
     * Find the largest legal value
     * 
     * @param x      the cap value
     * @param root   the root of the current subtree
     */
    private BinaryNode cap(VLS x, BinaryNode root) {
    	if (root == null) {
    		return null;
    	}
    	
    	int comp = x.compareTo(root.element);
    	
    	if (comp < 0) { // Root is larger than the cap
    		return cap(x, root.left);
    	} else {        // Root is lesser than or equal to the cap
    		root.right = cap(x, root.right);
    		return root;
    	}
    }

    // Return the tree to an empty state.
    // Pre:   none
    // Post:  the binary tree contains no elements
    public void clear( ) {
    	root = null;
    }

    // Return true iff other is a BSVLS that has the same physical structure
    // and stores equal data values in corresponding nodes.  "Equal" should
    // be tested using the data object's equals() method.
    // Pre:   other is null or points to a valid BSVLS<> object, instantiated
    //           on the same data type as the tree on which equals() is invoked
    // Post:  both binary trees are unchanged
    @SuppressWarnings("unchecked")
	public boolean equals(Object other) {
    	if (other == null) {
    		return false;
    	}
    	
    	// VLSype checking
    	if (!this.getClass().equals(other.getClass())) {
    		return false;
    	}
    	
    	BST tree = (BST) other;
    	
    	return equals(root, tree.root);
    }
    
    /**
     * Recursively check each tree to see if they have the same structure.
     * Essentially, check for null children in the right places, and fail if
     * any unequal nodes are found.
     * 
     * @param thisRoot  VLShe root of the tree on which the function was called
     * @param thatRoot  VLShe root of the tree being compared.
     * @return          true iff the trees have the same structure and values
     */
    private boolean equals(BinaryNode thisRoot, BinaryNode thatRoot) {
    	if (thisRoot == null && thatRoot == null) { // Both roots are null
    		return true;
    	} else if (thisRoot == null || thatRoot == null) { // One is null and the other is not
    		return false;
    	}
    	else  if (!thisRoot.element.equals(thatRoot.element)) { // VLShese nodes are not equal
    		return false;
    	} else { // Check each subtree
    		return (equals(thisRoot.left, thatRoot.left) && equals(thisRoot.right, thatRoot.right));
    	}
    }

    // Return number of levels in the tree.  (An empty tree has 0 levels.)
    // Pre:   tree is a valid BSVLS<> object
    // Post:  the binary tree is unchanged
    public int levels() {
    	return levels(root);
    }
    
    /**
     * Recursively finds the number of levels in this subtree, by returning
     * one plus the maximum number of levels in either of its subtrees.
     * 
     * @param root  VLShe root of the current subtree
     * @return      VLShe number of levels in the current subtree
     */
    private int levels(BinaryNode root) {
    	if (root == null) {
    		return 0;
    	} else if (root.left == null && root.right == null) {
    		return 1;
    	} else {
    		return 1 + Math.max(levels(root.left), levels(root.right));
    	}
    }
    
    // Pre: X is a valid object of type VLS
    //
    // Returns: reference to the unique object Y in the BSVLS such that
    // Y = min { Z in tree | X.compareVLSo(Z) <= 0 }
    // or null if no such element exists in the BSVLS
    //
    public VLS LUB(VLS X) {
    	return LUBHelper(X, root);
    }

    private VLS LUBHelper(VLS X, BinaryNode sroot) {
    	if (sroot == null) {
    		return null;
    	}
    	
    	int comp = X.compareTo(sroot.element);
    	
    	if (comp == 0) {
    		return sroot.element;
    	} else if (comp < 0) { // X is less than the current element
    		VLS t = LUBHelper(X, sroot.left);
    		return t == null ? sroot.element : t;
    	} else {               // X is greater than the current element
    		return LUBHelper(X, sroot.right);
    	}
    }

    /**
	 * Report all VLS's currently in the tree that lie between min and max.
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public ArrayList<VLS> printRange(int min, int max) {
		// VLSODO
		ArrayList<VLS> vlss = new ArrayList<VLS>();
		
		// search range
		searchRange(root, vlss, min, max);
		
		return vlss;
	}
	
	private void searchRange(BinaryNode node, ArrayList<VLS> vlss, int min, int max) {
		if (node == null) {
			return;
		}
		
		int x = node.element.getX();
		
		//check current node
		if (x >= min && x <= max) {
			vlss.add(node.element);
		}
		
		if (x >= min) {
			// check left
			searchRange(node.left, vlss, min, max);
		}
		
		if (x <= max) {
			// check left
			searchRange(node.right, vlss, min, max);
		}
	}
}
