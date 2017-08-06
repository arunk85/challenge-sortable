package com.arun.stbl.chlng.mappings;

import com.arun.stbl.chlng.execute.NormPdtEntry;
import com.arun.stbl.chlng.jsonmappings.ProductEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maintains the mapping of the ProductEntry which contributed to this "leaf"
 * in the product tree.
 */
public class ModelMapping {
    private Map<String, NormPdtEntry> products;
    private List<NormPdtEntry> _pdtList;

    public ModelMapping() {
        _pdtList = new ArrayList<>();
        products = new HashMap<>();
    }

    public void add(NormPdtEntry entry) {
        _pdtList.add(entry);
        products.put(entry.getAnnouncedDate(), entry);
    }

    /**
     * Any listing which reached here essentially matched this product entry.
     * @return
     */
    public List<ProductEntry> returnMatches() {
        List<ProductEntry> result = new ArrayList<>();
        int longModelLen = -1;
        NormPdtEntry tmp = null;
        for(NormPdtEntry entry : _pdtList){
            if(entry.getModel().length() > longModelLen){
                longModelLen = entry.getModel().length();
                tmp = entry;
            }
        }
        result.add(tmp.getPdtEntry());
        return result;
    }
}
