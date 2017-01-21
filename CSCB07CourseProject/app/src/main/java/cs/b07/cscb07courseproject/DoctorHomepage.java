package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import backend.Admin;
import backend.Client;
import backend.Driver;


/**
 * Created by mandy on 2016-11-28.
 */


public class DoctorHomepage extends AppCompatActivity {
    public static final String FLIGHT_DB = "flight_db";
    public static final String CLIENT_DB = "client_db";
    EditText et_flight_file, et_client_file, et_email, et_flightNo;
    Driver driver;
    Admin admin;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_homepage);
        Intent intent = getIntent();
        driver = (Driver) intent.getSerializableExtra("driver");
        admin = (Admin) intent.getSerializableExtra("admin");
        et_email = (EditText) findViewById(R.id.et_clients_email);


    }




    public void onClickLoadAdminLogOut(View v) {
        Intent intentAdminLogout = new Intent(this, Login.class);
        intentAdminLogout.putExtra("driver", this.driver);
        startActivity(intentAdminLogout);
    }
}

