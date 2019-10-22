package model;

import java.util.ArrayList;

public class UBC extends Places {
    private ArrayList<Building> buildings;

    // EFFECTS: constructs UBC, setting an empty list of buildings on campus
    public UBC() {
        buildings = new ArrayList<Building>();
    }

    // EFFECTS: return the list of buildings at UBC
    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }

    // MODIFIES: this
    // EFFECTS: add new building to UBC
    public boolean addBuilding(Building building) {
        buildings.add(building);
        return true;
    }

    // EFFECTS: return true if the amenity exists at UBC
    @Override
    public boolean checkForAmenityInClass(String amenity) {
        boolean result;
        if (checkForAmenityInListOfBuildings(amenity)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    // EFFECTS: return true if the Amenity exists in one or more of the buildings at UBC
    public boolean checkForAmenityInListOfBuildings(String amenity) {
        boolean result = false;
        for (int i = 0; i < buildings.size(); i++) {
            if (buildings.get(i).checkForAmenityInClass(amenity)) {
                result = true;
            }
        }
        return result;
    }

    // EFFECTS: return the list of buildings in which the amenity exists
    public ArrayList<Building> returnBuildingsWithAmenity(String amenity) {
        ArrayList<Building> result = new ArrayList<Building>();
        for (int i = 0; i < buildings.size(); i++) {
            if (checkForAmenityInListOfBuildings(amenity)) {
                result.add(getBuilding(i));
            }
        }
        return result;
    }

    // EFFECTS: get a building from the list of buildings
    private Building getBuilding(int i) {
        return buildings.get(i);
    }

    // EFFECTS: return the building with the same name as the given name
    public Building returnBuildingWithCorrectName(String name) {
        Building result = new Building();
        for (int i = 0; i < buildings.size(); i++) {
            if (buildings.get(i).getBuildingName().equals(name)) {
                result = buildings.get(i);
            }
        }
        return result;
    }
}
