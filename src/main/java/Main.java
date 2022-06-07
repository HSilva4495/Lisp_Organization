import static Checker.LISPBalancer.BalLISP;

import CSVReadWrite.ReadingCSV;
import CSVReadWrite.SortCSV;
import CSVReadWrite.Writing;
import Objects.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class Main {
    public static List<String> CompList = new ArrayList<String>();
    public static Map<String, UserObjects> userHMap = new HashMap<>();
    public static void main(String args[]) throws IOException {

        /**
         * LISP -------------------------------------------------------------------
         */
        //simple string function
        String LIspStrT1 = "(write(()+ (* (/ 9 5) 60) 1 32))";
        //this demosntrates If conditions
        String LIspStrT2 = """
                                   (if
                                   ; condition to test
                                   (< x 0)
                                   ; expression to evaluate/return if true
                                   (format t "x is negative~%")
                                   ; expression to evaluate/return if false
                                   (format t "x is non-negative~%")
                              )\s""";
        String LIspStrT3 = """
                (defun meaning (life)
                  "Return the computed meaning of LIFE"
                  (let ((meh "abc"))
                    ;; Invoke krakaboom
                    (loop :for x :across meh
                       :collect x)))                    ; store values into x, then return it
                \s""";

        String LIspStrF1 = "(write(+ (* (/ 9 5] 60 32))";
        String LIspStrF2 = """
                                   ((if
                                   ; condition to test
                                   (< x 0)
                                   ; expression to evaluate/return if true
                                   (format t "x is negative~%")
                                   ; expression to evaluate/return if false
                                   (format t "x is non-negative~%")
                              )\s""";
        String LIspStrF3 = """
                (defun meaning (life)(
                  "Return the computed meaning of LIFE"
                  (let ((meh "abc"))
                    ;; Invoke krakaboom
                    (loop :for x :across meh
                       :collect x()))                  ; store values into x, then return it
                \s""";

        System.out.println(BalLISP(LIspStrT1));
        System.out.println(BalLISP(LIspStrF1));
        System.out.println(BalLISP(LIspStrT2));
        System.out.println(BalLISP(LIspStrF2));
        System.out.println(BalLISP(LIspStrT3));
        System.out.println(BalLISP(LIspStrF3));


        /**
         * CSV Parsing-------------------------------------------------------------
         */
        ReadingCSV CSVParse = new ReadingCSV();
        SortCSV Sorting = new SortCSV();

        //reading file
        List<UserObjects> CustList = CSVParse.ReadingToFile();
        List<UserObjects> FillCList = Sorting.cleanList(CustList);//filter null

        CustList.addAll(userHMap.values());
        CompList = Sorting.SRTByInsurance(CustList,CompList);

        //move Cleaned list to a UserHash
        for(UserObjects user : FillCList){
            Sorting.CompanyToUser(user,userHMap);
        }

        List<UserObjects> endList = new ArrayList<UserObjects>(userHMap.values());
        Sorting.SortLast(endList);
        System.out.println(endList);

        //writing
        Writing.WNewFile(CompList, endList);


    }//end main
}
