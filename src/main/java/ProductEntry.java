import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by akrishnakumar on 7/9/17.
 * {
 "product_name": String // A unique id for the product
 "manufacturer": String
 "family": String // optional grouping of products
 "model": String
 "announced-date": String // ISO-8601 formatted date string, e.g. 2011-04-28T19:00:00.000-05:00
 }
 */

public class ProductEntry {
    private String _name;
    private String _manufacturer;
    private String _family;
    private String _model;
    private String _announcedDate;

    @JsonCreator
    public ProductEntry(@JsonProperty("product_name") String name,
                   @JsonProperty("manufacturer") String manufacturer,
                   @JsonProperty("family") String family,
                   @JsonProperty("model") String model,
                   @JsonProperty("announced-date") String announcedDate){
        _name = name;
        _manufacturer = manufacturer;
        _family = family;
        _model = model;
        _announcedDate = announcedDate;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getManufacturer() {
        return _manufacturer;
    }

    public void setManufacturer(String _manufacturer) {
        this._manufacturer = _manufacturer;
    }

    public String getModel() {
        return _model;
    }

    public void setModel(String _model) {
        this._model = _model;
    }

    public String getFamily() {
        return _family;
    }

    public void setFamily(String _family) {
        this._family = _family;
    }

    public String getAnnouncedDate() {
        return _announcedDate;
    }

    public void setAnnouncedDate(String _announcedDate) {
        this._announcedDate = _announcedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntry product = (ProductEntry) o;
        return com.google.common.base.Objects.equal(_name, product._name) &&
                com.google.common.base.Objects.equal(_manufacturer, product._manufacturer) &&
                com.google.common.base.Objects.equal(_family, product._family) &&
                com.google.common.base.Objects.equal(_model, product._model) &&
                com.google.common.base.Objects.equal(_announcedDate, product._announcedDate);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(_name, _manufacturer, _family, _model, _announcedDate);
    }

    @Override
    public String toString() {
        return "ProductEntry{" +
                "_name='" + _name + '\'' +
                ", _manufacturer='" + _manufacturer + '\'' +
                ", _family='" + _family + '\'' +
                ", _model='" + _model + '\'' +
                ", _announcedDate='" + _announcedDate + '\'' +
                '}';
    }
}
