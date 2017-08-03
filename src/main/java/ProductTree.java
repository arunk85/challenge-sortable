import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akrishnakumar on 7/12/17.
 */
public class ProductTree {

    private Map<String, ManufacturerMapping> _manufacturers;

    public ProductTree(){
        _manufacturers = new HashMap<>();
    }

    public List<ProductEntry> match(Listing l){
        List<ProductEntry> result = new ArrayList<>();
        for(Map.Entry<String, ManufacturerMapping> manufacturerEntry : _manufacturers.entrySet()){
            if((l.getTokens().contains(manufacturerEntry.getKey())
                    || l.getTokens().contains(manufacturerEntry.getKey().toLowerCase()))
                    && l.getManufacturer().contains(manufacturerEntry.getKey())){
                manufacturerEntry.getValue().match(l, result);
            }
        }
        return result;
    }

    public static ProductTree buildProductTree(List<ProductEntry> productEntries){
        ProductTree ptree = new ProductTree();
        for(ProductEntry entry : productEntries){
            if(ptree._manufacturers.containsKey(entry.getManufacturer())){
                ptree._manufacturers.get(entry.getManufacturer()).add(entry);
            } else {
                ManufacturerMapping m = new ManufacturerMapping();
                m.add(entry);
                ptree._manufacturers.put(entry.getManufacturer(), m);
            }
        }
        return ptree;
    }
}
