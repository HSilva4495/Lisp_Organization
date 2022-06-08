import static Checker.LISPBalancer.BalLISP;

import CSVReadWrite.ReadingCSV;
import CSVReadWrite.SortCSV;
import CSVReadWrite.Writing;
import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class MainProg {
    public static List<String> CompList = new ArrayList<String>();
    public static Map<String, UserObjects> userHMap = new HashMap<>();

    private String LISPString;

    public static void LISPEXP(){
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
                                   (format t "x is negative~%")]
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

    }//end lispexp

    public static void csvEXP() throws IOException {
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
    }
    public MainProg(){
        JFrame frame = new JFrame();
    //text filed Classes
       TextField tfCount = new TextField("UI input Lisp", 50);
        JLabel LISPLabel = new JLabel("Result:");



        //button classes

        //LISP check button added
        JButton buttonLisp = new JButton("Lisp Check");
            buttonLisp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    LISPString = tfCount.getText();
                    LISPLabel.setText("Result:" + BalLISP(LISPString));

                }
            });


        //csvFunction added================================================================
        JButton button = new JButton("CSV Function");
        button.setPreferredSize(new Dimension(100,50));
        JLabel label = new JLabel("CSV ungenerated");
        label.setPreferredSize(new Dimension(500, 50));
        button.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    csvEXP();
                    label.setText("CSV generated and Sorted");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }));

///setting the UI
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(200,200,100,200));
        panel.setLayout(new GridLayout(0,1));
        //adding button
        panel.add(tfCount);//super adds text box
        panel.add(buttonLisp);
        panel.add(LISPLabel);

        panel.add(button);
        panel.add(label);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Homework");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String args[]) throws IOException {

        new MainProg();

//        LISPEXP();
//        csvEXP();



    }//end main




}//end allclass
