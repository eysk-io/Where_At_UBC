package model;

import java.util.ArrayList;

public class Location extends Places {
    private String name;
    private ArrayList<Amenity> amenities;

    // EFFECTS: constructs the location, setting its name as blank and setting an empty list of amenities
    public Location() {
        name = "";
        amenities = new ArrayList<Amenity>();
    }

    // EFFECTS: return the name of the location
    public String getLocationName() {
        return this.name;
    }

    // EFFECTS: set the name of the building
    public void setLocationName(String name) {
        this.name = name;
    }

    // EFFECTS: return the list of amenities within the location
    public ArrayList<Amenity> getAmenities() {
        return this.amenities;
    }

    // MODIFIES: this
    // EFFECTS: add new amenity to location
    public boolean addAmenity(Amenity amenity) {
        amenities.add(amenity);
        return true;
    }

    // EFFECTS: return true if the amenity is found inside of the location
    @Override
    public boolean checkForAmenityInClass(String amenity) {
        boolean result = false;
        if (checkForAmenityInListOfAmenities(amenity)) {
            result = true;
        }
        return result;
    }

    // EFFECTS: return true if the amenity is found inside of the list of amenities
    public boolean checkForAmenityInListOfAmenities(String amenity) {
        boolean result = false;
        for (int i = 0; i < amenities.size(); i++) {
            if (amenities.get(i).checkForAmenityInClass(amenity)) {
                result = true;
            }
        }
        return result;
    }
}
