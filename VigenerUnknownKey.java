/*************************************************
 * Author: Apurva Gandhi
 * Date: 03/01/2022
 * VigenerUnknownKey
 * This program cryptanalyze Vigenere ciphertext when the keyword is unknown.
 * It prints it results in plainNoKey.txt file
 * It reads it text from file mentioned in commandline argument
 * program can be run like this: java VigenerUnknownKey cipherNoKey.txt
 **********************************************/

import java.io.*;
import java.util.*; 

public class VigenerUnknownKey
{
    //array list to store the list of all possible key words.
    public static ArrayList<String>alResult = new ArrayList<String>();   

    /************************************
     * Checks whether given string is a word in a dictonary
     * @param word
     * @return true if word is in a English Dictionary, false otherwise
     * @throws IOException
     *********************************/
    public static boolean check_for_word(String word) throws IOException
    {
       //this uses the local word list found on all Linux systems to check for the word
        BufferedReader in = new BufferedReader(new FileReader("/usr/share/dict/american-english"));
        String str;
        while ((str = in.readLine()) != null) 
        {
            if (str.indexOf(word) != -1) 
            {
                return true;
            }
        }
        in.close();       
        return false;
    }// end of check_for_word

    /******************
     * getAllKeyWord takes in double array of char conataining 
     * top 3 most frequent characters of given length
     * @param highFrequencyCharacters
     * calls the helper function 
     *****************/
    public static void getAllKeyWord(char[][] highFrequencyCharacters)
    {
        getAllKeyWordHelper(highFrequencyCharacters, 0, highFrequencyCharacters[0].length , "");
    }

    /***************
     * getAllKeyWordHelper recursively calls itself and adds the all possible key words 
     * with top 3 most frequent characters
     * @param keyLetters
     * @param index
     * @param length
     * @param word
     ************/
    public static void getAllKeyWordHelper(char[][] keyLetters, int index, int length, String word)
    {
        for(int i = 0; i < 3; i++)
        {
            if(index == length - 1)
            {
                alResult.add(word + keyLetters[i][index]);
            }
            else
            {
                getAllKeyWordHelper(keyLetters, index + 1, length, word + keyLetters[i][index]);
            }
        }
    } //end of getAllKeyWordHelper
    
    /*****************
     * maxFrequencyLetter returns the top,i.e. 1,2,3, frequent element as per 
     * the numberOfMax parameter
     * @param plaintext
     * @param numberOfMax
     * @return most frequent character as string
     *********************/
    public static String maxFrequencyLetter (String plaintext, int numberOfMax)
    {
        //Putting alphabets in the Hash Table
        HashMap<String, Integer> alphabets = new HashMap<>();
        alphabets.put("A",0);
        alphabets.put("B",0);
        alphabets.put("C",0);
        alphabets.put("D",0);
        alphabets.put("E",0);
        alphabets.put("F",0);
        alphabets.put("G",0);
        alphabets.put("H",0);
        alphabets.put("I",0);
        alphabets.put("J",0);
        alphabets.put("K",0);
        alphabets.put("L",0);
        alphabets.put("M",0);
        alphabets.put("N",0);
        alphabets.put("O",0);
        alphabets.put("P",0);
        alphabets.put("Q",0);
        alphabets.put("R",0);
        alphabets.put("S",0);
        alphabets.put("T",0);
        alphabets.put("U",0);
        alphabets.put("V",0);
        alphabets.put("W",0);
        alphabets.put("X",0);
        alphabets.put("Y",0);
        alphabets.put("Z",0);

        //depedning on the character, updates the hashmap's values
        for(int i = 0; i < plaintext.length(); i++)
        {
            // get the value of the specified key
            int count = alphabets.get(""+plaintext.charAt(i));
            alphabets.put("" + plaintext.charAt(i), count + 1);                
        }
        int maxTimeOccuringAlphabetNumber = Integer.MIN_VALUE;
        String maxOccuringAlphabet = "";
        for(int i = 0; i < numberOfMax; i++)
        {
            //calcualtes the most frequent occuring alphabet
            for (String key: alphabets.keySet()) 
            {
                if(alphabets.get(key) > maxTimeOccuringAlphabetNumber)
                {
                    maxOccuringAlphabet = key;
                    maxTimeOccuringAlphabetNumber = alphabets.get(key);
                }
            }
            //this will ignore the numberOfMax until we reach the one we need
            if(i != numberOfMax - 1)
            {
                //resets the variables
                alphabets.remove(maxOccuringAlphabet);
                maxTimeOccuringAlphabetNumber = 0;
            }
        }
        return maxOccuringAlphabet;
    }// end of maxFrequencyLetter

    /***********
     * calculateIOC calculates IOC of a given text
     * @param plaintext
     * @return IOC value of type double
     ************/
    public static double calculateIOC (String plaintext)
    {
        //Putting alphabets in the Hash Table
        HashMap<String, Integer> alphabets = new HashMap<>();
        int sum = 0;
        alphabets.put("A",0);
        alphabets.put("B",0);
        alphabets.put("C",0);
        alphabets.put("D",0);
        alphabets.put("E",0);
        alphabets.put("F",0);
        alphabets.put("G",0);
        alphabets.put("H",0);
        alphabets.put("I",0);
        alphabets.put("J",0);
        alphabets.put("K",0);
        alphabets.put("L",0);
        alphabets.put("M",0);
        alphabets.put("N",0);
        alphabets.put("O",0);
        alphabets.put("P",0);
        alphabets.put("Q",0);
        alphabets.put("R",0);
        alphabets.put("S",0);
        alphabets.put("T",0);
        alphabets.put("U",0);
        alphabets.put("V",0);
        alphabets.put("W",0);
        alphabets.put("X",0);
        alphabets.put("Y",0);
        alphabets.put("Z",0);

        //depedning on the character, updates the hashmap's values
        for(int i = 0; i < plaintext.length(); i++)
        {
            // get the value of the specified key
            int count = alphabets.get(""+plaintext.charAt(i));
            alphabets.put("" + plaintext.charAt(i), count + 1);                
        }
        
        //using the formula of IOC, calculates the IOC value
        for (String key: alphabets.keySet()) 
        {
            int n = alphabets.get(key) * (alphabets.get(key)-1);
            sum += n;
        }

        return (double)sum/(double)(plaintext.length()*(plaintext.length()-1));
    }//end of calculateIOC

    /********************
     * main
     * @param args
     * @throws IOException
     ******************/
    public static void main (String[] args) throws IOException
    {        
        if(args.length != 1)
        {
            System.out.println("Sorry, the number of argument is incorrect. You may run it like this");
            System.out.println("java VigenerUnknownKey [filename]");
            System.out.println("java VigenerUnknownKey cipherNoKey.txt");
            System.exit(0);
        }
        System.out.println("Welcome to Vigener Unknown Key cipher!");
        System.out.println("The result can be found in plainNoKey.txt file");

        long startTime = System.nanoTime();
        String outputFileName = ("plainNoKey.txt");
        String inputFileName = args[0];  
        PrintWriter outputFile = new PrintWriter(outputFileName);
        //Opens the file 
        File inputFile = new File(inputFileName);
        Scanner myReader = new Scanner(inputFile);
        String plaintext = "";
        HashSet<Integer> keyLength = new HashSet<>();
        double [][] groupIoc = new double [11][11];
        String [][] groupPlainText = new String [11][11];
        
        //reads the plain text into a string
        while (myReader.hasNext()) 
        {
            plaintext += myReader.next();
        }
        myReader.close();

        //converts the plaintext to uppercase, if needed
        plaintext = plaintext.toUpperCase();
        //outputs the plain text
        outputFile.println("plain text is: " + plaintext);
        outputFile.println();

        //this loops divides the text into i keylength, break up
        //ciphertext by i keylengh, and then use IOC on each group
        //to get the IOC value and stores the group and IOC value in double array
        outputFile.println("IOC Table for possible key length (upto 10) is as follows: ");
        for(int i = 1; i < 11; i++)
        {
            for(int k = 0; k < i; k++)
            {
                String group = "";
                for(int j = k; j <= plaintext.length() - i + (i - 1); j = j + i)
                {
                    group += "" + plaintext.charAt(j);
                }
                groupIoc[i][k] = calculateIOC(group);
                groupPlainText[i][k] = group;
                //outputFile.println("Group " + k + " is: " + group);
                outputFile.print(groupIoc[i][k] + "  ");
            }
            outputFile.println();
        }
        
        //this loops checks the IOC of each group and if it is >0.061
        //it considers that length as possible key length
        for(int i = 0; i < groupIoc.length; i++)
        {
            for(int j = 0; j < groupIoc[0].length; j++)
            {
                if(groupIoc[i][j] < 0.061)
                {
                    break;
                }
                else
                {
                    if(!keyLength.contains(i))
                    {
                        keyLength.add(i);
                    }
                }
            }
        }
        
        outputFile.println();
        outputFile.println("Possible key lengths are: " + keyLength);
        /********
         * The length of the keyLetter need to be change if there are more than
         * one possible key lengths. The code will need to be modified. This will only 
         * work if key length is 9 or can be changed to other length but not multiple lengths
         * *********/
        char [][] keyLetters = new char[3][9];
        int putativeKeyWordInteger = 0;
        //this loops determines the shift based on the max frequency 
        //and english letter E. Then it uses the CeaserCipher
        //based on the shift it calcualted to get the putative word
        for(int j = 0; j < 3; j++)
        {
            for (int KeyLength: keyLength) 
            {
                for(int i = 0; i < KeyLength; i++)
                {
                    String maxChar = maxFrequencyLetter(groupPlainText[KeyLength][i], j+1);
                    //calculates the shift
                    int shift = (int)maxChar.charAt(0) - (int)'E';
                    //checks for wrap around
                    if(shift < 0)
                    {
                        putativeKeyWordInteger = 65 + (26 + shift);
                    }
                    else
                    {
                        putativeKeyWordInteger = 65 + shift;
                    }
                    char putativeKeyWordLetter = (char)putativeKeyWordInteger;
                    //store the key characters into an array to check for possible words
                    keyLetters[j][i] = putativeKeyWordLetter;
                }
            }                          
        }  

        outputFile.println();
        //This loops prints the top 3 most frequent characters for 
        // each position in the key length
        for(int i = 0; i < keyLetters.length;i++)
        {
            outputFile.print("Top " + (i+1) + " Frequent letters are ");
            for(int j = 0; j < keyLetters[0].length;j++)
            {
                outputFile.print(keyLetters[i][j]);
            }
            outputFile.println();
        }          
        //this function call will add all possible keyWords to the arrayList
        getAllKeyWord(keyLetters);
        //creates an instance of VigenerKnownKey class
        VigenerKnownKey vigener = new VigenerKnownKey();
        outputFile.println();
        outputFile.println("Possible Keywords and its possible simple text is as follows: ");
        for(int i = 0; i < alResult.size(); i++)
        {   
            //for better readibility, adds the new line every 10 words
            if(i % 10 == 0)
            {
                outputFile.println();
            } 
            //prints the possible keyword               
            outputFile.println(alResult.get(i) + " ");
            //decrypts and prints the encrypted text using the keyword
            String result = vigener.performVigenereCipher(plaintext, alResult.get(i), "decrypt");
            outputFile.println(result);

            /************
            * this checks for the word in dictionary
            * if it is, it will be printed as one. This can be an easy keyword
            * This is not useful for this example but I am keeping it here for future. It does take longer
            * time for program to finish execution. It is commented for this input file.
            **************/
            /*
            if(check_for_word(alResult.get(i).toLowerCase()))
            {
                outputFile.println("Following keyword is found in the dictionary");
                outputFile.println(alResult.get(i) + " ");
            }
            */
        }
        //calcualtes and prints the elapsed time
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        double elapsedTimeInSecond = (double) totalTime / 1000000000;
        outputFile.println("Total Elapsed Time while running this program is: " + elapsedTimeInSecond + " seconds.");
        outputFile.close();
    }    
}
