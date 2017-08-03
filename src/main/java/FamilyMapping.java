import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyMapping {
    private Map<String, ModelMapping> _models;

    public FamilyMapping(){
        _models = new HashMap<>();
    }

    public void add(ProductEntry entry){
        if(_models.containsKey(entry.getModel())){
            _models.get(entry.getModel()).add(entry);
        } else {
            ModelMapping pm = new ModelMapping();
            pm.add(entry);
            _models.put(entry.getModel(), pm);
        }
    }

    public void match(Listing listing, List<ProductEntry> result){
        for(Map.Entry<String, ModelMapping> productModelEntry : _models.entrySet()){
            if(listing.getTokens().containsAll(Arrays.asList(productModelEntry.getKey().split(",|\\s|-")))
                    || listing.getTokens().contains(productModelEntry.getKey().replaceAll(" ",""))){
                productModelEntry.getValue().addResults(listing, result);
            }
        }
    }
}
