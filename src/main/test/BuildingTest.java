package test;

import model.Amenity;
import model.Building;
import model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BuildingTest {
    Building testBuilding;
    Building fakeBuilding;

    @BeforeEach
    public void setup() {
        testBuilding = new Building();
        ArrayList<Location> listOfFakeLocations = setFakeLocations();
        fakeBuilding = new Building();
        fakeBuilding.setBuildingName("fake b1");
        fakeBuilding.addLocation(listOfFakeLocations.get(0));
        fakeBuilding.addLocation(listOfFakeLocations.get(1));
        fakeBuilding.addLocation(listOfFakeLocations.get(2));
    }

    public ArrayList<Amenity> setFakeAmenities() {
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

    public ArrayList<Location> setFakeLocations() {
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

    @Test
    public void testGetBuildingName() {
        assertEquals(fakeBuilding.getBuildingName(), "fake b1");
    }
    //addLocation(Location location)

    @Test
    public void testAddLocation() {
        Location testLocation = new Location();
        assertEquals(fakeBuilding.getLocations().size(), 3);
        assertTrue(fakeBuilding.addLocation(testLocation));
        assertEquals(fakeBuilding.getLocations().size(), 4);
    }

    //checkForAmenityInBuilding(String amenityName)
    // amenity does NOT exist inside building
    @Test
    public void testCheckForAmenityInBuildingDoesNotExist() {
        assertEquals(fakeBuilding.getLocations().size(), 3);
        String testAmenityName = "test amenity";
        assertFalse(fakeBuilding.checkForAmenityInBuilding(testAmenityName));
    }

    // amenity does exist inside building
    @Test
    public void testCheckForAmenityInBuildingDoesExist() {
        assertEquals(fakeBuilding.getLocations().size(), 3);
        String testAmenityName = "lounge";
        assertTrue(fakeBuilding.checkForAmenityInBuilding(testAmenityName));
    }

    // checkForAmenityInListOfLocations(String amenity)
    // empty
    @Test
    public void testCheckForAmenityInListOfLocationsEmpty() {
        assertEquals(testBuilding.getLocations().size(), 0);
        String testAmenityName = "test amenity";
        assertFalse(testBuilding.checkForAmenityInListOfLocations(testAmenityName));
    }

    // nonempty not found
    @Test
    public void testCheckForAmenityInListOfLocationsNotEmptyNotFound() {
        assertEquals(fakeBuilding.getLocations().size(), 3);
        String testAmenityName = "test amenity";
        assertFalse(fakeBuilding.checkForAmenityInListOfLocations(testAmenityName));
    }

    // nonempty found
    @Test
    public void testCheckForAmenityInListOfLocationsNotEmptyFound() {
        assertEquals(fakeBuilding.getLocations().size(), 3);
        String testAmenityName = "lounge";
        assertTrue(fakeBuilding.checkForAmenityInListOfLocations(testAmenityName));
    }

    //returnLocationsFromBuilding
    // EFFECTS: return the list of locations for which the amenity exists within the building
    @Test
    public void testReturnLocationsFromBuildingEmptyListOfLocations() {
        assertEquals(testBuilding.getLocations().size(), 0);
        assertEquals(testBuilding.returnLocationsFromBuilding("lounge").size(), 0);
    }

    @Test
    public void testReturnLocationsFromBuildingNonEmptyListOfLocationsAmenityNotFound() {
        assertEquals(fakeBuilding.getLocations().size(), 3);
        assertEquals(fakeBuilding.returnLocationsFromBuilding("test").size(), 0);
    }

    @Test
    public void testReturnLocationsFromBuildingNonEmptyListOfLocationsAmenityFound() {
        assertEquals(fakeBuilding.getLocations().size(), 3);
        assertEquals(fakeBuilding.returnLocationsFromBuilding("lounge").size(), 3);
    }

    //returnLocationsFromListOfLocations
    // EFFECTS: return the list of locations for which the amenity exists within the list of locations
    @Test
    public void testReturnLocationsFromListOfLocationsEmpty() {
        assertEquals(testBuilding.getLocations().size(), 0);
        assertEquals(testBuilding.returnLocationsFromListOfLocations("lounge").size(), 0);
    }

    @Test
    public void testReturnLocationsFromListOfLocationsNonEmptyListOfLocationsAmenityNotFound() {
        assertEquals(fakeBuilding.getLocations().size(), 3);
        assertEquals(fakeBuilding.returnLocationsFromListOfLocations("test").size(), 0);
    }

    @Test
    public void testReturnLocationsFromListOfLocationsNonEmptyListOfLocationsAmenityFound() {
        assertEquals(fakeBuilding.getLocations().size(), 3);
        assertEquals(fakeBuilding.returnLocationsFromListOfLocations("lounge").size(), 3);
    }
}
