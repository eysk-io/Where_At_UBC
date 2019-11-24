package model;

import java.util.ArrayList;

public class Building extends Places {
    private String name;
    private ArrayList<Location> locations;

    // EFFECTS: constructs the building, setting its name as blank and setting an empty list of locations
    public Building() {
        name = "";
        locations = new ArrayList<Location>();
    }

    // EFFECTS: return the name of the building
    public String getBuildingName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: set the name of the building
    public void setBuildingName(String name) {
        this.name = name;
    }

    // EFFECTS: return the list of locations in the building
    public ArrayList<Location> getLocations() {
        return this.locations;
    }

    // MODIFIES: this
    // EFFECTS: add new location to building
    public boolean addLocation(Location location) {
        locations.add(location);
        return true;
    }

    // EFFECTS: return the list of locations for which the amenity exists within the building
    public ArrayList<Location> returnLocationsFromBuilding(String amenity) {
        ArrayList<Location> result = returnLocationsFromListOfLocations(amenity);
        return result;
    }

    // EFFECTS: return the list of locations for which the amenity exists within the list of locations
    public ArrayList<Location> returnLocationsFromListOfLocations(String amenity) {
        ArrayList<Location> result = new ArrayList<Location>();
        if (this.checkForAmenityInListOfLocations(amenity)) {
            for (int i = 0; i < locations.size(); i++) {
                result.add(locations.get(i));
            }
        }
        return result;
    }


    // EFFECTS: return true if the amenity is found inside the building
    @Override
    public boolean checkForAmenityInClass(String amenity) {
        boolean result = false;
        if (checkForAmenityInListOfLocations(amenity)) {
            result = true;
        }
        return result;
    }


    // EFFECTS: return true if the amenity is found inside the list of locations inside the building
    public Boolean checkForAmenityInListOfLocations(String amenity) {
        boolean result = false;
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).checkForAmenityInClass(amenity)) {
                result = true;
            }
        }
        return result;
    }
}
