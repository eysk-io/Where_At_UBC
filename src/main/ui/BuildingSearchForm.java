package ui;

import model.Building;
import model.UBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BuildingSearchForm {
    private JPanel panelMain;
    private JButton submitButton;
    private JTextField buildingTextBox;
    private JLabel output;
    private JLabel logo;

    public BuildingSearchForm(JFrame buildingFrame, ArrayList<Building> validBuildings, UBC ubc) {
        setup(buildingFrame);
        output.setText(validBuildingNames(validBuildings));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buildingName = buildingTextBox.getText();
                Building buildingResult = searchBuilding(validBuildings, buildingName, ubc);
                if (buildingResult.getBuildingName() == "false") {
                    buildingFrame.dispose();
                    new BuildingSearchForm(buildingFrame, validBuildings, ubc);
                } else {
                    buildingFrame.dispose();
                    JFrame locationFrame = new JFrame("LocationOutput");
                    new LocationOutputForm(locationFrame, buildingResult);
                }
            }
        });
    }

    // EFFECTS: sets up the JFrame for the form
    private void setup(JFrame buildingSearchFrame) {
        buildingSearchFrame.setContentPane(panelMain);
        buildingSearchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildingSearchFrame.pack();
        buildingSearchFrame.setVisible(true);
    }

    // EFFECTS: return a string of the buildings that have the searched amenity
    private static String validBuildingNames(ArrayList<Building> validBuildings) {
        String buildings = "| ";
        for (Building building: validBuildings) {
            buildings += building.getBuildingName() + " | ";
        }
        return buildings;
    }

    // EFFECTS: return the building to search on
    private static Building searchBuilding(ArrayList<Building> validBuildings, String buildingName, UBC ubc) {
        // Search based on chosen building
        Building building = new Building();
        building.setBuildingName(buildingName);

        // if invalid building given:
        if (!checkForBuildingInListOfBuildings(validBuildings, buildingName)) {
            JOptionPane.showMessageDialog(null,
                    "I'm sorry, that is not valid. Please try another building search");
            building.setBuildingName("false");
        } else {
            building = ubc.returnBuildingWithCorrectName(buildingName);
        }
        return building;
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

    private void createUIComponents() {
        logo = new JLabel(new ImageIcon("data/logo.png"));
    }
}
