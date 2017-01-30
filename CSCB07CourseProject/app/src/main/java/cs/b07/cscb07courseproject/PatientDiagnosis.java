package cs.b07.cscb07courseproject;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.ArrayList;

import backend.Driver;

/**
 * Created by vincent on 2017-01-24.
 */

public class PatientDiagnosis extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // see if this is a resumed web page, if not then create the driver

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }
}
