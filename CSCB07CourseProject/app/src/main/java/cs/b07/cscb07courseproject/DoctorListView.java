package cs.b07.cscb07courseproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    TextView doctorFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.doctorlistview);
        ListAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, doctorInfo);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        doctorFeedback = (TextView) findViewById(R.id.docInput);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Login.doctorSubmissions.add(doctorFeedback.getText().toString()); //add to the end of the queue
            }
        });
    }
}
