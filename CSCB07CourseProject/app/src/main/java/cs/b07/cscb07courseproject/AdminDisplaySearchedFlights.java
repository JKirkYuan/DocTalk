package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import backend.Admin;
import backend.Client;
import backend.Driver;
import backend.Flight;
import backend.Itinerary;
import backend.ResultItinerary;
import backend.Trip;

/**
 * Created by mandy on 2016-11-29.
 */

public class AdminDisplaySearchedFlights extends AppCompatActivity{

    private Driver driver;
    private Client client;
    private Admin admin;
    private ResultItinerary resultItinerary;
    private EditText et_origin, et_destination, et_date;
    // List view
    private ListView lv;
    private ArrayList<Itinerary> result;
    private TextView welcome;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_display_searched_flights);
        Intent intent = this.getIntent();
        // grab all the necessary data
        driver = (Driver) intent.getSerializableExtra("driver");
        client = (Client) intent.getSerializableExtra("client");
        admin = (Admin) intent.getSerializableExtra("admin");
        resultItinerary = new ResultItinerary(client.getBookedItineraries());
        welcome = (TextView) findViewById(R.id.welcome);
        String text = welcome.getText().toString();
        text += client.getEmail();
        welcome.setText(text);
        setAllInformation();

    }

    public void onNewIntent(Intent intent){
        // grab all the necessary data for a refresh and redo the page
        driver = (Driver) intent.getSerializableExtra("driver");
        client = (Client) intent.getSerializableExtra("client");
        resultItinerary = (ResultItinerary) intent.getSerializableExtra("itinerary");
        setAllInformation();
    }

    public void setAllInformation(){
        // this is a helper method to help load all the data on the page
        // now we have to unravel the classes to be able to get the individual flights to
        // parse into the page
        ArrayList<String> items = new ArrayList<>();
        result = resultItinerary.getResItinerary();
        ArrayList<Trip> trips;
        // grab all the text for the list items
        for(Itinerary i: result) {
            // here to format this, we want a rows of itinerary information
            String text = "";
            trips = i.getTripList();
            for (Trip trip : trips) {
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
        lv = (ListView) findViewById(R.id.tv_admin_booked_flights);
        // Adding items to ListView
        adapter = new ArrayAdapter<String>(AdminDisplaySearchedFlights.this, R.layout.list_item, R.id.item_name, items);
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
                // since we are not technically in the same anymore, we have to find the class
                // and then try to reference it from here to get the actual items
                AdminDisplaySearchedFlights.this.removeBooking(AdminDisplaySearchedFlights.this.result.get(position));
                AdminDisplaySearchedFlights.this.setAllInformation();
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
                }
            }
        }
    }

    public void backToHomepage(View view) {
        Intent backToHomepage = new Intent(this, AdminHomepage.class);
        backToHomepage.putExtra("driver", AdminDisplaySearchedFlights.this.driver);
        backToHomepage.putExtra("admin", AdminDisplaySearchedFlights.this.admin);

        startActivity(backToHomepage);
    }
}
