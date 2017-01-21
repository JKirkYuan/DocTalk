package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import backend.Client;
import backend.ClientDatabase;
import backend.Driver;

public class RegisteredClient extends AppCompatActivity {

    Driver driver;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_registered_finish);
        Intent intent = getIntent();
        driver = (Driver) intent.getSerializableExtra("driver");
        client = (Client) intent.getSerializableExtra("client");
    }

    public void backToHomepage(View view) {
        Intent intent = new Intent(this, ClientHomepage.class);
        intent.putExtra("driver", driver);
        intent.putExtra("client", client);
        startActivity(intent);
    }
}