package com.example.socialinsurance.service;

import com.example.socialinsurance.entity.InsuranceDetails;
import com.example.socialinsurance.entity.User;

import java.text.Collator;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;

public class DateComparator implements Comparator<InsuranceDetails> {
    private Collator collator = Collator.getInstance(new Locale("vi", "VN"));

    @Override
    public int compare(InsuranceDetails o1, InsuranceDetails o2) {
        return o1.getExpireDate().compareTo(o2.getExpireDate());
    }
}

//public class DateComparator implements Comparator<LocalDate> {
//    private Collator collator = Collator.getInstance(new Locale("vi", "VN"));
//
//
//
//
//    @Override
//    public int compare(LocalDate o1, LocalDate o2) {
//        return o1.compareTo(o2);
//    }
//}



