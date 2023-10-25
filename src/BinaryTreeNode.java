// TokTik BTNode
// 10 April 2023
// Shaylin Velen

public class BinaryTreeNode<Account>
{
   Account data;
   BinaryTreeNode<Account> left;
   BinaryTreeNode<Account> right;
   
   public BinaryTreeNode ( Account account, BinaryTreeNode<Account> l, BinaryTreeNode<Account> r )
   {
      this.data = account;
      left = l;
      right = r;
   }
   
   BinaryTreeNode<Account> getLeft () { return left; }
   BinaryTreeNode<Account> getRight () { return right; }
}
