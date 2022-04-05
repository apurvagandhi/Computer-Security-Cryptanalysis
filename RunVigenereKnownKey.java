
import java.io.*;
import java.util.*; 

/************************************
 * Author: Apurva Gandhi
 * Date: 03/01/2022
 * RunVigenereKnownKey
 * This program is runner class for ViegenrKnownKey
 * Takes in following commandline arguments: action, filename, and key
 * This can be run as: java RunVigenereKnownKey decrypt cipherKnownKey.txt TAGORE
 * It prints the result in the plainKnownKey.txt file 
 *******************************************/
public class RunVigenereKnownKey
{
    public static void main (String[] args) throws FileNotFoundException
    {
       
        if(args.length != 3)
        {
            System.out.println("Sorry, the number of argument is incorrect. You may run it like this");
            System.out.println("java RunVigenereKnownKey [action] [filename] [keyword]");
            System.out.println("java RunVigenereKnownKey decrypt cipherKnownKey.txt TAGORE");
            System.exit(0);
        }
        System.out.println("Welcome to the Vigenere Cipher!");
        System.out.println("The result is printed in plainKnownKey.txtfile.");
        //Reading commandline Arguments
        String action = args[0];
        String fileName = args[1];
        String key = args[2];
        String plaintText = "";
        String ResultText = "";
        String outputFileName = ("plainKnownKey.txt");
        PrintWriter outputFile = new PrintWriter(outputFileName);
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        
        //reads the text from the file
        while (myReader.hasNext()) 
        {
            plaintText = myReader.nextLine();
        }
        myReader.close(); 
        //creates an instance of the VigenerKnownKey class  
        VigenerKnownKey vigener = new VigenerKnownKey();
        //Depending the action, calls the performVigenerCipher method of class Viegener Known key
        if(action.equalsIgnoreCase("cipher") || action.equalsIgnoreCase("encrypt"))
        {
            ResultText = vigener.performVigenereCipher(plaintText, key, "encrypt");
        }
        else if(action.equalsIgnoreCase("dicipher") || action.equalsIgnoreCase("decrypt"))
        {
            ResultText = vigener.performVigenereCipher(plaintText, key, "decrypt");
        }
        //prints the details
        outputFile.println("Your original plain text is: " + plaintText);
        outputFile.println("Your key is: " + key);
        outputFile.println("After performing " + action + " on plaintext using the key, resulted text is: " + ResultText);
        outputFile.close();
    }
}
