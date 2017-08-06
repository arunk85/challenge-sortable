package com.arun.stbl.chlng.mappings;

import com.arun.stbl.chlng.execute.NormListing;
import com.arun.stbl.chlng.execute.NormPdtEntry;
import com.arun.stbl.chlng.jsonmappings.ProductEntry;
import com.arun.stbl.chlng.utils.MatchUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class builds the product tree which can be used to match a listing.
 */
public class ProductTree {

    private Map<String, ManufacturerMapping> _manufacturers;

    public ProductTree(){
        _manufacturers = new HashMap<>();
    }

    /**
     *
     * @param l The listing which has to be matched.
     * @return A list of products to which the listing matched.
     */
    public List<ProductEntry> match(NormListing l) {
        List<ProductEntry> matches = new ArrayList<>();
        for (Map.Entry<String, ManufacturerMapping> manEntry : _manufacturers.entrySet()) {
            if (MatchUtils.matchManufacturer(l, manEntry.getKey())) {
                matches.addAll(manEntry.getValue().match(l));
            }
        }
        return matches;
    }

    /**
     *
     * @param productEntries List of ProductEntry read from the input file
     * @return Product tree which captures all the manufacturer, family, model mapping.
     */
    public static ProductTree buildProductTree(List<ProductEntry> productEntries){
        ProductTree ptree = new ProductTree();
        for(ProductEntry entry : productEntries){
            NormPdtEntry normPdtEntry = new NormPdtEntry(entry);
            if(ptree._manufacturers.containsKey(normPdtEntry.getManufacturer())){
                ptree._manufacturers.get(normPdtEntry.getManufacturer()).add(normPdtEntry);
            } else {
                ManufacturerMapping m = new ManufacturerMapping();
                m.add(normPdtEntry);
                ptree._manufacturers.put(normPdtEntry.getManufacturer(), m);
            }
        }
        return ptree;
    }
}
