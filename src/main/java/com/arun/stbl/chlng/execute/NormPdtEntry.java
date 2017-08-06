package com.arun.stbl.chlng.execute;

import com.arun.stbl.chlng.jsonmappings.ProductEntry;

/**
 * A normalized version of ProductEntry
 */
public class NormPdtEntry {
    private String name;
    private String manufacturer;
    private String family;
    private String model;
    private String announcedDate;
    private ProductEntry pdtEntry;

    /**
     * Initializes all values to the lowercase form of ProductEntry.
     * @param pdtEntry
     */
    public NormPdtEntry(ProductEntry pdtEntry){
        this.pdtEntry = pdtEntry;
        name = pdtEntry.getName().toLowerCase().trim();
        manufacturer = pdtEntry.getManufacturer().toLowerCase().trim();
        family = pdtEntry.getFamily()!= null ? pdtEntry.getFamily().toLowerCase().trim() : null;
        model = pdtEntry.getModel().toLowerCase().trim();
        announcedDate = pdtEntry.getAnnouncedDate().trim();
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getFamily() {
        return family;
    }

    public String getModel() {
        return model;
    }

    public String getAnnouncedDate() {
        return announcedDate;
    }

    public ProductEntry getPdtEntry() {
        return pdtEntry;
    }



}
