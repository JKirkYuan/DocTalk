package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import backend.Admin;
import backend.Driver;
import backend.Flight;
import backend.FlightDatabase;

/**
 * Created by mandy on 2016-11-29.
 */

public class AdminEditFlights extends AppCompatActivity{

    Driver driver;
    Admin admin;
    Flight flight;
    EditText et_flight_no, et_departure_time, et_arrival_time, et_Airline,
            et_origin, et_destination, et_price, et_numSeats;
    DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_edit_flights);
        Intent intent = getIntent();
        driver = (Driver) intent.getSerializableExtra("driver");
        admin = (Admin) intent.getSerializableExtra("Admin");
        flight = (Flight) intent.getSerializableExtra("flight");

        et_flight_no = (EditText) findViewById(R.id.et_flight_no);
        et_departure_time = (EditText) findViewById(R.id.et_departure_time);
        et_arrival_time = (EditText) findViewById(R.id.et_arrival_time);
        et_Airline = (EditText) findViewById(R.id.et_Airline);
        et_origin = (EditText) findViewById(R.id.et_origin);
        et_destination = (EditText) findViewById(R.id.et_destination);
        et_price = (EditText) findViewById(R.id.et_price);
        et_numSeats = (EditText) findViewById(R.id.et_numSeats);

        et_flight_no.setText(flight.getFlightNumber());
        et_departure_time.setText(dateTime.format(flight.getDepartureTime()));
        et_arrival_time.setText(dateTime.format(flight.getArrivalTime()));
        et_Airline.setText(flight.getAirline());
        et_origin.setText(flight.getOrigin());
        et_destination.setText(flight.getDestination());
        et_price.setText(String.valueOf(flight.getCost()));
        et_numSeats.setText(String.valueOf(flight.getNumSeats()));
    }

    public void onClick_EditFlight_to_Homepage(View v) {
        Intent intentHomepage = new Intent(this, AdminHomepage.class);
        intentHomepage.putExtra("driver", this.driver);
        intentHomepage.putExtra("admin", admin);
        startActivity(intentHomepage);
    }

    public void onClickUpdateFlights (View v) {
        switch (v.getId()) {
            case R.id.b_udpateFlight:
                String originalFlightNo = flight.getFlightNumber();

                // Strings
                flight.setFlightNumber(et_flight_no.getText().toString());
                flight.setAirline(et_Airline.getText().toString());
                flight.setOrigin(et_origin.getText().toString());
                flight.setDestination(et_destination.getText().toString());

                // convert String to double
                Double doublePrice = Double.parseDouble((et_price.getText().toString()));
                flight.setCost(doublePrice);

                // convert String to int
                int intNumSeats = Integer.parseInt(et_numSeats.getText().toString());
                flight.setNumSeats(intNumSeats);

                String departTimeString = et_departure_time.getText().toString();
                String arrivalTimeString = et_arrival_time.getText().toString();
                DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                // convert String to Date. Use it to set the departTime for the flight
                try {
                    Date departDate = (Date)dateTime.parse(departTimeString);
                    flight.setDepartureTime(departDate);

                    Intent intentBack = new Intent(this, AdminHomepage.class);

                    // flight no. is the same, override the flight
                    if (originalFlightNo.equals(flight.getFlightNumber())){
                        driver.getFlightDatabase().addFlight(flight);
                    }
                    else { // flight no. is different, add new one, delete old one
                        driver.getFlightDatabase().addFlight(flight);
                        driver.getFlightDatabase().removeFlight(originalFlightNo);
                    }

                    intentBack.putExtra("driver", this.driver);
                    intentBack.putExtra("admin", this.admin);

                } catch (ParseException e) {
                    e.printStackTrace();
                    et_departure_time.setText("Enter: yyyy-MM-dd");
                }

                // convert String to Date. Use it to set the arrivalTime for the flight
                try {
                    Date departDate = (Date)dateTime.parse(departTimeString);
                    flight.setDepartureTime(departDate);
                    Date arriveDate = (Date)dateTime.parse(arrivalTimeString);
                    flight.setArrivalTime(arriveDate);

                    Intent intentBack = new Intent(this, AdminHomepage.class);

                    // flight no. is the same, override the flight
                    if (originalFlightNo.equals(flight.getFlightNumber())){
                        driver.getFlightDatabase().addFlight(flight);
                    }
                    else { // flight no. is different, add new one, delete old one
                        driver.getFlightDatabase().addFlight(flight);
                        driver.getFlightDatabase().removeFlight(originalFlightNo);
                    }

                    intentBack.putExtra("driver", this.driver);
                    intentBack.putExtra("admin", this.admin);
                    startActivity(intentBack);
                } catch (ParseException e) {
                    e.printStackTrace();
                    et_arrival_time.setText("Enter: yyyy-MM-dd");
                }
        }
    }
}
