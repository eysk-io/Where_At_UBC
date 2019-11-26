package ui;

import model.Amenity;
import model.Building;
import model.Location;

import javax.swing.*;
import java.util.ArrayList;

public class LocationOutputForm {
    private JPanel panelMain;
    private JLabel amenityOutput;
    private JLabel locationOutput;
    private JLabel logo;

    public LocationOutputForm(JFrame locationFrame, Building validBuilding) {
        setup(locationFrame);
        Amenity searchedAmenity = AmenitySearchForm.getAmenity();
        amenityOutput.setText("You can find the " + searchedAmenity.getAmenityName()
                + " at the following locations within " + validBuilding.getBuildingName() + ":");
        locationOutput.setText(outputAmenitySearchResult(validBuilding, searchedAmenity));
    }

    // EFFECTS: sets up the JFrame for the form
    private void setup(JFrame locationFrame) {
        locationFrame.setContentPane(panelMain);
        locationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        locationFrame.pack();
        locationFrame.setVisible(true);
    }

    // EFFECTS: return the final search result of locations with the searched amenity
    private static String outputAmenitySearchResult(Building building, Amenity amenity) {
        String result = "| ";
        ArrayList<Location> resultList = building.returnLocationsFromBuilding(amenity.getAmenityName());
        for (Location location: resultList) {
            result += location.getLocationName() + " | ";
        }
        return result;
    }

    private void createUIComponents() {
        logo = new JLabel(new ImageIcon("data/logo.png"));
    }
}
