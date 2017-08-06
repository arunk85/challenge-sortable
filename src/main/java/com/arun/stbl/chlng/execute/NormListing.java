package com.arun.stbl.chlng.execute;

import com.arun.stbl.chlng.jsonmappings.Listing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A normalized version of Listing class.
 */
public class NormListing {
    private String title;
    private String manufacturer;
    private String currency;
    private String price;
    private Listing listing;
    private Set<String> tokens;

    /**
     * This class is initialized by setting all the values in lower case. It also initializes a set of
     * derived tokens from the listing title.
     * @param l
     */
    public NormListing(Listing l){
        title = l.getTitle().toLowerCase();
        manufacturer = l.getManufacturer().toLowerCase();
        currency = l.getCurrency().toLowerCase();
        price = l.getPrice();
        listing = l;
        Set<String> titleTokens = new HashSet<>();
        titleTokens.addAll(Arrays.asList(title.split("\\s")));
        Set<String> derivedTokens = new HashSet<>();
        for(String token : titleTokens){
            derivedTokens.add(token.replaceAll("-|/|,", ""));
            derivedTokens.addAll(Arrays.asList(token.split("-|/|,")));
        }
        tokens = new HashSet<>();
        tokens.addAll(titleTokens);
        tokens.addAll(derivedTokens);
    }

    public String getTitle() {
        return title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPrice() {
        return price;
    }

    public Listing getListing() {
        return listing;
    }

    public Set<String> getTokens() {
        return tokens;
    }
}

