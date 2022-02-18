package com.example.ostestadvver10;

public class Room {

    static final int NO_EXIT = -1;
    private int north;
    private int east;
    private int south;
    private int west;


    private String description;

    private String inventory;


    Room() //CONSTRUCTOR!!!
    {

        north = NO_EXIT;
        east = NO_EXIT;
        south = NO_EXIT;
        west = NO_EXIT;

        description = "NOTHING";

        inventory = Player.NOTHING;

    }//end of constructor

    public int getNorth() {
        return north;
    }

    public void setNorth(int north) {
        this.north = north;
    }

    public int getEast() {
        return east;
    }

    public void setEast(int east) {
        this.east = east;
    }

    public int getSouth() {
        return south;
    }

    public void setSouth(int south) {
        this.south = south;
    }

    public int getWest() {
        return west;
    }

    public void setWest(int west) {
        this.west = west;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}//end of public class Room
