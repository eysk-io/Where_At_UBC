package model;

import interfaces.Places;

public class Amenity implements Places {
    private String name;

    // EFFECTS: constructs the amenity, setting its name as an empty string
    public Amenity() {
        name = "";
    }

    // EFFECTS: return the name of the amenity
    public String getAmenityName() {
        return this.name;
    }

    // EFFECTS: set the name of the amenity
    public boolean setAmenityName(String name) {
        this.name = name;
        return true;
    }

    // EFFECTS: return true if the given amenity name equals the amenity's name
    @Override
    public boolean checkForAmenityInClass(String amenity) {
        boolean result = false;
        if (this.getAmenityName().equals(amenity)) {
            result = true;
        }
        return result;
    }
}
