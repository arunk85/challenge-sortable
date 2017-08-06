package com.arun.stbl.chlng.jsonmappings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
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

    public  Listing(@JsonProperty ("title") String title,
                    @JsonProperty ("manufacturer") String manufacturer,
                    @JsonProperty ("currency") String currency,
                    @JsonProperty ("price") String price) {
        _title = title;
        _manufacturer = manufacturer;
        _currency = currency;
        _price = price;
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
