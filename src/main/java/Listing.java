import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by akrishnakumar on 7/9/17.
 * {
 "title": String // description of product for sale
 "manufacturer": String // who manufactures the product for sale
 "currency": String // currency code, e.g. USD, CAD, GBP, etc.
 "price": String // price, e.g. 19.99, 100.00
 }
 */
public class Listing {
    private String _title;
    private String _manufacturer;
    private String _currency;
    private String _price;
    @JsonIgnore
    private Set<String> tokens;

    public  Listing(@JsonProperty ("title") String title,
                    @JsonProperty ("manufacturer") String manufacturer,
                    @JsonProperty ("currency") String currency,
                    @JsonProperty ("price") String price) {
        _title = title;
        _manufacturer = manufacturer;
        _currency = currency;
        _price = price;
        Set<String> titleTokens = new HashSet<>();
        titleTokens.addAll(Arrays.asList(_title.split("\\s")));
        Set<String> derivedTokens = new HashSet<>();
        for(String token : titleTokens){
            derivedTokens.add(token.replaceAll("-|/|,", ""));
            derivedTokens.addAll(Arrays.asList(token.split("-|/|,")));
            derivedTokens.add(token.trim().toLowerCase());
            derivedTokens.add(token.trim().toUpperCase());
        }
        tokens = new HashSet<>();
        tokens.addAll(titleTokens);
        tokens.addAll(derivedTokens);
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public String getManufacturer() {
        return _manufacturer;
    }

    public void setManufacturer(String _manufacturer) {
        this._manufacturer = _manufacturer;
    }

    public String getCurrency() {
        return _currency;
    }

    public void setCurrency(String _currency) {
        this._currency = _currency;
    }

    public String getPrice() {
        return _price;
    }

    public void setPrice(String _price) {
        this._price = _price;
    }

    public Set<String> getTokens(){
        return tokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return Objects.equal(_title, listing._title) &&
                Objects.equal(_manufacturer, listing._manufacturer) &&
                Objects.equal(_currency, listing._currency) &&
                Objects.equal(_price, listing._price);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_title, _manufacturer, _currency, _price);
    }
}
