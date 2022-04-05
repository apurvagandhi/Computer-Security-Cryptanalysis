/************************************
 * Author: Apurva Gandhi
 * Date: 03/01/2022
 * LFSR
 * This program allow the user to encrypt and decrypt LFSR-based ciphertext 
 * using a user-specied key.
 * Takes in following commandline arguments: filename
 * This can be run as: java LFSR LFSR.encrypted
 * It prints the result in the LFSR.decrypted file 
 *******************************************/

import java.io.*;
import java.math.BigInteger;

public class LFSR
{
    public static void main(String args[]) throws IOException
    {
        if(args.length != 1)
        {
            System.out.println("Sorry, the number of argument is incorrect. You may run it like this");
            System.out.println("java LFSR [filename]");
            System.out.println("java LFSR LFSR.encrypted");
            System.exit(0);
        }
        System.out.println("Welcome to the LFSR Cipher!");
        System.out.println("The result is printed in LFSR.decrypted file.");
        //intital key string
        String key = "11111111";
        //input file name
        String inputFilename = args[0];
        //output file name
        String outputFileName = "LFSR.decrypted";
        //to store the actual calculated key
        String stringKey = "";

        long fileSize = new File(inputFilename).length();
        byte [] simpleTextByte = new byte[(int)fileSize];
        int [] b = new int [simpleTextByte.length * 8];
        byte [] resultByte = new byte[(int)fileSize];
        InputStream inputStream = new FileInputStream(inputFilename);
        OutputStream outputStream = new FileOutputStream(outputFileName);

        //reading first byte
        int NumberOfByteRead = inputStream.read(simpleTextByte);
        //reading rest of the bytes from encrypted file to byte array
        while (NumberOfByteRead != -1) 
        {
            NumberOfByteRead = inputStream.read(simpleTextByte);    
        }
    
        //Assigning  Intial key values
        for(int i = 0; i < key.length(); i++)
        {
            b[i] = Character.getNumericValue(key.charAt(i));
        }

        //Calculating key values using provided formula
        for(int i = key.length(); i < simpleTextByte.length * 8; i++)
        {
            b[i] = ((b[i-4]) + (b[i-5]) + (b[i-6]) + (b[i-8])) % 2;
        }

        //converting key of type integer array to key to type string
        for(int i = 0; i < b.length; i++)
        {
            stringKey += b[i];
        }

        //converting key of type string to key of type byte array
        byte[] keyTextByte = new BigInteger(stringKey, 2).toByteArray();            
        for (int i = 0; i < simpleTextByte.length; i++) 
        {
            //XOR simple text with calcualted key of type byte 
            resultByte[i] = (byte) (simpleTextByte[i] ^ keyTextByte[i+1]);
            //prints it to the output file
            outputStream.write(resultByte[i]);
        } 
        inputStream.close();
        outputStream.close();      
    }
}