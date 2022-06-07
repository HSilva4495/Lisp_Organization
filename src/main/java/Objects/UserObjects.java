package Objects;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * @author Henry Silva Quevedo
 * @since 06/01/2022
 */

/*
parameters
User Id (string)
First Name (string)
Last Name (string)
Version (integer)
Insurance Company (string)
 */

public class UserObjects {
//    private String UserID,FirstName,LastName,InsuranceCompany;
    @CsvBindByName(column = "User ID", required = true)
    @CsvBindByPosition(position = 0)
    private String UserID;
    @CsvBindByName(column = "First Name", required = true)
    @CsvBindByPosition(position = 1)
    private String FirstName;
    @CsvBindByName(column = "Last Name", required = true)
    @CsvBindByPosition(position = 2)
    private String LastName;
    @CsvBindByName(column =  "Version")
    @CsvBindByPosition(position = 3)
    private int Version;
    @CsvBindByName(column = "Insurance Company")
    @CsvBindByPosition(position = 4)
    private String InsuranceCompany;

    //These are the getters and setters of the object
    //UserID
    public String getUserID() {return UserID;}
    public void setUserID(String userID) {UserID = userID;}

    //FirstName
    public String getFirstName() {return FirstName;}
    public void setFirstName(String firstName) {FirstName = firstName;}

    //lastName
    public String getLastName() {return LastName;}
    public void setLastName(String lastName) {LastName = lastName;}

    //version
    public int getVersion() {return Version;}
    public void setVersion(int version) {Version = version;}

    //InsuranceCompany
    public String getInsuranceCompany() {return InsuranceCompany;}
    public void setInsuranceCompany(String insuranceCompany) {InsuranceCompany = insuranceCompany;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append(" ").append(UserID);
        sb.append(",").append(LastName);
        sb.append(",").append(FirstName);
        sb.append(",").append(Version);
        sb.append(",").append(InsuranceCompany);
        sb.append("\n");
        return sb.toString();
    }
}
