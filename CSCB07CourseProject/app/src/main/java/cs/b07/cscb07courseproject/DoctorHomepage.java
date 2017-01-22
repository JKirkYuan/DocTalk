package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import backend.Doctor;
import backend.Patient;
import backend.Driver;


/**
 * Created by mandy on 2016-11-28.
 */


public class DoctorHomepage extends AppCompatActivity {
    public static final String FLIGHT_DB = "flight_db";
    public static final String CLIENT_DB = "client_db";
    EditText et_flight_file, et_client_file, et_email, et_flightNo;
    Driver driver;
    Doctor doctor;
    Patient patient;
    Button listViewGetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_homepage);
        Intent intent = getIntent();
        driver = (Driver) intent.getSerializableExtra("driver");
        doctor = (Doctor) intent.getSerializableExtra("doctor");
        et_email = (EditText) findViewById(R.id.et_clients_email);

        //fetch and display all the info
        listViewGetter = (Button) findViewById(R.id.listViewGetter);
        listViewGetter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                seeRecords(v);
            }
        });
    }

    public void seeRecords(View v) {
        Intent intentListView = new Intent(this, DoctorListView.class);
        intentListView.putExtra("driver", driver);
        startActivity(intentListView);
    }

    public void Logout(View v) {
        Intent intentLogout = new Intent(this, Login.class);
        intentLogout.putExtra("driver", driver);
        startActivity(intentLogout);
    }
}

