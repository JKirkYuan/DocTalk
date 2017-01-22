package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static cs.b07.cscb07courseproject.Login.doctorInfo;
import static cs.b07.cscb07courseproject.Login.doctorSubmissions;

/**
 * Created by maximkuzmenko on 2017-01-22.
 */

public class DoctorListView extends AppCompatActivity {

    Button submit;
    TextView listView1In, listView2In, listView3In;
    EditText doctorFeedbackIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String tempString = "";
        listView1In = (TextView) findViewById(R.id.listView1);
        listView2In = (TextView) findViewById(R.id.listView2);
        listView3In = (TextView) findViewById(R.id.listView3);
        Log.d("v", "1");


        setContentView(R.layout.doctorlistview);


        //doctorFeedbackIn = (EditText) findViewById(R.id.docInput);
       //Login.doctorSubmissions.add(doctorFeedbackIn.getText().toString()); //add to the end of the queue



    }
    public void logout(View v) {
        Intent intentLogout = new Intent(this, Login.class);
        startActivity(intentLogout);
    }
}
