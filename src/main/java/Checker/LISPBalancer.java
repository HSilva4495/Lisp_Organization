package Checker;
import java.util.*;

    /*
    * LISP is the second-oldest high-level programming language. Developed to be
    * machine independent practical mathematical notation for computer programs. 
    * LISP Uses prefix notation which means the parentheses balance of the notation
    * is essential for debugging.
    * 
    * As the Primary Data Structure to figure out the balance of the
    * input I used Deque for it's ability to function as a double ended Queue with a head and tail 
    * though we will be using it mainly as a Stack (Last-In-First-Out) since in java using 
    * ArrayDeque reported to be faster than using Stack though in general we are shooting 
    * for a O(n) complexity
    * 
    */

    /*
    * Steps:
    * 1. Check if input is not Null. If it is Null then the string is not balanced,
    * ret false.
    * 2. Get rid of characters that are not parentheses.
    * 3. Check if character is an open parentheses and push it into the Deque, else
    * false.
    * 4. CheckCheck if character is a closed parentheses if it is then pop it to remove open
    * character from the Deque, else false.
    * 5. If Deque is empty at this point then it is balanced and a valid function.  
    *
    */

    /**
     @author Henry Silva Quevedo
     @since 06/01/2022
     */

public class LISPBalancer {

   public static boolean BalLISP(String Input){
        //innit stack
        Deque<Character> stack = new ArrayDeque<>();
 

        //This Line strips the string of characters not parentheses using regex 
        String StackString = Input.replaceAll("[^()]", "");
        System.out.println("String Regex:" + "\n" +  StackString);

        // len
        int StackStringLen = StackString.length();

        //checks if the input is balanced before the whole traversal 
          if (StackStringLen % 2 != 0){
            return false;
          }

        //Itterating through the string input
        for(int i=0; i< StackStringLen;i++){
            //Checking if first character is open paren
            if(StackString.charAt(i) == '('){
                stack.push(StackString.charAt(i));
            }else{
                if(stack.isEmpty()){
                     return false;// if it's empty then there are no opening paren it is false.
                }else{
                    char popChar = stack.pop();
                    if(StackString.charAt(i)==')' && popChar != '('){
                        return false; // if the char is closed bracket or and not open it is false.
                    }
                }// end nested
            }// end else 
        }//finish itterating
    
        if(stack.isEmpty()){// if stack is empty then all popped at this point
            return true;}// the function is true 
            else{
                return false;}  //else if there is still something then it is false
    }//end ballFunc
    
}//end Class
