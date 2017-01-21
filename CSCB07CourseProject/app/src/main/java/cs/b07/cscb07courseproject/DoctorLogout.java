package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import backend.Driver;

/**
 * Created by mandy on 2016-11-29.
 */

public class DoctorLogout extends AppCompatActivity {

    Driver driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Intent intent = getIntent();
        driver = (Driver) intent.getSerializableExtra("driver");
    }
}
