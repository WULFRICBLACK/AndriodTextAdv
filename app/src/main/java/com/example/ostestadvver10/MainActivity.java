package com.example.ostestadvver10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    //used for saving player position
    public static final String MY_PREFS = "prefs";
    public static final String MY_KEY = "pos";
    SharedPreferences sharedPrefs;

    static final int NUM_OF_ROOMS = 10 ;
    Room[] thedungeon;  //array of room objects called dungeon

    Player thePlayer; // the integer playerpos is now an element or a value in plauyer class!!!

    //int playerPos = 0 ;

    //controls
    TextView descriptionTextView;

    Button northbutton;
    Button eastbutton;
    Button southbutton;
    Button westbutton;


    TextView playerInventoryTextView;
    TextView roomInventoryTextView;

    Button pickButton;
    Button dropButton;

    Button saveButton;

    ImageView imageView;

    Button mainBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String value = extras.getString(MY_KEY);

            initPlayerWithLoad(Integer.valueOf( value ) );
        }
        else
        {
            initPLayer();//playerpos 0 aka new game

        }// if (extras!=null)

        initPLayer();

        initTheDungeon();
        displayRooms();


        readXMLFile();
        displayRooms();


        setupControls();

        updateDirection();


        thedungeon[5].setInventory("BROOM");

        thedungeon[8].setInventory("SOCCER'S STONE");

        checkForBroom();
        checkForStone();


        descriptionTextView.setText(thedungeon[thePlayer.getPlayerPos()].getDescription());

        validDirections( thePlayer.getPlayerPos() );

        thedungeon[0].setInventory("Wand");

        playerInventoryTextView.setText("Player inventory = "+ thePlayer.getInventory() );

        roomInventoryTextView.setText("Room Inventory =" + thedungeon[thePlayer.getPlayerPos()].getInventory());



    }//end of protected void onCreate(Bundle savedInstanceState)



    protected void initPLayer()
    {

       thePlayer = new Player();

    }//protected void initPlayer()

    protected void initPlayerWithLoad(int loadPosition)
    {

        thePlayer= new Player(loadPosition);

    }// protected void initPlayerWithLoad


    protected void setupControls()
    {
        descriptionTextView = findViewById(R.id.descriptionTextView);

        northbutton = findViewById(R.id.northbutton);
        northbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                thePlayer.setPlayerPos(thedungeon[thePlayer.getPlayerPos()].getNorth()); //means = move north

               updateDirection();
            }
        });

        eastbutton = findViewById(R.id.eastbutton);
        eastbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                thePlayer.setPlayerPos(thedungeon[thePlayer.getPlayerPos()].getEast()); //means = move east

                updateDirection();

            }
        });

        southbutton = findViewById(R.id.southbutton);
        southbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                thePlayer.setPlayerPos(thedungeon[thePlayer.getPlayerPos()].getSouth()); //means = move south

                updateDirection();
            }
        });

        westbutton = findViewById(R.id.westbutton);
        westbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                thePlayer.setPlayerPos(thedungeon[thePlayer.getPlayerPos()].getWest()); //means move west

                updateDirection();
            }
        });

        playerInventoryTextView = findViewById(R.id.playeInventoryTextView);

        roomInventoryTextView = findViewById(R.id. roomInventoryTextView);

        pickButton =findViewById(R.id.pickButton);
        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if ((thePlayer.getInventory() == Player.NOTHING) &&
                        (thedungeon[thePlayer.getPlayerPos()].getInventory() != Player.NOTHING))
                {
                   thePlayer.setInventory(thedungeon[thePlayer.getPlayerPos()].getInventory());

                   thedungeon[thePlayer.getPlayerPos()].setInventory(Player.NOTHING);

                    playerInventoryTextView.setText("Player inventory = "+ thePlayer.getInventory());
                    roomInventoryTextView.setText("Room Inventory =" + thedungeon[thePlayer.getPlayerPos()].getInventory());
                }

            }

        });

        dropButton = findViewById(R.id.dropButton);
        dropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if((thePlayer.getInventory() != Player.NOTHING
                        && (thedungeon[thePlayer.getPlayerPos()].getInventory() ==Player.NOTHING)))
                {
                    thedungeon[thePlayer.getPlayerPos()].setInventory(thePlayer.getInventory());
                    thePlayer.setInventory(Player.NOTHING);

                    playerInventoryTextView.setText("Player inventory = "+ thePlayer.getInventory());
                    roomInventoryTextView.setText("Room Inventory =" + thedungeon[thePlayer.getPlayerPos()].getInventory());
                }


            }
        });
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                savePosition();

            }
        });
        imageView = findViewById(R.id.imageView);

        mainBackButton = findViewById(R.id.mainBackButton);
        mainBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(intent);


            }
        });



    }  //end of setupControls()

    protected void displayRoomImage(int currentPos)
    {
        if(currentPos == 0)
        {
            imageView.setImageResource(R.drawable.room0);
        }
        else if(currentPos==1)
        {
            imageView.setImageResource(R.drawable.room1);
        }
        else if(currentPos==2)
        {
            imageView.setImageResource(R.drawable.room2);
        }
        else if(currentPos==3)
        {
            imageView.setImageResource(R.drawable.room3);
        }
        else if(currentPos==4)
        {
            imageView.setImageResource(R.drawable.room4);
        }
        else if(currentPos==5)
        {
            imageView.setImageResource(R.drawable.room5);
        }
        else if(currentPos==6)
        {
            imageView.setImageResource(R.drawable.room6);
        }
        else if(currentPos==7)
        {
            imageView.setImageResource(R.drawable.room7);
        }
        else if(currentPos==8)
        {
            imageView.setImageResource(R.drawable.room8);
        }

    }//protected void displayRoomImage

    protected void checkForBroom()
    {
        if ((thePlayer.getInventory() == "broom") && (thePlayer.getPlayerPos() == 5))
        {
            // unlock the east door in room 1
            thedungeon[thePlayer.getPlayerPos()].setSouth(6);

            // keys is removed !!!
            thePlayer.setInventory(Player.NOTHING);

            DisplayMessage("YOU FLEW AND BROOM MAGICALLY RAN AWAY");
        }

    }
    protected void checkForStone()
    {
        if ((thePlayer.getInventory() == "Stone") && (thePlayer.getPlayerPos() == 8))
        {

            DisplayMessage("YOU Have the SOCCER'S STONE");
        }

    }


    public void savePosition()
    {
        sharedPrefs = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPrefs.edit();
        edit.putInt(MY_KEY, thePlayer.getPlayerPos() );

        edit.commit();

        DisplayMessage("Player Position Saved !");

    } // public void savePosition()

    public void DisplayMessage(CharSequence text)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    protected void updateDirection()
    {
        descriptionTextView.setText(thedungeon[thePlayer.getPlayerPos()].getDescription());

        validDirections(thePlayer.getPlayerPos() );

        playerInventoryTextView.setText("Player inventory = "+ thePlayer.getInventory());
        roomInventoryTextView.setText("Room Inventory =" + thedungeon[thePlayer.getPlayerPos()].getInventory());

        displayRoomImage(thePlayer.getPlayerPos());

    } //protected void updateDirection




    protected void validDirections(int newPlayerPos)
    {
        if (thedungeon[newPlayerPos].getNorth() ==Room.NO_EXIT)
        {
            northbutton.setEnabled(false);
        }
        else
        {
            northbutton.setEnabled(true);
        }
        if (thedungeon[newPlayerPos].getEast() ==Room.NO_EXIT)
        {
            eastbutton.setEnabled(false);
        }
        else
        {
            eastbutton.setEnabled(true);
        }
        if (thedungeon[newPlayerPos].getSouth() ==Room.NO_EXIT)
        {
            southbutton.setEnabled(false);
        }
        else
        {
            southbutton.setEnabled(true);
        }
        if (thedungeon[newPlayerPos].getWest() ==Room.NO_EXIT)
        {
            westbutton.setEnabled(false);
        }
        else
        {
            westbutton.setEnabled(true);
        }

    }// end of valid direction


    protected void initTheDungeon()
    {
        {
            thedungeon  = new Room[NUM_OF_ROOMS];
            for(int pos = 0; pos<NUM_OF_ROOMS; pos++)
            {
                thedungeon[pos] = new Room();
            }
        }//protected void initTheDungeon()

    } // public static void initTheDungeon()


    public void displayRooms()
    {

        for (int pos = 0; pos < NUM_OF_ROOMS; pos++) {

            Log.w("POS","pos=" + pos);
            Log.w("display ROOM", "North = " + thedungeon[pos].getNorth());
            Log.w("display ROOM", "East = " + thedungeon[pos].getEast());
            Log.w("display ROOM", "South = " + thedungeon[pos].getSouth());
            Log.w("display ROOM", "West = " + thedungeon[pos].getWest());
            Log.w("display ROOM", "Description = " + thedungeon[pos].getDescription());


            Log.w(" "," ");

        }

        Log.w("Display ROOM","**** end of the rooms ****");

    } // public void displayRooms()

    public void readXMLFile()
    {
        int pos = 0; // May be use this variable, to keep track of what position of the array of Room Objects.
        try
        {
            int room_count = 0;

            XmlResourceParser xpp = getResources().getXml(R.xml.dungeon); //open xml file to read
            xpp.next();
            int eventType = xpp.getEventType();
            String elemtext = null;

            while (eventType != XmlPullParser.END_DOCUMENT) // end of file
            {
                if (eventType == XmlPullParser.START_TAG)
                {
                    String elemName = xpp.getName();

                    if (elemName.equals("dungeon"))
                    {
                        String titleAttr = xpp.getAttributeValue(null,"title");
                        String authorAttr = xpp.getAttributeValue(null,"author");

                    } // if (elemName.equals("dungeon"))

                    if (elemName.equals("room"))
                    {
                        room_count = room_count + 1;
                    }
                    if (elemName.equals("north"))
                    {
                        elemtext = "north";
                    }
                    if (elemName.equals("east"))
                    {
                        elemtext = "east";
                    }
                    if (elemName.equals("south"))
                    {
                        elemtext = "south";
                    }
                    if (elemName.equals("west"))
                    {
                        elemtext = "west";
                    }
                    if (elemName.equals("description"))
                    {
                        elemtext = "description";
                    }
                } // if (eventType == XmlPullParser.START_TAG)
                // You will need to add code in this section to read each element of the XML file
                // And then store the value in the current Room Object.
                // NOTE: This method initTheDungeon() creates and array of Room Objects, ready to be populated!
                // As you can see at the moment the data/text is displayed in the LogCat Window
                // Hint: xpp.getText()
 else if (eventType == XmlPullParser.TEXT)
            {
                if (elemtext.equals("north"))
                {
                    Log.w("ROOM", "north = " + xpp.getText());
                    thedungeon[room_count-1].setNorth( Integer.valueOf(xpp.getText())); //do not == to compare string object
                }
                else if (elemtext.equals("east"))
                {
                    Log.w("ROOM", "east = " + xpp.getText());
                    thedungeon[room_count-1].setEast(Integer.valueOf(xpp.getText()));
                }
                else if (elemtext.equals("south"))
                {
                    Log.w("ROOM", "south = " + xpp.getText());
                    thedungeon[room_count-1].setSouth(Integer.valueOf(xpp.getText()));
                }
                else if (elemtext.equals("west"))
                {
                    Log.w("ROOM", "west = " + xpp.getText());
                    thedungeon[room_count-1].setWest(Integer.valueOf(xpp.getText()));
                }
                else if (elemtext.equals("description"))
                {
                    Log.w("ROOM", "description = " + xpp.getText());
                    thedungeon[room_count-1].setDescription( xpp.getText() );
                }
            } // else if (eventType == XmlPullParser.TEXT)

                eventType = xpp.next();

            } // while (eventType != XmlPullParser.END_DOCUMENT)
        } // try
        catch (XmlPullParserException e)
        {

        }
        catch (IOException e)
        {

        }
    } // public void readXMLFile()

}//end of public class MainActivity extends AppCompatActivity