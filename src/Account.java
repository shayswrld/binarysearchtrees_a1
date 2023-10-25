
//TokTik class
//Shaylin Velen
//05/04/2023

import java.util.*;


public class Account implements Comparable<Account> {

   
   private String name, description;
   private ArrayList<Post>  posts;
   private BinarySearchTree<Account> followers;  
   private BinarySearchTree<Account> following;
   //private BinarySearchTree<Account> following;

   /**
   
   A class representing an account
   
   @param: name: The account name
   @param: description: The account description
   @param: An arraylist of posts 
   @param: followers: Binary Search Tree of followers for this account
   @param: following: Binary Search Tree of accounts that this account follows
   
   
   */
   
   //Constructor if this is a new user with no posts
   public Account(String name, String description) { 
    
      this.name = name;
      this.description = description;
      posts = new ArrayList<Post>();
      followers = new BinarySearchTree<Account>();
      following = new BinarySearchTree<Account>();
   }
   
   //Constructor for only the accountName 
   public Account(String name) {
   
    this.name = name;
    posts = new ArrayList<Post>();
    this.description = "";
    followers = new BinarySearchTree<Account>();
    following = new BinarySearchTree<Account>();
    
   }
   
   public Account(String name, String description, ArrayList<Post> posts) {
   
   this.posts = posts;
   this.name = name;
   this.description = description;
   followers = new BinarySearchTree<Account>();
   following = new BinarySearchTree<Account>();
   
  }
  
  public void setDescr(String newDescr) {
  
  /*
   
   set method to change the account description
   
   @param: newDescription: The account description
      
   */
   
   description = newDescr;
  
  }
  
  public void setAccName(String newName) {
  
  /**
  *
   
   set method to change the account name
   
   @param: newName: The account name
      
   */
  
   name = newName;
  
  }
  
  public void insertPosts(Post p) {
  
  /**
   
   function to make a new post 
   
   @param: p: the post that needs to be made/inserted into the list
      
   */
   posts.add(p);
  
  }
  
   public String getAccName() {
   
   /**
   
   * get method to return the account name
   
   */
   
      return name;
   }
   
   public String getDescr() {
   
    /**
   
   * get method to return the account name
   
   */
      return description;
   }
   
   @Override
   public int compareTo (Account another) {
   
   return name.toLowerCase().compareTo(another.getAccName().toLowerCase()); 
   
   }
   
   public String getPosts() {
      
   /**
   
   *return the posts in the list for the account
   
   */
   
    ListIterator<Post> postIterator = posts.listIterator(posts.size());
    
    String accPosts = "";
    
    while (postIterator.hasPrevious()) {
    
     Post p = postIterator.previous();
     String numLikes = Integer.toString(p.getLikes());
     
     accPosts = accPosts + p.toString();
       
    }
    
   return accPosts;
   }
   
   public String toString(){
   
   /**
   
   *return account name
   */

   String output = "";
   output = "Account name: " + name + "\nAccount description: " + description;
   
   return output;
   
   }

   public void follow(Account a, Account f) {

      /**
       * @param: a: The account f, which the user wants to follow now gains a follower in it's tree
       * @param: f: The account a, now follows f, a is our account in the main method for option 8
       */
      
      following.insert(f);
      f.followers.insert(a);

   }

    
   public void listFollowers() {
      
      
      followers.inOrder();

   }

   public void removeFollower(Account a) {
      
      /**
       * delete a follower from the follower binary tree in a certain acccount thus removing the follower
       * @param: a: account that you would like to delete;
       */

      followers.delete(a);
      a.following.delete(this);
   }

   public void listFollowing() {
      
      
      following.inOrder();

   }

   public void unfollow(Account u) {
      
      /**
       * delete an account from the following binary tree in a certain acccount thus unfollowing the account
       * @param: a: account that you would like to unfollow;
       */

      following.delete(u);
      u.followers.delete(this);

   }

   
   
   }
   
