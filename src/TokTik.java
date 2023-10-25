//TokTik implementation using a BinarySerchTree
//Shaylin Velen
//11 APril 2023
import java.util.*;
import java.io.*;

/**
 * The main method for the Toktik class presents a list to the user where they can enter an option which would allow them to perform some action on the binary tree
 */

public class TokTik {

    public static void main(String [] args) {
    
    BinarySearchTree<Account> bt = new BinarySearchTree<Account>();
    
    
        
  Scanner keyboard = new Scanner(System.in);
  
  while (true) {
  
  System.out.println("Choose an action from the menu:");
  System.out.println("1. Find the profile description for a given account\n" + "2. List all accounts\n" + "3. Create an account\n" + "4. Delete an account\n" + "5. Display all posts for a single account" );
  System.out.println("6. Add a new post for an account\n" + "7. Load a file of actions from disk and process this\n" + "8. Follow an account\n" + "9. List followers\n" + "10. List following\n" + "11. Remove follower");
  System.out.println("12. Unfollow account\n" + "13. Quit");
  System.out.print("Enter your choice: ");
  
  int choice = 7;
  try {
            choice = Integer.parseInt(keyboard.nextLine());
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid integer.");
            continue;
            }

    
   if (choice == 1) {
  
    System.out.println("\nEnter the account name: ");
    String accName = keyboard.nextLine();
    
    Account account = new Account(accName);
    //Create an account with only account name constructor for searching purposes
    //Search for account, this will match as account names are compared in compareTo using lowercase so it is not case sensitive
    BinaryTreeNode<Account> node = bt.find(account);
    
    
    if (node == null) {
    //Find method will return null if the account is not stored in the binary search tree  
    
    System.out.println("The account could not be found");
    
    }
    
    else {

      //get the description for the account if it is found
    String accDescr = node.data.getDescr();
    System.out.println("The profile description is: " + accDescr);
    
    }
       
  }
  
  else if (choice == 2) {
  
    //in-order traversal allows for all accounts to be outputted in alphabetical order without respecting case as that is the way the values are organized in the tree
    bt.inOrder();
    System.out.println("");
  
  }
  
  else if (choice == 3) {
  
    System.out.print("Enter the account name: ");
    String accName = keyboard.nextLine();
    
    Account account = new Account(accName);
    //If an account is created put it into the tree
    bt.insert(account);
    System.out.println("Account created");
    
    System.out.println("\nWould you like to add your description?(y/n)");
    String add = keyboard.nextLine();
    
      if (add.equals("y")) {
        
        System.out.print("Enter the account description: ");
        String accDescr = keyboard.nextLine();
        
        BinaryTreeNode<Account> node = bt.find(account);
        
        if (node == null) {
    
         System.out.println("The account could not be found");
    
    	}
    
    	else {
        //setDescr allows user to change the description as the initial account object created and inserted has description = ""
    	node.data.setDescr(accDescr);
    	System.out.println("\nDescription updated");
    
    	}
    	
      }
      
    }
    	
  else if (choice == 4) {
  
    
  
    System.out.print("Please enter the name of the account you would like to delete: " );
    Account del = new Account (keyboard.nextLine());
    
    BinaryTreeNode<Account> delNode = bt.find(del);
    
    if (delNode != null) {     
      //if .find returns a node delete that node
    bt.delete(delNode.data);
    System.out.println("Account deleted\n");
    }
    
    else
    System.out.println("Account could not be found, please enter the correct account name.\n");
    
    continue;
  
  }
  
  else if (choice == 5) {
  
  System.out.print("Enter the account name: ");
  Account account = new Account(keyboard.nextLine());
  
  BinaryTreeNode<Account> node = bt.find(account);
  
  System.out.println(node.data.getPosts()); 
  continue;
  }
  
  else if (choice == 6) {
  
  
  System.out.print("Enter your account name: ");
  String accName = keyboard.nextLine();
  Account account = new Account(accName);
  
  BinaryTreeNode<Account> node = bt.find(account);
  
  if (node == null) {
  
  System.out.println("The account could not be found");
  continue;
  
  }
  
  else {
  
  System.out.print("Title of post: ");
  String title = keyboard.nextLine();
  
  System.out.print("Video Name: ");
  String vidName = keyboard.nextLine();
  
  System.out.print("Number of likes: ");
  int numLikes = Integer.parseInt(keyboard.nextLine());
  
  Post post = new Post(title, vidName, numLikes);
  
  node.data.insertPosts(post);
  
  System.out.println("Post added");
  
  }
  continue;
  
  
  }
  
  else if (choice == 7) {
  
  //Reading from file into the BinarySearchTree
    
    Scanner fileIn = null;
    //File file = new File("/home/shaylin/A4toktik/src/dataset.txt");
    
    try { 
    
    System.out.print("Specify a file path: ");
    String path = keyboard.nextLine();

    //create a Scanner object with arguments FileInputStream
    //path is user specified so that any device could load the data
    fileIn = new Scanner(new FileInputStream(path));
    
    
    }
    
    catch (FileNotFoundException e) {
    
    System.out.println("File could not be found");
    continue;
    
    }
    
    //loop through the textfile until there are no more lines to read
    while (fileIn.hasNextLine()) {
    
    //Store the current line in the textfile in a string
    String line = fileIn.nextLine();

      //extract the action from the string, sep = " "
    String action = line.substring(0, line.indexOf(" "));

    //Replace the action part with an empty string
    line = line.replace(action + " ", "");
    
	    if (action == "Add") {
	        
		///Repeat process extracting information from each line of the textfile and storing it in appropriate variables
		
		String accName = line.substring(0, line.indexOf(" "));
    		line = line.replace(accName + " ", "");
    		
    		Account account = new Account(accName);
    		BinaryTreeNode<Account> node = bt.find(account);   			
    	 		
    		//String vidNam
    		String vidName = line.substring(0, line.indexOf(" "));
    		line = line.replace(vidName + " ", "");
    		
    		String likes = line.substring(0, line.indexOf(" "));
    		int numLikes = Integer.parseInt(likes);
    		line = line.replace(likes + " ", "");
    		
    		String title = line;
    		
    		Post post = new Post(title, vidName, numLikes);
    		
    		node.data.insertPosts(post);
    		
    		
		
	    }
	    else if (action.equals("Create")) {
	    
	    	String accName = line.substring(0, line.indexOf(" "));
	    	line = line.replace(accName + " ", "");
	    	
	    	String accDes = line;
	    	
	    	Account account = new Account(accName, accDes);
	    	
	    	BinaryTreeNode<Account> node = bt.find(account);
	    	
	    	if (node == null)
	    	bt.insert(account);
	    	
	    } 
    }
  fileIn.close();
  
  }
  
  else if (choice == 8) {

    //Ensure the account adding a follower is in the tree/'has been created'
    System.out.print("Enter your account name: ");
    Account myAcc = new Account(keyboard.nextLine());
    BinaryTreeNode<Account> accNode = bt.find(myAcc);


//If not in tree revert back to decision board - the account has not been created yet
    if (accNode == null) {

      System.out.println("That account does not exist");
      continue;
    }


    System.out.print("Enter the name of the account you would like to follow: ");
    Account followAccount = new Account(keyboard.nextLine());
    

    //Ensure both account signed in and account being followed are both in the initial tree

    
    BinaryTreeNode<Account> fNode = bt.find(followAccount);

    


    if (fNode != null) {

      System.out.println("Account name: " + fNode.data.getAccName());
      System.out.println("Account description: " + fNode.data.getDescr());
      System.out.println("Posts:");
      System.out.println(fNode.data.getPosts());
      
      System.out.println("Confirm?[y/n]");
      String ans = keyboard.nextLine();

      if (ans.equals("y")) {
        //follow method will insert the account we are using into the follower tree of the account we want to follow and update our 'following' tree
          accNode.data.follow(accNode.data, fNode.data);
          System.out.println(fNode.data.getAccName() + " has been followed by " + accNode.data.getAccName());
          continue;
        }

      else if (ans.equals("n")) {
          continue;

      }

    }

  else {

    System.out.println("The account that you want to follow could not be found");
    continue;
  }
  }

  else if (choice == 9) {

    System.out.println("Enter your account name: ");
    Account followersAcc = new Account(keyboard.nextLine());

    BinaryTreeNode<Account> node = bt.find(followersAcc);
    if (node == null) {

      System.out.println("That account does not exist");
      continue;
    }

    System.out.println("These are your followers: ");
    node.data.listFollowers();
    continue;

  }

  else if (choice == 10) {

    System.out.println("Enter your account name: ");
    Account followersAcc = new Account(keyboard.nextLine());

    BinaryTreeNode<Account> node = bt.find(followersAcc);
    if (node == null) {

      System.out.println("That account could not be found.");
      continue;
    }

    System.out.println("You follow these accounts:");
    node.data.listFollowing();


  }

  else if (choice == 11) {

    System.out.println("Enter your account name: ");
    Account followersAcc = new Account(keyboard.nextLine());

    BinaryTreeNode<Account> node = bt.find(followersAcc);
    if (node == null) {

      System.out.println("That account does not exist");
      continue;
    }

    System.out.print("Enter the account name of the follower you would like to remove: ");

    /**
     * find the follower account stored in the tree of followers
    

    */

    Account fRemove = new Account(keyboard.nextLine());
    BinaryTreeNode<Account> rNode = bt.find(fRemove);


    


    if (rNode != null) {

      System.out.println("Account name: " + rNode.data.getAccName());
      System.out.println("Account description: " + rNode.data.getDescr());
      System.out.println("Posts:");
      System.out.println(rNode.data.getPosts());
      
      System.out.println("Confirm?[y/n]");
      String ans = keyboard.nextLine();

      if (ans.equals("y")) {
          node.data.removeFollower(rNode.data);
          rNode.data.unfollow(node.data);
          System.out.println(node.data.getAccName() + " has been unfollowed by " + rNode.data.getAccName());
          continue;
        }

      else if (ans.equals("n")) {
          continue;

      }

    }

     else {

      System.out.println("The account that you want to remove could not be found in your follower list");
      continue;
    }



}

else if (choice == 12) {

  System.out.println("Enter your account name: ");
    Account followersAcc = new Account(keyboard.nextLine());

    BinaryTreeNode<Account> node = bt.find(followersAcc);
    if (node == null) {

      System.out.println("That account does not exist");
      continue;
    }

    System.out.print("Enter the account name of the account you would like to unfollow: ");

    /**
     * find the follower account stored in the tree of followers
    

    */

    Account fRemove = new Account(keyboard.nextLine());
    BinaryTreeNode<Account> rNode = bt.find(fRemove);


    


    if (rNode != null) {

      System.out.println("Account name: " + rNode.data.getAccName());
      System.out.println("Account description: " + rNode.data.getDescr());
      System.out.println("Posts:");
      System.out.println(rNode.data.getPosts());
      
      System.out.println("Confirm?[y/n]");
      String ans = keyboard.nextLine();

      if (ans.equals("y")) {
          node.data.unfollow(rNode.data);
          System.out.println(rNode.data.getAccName() + " has been unfollowed by " + node.data.getAccName());
          continue;
        }

      else if (ans.equals("n")) {
          continue;

      }

    }

     else {

      System.out.println("The account that you want to follow could not be found");
      continue;
    }
  

}
 
  
  
  else if (choice == 13) {
  
    System.out.println("Bye!");
    break;
    
  } 

 }//end while
	
    
 }//end public static void main


}//end class
