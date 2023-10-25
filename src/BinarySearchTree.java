// <Account> Binary Search Tree
// 10 April 2023
// Shaylin Velen

//@Author: Hussein Suleiman

public class BinarySearchTree<Account extends Comparable<? super Account>> extends BinaryTree<Account>
{
   public void insert ( Account account )
   
   /**
   *insert function to insert an account object into the binarysearchtree
   
   @param: account: account object that stores account name, description and a list of posts
   */
   
   {
      if (root == null)
         root = new BinaryTreeNode<Account> (account, null, null);
      else
         insert (account, root);
   }
   public void insert ( Account account, BinaryTreeNode<Account> node )
   {
      if (account.compareTo (node.data) <= 0)
      {
         if (node.left == null)
            node.left = new BinaryTreeNode<Account> (account, null, null);
         else
            insert (account, node.left);
      }
      else
      {
         if (node.right == null)
            node.right = new BinaryTreeNode<Account> (account, null, null);
         else
            insert (account, node.right);
      }
   }
   
   public BinaryTreeNode<Account> find (Account account )
   
   /**
   *recursive function that passes an account defines a root node and traverse the tree to find an account
   *finds a node in the binary search tree and returns the node that stores the account object passed
   
   *@param: account: account object
   
   */
   
   {
      if (root == null)
         return null;
      else
         return find (account, root);
   }
   public BinaryTreeNode<Account> find (Account account, BinaryTreeNode<Account> node )
   {
      if (account.compareTo (node.data) == 0) 
         return node;
      else if (account.compareTo (node.data) < 0)
         return (node.left == null) ? null : find (account, node.left);
      else
         return (node.right == null) ? null : find (account, node.right);
   }
      
   public void delete ( Account account )
   /**
   *recursive function that searches through the binary search tree finds a node using an account passed and deletes that node from the tree
   
   *@param: account: account object with an account name, account description and a list of posts
   
   */
   
   {
      root = delete (account, root);
   }   
   public BinaryTreeNode<Account> delete (Account account, BinaryTreeNode<Account> node )
   {
   
   /** 
   
   *recursive function delete(account) calls this function to traverse the tree and find the correct object
   @param: account
   @param: root: function will return the root node in order to call this function recursively
   
   */
      if (node == null) return null;
      if (account.compareTo (node.data) < 0)
         node.left = delete (account, node.left);
      else if (account.compareTo (node.data) > 0)
         node.right = delete (account, node.right);
      else if (node.left != null && node.right != null )
      {
         node.data = findMin (node.right).data;
         node.right = removeMin (node.right);
      }
      else
         if (node.left != null)
            node = node.left;
         else
            node = node.right;
      return node;
   }
   
   public BinaryTreeNode<Account> findMin ( BinaryTreeNode<Account> node )
   {
   
   /**
   
   *stores the lowest value in the binary search tree
   @param: node: node to start traversing the binary search tree from
   
   */
   
      if (node != null)
         while (node.left != null)
            node = node.left;
      return node;
   }

   public BinaryTreeNode<Account> removeMin ( BinaryTreeNode<Account> node )
   {
   
   /**
   
   *removes the lowest value in the tree
   @param: node: start on this node in the binary search tree  
   *
   
   */
      if (node == null)
         return null;
      else if (node.left != null)
      {
         node.left = removeMin (node.left);
         return node;
      }
      else
         return node.right;
   }
   
}
