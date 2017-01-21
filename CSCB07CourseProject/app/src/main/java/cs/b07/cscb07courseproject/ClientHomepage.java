package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import backend.Client;
import backend.Driver;
import backend.Flight;
import backend.Itinerary;
import backend.Trip;

public class ClientHomepage extends AppCompatActivity {

    private Driver driver;
    private Client client;
    private ListView lv;
    ArrayAdapter<String> adapter;
    private ArrayList<Itinerary> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_homepage);

        Intent intent = getIntent();

        this.client = (Client) intent.getSerializableExtra("client");
        this.driver = (Driver) intent.getSerializableExtra("driver");

        setUpPage();
    }
    public void onNewIntent(Intent intent){
        // grab all the necessary data for a refresh and redo the page

        this.client = (Client) intent.getSerializableExtra("client");
        this.driver = (Driver) intent.getSerializableExtra("driver");

        setUpPage();
    }

    public void setUpPage(){
        ArrayList<String> items = new ArrayList<>();
        result = client.getBookedItineraries();
        ArrayList<Trip> trips;
        // grab all the text for the list items
        for(Itinerary i: result) {

            // here to format this, we want a rows of itinerary information
            String text = "";
            trips = i.getTripList();
            for (Trip trip : trips) {
                // first we update the client's itineraries to reflect changed information
                Flight f = (Flight) trip;
                trip = (Trip) driver.getFlightDatabase().getFlight(f.getFlightNumber());
                // then we grab all the flight information and put it into the file
                text += "Departs from: " + trip.getOrigin();
                text += " at " + trip.getDepartureTime();
                text += " to arrive at " + trip.getDestination();
                text += " at " + trip.getArrivalTime();
                // then we place and -> to show that the flights are connected
                text += " -> ";
            }
            text += " , Trip will take: " + i.getTotalTime();
            text += " , Number of free seats: " + i.getLowestNumSeats();
            text += " , Will cost in total: " + i.getTotalCost();
            items.add(text);
        }
        lv = (ListView) findViewById(R.id.booked_flights);
        // Adding items to ListView
        adapter = new ArrayAdapter<String>(ClientHomepage.this, R.layout.list_item, R.id.item_name, items);
        lv.setAdapter(adapter);
        lv.setClickable(true);
        adapter.setNotifyOnChange(true);
        lv.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
                // When clicked, make the information of the itinerary given to the next activity
                Log.w("path", "Clicked on List item");
                // since we are not technically in the same anymore, we have to find the class
                // and then try to reference it from here to get the actual items
                ClientHomepage.this.removeBooking(result.get(position));
                ClientHomepage.this.setUpPage();
            }

        });
    }

    public void removeBooking(Itinerary itinerary){
        // just get the client and remove the result itinerary
        client.cancelItinerary(itinerary);
        // update the driver with the fact that someone cancelled
        driver.getClientDatabase().addClient(client);
        for(Trip tripInDr: driver.getFlightDatabase().getFlights().values()) {
            Flight flightInDir = (Flight) tripInDr;
            for (Trip tripInIt : itinerary.getTripList()) {
                Flight flightInIt = (Flight) tripInIt;
                if(flightInDir.equals(flightInIt)){
                    tripInDr.addNewSeats(1);
                    Log.w("path", "removed the seat in the driver");
                }
            }
        }

    }

    public void onClickSearchFlights(View v) {
        Intent intentSearch = new Intent(this, ClientSearchFlights.class);
        intentSearch.putExtra("driver", driver);
        intentSearch.putExtra("client", client);
        startActivity(intentSearch);
    }

    public void onClickEditProfile(View v) {
        Intent intentEdit = new Intent(this, EditProfile.class);
        intentEdit.putExtra("driver", driver);
        intentEdit.putExtra("client", client);
        startActivity(intentEdit);
    }

    public void Logout(View v) {
        Intent intentLogout = new Intent(this, Login.class);
        intentLogout.putExtra("driver", driver);
        startActivity(intentLogout);
    }
}

