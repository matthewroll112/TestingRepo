/*
  Matthew Rolleston,
  1569761
  Date: 1/04/2023
*/

// Class to hold information on one bank account

public class Account{
    //Var to hold key/number of account
    private int Key;
    //Var to hold current balance of account
    private Double Balance;
    //Var to hold the account to the left of this node in BST
    public Account left;
    //Var to hold the account to the right of this node in BST
    public Account right;

    //Will create an account object using an account number and balance as parameters
    public Account(int key, Double balance){
	Key = key;
	Balance = balance;
	//setting left and right to null
	left = null;
	right = null;
    }

    //getKey(): Will return the key of the bank account
    public int getKey(){
	return Key;
    }

    //getBalance(): Will return the current balance of the account
    public Double getBalance(){
	return Balance;
    }

    //setBalance(int amount): Will change the accounts balance by the passed in amount
    public void setBalance(Double amount){
	//changing balance by parameter amount
	Balance += amount;
    }
}
