package cs.b07.cscb07courseproject;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import backend.Doctor;
import backend.Patient;
import backend.Driver;
/**
 * Created by vincent on 2017-01-21.
 */

public class MedRequest extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.med_request);

        Intent intent = getIntent();


        //setUpPage();
    }


}
