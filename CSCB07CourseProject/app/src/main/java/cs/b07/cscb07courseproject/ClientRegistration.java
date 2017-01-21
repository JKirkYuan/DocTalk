package cs.b07.cscb07courseproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import backend.Client;
import backend.Driver;

public class ClientRegistration extends AppCompatActivity {

    EditText et_email, et_firstName, et_lastName, et_address, et_ccNumber, et_ccDate, et_password;
    Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_registration);

        Intent intent = getIntent();
        et_email = (EditText) findViewById(R.id.et_email);
        et_firstName = (EditText) findViewById(R.id.et_firstName);
        et_lastName = (EditText) findViewById(R.id.et_lastName);
        et_address = (EditText) findViewById(R.id.et_address);
        et_ccNumber = (EditText) findViewById(R.id.et_ccNumber);
        et_ccDate = (EditText) findViewById(R.id.et_ccDate);
        et_password = (EditText) findViewById(R.id.et_password);
        driver = (Driver) intent.getSerializableExtra("driver");

    }

    public void register(View v) throws ParseException {
        String email = et_email.getText().toString();
        String firstName = et_firstName.getText().toString();
        String lastName = et_lastName.getText().toString();
        String address = et_address.getText().toString();
        String ccNumber = et_ccNumber.getText().toString();
        String ccDate = et_ccDate.getText().toString();
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date ccDateobj = date.parse(ccDate);
        String password = et_password.getText().toString();
        Client registeredClient = new Client(email, firstName, lastName, address, password, ccNumber, ccDateobj);

        Intent intent = new Intent(this, RegisteredClient.class);
        driver.getClientDatabase().addClient(registeredClient);
        intent.putExtra("driver", this.driver);
        intent.putExtra("client", registeredClient);
        startActivity(intent);
    }
}

