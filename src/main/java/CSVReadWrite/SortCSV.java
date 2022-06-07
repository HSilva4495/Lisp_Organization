package CSVReadWrite;


import Objects.UserObjects;

import java.util.*;
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

    //Separate enrollees by insurance company in its own file.
    public Map<String, UserObjects> CompanyToUser(UserObjects UserOBJList, Map<String, UserObjects> userHashMap) {
        //key to validate the users to the company
        //using UserID instead of name since I assume it's unique to account
        String key = UserOBJList.getUserID() + UserOBJList.getInsuranceCompany();

        //users object for latest version
        UserObjects UserWLatest = userHashMap.get(key);
        //if User latest is present and < than reg object then the user is replaced
        if (UserWLatest != null && UserWLatest.getVersion() < UserOBJList.getVersion()) {
            userHashMap.replace(key, UserOBJList);
        } else {
            userHashMap.put(key, UserOBJList);
        }
        return userHashMap;
    }

    public List<String> SRTByInsurance(List<UserObjects> UserOBJList, List<String> compList) {
        for (UserObjects user : UserOBJList)
            if (!(compList.contains(user.getInsuranceCompany())))
                compList.add(user.getInsuranceCompany());
        compList = compList.stream().sorted().collect(Collectors.toList());
        return compList;
    }

    public List<UserObjects> cleanList(List<UserObjects> OBJList) {
        OBJList.removeIf(Objects::isNull);
        return OBJList;
    }



    
}
