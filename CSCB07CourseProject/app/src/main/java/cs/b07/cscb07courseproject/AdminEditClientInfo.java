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

import backend.Admin;
import backend.Client;
import backend.Driver;

/**
 * Created by mandy on 2016-11-28.
 */

public class AdminEditClientInfo extends AppCompatActivity {

    Driver driver;
    Client client;
    Admin admin;
    EditText et_email, et_address, et_ccDate, et_ccNumber, et_firstName, et_lastName, et_password;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_edit_client_profile);
        Intent intent = getIntent();
        driver = (Driver) intent.getSerializableExtra("driver");
        admin = (Admin) intent.getSerializableExtra("Admin");
        client = (Client) intent.getSerializableExtra("client");

        et_email = (EditText) findViewById(R.id.et_admin_edit_email);
        et_address = (EditText) findViewById(R.id.et_admin_edit_address);
        et_ccDate = (EditText) findViewById(R.id.et_admin_edit_ccDate);
        et_ccNumber = (EditText) findViewById(R.id.et_admin_edit_ccNumber);
        et_firstName = (EditText) findViewById(R.id.et_admin_edit_firstName);
        et_lastName = (EditText) findViewById(R.id.et_admin_edit_lastName);
        et_password = (EditText) findViewById(R.id.et_admin_edit_password);

        et_email.setText(client.getEmail());
        et_address.setText(client.getAddress());
        et_ccNumber.setText(client.getCreditCardNumber());
        et_password.setText(client.getPassword());
        et_firstName.setText(client.getFirstName());
        et_lastName.setText(client.getLastName());

        // parse Date to string
        String Date = formatter.format(client.getCreditCardExpiry());
        et_ccDate.setText(Date);
    }

    public void backToHomepage(View v) {
        Intent intentBack = new Intent(this, AdminHomepage.class);
        intentBack.putExtra("client",this.client);
        intentBack.putExtra("driver", this.driver);
        intentBack.putExtra("admin", this.admin);
        startActivity(intentBack);
    }

    public void onClickUpdate(View v) {
        switch (v.getId()) {
            case R.id.b_admin_udpate_clientInfo:
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
                    Intent intentBack = new Intent(this, AdminHomepage.class);

                    // if the e-mail is the same, override the client
                    if (originalEmail.equals(client.getEmail())) {
                        driver.getClientDatabase().addClient(client);
                    }
                    else { // e-mail is different, add new one, delete the old email
                        driver.getClientDatabase().addClient(client);
                        driver.getClientDatabase().removeClient(originalEmail);
                    }

                    intentBack.putExtra("driver", this.driver);
                    intentBack.putExtra("admin", this.admin);
                    startActivity(intentBack);
                } catch (ParseException e) {
                    // Date not in correct yyyy-MM-dd format, prompt error message
                    e.printStackTrace();
                    et_ccDate.setText("Enter: yyyy-MM-dd");
                }
        }
    }
}


