package test;

import model.Amenity;
import model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    Location testLocation;
    Location fakeLocation;

    @BeforeEach
    public void setup() {
        testLocation = new Location();

        Amenity fakeAmenity1 = new Amenity();
        Amenity fakeAmenity2 = new Amenity();
        Amenity fakeAmenity3 = new Amenity();
        fakeAmenity1.setAmenityName("bathroom");
        fakeAmenity2.setAmenityName("water fountain");
        fakeAmenity3.setAmenityName("lounge");

        fakeLocation = new Location();
        fakeLocation.setLocationName("fake l1");
        fakeLocation.addAmenity(fakeAmenity1);
        fakeLocation.addAmenity(fakeAmenity2);
        fakeLocation.addAmenity(fakeAmenity3);
    }

    @Test
    public void testGetLocationName() {
        assertEquals(fakeLocation.getLocationName(), "fake l1");
    }

    //addAmenity(Amenity amenity)
    @Test
    public void testAddAmenity() {
        assertEquals(fakeLocation.getAmenities().size(), 3);
        Amenity testAmenity = new Amenity();
        assertTrue(fakeLocation.addAmenity(testAmenity));
        assertEquals(fakeLocation.getAmenities().size(), 4);
    }

    //checkForAmenityInLocation(String amenity)
    @Test
    public void testCheckForAmenityInLocationEmpty() {
        assertEquals(testLocation.getAmenities().size(), 0);
        String testAmenityName = "test amenity";
        assertFalse(testLocation.checkForAmenityInClass(testAmenityName));
    }

    @Test
    public void testCheckForAmenityInLocationNonEmptyNotFound() {
        assertEquals(fakeLocation.getAmenities().size(), 3);
        String testAmenityName = "test amenity";
        assertFalse(fakeLocation.checkForAmenityInClass(testAmenityName));
    }

    @Test
    public void testCheckForAmenityInLocationNonEmptyFound() {
        assertEquals(fakeLocation.getAmenities().size(), 3);
        String testAmenityName = "lounge";
        assertTrue(fakeLocation.checkForAmenityInClass(testAmenityName));
    }

    // checkForAmenityInListOfAmenities(String amenity)
    // empty
    @Test
    public void testCheckForAmenityInListOfAmenitiesEmpty() {
        assertEquals(testLocation.getAmenities().size(), 0);
        String testAmenityName = "test amenity";
        assertFalse(testLocation.checkForAmenityInListOfAmenities(testAmenityName));
    }

    // nonempty not found
    @Test
    public void testCheckForAmenityInListOfAmenitiesNonEmptyNotFound() {
        assertEquals(fakeLocation.getAmenities().size(), 3);
        String testAmenityName = "test amenity";
        assertFalse(fakeLocation.checkForAmenityInListOfAmenities(testAmenityName));
    }

    // nonempty found
    @Test
    public void testCheckForAmenityInListOfAmenitiesNonEmptyFound() {
        assertEquals(fakeLocation.getAmenities().size(), 3);
        String testAmenityName = "lounge";
        assertTrue(fakeLocation.checkForAmenityInListOfAmenities(testAmenityName));
    }
}
