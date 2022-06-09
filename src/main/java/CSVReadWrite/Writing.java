package CSVReadWrite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Objects.UserObjects;


public class Writing {

    public static void WNewFile(List<String> CompanyList, List<UserObjects> finalList) throws IOException {
        for(String Company: CompanyList) {
            FileWriter writer = new FileWriter(new File("D:\\OneDrive\\OneDrive - Gulf Coast University - FGCU\\OneDrive - Florida Gulf Coast University\\resume\\Avility test\\Availity_Backend\\src\\main\\java\\NewCSV", Company + ".csv"));
            writer.write("User ID, Last Name,First Name,Version,Insurance Company\n");
            // for every object in list write to a file based on the insurance company
            for (UserObjects user : finalList) {
                try {
                    if (user.getInsuranceCompany().equals(Company)) {
                        writer.write(user.toString());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("File is done");
            writer.close();
        }//end writing

    }
}
