package cs.b07.cscb07courseproject;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import backend.Admin;
import backend.Client;
import backend.Driver;
import backend.Flight;
import backend.Itinerary;
import backend.Trip;

/**
 * Created by lovehela on 2016-12-03.
 */

public class AdminBookResults extends AppCompatActivity{
    Driver driver;
    Itinerary itinerary;
    Client client;
    Admin admin;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_booking_result);

        Intent intent = this.getIntent();
        this.driver = (Driver) intent.getSerializableExtra("driver");
        this.client = (Client) intent.getSerializableExtra("client");
        admin = (Admin) intent.getSerializableExtra("admin");
        this.itinerary = (Itinerary) intent.getSerializableExtra("itinerary");
        welcome = (TextView) findViewById(R.id.welcome);
        String text = welcome.getText().toString();
        text += client.getEmail();
        welcome.setText(text);

        bookItinerary();
    }
    public void onNewIntent(Intent intent){
        // grab all the necessary data for a refresh and redo the page
        this.driver = (Driver) intent.getSerializableExtra("driver");
        this.client = (Client) intent.getSerializableExtra("client");
        this.itinerary = (Itinerary) intent.getSerializableExtra("itinerary");
        welcome = (TextView) findViewById(R.id.welcome);
        bookItinerary();
    }

    public void bookItinerary(){
        // Check if all flights in the itinerary could be booked
        boolean booked = itinerary.bookSeat();
        itinerary.bookSeat();

        if(booked) {
            // Match the flight seat count in the itinerary to the one in driver
            for(Trip tripInDr: driver.getFlightDatabase().getFlights().values()) {
                Flight flightInDir = (Flight) tripInDr;
                for (Trip tripInIt : itinerary.getTripList()) {
                    Flight flightInIt = (Flight) tripInIt;
                    if(flightInDir.equals(flightInIt)){
                        tripInDr.bookSeats(1);
                        Log.w("path", "added the seat in the driver");
                    }
                }
            }
            client.cancelItinerary(itinerary); // remove the old itinerary to allow for updating the new one
            client.addItinerary(itinerary);
            driver.getClientDatabase().addClient(client);
            welcome.setText("Your booking was successful.");

        }
        else{
            welcome.setText("Due to flight capacity, your booking could not be processed");
        }
    }

    public void backToHomepage(View v) {
        Intent intentBack = new Intent(this, AdminHomepage.class);
        intentBack.putExtra("admin",this.admin);
        intentBack.putExtra("driver", this.driver);
        startActivity(intentBack);
    }

    public void backToSearch(View v) {
        Intent intentBack = new Intent(this, AdminSearchFlights.class);
        intentBack.putExtra("client",this.client);
        intentBack.putExtra("admin",this.admin);
        intentBack.putExtra("driver", this.driver);
        startActivity(intentBack);
    }
}
