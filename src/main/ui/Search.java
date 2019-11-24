package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Search {
    private static Amenity amenity = new Amenity();
    private static User user;
    private static UBC ubc;

    // EFFECTS: create required fake location data
    private static ArrayList<Location> setFakeLocations() {
        ArrayList<Location> result = new ArrayList<Location>();
        ArrayList<Amenity> amenities = setFakeAmenities();
        Location fakeLocation1 = new Location();
        Location fakeLocation2 = new Location();
        Location fakeLocation3 = new Location();
        fakeLocation1.setLocationName("fake l1");
        fakeLocation2.setLocationName("fake l2");
        fakeLocation3.setLocationName("fake l3");
        for (int i = 0; i < amenities.size(); i++) {
            fakeLocation1.addAmenity(amenities.get(i));
            fakeLocation2.addAmenity(amenities.get(i));
            fakeLocation3.addAmenity(amenities.get(i));
        }
        result.add(fakeLocation1);
        result.add(fakeLocation2);
        result.add(fakeLocation3);
        return result;
    }

    // EFFECTS: create required fake amenity data and return list of amenity
    private static ArrayList<Amenity> setFakeAmenities() {
        ArrayList<Amenity> result = new ArrayList<Amenity>();
        Amenity fakeAmenity1 = new Amenity();
        Amenity fakeAmenity2 = new Amenity();
        Amenity fakeAmenity3 = new Amenity();
        fakeAmenity1.setAmenityName("bathroom");
        fakeAmenity2.setAmenityName("water fountain");
        fakeAmenity3.setAmenityName("lounge");
        result.add(fakeAmenity1);
        result.add(fakeAmenity2);
        result.add(fakeAmenity3);
        return result;
    }

    // EFFECTS: create and return building for Search class
    private static Building createBuilding(ArrayList<Location> listOfFakeLocations, String fakeName) {
        Building fakeBuilding = new Building();
        fakeBuilding.setBuildingName(fakeName);
        fakeBuilding.addLocation(listOfFakeLocations.get(0));
        fakeBuilding.addLocation(listOfFakeLocations.get(1));
        fakeBuilding.addLocation(listOfFakeLocations.get(2));
        return fakeBuilding;
    }

    // EFFECTS: create and return UBC data
    private static UBC createUBC() {
        ArrayList<Amenity> listOfAmenities = setFakeAmenities();
        ArrayList<Location> listOfLocations = setFakeLocations();
        Building fakeBuilding1 = createBuilding(listOfLocations, "fake b1");
        Building fakeBuilding2 = createBuilding(listOfLocations, "fake b2");
        UBC ubc = new UBC();
        ubc.addBuilding(fakeBuilding1);
        ubc.addBuilding(fakeBuilding2);
        return ubc;
    }

    public static void main(String[] args) {
        // Create all the fake data required
        ubc = createUBC();

        // Receive user name input
        user = userNameInput();

        // Receive amenity request from user
        ArrayList<Building> validBuildings = validBuildingsOutput(user.getName(), ubc, amenity);

        // Check if building searched for is a building with the amenity
        Building searchedBuilding = searchBuilding(validBuildings, ubc);

        // Output the final result of the search
        outputAmenitySearchResult(searchedBuilding, amenity);
    }

    // EFFECTS: helper for receiving user name input
    private static User userNameInput() {
        System.out.println("Welcome to Where@UBC! Please input your name below to begin:");
        User user = new User();
        Scanner userScanner = new Scanner(System.in);
        String userName = userScanner.nextLine();
        user.setUserName(userName);
        return user;
    }

    // MODIFIES: Amenity, this
    // EFFECTS: output the valid buildings that have the required amenity
    private static ArrayList<Building> validBuildingsOutput(String userName, UBC ubc, Amenity amenity) {
        System.out.println("Thanks " + userName + "! What can I find for you today?");
        Scanner amenityScanner = new Scanner(System.in);
        String amenityName = amenityScanner.nextLine();
        amenity.setAmenityName(amenityName);
        // check if amenity exists at UBC. If NOT, try again until amenity found
        ArrayList<Building> validBuildings = tryAgainUntilAmenityFound(amenityName, ubc, amenity, amenityScanner);
        return validBuildings;
    }

    // EFFECTS: return the building to search on
    private static Building searchBuilding(ArrayList<Building> validBuildings, UBC ubc) {
        // Search based on chosen building
        System.out.println("Which building would you like to search?");
        Building building = new Building();
        Scanner buildingScanner = new Scanner(System.in);
        String buildingName = buildingScanner.nextLine();
        building.setBuildingName(buildingName);
        // if invalid building given:
        while (!checkForBuildingInListOfBuildings(validBuildings, buildingName)) {
            System.out.println("I'm sorry, that is not valid. Please try another building search based on below:");
            for (int i = 0; i < validBuildings.size(); i++) {
                System.out.println(validBuildings.get(i).getBuildingName());
            }
            buildingName = buildingScanner.nextLine();
        }
        building = ubc.returnBuildingWithCorrectName(buildingName);
        return building;
    }

    // EFFECTS: return the final search result of amenity from the search
    private static void outputAmenitySearchResult(Building building, Amenity amenity) {
        // if valid building given:
        if (building.checkForAmenityInClass(amenity.getAmenityName())) {
            System.out.println("You can find the " + amenity.getAmenityName()
                    + " at the following locations within " + building.getBuildingName() + ":");
            ArrayList<Location> resultList = building.returnLocationsFromBuilding(amenity.getAmenityName());
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i).getLocationName());
            }
        }
    }

    // EFFECTS: return a list of buildings which holds the searched amenity
    private static ArrayList<Building> tryAgainUntilAmenityFound(String name, UBC ubc, Amenity amenity, Scanner as) {
        ArrayList<Building> validBuildings = new ArrayList<Building>();
        while (!ubc.checkForAmenityInClass(name)) {
            System.out.println("I'm sorry, that amenity does not exist @UBC. Anything else?");
            name = as.nextLine();
            amenity.setAmenityName(name);
        }
        // if amenity exists, return list of buildings at UBC with the amenity
        System.out.println("You can find the " + name + " in the following buildings:");
        validBuildings = ubc.returnBuildingsWithAmenity(name);
        for (int i = 0; i < validBuildings.size(); i++) {
            System.out.println(validBuildings.get(i).getBuildingName());
        }
        return validBuildings;
    }

    // EFFECTS: return true if a building exists within list of buildings
    public static boolean checkForBuildingInListOfBuildings(ArrayList<Building> validBuildings, String name) {
        boolean result = false;
        for (int i = 0; i < validBuildings.size(); i++) {
            if (validBuildings.get(i).getBuildingName().equals(name)) {
                result = true;
            }
        }
        return result;
    }
}
