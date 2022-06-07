package CSVReadWrite;

import Objects.UserObjects;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class ReadingCSV {

        public Scanner scan = new Scanner(System.in);

        /**
         * Per the opencsv website this file helps tranfer the csv to javabean object aka
         * classes that encapsulate one or more objects into a single standardized object (the bean).
         *  Assuming there are no Heders in the file I will use the beans as shown.
         *
         *  information taken from http://opencsv.sourceforge.net/
         */

        public List<UserObjects> ReadingToFile() throws FileNotFoundException {
            String filename = "InitFile";
            //reader
            FileReader reader = new FileReader("D:\\OneDrive\\OneDrive - Gulf Coast University - FGCU\\OneDrive - Florida Gulf Coast University\\resume\\Avility test\\Availity_Backend\\src\\main\\java\\"+filename+".csv");
            CsvToBean<UserObjects> FIleToBean = new CsvToBeanBuilder<UserObjects>(reader)
                    .withType(UserObjects.class).withIgnoreLeadingWhiteSpace(true).build();

            return FIleToBean.parse();
        }
    
}
