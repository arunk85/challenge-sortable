package com.arun.stbl.chlng.execute;

import com.arun.stbl.chlng.jsonmappings.Listing;
import com.arun.stbl.chlng.jsonmappings.MatchResult;
import com.arun.stbl.chlng.jsonmappings.ProductEntry;
import com.arun.stbl.chlng.mappings.ProductTree;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This main class reads input files, constructs JSON objects corresponding
 * to product and listing entries. It then uses a product tree to match
 * the listings and print the output.
 */
public class ExecuteSortableChallenge {

    private List<ProductEntry> productEntries;
    private List<Listing> listings;
    private Map<String, MatchResult> finalResults;
    public final ObjectMapper mapper = new ObjectMapper();

    public ExecuteSortableChallenge(){
        mapper.registerModule(new Jdk8Module());
        productEntries = new ArrayList<>();
        listings = new ArrayList<>();
        finalResults = new HashMap<>();
    }

    public static void main(String args[]){
        String listingFile = "";
        String productsFile = "";

        if(args.length == 2){
            listingFile = args[0];
            productsFile = args[1];
        } else {
            System.out.println("Input files not provided.");
        }

        ExecuteSortableChallenge challenge = new ExecuteSortableChallenge();
        try(BufferedReader brPdts = Files.newBufferedReader(Paths.get(productsFile));
            BufferedReader brLst = Files.newBufferedReader(Paths.get(listingFile));
            BufferedWriter brOutMatch = Files.newBufferedWriter(Paths.get("results.txt"))){

            String line = "";

            while((line = brPdts.readLine()) != null){
                ProductEntry entry = challenge.mapper.readValue(line, ProductEntry.class);
                challenge.productEntries.add(entry);
            }

            ProductTree tree = ProductTree.buildProductTree(challenge.productEntries);

            while((line = brLst.readLine()) != null){
                Listing listingEntry = challenge.mapper.readValue(line, Listing.class);
                List<ProductEntry> matches = tree.match(new NormListing(listingEntry));
                if ( matches.size() == 1){
                    if(challenge.finalResults.containsKey(matches.get(0).getName())){
                        challenge.finalResults.get(matches.get(0).getName()).addListing(listingEntry);
                    } else {
                        List<Listing> listings = new ArrayList<>();
                        MatchResult res = new MatchResult(matches.get(0).getName(), listings);
                        res.addListing(listingEntry);
                        challenge.finalResults.put(matches.get(0).getName(), res);
                    }
                }
            }

            for(Map.Entry<String, MatchResult> entry : challenge.finalResults.entrySet()){
                brOutMatch.write(challenge.mapper.writeValueAsString(entry.getValue()) + "\n");
            }

            brOutMatch.flush();

            System.out.println("Matched listings to products and results available in results.txt.");

        } catch (IOException ioe){
            System.out.println(ioe);
        }
    }
}
