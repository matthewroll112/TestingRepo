import java.util.ArrayList;
import java.util.List;

/*
  Matthew Rolleston,
  1569761
  Date: 19/04/2023
*/

// Class to contain all methods necessary
// to build and maintain a BST of Accounts

public class BankBST{
    //Root node of BST
    private Account root;
    //List to contain visited nodes along BST to print
    List<Integer> visitedNodes = new ArrayList<Integer>();

    //Contrsutor that does not take a root node
    public BankBST(){
	    root = null;
    }

    //Insert(Account node): Will insert a node into the BST using a private recursive method
    public void Insert(int key){
        //Check if root is null before inserting
        if (root == null) {
            //if root is null create new Account and make it root
            root = new Account(key, 0.00);
        } else {
            //Root is not null, use recursion to insert node into BST
            root = InsertRec(root, key);
        }
    }
	
    //InsertRec(Account current, Account new): Will insert a node into BST using recursion
    private Account InsertRec(Account current, int key){
        //current is empty, create new account and return it
        if(current == null){
            current = new Account(key, 0.00);
            return current;
        }

        //if key is less than current key, go left
        if(key < current.getKey()){
            current.left = InsertRec(current.left, key);
        }
        //if key is greater than current key, go right
        else if(key > current.getKey()){
            current.right = InsertRec(current.right, key);
        }

        //account with same key node is already in BST, return current
        return current;
    }

    //Find(int key): Will find the account with the requested key and return it, if account is not in bst, return null
    public Account Find(int key){
        //If root is empty return null, else continue
        if(root == null){
            return null;
        }
        //Create new variable to hold root
        Account node = root;
        
        //Loop through bst to find node
        while(node != null){
            visitedNodes.add(node.getKey());
            //if nodes key is equal to key return node
            if(node.getKey() == key){
                //print nodes before returning
                PrintNodes();
                return node;
            }
            //if key is greater than nodes key go right
            else if(key > node.getKey()){
                node = node.right;
            }
            //if key is less than nodes key, go left
            else if(key < node.getKey()){
                node = node.left;
            }
        }
        //node not found clear visitedNodes and return null
        visitedNodes.clear();
        return null;
    }

    //PrintNodes(): Will print all the nodes keys in the visitedNodes list
    private void PrintNodes(){
        //print nodes keys
        for(int key:visitedNodes){
            //print string
            System.out.print(key + " ");
        }
        //clear list
        visitedNodes.clear();
        //return
        return;
    }

    //Remove(int key): Will remove a node with the same key as parameter from bst
    public void Remove(int key){
        //Use recursion method to remove node
        root = RemoveRec(root, key);
    }

    //RemoveRec(Account node, int key): Will use recursion to remove specified node from bst
    private Account RemoveRec(Account node, int key){
        //Did not find node, or tree is empty
        if(node == null){
            return node;
        }

        //If key is greater than node, go right
        if(key > node.getKey()){
            node.right = RemoveRec(node.right, key);
        }
        //If key is less than node, go left
        else if(key < node.getKey()){
            node.left = RemoveRec(node.left, key);
        }
        //Node is found, delete
        else{
            //Removing a leaf node
            if(node.left == null && node.right == null){
                return null;
            }
            //Removing node which only has one child
            else if(node.left == null){
                return node.right;
            }
            else if(node.right == null){

                return node.left;
            }
            //If node has two children set to inorder successor
            else{
                Account successor = FindMin(node.right);

                //if successor is child of node, point successor.left to node.left and return successor
                if(node.right == successor){
                    successor.left = node.left;
                    return successor;
                }
                //successor is not child of node
                else{
                    successor.left = node.left;
                    successor.right = node.right;
                    return successor;
                }
                
            }
        }
        //return back current node
        return node;
    }

    //findMin(Account node): Will find the next inorder successor of a node, then return the inorder successors key
    private Account FindMin(Account node){
        //loop to find lowest value node is subtree
        while(node.left != null){
            node = node.left;
        }
        //return nodes key
        return node;
    }

    //InorderTraversal(): Will traverse the entire BST, displaying the nodes in ascending order
    public void InorderTraversal(){
        //call recursive method to display
        InorderTraversalRec(root);
    }

    //InorderTraversalRec(Account node): Will traverse entire BST using recursion displaying nodes and balance in ascending order
    private void InorderTraversalRec(Account node){
        //while node does not equal null
        if(node != null){
            //go down left of tree and subtrees to get minimum node
            InorderTraversalRec(node.left);
            //display current node
            System.out.println(node.getKey() + "\t" + node.getBalance());
            //go down right of tree and subtrees
            InorderTraversalRec(node.right);
        }
    }
}
	    
    
