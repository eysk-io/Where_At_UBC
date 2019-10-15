package ui;

import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Search {
    private static Amenity amenity = new Amenity();

    public static void main(String[] args) throws IOException {
        User user = createUser();
        List<String> lines = Files.readAllLines(Paths.get("./data/userInfo.txt"));
        PrintWriter writer = new PrintWriter("./data/userInfo.txt","UTF-8");

        // START OF fakeBuilding/Location/Amenity
        ArrayList<Location> listOfFakeLocations = setFakeLocations();
        String fakeName = "fake b1";
        Building fakeBuilding1 = createBuilding(listOfFakeLocations, fakeName);
        fakeName = "fake b2";
        Building fakeBuilding2 = createBuilding(listOfFakeLocations, fakeName);

        UBC ubc = new UBC();
        ubc.addBuilding(fakeBuilding1);
        ubc.addBuilding(fakeBuilding2);

        ArrayList<Building> validBuildings = createValidBuildings(user.getName(), ubc, amenity);
        Building building = createBuildingSearch(validBuildings, ubc);
        lines.add(building.getBuildingName());

        finishAmenitySearch(building, amenity);
        outPutToFile(lines, writer);
    }

    private static void outPutToFile(List<String> lines, PrintWriter writer) {
        lines.add(amenity.getAmenityName());
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }

    // EFFECTS: Create a fake building for Search class
    private static Building createBuilding(ArrayList<Location> listOfFakeLocations, String fakeName) {
        Building fakeBuilding = new Building();
        fakeBuilding.setBuildingName(fakeName);
        fakeBuilding.addLocation(listOfFakeLocations.get(0));
        fakeBuilding.addLocation(listOfFakeLocations.get(1));
        fakeBuilding.addLocation(listOfFakeLocations.get(2));
        return fakeBuilding;
    }

    private static User createUser() {
        System.out.println("Welcome to Where@UBC! Please input your name below to begin:");
        User user = new User();
        Scanner userScanner = new Scanner(System.in);
        String userName = userScanner.nextLine();
        user.setUserName(userName);
        return user;
    }

    private static ArrayList<Building> createValidBuildings(String userName, UBC ubc, Amenity amenity) {
        System.out.println("Thanks " + userName + "! What can I find for you today?");
        Scanner amenityScanner = new Scanner(System.in);
        String amenityName = amenityScanner.nextLine();
        amenity.setAmenityName(amenityName);
        // check if amenity exists at UBC. If NOT, try again until amenity found

        ArrayList<Building> validBuildings = tryAgainUntilAmenityFound(amenityName, ubc, amenity, amenityScanner);
        return validBuildings;
    }

    private static Building createBuildingSearch(ArrayList<Building> validBuildings, UBC ubc) {
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

    private static void finishAmenitySearch(Building building, Amenity amenity) {
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

    private static ArrayList<Building> tryAgainUntilAmenityFound(String name, UBC ubc, Amenity amenity, Scanner as) {
        ArrayList<Building> validBuildings = new ArrayList<Building>();
        while (!ubc.checkForAmenityInClass(name)) {
            System.out.println("I'm sorry, that amenity does not exist @UBC. Anything else?");
            name = as.nextLine();
            amenity.setAmenityName(name);
        }

        // if amenity exists, return list of buildings at UBC with the amenities
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

    public static ArrayList<Amenity> setFakeAmenities() {
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

    public static ArrayList<Location> setFakeLocations() {
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
}
