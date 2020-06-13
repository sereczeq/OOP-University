package different;

public class BST<T extends Comparable<T>>
{
	
	public static void main(String[] args)
	{
		
		BST bst = new BST();
		bst.add(5);
		bst.add(3);
		bst.add(6);
		System.out.println(bst.remove(5));
		System.out.println(bst.toStringInOrder());
		
	}
	
	private class Node
	{
		
		T value;
		Node left, right, parent;
		
		public Node(T v)
		{
			
			value = v;
			
		}
		
		
		public Node(T value, Node left, Node right, Node parent)
		{
			
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
			
		}
		
	}
	
	private Node root = null;
	
	public BST()
	{
		
	}
	
	
	public T getElemNode(Node current, T toFind)
	{
		
		if (current == null) return null;
		if (current.value.compareTo(toFind) == 0) return current.value;
		if (current.value.compareTo(toFind) > 0) return getElemNode(current.left, toFind);
		else
		{
			return getElemNode(current.right, toFind);
		}
		
	}
	
	
	public T getElement(T toFind)
	{
		
		// TODO
		return getElemNode(root, toFind);
		
	}
	
	
	private T findSuccessor(Node root, Node succ, T key)
	{
		
		// base case
		if (root == null)
		{
			return null;
		}
		
		// if node with key's value is found, the successor is minimum
		// value node in its right subtree (if any)
		if (root.value.compareTo(key) == 0)
		{
			if (root.right != null)
			{
				return minValue(root.right);
			}
		}
		
		// if given key is less than the root node, recur for left subtree
		else if (root.value.compareTo(key) > 0)
		{
			// update successor to current node before recursing in
			// left subtree
			succ = root;
			return findSuccessor(root.left, succ, key);
		}
		
		// if given key is more than the root node, recur for right subtree
		else
		{
			return findSuccessor(root.right, succ, key);
		}
		
		return succ.value;
		
	}
	
	
	public T successor(T elem)
	{
		
		// TODO
		return findSuccessor(root, null, elem);
		
	}
	
	
	private String removeComma(String string)
	{
		
		if (string.length() < 2) return string;
		return string.substring(0, string.length() - 2);
		
	}
	
	
	private String inOrder(Node current)
	{
		
		if (current == null)
		{
			return "";
		}
		return inOrder(current.left) + current.value.toString() + ", " + inOrder(current.right);
		
	}
	
	
	public String toStringInOrder()
	{
		
		// TODO
		return removeComma(inOrder(root));
		
	}
	
	
	private String preOrder(Node current)
	{
		
		if (current == null)
		{
			return "";
		}
		return current.value.toString() + ", " + preOrder(current.left) + preOrder(current.right);
		
	}
	
	
	public String toStringPreOrder()
	{
		
		// TODO
		return removeComma(preOrder(root));
		
	}
	
	
	private String postOrder(Node current)
	{
		
		if (current == null)
		{
			return "";
		}
		return postOrder(current.left) + postOrder(current.right) + current.value.toString() + ", ";
		
	}
	
	
	public String toStringPostOrder()
	{
		
		// TODO
		return removeComma(postOrder(root));
		
	}
	
	
	public boolean add(T elem)
	{
		
		return add(root, elem);
		
	}
	
	
	@SuppressWarnings("unchecked")
	private boolean add(Node startNode, T elem)
	{
		
		Node currNode = null;
		Node root = startNode;
		Node newNode = new Node(elem);
		
		while (root != null)
		{
			currNode = root;
			if (((Comparable<T>) newNode.value).compareTo(root.value) < 0) root = root.left;
			else if (((Comparable<T>) newNode.value).compareTo(root.value) > 0) root = root.right;
			else return false;
		}
		
		newNode.parent = currNode;
		if (currNode == null) this.root = newNode;
		else if (((Comparable) newNode.value).compareTo(currNode.value) < 0) currNode.left = newNode;
		else if (((Comparable) newNode.value).compareTo(currNode.value) > 0) currNode.right = newNode;
		else return false;
		return true;
		
	}
	
	
	private T minValue(Node current)
	{
		
		T minv = current.value;
		while (current.left != null)
		{
			minv = current.left.value;
			current = current.left;
		}
		return minv;
		
	}
	
	
	private Node removeRecursive(Node current, T elem)
	{
		
		{
			/* Base Case: If the tree is empty */
			if (current == null) return current;
			/* Otherwise, recur down the tree */
			if (elem.compareTo(current.value) < 0) current.left = removeRecursive(current.left, elem);
			else if (elem.compareTo(current.value) > 0) current.right = removeRecursive(current.right, elem);
			
			// if key is same as root's key, then This is the node
			// to be deleted
			else
			{
				// node with only one child or no child
				if (current.left == null)
				{
					if (current.right != null) current.right.parent = current.parent;
					return current.right;
				}
				
				else if (current.right == null)
				{
					if (current.left != null) current.left.parent = current.parent;
					
				}
				// node with two children: Get the inorder successor (smallest
				// in the right subtree)
				current.value = minValue(current.right);
				
				// Delete the inorder successor
				current.right = removeRecursive(current.right, current.value);
			}
			
			return current;
		}
		
	}
	
	
	public T remove(T value)
	{
		
		T temp = getElement(value);
		root = removeRecursive(root, value);
		return temp;
		
	}
	
	
	public void clear()
	{
		
		// TODO
		root = null;
		
	}
	
	
	public int sizeNode(Node current)
	{
		
		if (current == null) return 0;
		else
		{
			return 1 + sizeNode(current.left) + sizeNode(current.right);
		}
		
	}
	
	
	public int size()
	{
		
		// TODO
		return sizeNode(root);
		
	}
	
}
