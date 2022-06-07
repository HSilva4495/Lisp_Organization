package CSVRead;


import Objects.UserObjects;

import javax.jws.soap.SOAPBinding;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Parameters:
 * -Separate enrollees by insurance company in its own file.
 * -Sort the contents of each file by last and first name (ascending).
 * -If there are duplicate User Ids for the same Insurance Company, then only the record with the highest version should be included
 */
public class SortCSV {

    //Sort By Last and First Name
    public List<UserObjects> SortLast(List<UserObjects> UserOBJList){

        Comparator<UserObjects> byLastN = Comparator.comparing(UserObjects::getLastName).thenComparing(UserObjects::getLastName);
        Collections.sort(UserOBJList, byLastN);

        return UserOBJList;
    }//end Sort

    public List<String> SRTByInsurance(List<UserObjects> UserOBJList, List<String> companyList) {
        for (UserObjects user : UserOBJList)
            if (!(companyList.contains(user.getInsuranceCompany())))
                companyList.add(user.getInsuranceCompany());
        companyList = companyList.stream().sorted().collect(Collectors.toList());
        return companyList;
    }

    public Map<String, UserObjects> mapCompanyToUser(UserObjects UserOBJList, Map<String, UserObjects> userHashMap) {
        String key = UserOBJList.getUserID() + UserOBJList.getInsuranceCompany();
        UserObjects addUserWithLatestVersion = userHashMap.get(key);
        if (addUserWithLatestVersion != null && addUserWithLatestVersion.getVersion() < UserOBJList.getVersion()) {
            userHashMap.replace(key, UserOBJList);
        } else {
            userHashMap.put(key, UserOBJList);
        }
        return userHashMap;
    }


    
}
