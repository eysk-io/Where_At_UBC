package ui;

import model.Amenity;
import model.Building;
import model.Location;
import model.UBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AmenitySearchForm {
    private JPanel panelMain;
    private JButton submitButton;
    private JTextField amenityTextBox;

    private static Amenity amenity = new Amenity();
    private static UBC ubc;

    public AmenitySearchForm(JFrame amenityFrame) {
        setup(amenityFrame);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ubc = createUBC();
                String amenityName = amenityTextBox.getText();
                amenity.setAmenityName(amenityName);
                ArrayList<Building> validBuildings;
                validBuildings = validBuildingsOutput(ubc, amenity);
                if (validBuildings.size() == 0) {
                    amenityFrame.dispose();
                    new AmenitySearchForm(amenityFrame);
                } else {
                    amenityFrame.dispose();
                    JFrame buildingFrame = new JFrame("BuildingSearch");
                    new BuildingSearchForm(buildingFrame, validBuildings, ubc);
                }
            }
        });
    }

    // EFFECTS: sets up the JFrame for the form
    private void setup(JFrame amenityFrame) {
        amenityFrame.setContentPane(panelMain);
        amenityFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        amenityFrame.pack();
        amenityFrame.setVisible(true);
    }

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
        ArrayList<Location> listOfLocations = setFakeLocations();
        Building fakeBuilding1 = createBuilding(listOfLocations, "fake b1");
        Building fakeBuilding2 = createBuilding(listOfLocations, "fake b2");
        UBC ubc = new UBC();
        ubc.addBuilding(fakeBuilding1);
        ubc.addBuilding(fakeBuilding2);
        return ubc;
    }

    // EFFECTS: output the valid buildings that have the required amenity
    private static ArrayList<Building> validBuildingsOutput(UBC ubc, Amenity amenity) {
        ArrayList<Building> validBuildings = tryAgainUntilAmenityFound(amenity.getAmenityName(), ubc, amenity);
        return validBuildings;
    }

    // EFFECTS: return a list of buildings which holds the searched amenity
    private static ArrayList<Building> tryAgainUntilAmenityFound(String name, UBC ubc, Amenity amenity) {
        ArrayList<Building> validBuildings = new ArrayList<Building>();
        if (!ubc.checkForAmenityInClass(name)) {
            JOptionPane.showMessageDialog(null,
                    "I'm sorry, that amenity does not exist @UBC. Anything else?");
        } else {
            validBuildings = ubc.returnBuildingsWithAmenity(name);
        }
        return validBuildings;
    }

    public static Amenity getAmenity() {
        return amenity;
    }
}
