/**
 * A self-balancing binary search tree.
 */
public class BBT {
	// Fields
	private BBN root;

	/**
	 * Initialize an empty tree.
	 */
	public BBT() {
		this.root = null;
	}

	public int size() {
		return size(root);
	}

	private int size(BBN node) {
		if (node == null)
			return 0;
		return node.size;
	}

	public int height() {
		if (root == null)
			return -1;
		return height(root);
	}

	private int height(BBN node) {
		BBN left = node.left;
		BBN right = node.right;

		if (left == null && right == null)
			return 0;
		else if (left == null)
			return 1 + right.height;
		else if (right == null)
			return 1 + left.height;
		else
			return 1 + Math.max(left.height, right.height);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	private BBN insert(BBN node, VLS segment) {
		if (node == null) {
			return new BBN(segment);
		}

		if (node.seg.compareTo(segment) < 0) {
			node.left = insert(node.left, segment);
		} else if (node.seg.compareTo(segment) < 0) {
			node.right = insert(node.right, segment);
		} else {
			node.seg = segment;
		}

		node.height = height(node);
		node.size = size(node.left) + size(node.right) + 1;

		return node;
	}

	public void insert(VLS segment) {
		this.root = insert(root, segment);
	}

	public void delete(VLS segment) {
		this.root = delete(root, segment);
	}

	private BBN delete(BBN node, VLS segment) {
		if (node == null)
			return null;

		if (node.seg.compareTo(segment) < 0) {
			node.left = delete(node.left, segment);
		} else if (node.seg.compareTo(segment) > 0) {
			node.right = delete(node.right, segment);
		} else {
			// If left/right child is null, return the opposite
			// If both are null, then obviously the node must be substituted
			// with null, as well
			if (node.left == null)
				return node.right;
			if (node.right == null)
				return node.left;

			// Save node as it was
			// Substitute it with the lowest element in the right sub-tree
			// Put the old right sub-tree in the substitution, but additionally
			// remove the minimal element from there
			// Copy left sub-tree
			BBN temp = node;
			node = min(node.right);
			node.right = deleteMin(temp.right);
			node.left = temp.left;
		}

		node.height = height(node);
		node.size = size(node.left) + size(node.right) + 1;

		return node;
	}

	// Deletes the minimal element of the sub-tree
	private BBN deleteMin(BBN node) {
		if (node.left == null)
			return node.right;
		node.left = deleteMin(node.left);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}

	// Finds the minimal element of the sub-tree
	private BBN min(BBN node) {
		if (node.left == null)
			return node;
		return min(node.left);
	}

	public VLS valueAtPosition(int position) {
		if (position < 0 || position > size() - 1) {
			throw new IllegalArgumentException(
					"Position lies in the wrong range.");
		}
		return valueAtPosition(root, position).seg;
	}

	private BBN valueAtPosition(BBN node, int position) {
		if (node == null)
			return null;

		int leftSize = size(node.left);
		if (leftSize > position) {
			return valueAtPosition(node.left, position);
		} else if (leftSize < position) {
			// Ignore left sub-tree of the root
			return valueAtPosition(node.right, position - leftSize - 1);
		} else {
			return node;
		}
	}

	public int position(VLS segment) {
		if (root == null)
			return 0;
		return position(root, segment);
	}

	public int position(BBN node, VLS segment) {
		if (node.seg.compareTo(segment) < 0) {
			if (node.left != null) {
				return position(node.left, segment);
			} else {
				return 0;
			}
		} else if (node.seg.compareTo(segment) > 0) {
			if (node.right == null) {
				return position(node, node.seg) + 1;
			} else if (node.left != null) {
				return node.left.size + 1 + position(node.right, segment);
			} else {
				return position(node.right, segment) + 1;
			}
		} else {
			if (node.left != null) {
				return node.left.size;
			} else {
				return 0;
			}
		}
	}

	public void simpleBalance() {
		BBT balanced = new BBT();
		simpleBalance(balanced, 0, root.size - 1);
		this.root = balanced.root;
	}

	private void simpleBalance(BBT tree, int l, int r) {
		int median = Math.round((r + l) / 2);

		tree.insert(valueAtPosition(median));

		if (l <= (median - 1))
			simpleBalance(tree, l, median - 1);
		if ((median + 1) <= r)
			simpleBalance(tree, median + 1, r);
	}

	/**
	 * Report all VLS's currently in the tree that lie between min and max.
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public VLS[] printRange(int min, int max) {
		// TODO
		return null;
	}
}
