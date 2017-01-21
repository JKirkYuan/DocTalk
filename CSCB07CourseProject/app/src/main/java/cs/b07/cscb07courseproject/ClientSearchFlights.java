package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import backend.Client;
import backend.Driver;
import backend.ResultItinerary;

/**
 * Created by Ciel on 2016-11-26.
 */

public class ClientSearchFlights extends AppCompatActivity {
    private Driver driver;
    private Client client;
    EditText et_origin, et_destination, et_date;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_flight);
        //put client in database
        Intent intent = this.getIntent();
        this.driver = (Driver) intent.getSerializableExtra("driver");
        this.client = (Client) intent.getSerializableExtra("client");
        et_origin = (EditText) findViewById(R.id.et_origin);
        et_destination = (EditText) findViewById(R.id.et_destination);
        et_date = (EditText) findViewById(R.id.et_date);

    }
    public void onNewIntent(Intent intent){
        // grab all the necessary data for a refresh and redo the page
        this.driver = (Driver) intent.getSerializableExtra("driver");
        this.client = (Client) intent.getSerializableExtra("client");
        et_origin = (EditText) findViewById(R.id.et_origin);
        et_destination = (EditText) findViewById(R.id.et_destination);
        et_date = (EditText) findViewById(R.id.et_date);
    }
    public void displayFlights(View v) throws ParseException {
        Intent intentDisplay = new Intent(this, ClientDisplaySearchedFlights.class);
        intentDisplay.putExtra("driver", driver);
        intentDisplay.putExtra("client", client);
        SimpleDateFormat generalDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ResultItinerary resultItinerary = client.searchItinerary(et_origin.getText().toString(), et_destination.getText().toString(), generalDate.parse(et_date.getText().toString()), driver);
            intentDisplay.putExtra("itinerary", resultItinerary);
            startActivity(intentDisplay);
        }catch(Exception e){
           Log.w("path","exception");
        }
    }
    public void backToHomepage(View v) {
        Intent intentBack = new Intent(this, ClientHomepage.class);
        intentBack.putExtra("driver", driver);
        intentBack.putExtra("client", client);
        startActivity(intentBack);
    }
}
