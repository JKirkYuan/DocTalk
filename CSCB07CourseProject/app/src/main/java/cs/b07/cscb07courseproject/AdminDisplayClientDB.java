package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import backend.ClientDatabase;
import backend.Driver;

/**
 * Created by mandy on 2016-11-29.
 */

public class AdminDisplayClientDB extends AppCompatActivity{

    private ClientDatabase clientDatabase;
    private Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_display_clientdb);

        // get the Intent that launched me
        Intent intent = getIntent();
        clientDatabase = (ClientDatabase) intent.getSerializableExtra(AdminHomepage.CLIENT_DB);
        driver = (Driver) intent.getSerializableExtra("driver");

        String content = clientDatabase.toString();
        TextView view = (TextView) findViewById(R.id.clientDB_info);
        view.setText(content);

    }

}
