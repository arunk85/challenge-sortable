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
 * This class holds all the families and models without families
 * belonging to a particular manufacturer.
 */

public class ManufacturerMapping {
    private Map<String, ModelMapping> _productModels;
    private Map<String, FamilyMapping> _pdtFamily;

    public ManufacturerMapping(){
        _productModels = new HashMap<>();
        _pdtFamily = new HashMap<>();
    }

    public void add(NormPdtEntry entry){
        if(entry.getFamily() == null){
            if(_productModels.containsKey(entry.getModel())){
                _productModels.get(entry.getModel()).add(entry);
            } else {
                ModelMapping pm = new ModelMapping();
                pm.add(entry);
                _productModels.put(entry.getModel(), pm);
            }
        } else {
            if(_pdtFamily.containsKey(entry.getFamily())){
                _pdtFamily.get(entry.getFamily()).add(entry);
            } else {
                FamilyMapping pf = new FamilyMapping();
                pf.add(entry);
                _pdtFamily.put(entry.getFamily(), pf);
            }
        }
    }

    public List<ProductEntry> match(NormListing listing){
        List<ProductEntry> matches = new ArrayList<>();

        for(Map.Entry<String, FamilyMapping> productFamilyEntry : _pdtFamily.entrySet()){
            if(MatchUtils.matchFamily(listing, productFamilyEntry.getKey())){
                matches.addAll(productFamilyEntry.getValue().match(listing));
            }
        }

        for(Map.Entry<String, ModelMapping> productModelEntry : _productModels.entrySet()){
            if(MatchUtils.matchModel(listing, productModelEntry.getKey())){
                 matches.addAll(productModelEntry.getValue().returnMatches());
            }
        }

        /* The listing is matched with all models if it did not match with any family or
        * any model without family. This will capture listings which match to a model
        * which has a family in the products.txt but does not have in listing.*/
        if(matches.size() == 0){
            for(Map.Entry<String, FamilyMapping> productFamilyEntry : _pdtFamily.entrySet()) {
                matches.addAll(productFamilyEntry.getValue().match(listing));
            }
        }

        return matches;
    }
}


