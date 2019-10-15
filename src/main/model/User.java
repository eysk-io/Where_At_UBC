package model;

import interfaces.People;

public class User implements People {
    private String name;

    // EFFECTS: constructs the amenity, setting its name as an empty string
    public User() {
        name = "";
    }

    // EFFECTS: return the name of the user
    @Override
    public String getName() {
        return this.name;
    }

    // EFFECTS: set the name of the user
    public void setUserName(String name) {
        this.name = name;
    }
}
