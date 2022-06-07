import static Checker.LISPBalancer.BalLISP;

public class Main {


    public static void main(String args[]){
        String LIspStrT1 = "(write(+ (* (/ 9 5) 60) 1 32))";
        String LIspStrF1 = "(write(+ (* (/ 9 5] 60 32))";

        System.out.println(BalLISP(LIspStrT1));

    }//end main
}
