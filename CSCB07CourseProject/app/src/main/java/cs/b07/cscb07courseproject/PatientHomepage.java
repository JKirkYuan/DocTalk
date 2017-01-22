package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import backend.Patient;
import backend.Driver;
import backend.Flight;
import backend.Itinerary;
import backend.Trip;

import static android.R.attr.button;

public class PatientHomepage extends AppCompatActivity {

    private Driver driver;
    private Patient patient;
    private ListView lv;
    ArrayAdapter<String> adapter;
    private ArrayList<Itinerary> result;
    private TextView patientInput;
    private String toDatabase;
    private Button sendButton, diagnosticsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_homepage);

        //patientInput = (TextView) findViewById(R.id.patientInput);

        Intent intent = getIntent();

        diagnosticsButton = (Button) findViewById(R.id.viewDiagnosis);

//        this.patient = (Patient) intent.getSerializableExtra("patient");
//        this.driver = (Driver) intent.getSerializableExtra("driver");

//        sendButton = (Button) findViewById(R.id.sendButton);
//        sendButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                toDatabase = patientInput.getText().toString();
//
//            }
//        });

        diagnosticsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                seeDiagnostics(v);
            }
        });

    }

    public void seeDiagnostics(View v) {
        Intent intentDiagnosisPage = new Intent(this, DiagnosisPage.class);
        intentDiagnosisPage.putExtra("driver", driver);
        startActivity(intentDiagnosisPage);
    }

    public void onNewIntent(Intent intent){
        // grab all the necessary data for a refresh and redo the page

        this.patient = (Patient) intent.getSerializableExtra("patient");
        this.driver = (Driver) intent.getSerializableExtra("driver");
    }


    public void makeMedRequest(View v){
        Intent MakeMedRequest = new Intent(this, MedRequest.class);
        MakeMedRequest.putExtra("driver", driver);
        startActivity(MakeMedRequest);
    }

    public void Logout(View v) {
        Intent intentLogout = new Intent(this, Login.class);
        intentLogout.putExtra("driver", driver);
        startActivity(intentLogout);
    }

}

