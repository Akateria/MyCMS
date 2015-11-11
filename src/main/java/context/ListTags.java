package context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTags {

    public ArrayList<Integer> getArrayFromString(String tagsId){
        List<String> items = Arrays.asList(tagsId.split(","));
        ArrayList<Integer> arrayId = new ArrayList<>();
        for (String id: items){
            arrayId.add(new Integer(id));
        }
        return arrayId;
    }

}
