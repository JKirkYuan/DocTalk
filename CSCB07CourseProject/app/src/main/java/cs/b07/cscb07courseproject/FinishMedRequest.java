package cs.b07.cscb07courseproject;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.SparseBooleanArray;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Arrays;
import java.util.List;

import backend.Doctor;
import backend.Patient;
import backend.Driver;

/**
 * Created by vincent on 2017-01-22.
 */

public class FinishMedRequest extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.med_request_complete);
        Intent intent = getIntent();
    }

    public void finishRequest (View v) {
        Intent intentFinishRequest = new Intent(this, PatientHomepage.class);
        startActivity(intentFinishRequest);
    }
}
