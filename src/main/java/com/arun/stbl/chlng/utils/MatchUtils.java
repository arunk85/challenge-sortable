package com.arun.stbl.chlng.utils;

import com.arun.stbl.chlng.execute.NormListing;

import java.util.Arrays;

/**
 * A utilty class which has methods to match the manufacturer, family and models.
 */
public class MatchUtils {

    public static boolean matchManufacturer(NormListing l, String mft){
        boolean matched = false;
        if((l.getTokens().contains(mft)
                || l.getTokens().contains(mft))
                && l.getManufacturer().contains(mft)) {
            matched = true;
        }
        return matched;
    }

    public static boolean matchModel(NormListing l, String mdl) {
        boolean matched = false;
        if (l.getTokens().contains(mdl)
                || l.getTokens().containsAll(Arrays.asList(mdl.split(",|\\s|-")))
                || l.getTokens().contains(mdl.replaceAll(" ",""))
                || l.getTokens().contains(mdl.replaceAll("-",""))){
            matched = true;
        }
        return matched;
    }

    public static boolean matchFamily(NormListing l, String familyName){
        boolean matched = false;
        if(l.getTokens().contains(familyName)){
            matched = true;
        }
        return matched;
    }
}
