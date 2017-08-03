import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelMapping {
    private Map<String, ProductEntry> products;
    private List<ProductEntry> _pdtList;

    public ModelMapping() {
        _pdtList = new ArrayList<>();
        products = new HashMap<>();
    }

    public void add(ProductEntry entry) {
        _pdtList.add(entry);
        products.put(entry.getAnnouncedDate(), entry);
    }

    public void addResults(Listing listing, List<ProductEntry> result) {
        int longModelLen = -1;
        ProductEntry tmp = null;
        for(ProductEntry entry : _pdtList){
            if(entry.getModel().length() > longModelLen){
                longModelLen = entry.getModel().length();
                tmp = entry;
            }
        }
        result.add(tmp);
    }
}
