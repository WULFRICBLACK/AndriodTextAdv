package com.example.ostestadvver10;

public class Player {

    static final String NOTHING = "Nothing";

    private int playerPos;

    private String inventory;

    Player()
    {
        playerPos=0;
        inventory= NOTHING ;

    }//player()

    Player(int newPosition)
    {

        playerPos = newPosition;
        inventory= NOTHING;

    }//Player(int newPosition)

    public int getPlayerPos() {
        return playerPos;
    }

    public void setPlayerPos(int playerPos) {
        this.playerPos = playerPos;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }


}  //end of player class
