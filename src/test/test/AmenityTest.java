package test;

import model.Amenity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AmenityTest {
    Amenity testAmenity;

    @BeforeEach
    public void setup() {
        testAmenity = new Amenity();
    }

    //checkForAmenityInAmenity(String amenity)
    @Test
    public void testCheckForAmenityInAmenityFound() {
        String testAmenityName = "bathroom";
        assertTrue(testAmenity.setAmenityName(testAmenityName));
        assertEquals(testAmenity.getAmenityName(), testAmenityName);
        assertTrue(testAmenity.checkForAmenityInClass(testAmenityName));
    }

    @Test
    public void testCheckForAmenityInAmenityNotFound() {
        String testAmenityName = "bathroom";
        assertTrue(testAmenity.setAmenityName("lounge"));
        assertEquals(testAmenity.getAmenityName(), "lounge");
        assertFalse(testAmenity.checkForAmenityInClass(testAmenityName));
    }
}
