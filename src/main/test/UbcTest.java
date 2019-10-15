package test;

import model.Amenity;
import model.Building;
import model.Location;
import model.UBC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UbcTest {
    UBC testUBC;
    Building testBuilding;

    UBC ubc;

    @BeforeEach
    public void setup() {
        testUBC = new UBC();
        testBuilding = new Building();
        ArrayList<Location> listOfFakeLocations = setFakeLocations();
        Building fakeBuilding1 = new Building();
        fakeBuilding1.setBuildingName("fake b1");
        fakeBuilding1.addLocation(listOfFakeLocations.get(0));
        fakeBuilding1.addLocation(listOfFakeLocations.get(1));
        fakeBuilding1.addLocation(listOfFakeLocations.get(2));

        Building fakeBuilding2 = new Building();
        fakeBuilding2.setBuildingName("fake b2");
        fakeBuilding2.addLocation(listOfFakeLocations.get(0));
        fakeBuilding2.addLocation(listOfFakeLocations.get(1));
        fakeBuilding2.addLocation(listOfFakeLocations.get(2));

        ubc = new UBC();
        ubc.addBuilding(fakeBuilding1);
        ubc.addBuilding(fakeBuilding2);
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

    //addBuilding
    // add building to list of buildings
    @Test
    public void testAddBuilding() {
        assertEquals(testUBC.getBuildings().size(), 0);
        assertTrue(testUBC.addBuilding(testBuilding));
        assertEquals(testUBC.getBuildings().size(), 1);
    }

    //checkForAmenityAtUBC
    // amenity does NOT exist (because no buildings)
    @Test
    public void testCheckForAmenityAtUbcEmpty() {
        String testAmenityName = "test amenity";
        assertEquals(testUBC.getBuildings().size(), 0);
        assertFalse(testUBC.checkForAmenityInClass(testAmenityName));
    }

    // amenity does NOT exist
    @Test
    public void testCheckForAmenityAtUbcDoesNotExist() {
        String testAmenityName = "test amenity";
        assertEquals(ubc.getBuildings().size(), 2);
        assertFalse(ubc.checkForAmenityInClass(testAmenityName));
    }

    // amenity does exist
    @Test
    public void testCheckForAmenityAtUbcDoesExist() {
        String testAmenityName = "lounge";
        assertEquals(ubc.getBuildings().size(), 2);
        assertTrue(ubc.checkForAmenityInClass(testAmenityName));
    }

    //checkForAmenityInListOfBuildings(String amenity)
    @Test
    public void testCheckForInListOfBuildingsEmpty() {
        String testAmenityName = "test amenity";
        assertEquals(testUBC.getBuildings().size(), 0);
        assertFalse(testUBC.checkForAmenityInListOfBuildings(testAmenityName));
    }

    @Test
    public void testCheckForInListOfBuildingsNotEmptyNotFound() {
        String testAmenityName = "test amenity";
        assertEquals(ubc.getBuildings().size(), 2);
        assertFalse(ubc.checkForAmenityInListOfBuildings(testAmenityName));
    }

    @Test
    public void testCheckForInListOfBuildingsNotEmptyFound() {
        String testAmenityName = "lounge";
        assertEquals(ubc.getBuildings().size(), 2);
        assertTrue(ubc.checkForAmenityInListOfBuildings(testAmenityName));
    }
    // amenity found

    //returnBuildingsWithAmenity
    // no building has the amenity
    @Test
    public void testReturnBuildingsWithAmenityNotFound() {
        String testAmenityName = "test amenity";
        assertEquals(ubc.getBuildings().size(), 2);
        assertFalse(ubc.checkForAmenityInClass(testAmenityName));
        assertEquals(ubc.returnBuildingsWithAmenity(testAmenityName).size(), 0);
    }

    // more than one buildings have the amenity
    @Test
    public void testReturnBuildingsWithAmenityFound() {
        String testAmenityName = "lounge";
        assertEquals(ubc.getBuildings().size(), 2);
        assertTrue(ubc.checkForAmenityInClass(testAmenityName));
        assertEquals(ubc.returnBuildingsWithAmenity(testAmenityName).size(), 2);
    }

    // returnBuildingWithCorrectName
    // EFFECTS: return the building with the same name as the given name

    @Test
    public void testReturnBuildingsWithCorrectNameNoBuildingsExist() {
        assertEquals(testUBC.getBuildings().size(), 0);
        Building testResult = testUBC.returnBuildingWithCorrectName("fake b2");
        assertEquals(testResult.getBuildingName(), "");
    }

    @Test
    public void testReturnBuildingsWithCorrectNameNoBuildingWithGivenNameExists() {
        assertEquals(ubc.getBuildings().size(), 2);
        Building testResult = ubc.returnBuildingWithCorrectName("fake b5");
        assertEquals(testResult.getBuildingName(), "");
    }

    @Test
    public void testReturnBuildingsWithCorrectNameBuildingWithGivenNameExists() {
        assertEquals(ubc.getBuildings().size(), 2);
        String testBuildingName = "fake b2";
        Building testResult = ubc.returnBuildingWithCorrectName(testBuildingName);
        assertEquals(testResult.getBuildingName(), testBuildingName);
    }
}
