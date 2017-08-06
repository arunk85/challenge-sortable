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
 * This class holds all the models belonging to a family.
 */
public class FamilyMapping {
    private Map<String, ModelMapping> _models;

    public FamilyMapping(){
        _models = new HashMap<>();
    }

    public void add(NormPdtEntry entry){
        if(_models.containsKey(entry.getModel())){
            _models.get(entry.getModel()).add(entry);
        } else {
            ModelMapping pm = new ModelMapping();
            pm.add(entry);
            _models.put(entry.getModel(), pm);
        }
    }

    public List<ProductEntry> match(NormListing listing) {
        List<ProductEntry> matches = new ArrayList<>();
        String match = "";
        for (Map.Entry<String, ModelMapping> productModelEntry : _models.entrySet()) {
            if (MatchUtils.matchModel(listing, productModelEntry.getKey())) {
                if (productModelEntry.getKey().length() > match.length()) {
                    match = productModelEntry.getKey();
                }
            }
        }
        if(_models.containsKey(match)){
            matches.addAll(_models.get(match).returnMatches());
        }
        return matches;
    }
}
