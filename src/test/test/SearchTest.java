package test;

import model.Building;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Search;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {
    Search testSearch;
    ArrayList<Building> testBuildings;
    String testName;

    @BeforeEach
    public void setup() {
        testSearch = new Search();
        testBuildings = new ArrayList<Building>();
        testName = "fake b3";
    }

    @Test
    public void testCheckForBuildingInListOfBuildingsEmptyListOfBuildings() {
        assertEquals(testBuildings.size(), 0);
        assertFalse(testSearch.checkForBuildingInListOfBuildings(testBuildings, testName));
    }

    @Test
    public void testCheckForBuildingInListOfBuildingsNonEmptyListOfBuildingsNotFound() {
        assertEquals(testBuildings.size(), 0);
        Building building1 = new Building();
        Building building2 = new Building();
        Building building3 = new Building();
        building1.setBuildingName("fake b1");
        building2.setBuildingName("fake b2");
        building3.setBuildingName("fake b3");
        assertTrue(testBuildings.add(building1));
        assertTrue(testBuildings.add(building2));
        assertTrue(testBuildings.add(building3));
        assertEquals(testBuildings.size(), 3);
        assertFalse(testSearch.checkForBuildingInListOfBuildings(testBuildings, "test"));
    }

    @Test
    public void testCheckForBuildingInListOfBuildingsNonEmptyListOfBuildingsFound() {
        assertEquals(testBuildings.size(), 0);
        Building building1 = new Building();
        Building building2 = new Building();
        Building building3 = new Building();
        building1.setBuildingName("fake b1");
        building2.setBuildingName("fake b2");
        building3.setBuildingName("fake b3");
        assertTrue(testBuildings.add(building1));
        assertTrue(testBuildings.add(building2));
        assertTrue(testBuildings.add(building3));
        assertEquals(testBuildings.size(), 3);
        assertTrue(testSearch.checkForBuildingInListOfBuildings(testBuildings, testName));
    }
}
