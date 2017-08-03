import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * {
 "product_name": String
 "listings": Array[Listing]
 }
 */
public class MatchResult {
    private String product_name;
    private List<Listing> listings;

    public MatchResult(@JsonProperty("product_name") String productName,
                       @JsonProperty ("listings") List<Listing> ls){
        product_name = productName;
        listings = ls;
    }
    public String getProduct_name() {
        return product_name;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void addListing(Listing l){
        listings.add(l);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchResult that = (MatchResult) o;
        return Objects.equal(product_name, that.product_name) &&
                Objects.equal(listings, that.listings);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(product_name, listings);
    }
}
