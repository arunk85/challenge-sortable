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
 * Created by akrishnakumar on 7/9/17.
 */
public class ExecuteSortableChallenge {

    private List<ProductEntry> productEntries;
    private List<Listing> listings;
    private Map<String, MatchResult> _finalResults;
    public final ObjectMapper mapper = new ObjectMapper();


    public ExecuteSortableChallenge(){
        mapper.registerModule(new Jdk8Module());
        productEntries = new ArrayList<>();
        listings = new ArrayList<>();
        _finalResults = new HashMap<>();
    }


    public static void main(String args[]){
        String[] test = "abc ghd-knm".split(",|\\s|-");
        ExecuteSortableChallenge challenge = new ExecuteSortableChallenge();
        try(BufferedReader brPdts = Files.newBufferedReader(Paths.get("src/main/resources/products.txt"));
            BufferedReader brLst = Files.newBufferedReader(Paths.get("src/main/resources/testPdt.txt"));
            BufferedWriter brOutMatch = Files.newBufferedWriter(Paths.get("src/main/resources/outputMatch.txt"));
            BufferedWriter brOutNoMatch = Files.newBufferedWriter(Paths.get("src/main/resources/outputNoMatch.txt"));
            BufferedWriter brOutMultMatch = Files.newBufferedWriter(Paths.get("src/main/resources/outputMultMatch.txt"))){
            String line = "";

            while((line = brPdts.readLine()) != null){
                ProductEntry entry = challenge.mapper.readValue(line, ProductEntry.class);
                challenge.productEntries.add(entry);
            }

            ProductTree tree = ProductTree.buildProductTree(challenge.productEntries);

            while((line = brLst.readLine()) != null){
                Listing listingEntry = challenge.mapper.readValue(line, Listing.class);
                List<ProductEntry> matches = tree.match(listingEntry);
                if(matches.size() == 0){
                    brOutNoMatch.write(challenge.mapper.writeValueAsString(listingEntry) + "\n");
                    brOutNoMatch.flush();
                } else if ( matches.size() == 1){
                    if(challenge._finalResults.containsKey(matches.get(0).getName())){
                        challenge._finalResults.get(matches.get(0).getName()).addListing(listingEntry);
                    } else {
                        List<Listing> listings = new ArrayList<>();
                        MatchResult res = new MatchResult(matches.get(0).getName(), listings);
                        res.addListing(listingEntry);
                        challenge._finalResults.put(matches.get(0).getName(), res);
                    }
                } else {
                    brOutMultMatch.write(listingEntry.getTitle() + "\n");
                    brOutMultMatch.write(challenge.mapper.writeValueAsString(matches) + "\n");
                    brOutMultMatch.write("" + "\n");
                    brOutMultMatch.flush();
                }

            }
            for(Map.Entry<String, MatchResult> entry : challenge._finalResults.entrySet()){
                brOutMatch.write(challenge.mapper.writeValueAsString(entry.getValue()) + "\n");
            }

            brOutMatch.flush();

        } catch (IOException ioe){
            System.out.println(ioe);
        }
        System.out.println("Attempting sortable challenge");
    }
}
