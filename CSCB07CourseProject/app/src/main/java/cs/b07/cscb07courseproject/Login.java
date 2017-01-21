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

public class Login extends AppCompatActivity {

    Button bLogin;
    EditText et_email, et_password;
    Driver driver = null;
    Patient patient;
    Doctor doctor;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // see if this is a resumed web page, if not then create the driver

        this.driver = new Driver(Environment.getExternalStorageDirectory().getAbsolutePath() + "/flightstorage.ser", Environment.getExternalStorageDirectory().getAbsolutePath() + "/clientstorage.ser");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_EXTERNAL_STORAGE);
    }

    public void onNewIntent(Intent intent){
        this.driver = (Driver) intent.getSerializableExtra("driver");
    }


    public void onClickLogin(View v) {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        //check if the email exists in our database
        //if it does send it to the patient home page
        if (email.equals("doctor") && password.equals("1234")) {
            Intent intentLoginAdmin = new Intent(this, DoctorHomepage.class);
            intentLoginAdmin.putExtra("doctor", this.doctor);
            intentLoginAdmin.putExtra("driver", this.driver);
            startActivity(intentLoginAdmin);
        }

        else if (email.equals("patient") && password.equals("1234")) {
            Intent intentLogin = new Intent(this, PatientHomepage.class);
            intentLogin.putExtra("patient", patient);
            Log.d("f", "This is my message");
            intentLogin.putExtra("driver", this.driver);
            startActivity(intentLogin);
        }
        // Uncomment and implement with database
//        else if(driver.getClientDatabase().getClient().keySet().contains(email)) {
//            patient = driver.getClient(email);
//            if (patient.getPassword().equals(password)) {
//                Intent intentLogin = new Intent(this, PatientHomepage.class);
//                intentLogin.putExtra("patient", patient);
//                intentLogin.putExtra("driver", this.driver);
//                startActivity(intentLogin);
//            }
//            else{
//                et_email.setText("Try again");
//            }
//        }
        else {
            et_email.setText("Account doesn't exist");
        }
    }
}

