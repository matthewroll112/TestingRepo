/*
  Matthew Rolleston,
  1569761
  Date: 19/04/2023
*/

// Main class to process bank accounts and BST
// Will read in a file and process according to instructions

import java.io.BufferedReader;
import java.io.Console;
import java.io.File; //File class
import java.io.FileInputStream;
import java.io.FileNotFoundException; //Class to handle exceptions
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner; //Scanner to read text files

public class XProcess{
    public static void main(String[] args) throws IOException{
        //Let user input the file to be opened
        Scanner userReader = new Scanner(System.in); //Scanner object for input
        System.out.print("Enter name of file to be opened (File must be in current directory unless specifying path): ");

        //Read input
        String file = userReader.nextLine();
        System.out.println("-------------------------------"); //seperating user input from file
        userReader.close();

        //Create BST for adding nodes
        BankBST bank = new BankBST();

        //Try catch structure to handle exceptions with reading file
        try{
            //Get file to read, and open for reading
            File sample = new File(file);
            FileInputStream fis = new FileInputStream(sample);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;

            //Loop through file line by line and excract contents
            while((line = br.readLine()) != null){
                //Get current line from file
                String[] segments = line.split(" ");

                //Variables for ease
                int key = Integer.parseInt(segments[0]);
                double amount = Double.parseDouble(segments[2]);

                //Deposit
                if(segments[1].equals("d")){
                    //if account is not in bst, then create and execute
                    if(bank.Find(key) == null){
                        bank.Insert(key);
                    }
                    //Deposit amount to account
                    Account acc = bank.Find(key);
                    acc.setBalance(amount);
                    System.out.print("DEPOSIT\n");
                }
                //Withdraw
                else if(segments[1].equals("w")){
                    //if account is not in bst, then create and execute
                    if(bank.Find(key) == null){
                        bank.Insert(key);
                    }
                    //Withdraw amount to account
                    Account acc = bank.Find(key);
                    acc.setBalance(-amount);
                    System.out.print("WITHDRAW\n");

                }
                //Closure
                else if(segments[1].equals("c")){
                    bank.Remove(key);
                    System.out.print("CLOSE\n");
                }
                
            }

            br.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Error has occured");
            ex.printStackTrace();
        }

        //Line to seperate actions and displaying in console
        System.out.println("RESULT");
        bank.InorderTraversal();
    }
}