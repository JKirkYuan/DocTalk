package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import backend.Client;
import backend.Driver;

/**
 * Created by Ciel on 2016-11-26.
 */

public class EditProfile extends AppCompatActivity{

    private Driver driver;
    private Client client;
    EditText et_email,et_address, et_ccDate, et_ccNumber, et_firstName, et_lastName, et_password;
    String email;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_client);
        Intent intent = getIntent();
        driver = (Driver) intent.getSerializableExtra("driver");
        client = (Client) intent.getSerializableExtra("client");

        et_email = (EditText) findViewById(R.id.et_email);
        et_address = (EditText) findViewById(R.id.et_address);
        et_ccDate= (EditText) findViewById(R.id.et_ccdate);
        et_ccNumber = (EditText) findViewById(R.id.et_ccnum);
        et_firstName = (EditText) findViewById(R.id.et_firstname);
        et_lastName = (EditText) findViewById(R.id.et_lastname);
        et_password = (EditText) findViewById(R.id.et_password);

        et_email.setText(client.getEmail());
        et_address.setText(client.getAddress());

        String Date = formatter.format(client.getCreditCardExpiry());
        et_ccDate.setText(Date);

        et_ccNumber.setText(client.getCreditCardNumber());
        et_password.setText(client.getPassword());
        et_firstName.setText(client.getFirstName());
        et_lastName.setText(client.getLastName());

    }

    public void backToHomepage(View v) {
        Intent intentBack = new Intent(this, ClientHomepage.class);
        //check if the email exists in our database
        //if it does send it to the client home page
        intentBack.putExtra("driver", driver);
        intentBack.putExtra("client", client);
        startActivity(intentBack);
    }
    public void updateProfile(View v) {
        switch (v.getId()) {
            case R.id.b_udpate:
                String originalEmail = client.getEmail();

                client.setEmail(et_email.getText().toString());
                client.setFirstName(et_firstName.getText().toString());
                client.setLastName(et_lastName.getText().toString());
                client.setPassword(et_password.getText().toString());
                client.setAddress(et_address.getText().toString());
                client.setCreditCardNumber(et_ccNumber.getText().toString());

                String ccDate = et_ccDate.getText().toString();

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date ccDate2 = (Date)formatter.parse(ccDate);
                    client.setCreditCardExpiry(ccDate2);
                    Intent intentBack = new Intent(this, ClientHomepage.class);

                    // if the e-mail is the same, override the client
                    if (originalEmail.equals(client.getEmail())) {
                        driver.getClientDatabase().addClient(client);
                    }
                    else { // e-mail is different, add new one, delete the old email
                        driver.getClientDatabase().addClient(client);
                        driver.getClientDatabase().removeClient(originalEmail);
                    }

                    intentBack.putExtra("driver", this.driver);
                    intentBack.putExtra("client", this.client);
                    startActivity(intentBack);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // if the e-mail is the same, override the client
                if (originalEmail.equals(client.getEmail())) {
                    driver.getClientDatabase().addClient(client);
                }
                else { // e-mail is different, add new one, delete the old email
                    driver.getClientDatabase().addClient(client);
                    driver.getClientDatabase().removeClient(originalEmail);
                }
                Intent intentBack = new Intent(this, ClientHomepage.class);
                intentBack.putExtra("driver", this.driver);
                intentBack.putExtra("client", this.client);

                startActivity(intentBack);
        }

    }
}

