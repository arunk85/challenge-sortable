import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akrishnakumar on 7/19/17.
 */

public class ManufacturerMapping {
    private Map<String, ModelMapping> _productModels;
    private Map<String, FamilyMapping> _pdtFamily;

    public ManufacturerMapping(){
        _productModels = new HashMap<>();
        _pdtFamily = new HashMap<>();
    }

    public void add(ProductEntry entry){
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

    public void match(Listing listing, List<ProductEntry> result){
        for(Map.Entry<String, ModelMapping> productModelEntry : _productModels.entrySet()){
            if(listing.getTokens().containsAll(Arrays.asList(productModelEntry.getKey().split(",|\\s|-")))
                    || listing.getTokens().contains(productModelEntry.getKey().replaceAll(" ",""))){
                productModelEntry.getValue().addResults(listing, result);
            }
        }

        for(Map.Entry<String, FamilyMapping> productFamilyEntry : _pdtFamily.entrySet()){
            if(listing.getTokens().contains(productFamilyEntry.getKey())){
                productFamilyEntry.getValue().match(listing, result);
            }
        }
    }
}


