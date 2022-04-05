/******************************************************************
 * Author: Apurva Gandhi
 * Date: 03/01/2022
 * VigenerKnownKey
 * This program  allow the user to encrypt and decrypt Vigenere ciphertext using a user-specied
 * keyword. 
 * A program can create an instance of this class and call performVigenereCipher () function
 * with simpleText, keyword, and action (encrypt or decrypt) they want to perform as parameter. 
 * It uses ceaser cipher to peform the actions. 
 **************************************************************/

public class VigenerKnownKey
{
    /****************
     * Constructor
     ****************/
    public VigenerKnownKey ()
    {   
        //empty
    }

    /********************************************
     * encryptCaesarCipher
     * @param inputChar
     * @param shift
     * @return encrypted char 
     *********************************************/
    private char encryptCaesarCipher(char inputChar, char shift)
    {
        char cipherChar; //for result
        char comparisionChar = 'z'; //to determine whether char is upper case or lower case, default is lower case
        int integerShift = 0;
        //if char is lower case
        if(inputChar >= 97 && inputChar <= 122)
        {
            //calculates the integer shift from shift character parameter
            integerShift = (int) shift - 97;
        }
        //if char is upper case
        else if(inputChar >= 65 && inputChar <= 90)
        {
            //calculates the integer shift from shift character parameter
            integerShift = (int) shift - 65;
            comparisionChar = 'Z';
        }
        else
        {
            System.out.println("Only alphabets are allowed. No special characters can be encoded for this program. Sorry! ");
        }
        //Add shift to the character and if it falls off the end of the alphabet 
        //then subtract shift from the number of letters in the alphabet (26)
        char c = (char)(inputChar + integerShift);
        if (c > comparisionChar)
        {
            cipherChar = (char)(inputChar - (26-integerShift));
        }
        //If the shift does not make the character fall off the end of the alphabet, then add the shift to the character.
        else
        {
            cipherChar = (char)(inputChar + integerShift);
        }
        return cipherChar;
    }//end of encryptCaesarCipher

    /***********************************
     * decryptCaesarCipher
     * @param inputChar
     * @param shift
     * @return decrypted char 
     ***************************************/
    private char decryptCaesarCipher(char inputChar, char shift)
    {
        char cipherChar; //for result
        char comparisionChar = 'a'; //to determine whether char is upper case or lower case, default is lower case
        int integerShift = 0;
        //if char is lower case
        if(inputChar >= 97 && inputChar <= 122)
        {
            //calculates the integer shift from shift character parameter
            integerShift = (int) shift - 97;
        }
        //if char is upper case
        else if(inputChar >= 65 && inputChar <= 90)
        {
            //calculates the integer shift from shift character parameter
            integerShift = (int) shift - 65;
            comparisionChar = 'A';
        }
        else
        {
            System.out.println("Only alphabets are allowed. No special characters can be encoded for this program. Sorry! ");
        }

        //Add shift to the character and if it falls off the end of the alphabet 
        //then subtract shift from the number of letters in the alphabet (26)
        char c = (char)(inputChar - integerShift);
        if (c < comparisionChar)
        {
            cipherChar = (char)(inputChar + (26-integerShift));
        }
        //If the shift does not make the character fall off the end of the alphabet, then add the shift to the character.
        else
        {
            cipherChar = (char)(inputChar - integerShift);
        }
        return cipherChar;
    } // end of decryptCaesarCipher

    /********************************
     * performVigenereCipher
     * @param plainText
     * @param keyword
     * @param action (encrypt or decrypt)
     * @return encrypted or decrypted text as a String
     *******************************/    
    public String performVigenereCipher (String plainText, String keyword, String action)
    {
        String result = "";
        int keyIndex = 0;
        for(int i = 0; i < plainText.length(); i++)
        {
            if(i % keyword.length() == 0)
            {
                keyIndex = 0;
            }
            else
            {
                keyIndex++;
            }
            //if user chooses to encrypt the text
            if(action.equals("encrypt"))
            {
                result +=  encryptCaesarCipher(plainText.charAt(i), keyword.charAt(keyIndex));
            }
            //if user chooses to decrypt the text
            else if((action.equals("decrypt")))
            {
                result +=  decryptCaesarCipher(plainText.charAt(i), keyword.charAt(keyIndex));
            }
            //erro checking
            else 
            {
                result = "Please enter the correct action you wish to perform: 'encrypt' or 'decrypt' ";
            }
        }
        return result;
    }//end of performVigenereCipher
}//end of class