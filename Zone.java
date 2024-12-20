package com.mycompany.trafficmanagementsystem;

import java.util.ArrayList;

public class Zone {
    private int ID;
    private String name;
    private String location;
    ArrayList <Traffic_Light> trafficLights = new ArrayList();
    
    public Zone(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Traffic_Light> getTrafficLights() {
        return trafficLights;
    }

    public void setTrafficLights(ArrayList<Traffic_Light> trafficLights) {
        this.trafficLights = trafficLights;
    }
    
    
}
