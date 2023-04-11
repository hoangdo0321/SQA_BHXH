package com.example.socialinsurance.service;

import com.example.socialinsurance.dto.impl.UserInfoDTO;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class UserComparator implements Comparator<UserInfoDTO> {
    private Collator collator = Collator.getInstance(new Locale("vi", "VN"));
    @Override
    public int compare(UserInfoDTO o1, UserInfoDTO o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();
        String lastName1 = name1.substring(name1.lastIndexOf(" ") + 1);
        String lastName2 = name2.substring(name2.lastIndexOf(" ") + 1);
        int compare = collator.compare(lastName1, lastName2);
        if(compare == 0){
            String firstName1 = name1.substring(0, name1.indexOf(" "));
            String firstName2 = name2.substring(0, name2.indexOf(" "));
            return collator.compare(firstName1, firstName2);
        }else{
            return compare;
        }
    }


}
