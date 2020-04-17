/*
	BST.java
	Author: Aileen L. Cai
	Data Structures & Algorithms - David Guy Brizan
	04/16/2020
	Practice Assignment 08
*/


class BSTNode<T extends Comparable>   //node class
{
	T key;
	BSTNode left;
	BSTNode right;

	public void BSTNode(T key)   //constructor to create node
	{
		this.key = key;
		left = right = null;
	}
}


public class BST<T extends Comparable>
{
	BSTNode<T> root;   //node from which tree begins from
 
	public boolean find(T key)   //public function to find certain value in tree
	{
		return find(root, key);
	}
  
	protected boolean find(BSTNode root, T key)  //private function to find certain value in true
	{
		if (root == null)  //if root hasn't been created, not going to find value 
			return false;
		
		if (key == root.key)  //if root equals key, return true
			return true;

		else if (key.compareTo(root.key) < 0)  //if key is less than root, search left
			return find(root.left, key);

		else 
			return find(root.right, key); //if key is greater than root, search right
	}

	public void insert(T key) //public function to insert value into tree
	{
		root = insert(root, key);
	}

	protected BSTNode insert(BSTNode root, T key) //private functtion to insert value into tree
	{
		if (root == null) 
			return new BSTNode(key); //if root hasn't been established, insert value at root 
			//couldn't get constructor to work when compiling program

		if (key.compareTo(root.key) < 0)     //if root has been established and value inserting is less than, go left
			root.left = insert(root.left, key);
		else
			root.right = insert(root.right, key); //if root has been established and value inserting is greater than, go right

		return root;
	}

	public void print()
	{
		print(root);
	}
	protected void print(BSTNode root)
	{
		if (root != null)   //printing the values of the tree in order 
		{
			print(root.left);
			System.out.print(" " + root.key);
			print(root.right);
		}

	}

	public void delete(T key)  //public function to delete a value in the tree
	{
		root = delete(root, key);
	}

	protected BSTNode delete(BSTNode root, T key)
	{
		if (root == null)  //can't find key to delete
			return null;

		if (root.key.compareTo(key) < 0)   //depending on whether key is at the left or right, delete node
		{
			root.right = delete(root.right, key);  
			return root;
		}
		
		else if (root.key.compareTo(key) > 0)
		{
			root.left = delete(root.left, key);
			return root;
		}

		else 
		{ 
			if (root.left == null)   //what to do if deleted node has a child/children 
				return root.right;   //need to promote child to parent node
			
			else if(root.right == null)
				return root.left;
			else 
			{
				if (root.right.left == null)
				{
					root.key = root.right.key;
					root.right = root.right.right;
				}

				else 
					root.key = removeSmallest(root);
				
				return root;
			}
		}
	}

	protected Comparable removeSmallest(BSTNode root)
	{
		if (root.left.left == null)
		{
			Comparable smallest = root.left.key;
			root.left = root.left.right;
			return smallest;
		}

		else 
			return removeSmallest(root.left);
	}
}