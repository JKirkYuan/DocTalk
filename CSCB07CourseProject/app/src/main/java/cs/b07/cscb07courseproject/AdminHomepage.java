package cs.b07.cscb07courseproject;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.text.ParseException;

import backend.Admin;
import backend.Client;
import backend.Driver;
import backend.Flight;


/**
 * Created by mandy on 2016-11-28.
 */


public class AdminHomepage extends AppCompatActivity {
    public static final String FLIGHT_DB = "flight_db";
    public static final String CLIENT_DB = "client_db";
    EditText et_flight_file, et_client_file, et_email, et_flightNo;
    Driver driver;
    Admin admin;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_homepage);
        Intent intent = getIntent();
        driver = (Driver) intent.getSerializableExtra("driver");
        admin = (Admin) intent.getSerializableExtra("admin");
        et_client_file = (EditText) findViewById(R.id.et_client_file);
        et_flight_file = (EditText) findViewById(R.id.et_flight_file);
        et_email = (EditText) findViewById(R.id.et_clients_email);
        et_flightNo = (EditText) findViewById(R.id.et_flight_number);

    }

    public void onClickEditClientInfo(View v) {

        String email = et_email.getText().toString();

        // check if email exists in our database
        // if it does send it to the view/edit client page
        if (driver.getClientDatabase().getClient().keySet().contains(email)) {
            Intent intentEditClientProfile = new Intent(this, AdminEditClientInfo.class);
            Client client = driver.getClientDatabase().getClient().get(((EditText) findViewById(R.id.et_clients_email)).getText().toString());
            intentEditClientProfile.putExtra("driver", this.driver);
            intentEditClientProfile.putExtra("admin", admin);
            intentEditClientProfile.putExtra("client", client);
            startActivity(intentEditClientProfile);
        }
        else { // email does not exists in our database
            et_email.setText("Try again, e-mail does not exist.");
        }
    }

    public void onClickViewClientsItineraries(View v) {

        String email = et_email.getText().toString();

        // check if email exists in our database
        // if it does send it to the view/edit client page
        if (driver.getClientDatabase().getClient().keySet().contains(email)) {
            Intent intentViewClientItineraries = new Intent(this, AdminDisplaySearchedFlights.class);
            Client client = driver.getClientDatabase().getClient().get(((EditText) findViewById(R.id.et_clients_email)).getText().toString());
            intentViewClientItineraries.putExtra("driver", this.driver);
            intentViewClientItineraries.putExtra("admin", admin);
            intentViewClientItineraries.putExtra("client", client);
            startActivity(intentViewClientItineraries);
        }
        else { // email does not exists in our database
            et_email.setText("Try again, e-mail does not exist.");
        }
    }

    public void onClickAdminSearchFlights(View v) {

        String email = et_email.getText().toString();

        // check if email exists in our database
        // if it does send it to the view/edit client page
        if (driver.getClientDatabase().getClient().keySet().contains(email)) {
            Intent intentAdminSearchFlights = new Intent(this, AdminSearchFlights.class);
            Client client = driver.getClientDatabase().getClient().get(((EditText) findViewById(R.id.et_clients_email)).getText().toString());
            intentAdminSearchFlights.putExtra("driver", this.driver);
            intentAdminSearchFlights.putExtra("admin", admin);
            intentAdminSearchFlights.putExtra("client", client);
            startActivity(intentAdminSearchFlights);
        }
        else { // email does not exists in our database
            et_email.setText("Try again, e-mail does not exist.");
        }
    }

    public void onClickEditFlights(View v) {

        String flightNo = et_flightNo.getText().toString();

        // check if email exists in our database
        // if it does send it to the view/edit client page
        if (driver.getFlightDatabase().getFlights().keySet().contains(flightNo)) {
            Flight flight = driver.getFlightDatabase().getFlight((((EditText) findViewById(R.id.et_flight_number)).getText().toString()));
            Intent intentEditFlights = new Intent(this, AdminEditFlights.class);
            intentEditFlights.putExtra("driver", this.driver);
            intentEditFlights.putExtra("admin", admin);
            intentEditFlights.putExtra("flight", flight);
            startActivity(intentEditFlights);
        }
        else { // email does not exists in our database
            et_flightNo.setText("Try again, flight number does not exist.");
        }
    }

    public void onClickLoadClientFile(View v) {

        String fileName = et_client_file.getText().toString();
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + fileName);
        if (file.exists()) {
            driver.uploadClientInfo(fileName);

            Intent intentDisplayClientDB = new Intent(this, AdminDisplayClientDB.class);
            intentDisplayClientDB.putExtra(CLIENT_DB, driver.getClientDatabase());
            intentDisplayClientDB.putExtra("driver", this.driver);
            intentDisplayClientDB.putExtra("admin", admin);
            startActivity(intentDisplayClientDB);
        }
        else { // file does not exist
            et_client_file.setText("Try again. File does not exist.");
        }
    }

    public void onClickLoadFlightFile(View v) throws ParseException{

        String fileName = et_flight_file.getText().toString();
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + fileName);

        if (file.exists()) {
            driver.uploadFlightInfo(fileName);

            Intent intentDisplayFlightDB = new Intent(this, AdminDisplayFlightDB.class);
            intentDisplayFlightDB.putExtra(FLIGHT_DB, driver.getFlightDatabase());
            intentDisplayFlightDB.putExtra("driver", this.driver);
            intentDisplayFlightDB.putExtra("admin", admin);
            startActivity(intentDisplayFlightDB);
        }

        else { // file does not exist
            et_flight_file.setText("Try again. File does not exist.");
        }
    }

    public void onClickLoadAdminLogOut(View v) {
        Intent intentAdminLogout = new Intent(this, Login.class);
        intentAdminLogout.putExtra("driver", this.driver);
        startActivity(intentAdminLogout);
    }
}

