package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import backend.Driver;
import backend.FlightDatabase;

/**
 * Created by mandy on 2016-11-29.
 */

public class AdminDisplayFlightDB extends AppCompatActivity{

    private FlightDatabase flightDatabase;
    private Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_display_flightdb);

        // get the Intent that launched me
        Intent intent = getIntent();
        flightDatabase = (FlightDatabase) intent.getSerializableExtra(AdminHomepage.FLIGHT_DB);
        driver = (Driver) intent.getSerializableExtra("driver");

        String content = flightDatabase.toString();
        TextView view = (TextView) findViewById(R.id.flightDB_info);
        view.setText(content);

    }

    protected void onNewIntent(Intent intent){
        flightDatabase = (FlightDatabase) intent.getSerializableExtra(AdminHomepage.FLIGHT_DB);
        driver = (Driver) intent.getSerializableExtra("driver");

        String content = flightDatabase.toString();
        TextView view = (TextView) findViewById(R.id.flightDB_info);
        view.setText(content);
    }

}