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

import java.util.ArrayList;

import backend.Admin;
import backend.Client;
import backend.Driver;
import backend.Itinerary;
import backend.ResultItinerary;
import backend.Trip;

/**
 * Created by tengvinc on 2016-12-03.
 */

public class AdminBookFlights extends AppCompatActivity {
    private Driver driver;
    private Client client;
    private ResultItinerary resultItinerary;
    private EditText et_origin, et_destination, et_date;
    // List view
    private ListView lv;
    private ArrayList<Itinerary> result;
    private Admin admin;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_display_searched_flights);
        Intent intent = this.getIntent();
        // grab all the necessary data
        driver = (Driver) intent.getSerializableExtra("driver");
        client = (Client) intent.getSerializableExtra("client");
        resultItinerary = (ResultItinerary) intent.getSerializableExtra("itinerary");
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
        Log.w("path", result.toString());
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
        lv = (ListView) findViewById(R.id.list_view);
        // Adding items to ListView
        adapter = new ArrayAdapter<String>(AdminBookFlights.this, R.layout.list_item, R.id.item_name, items);
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
                Intent intent = new Intent(AdminBookFlights.this, AdminBookResults.class);
                intent.putExtra("driver", AdminBookFlights.this.driver);
                intent.putExtra("client", AdminBookFlights.this.client);
                intent.putExtra("itinerary", AdminBookFlights.this.result.get(position));
                //based on item add info to intent
                startActivity(intent);
            }

        });
    }

    public void sortByCost(View v) {
        this.resultItinerary = this.resultItinerary.sortByCost();
        setAllInformation();
    }

    public void sortByTime(View v) {
        this.resultItinerary = this.resultItinerary.sortByTime();
        setAllInformation();
    }

    public void backToHomepage(View v) {
        Intent intentBack = new Intent(this, AdminHomepage.class);
        intentBack.putExtra("admin",this.admin);
        intentBack.putExtra("driver", this.driver);
        startActivity(intentBack);
    }


}
