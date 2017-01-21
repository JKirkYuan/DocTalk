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


    public void onClickLoadAdminLogOut(View v) {
        Intent intentAdminLogout = new Intent(this, Login.class);
        intentAdminLogout.putExtra("driver", this.driver);
        startActivity(intentAdminLogout);
    }
}

