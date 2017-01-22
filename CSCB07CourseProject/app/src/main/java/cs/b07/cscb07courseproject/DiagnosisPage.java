package cs.b07.cscb07courseproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static cs.b07.cscb07courseproject.Login.doctorInfo;
import static cs.b07.cscb07courseproject.Login.doctorSubmissions;

/**
 * Created by maximkuzmenko on 2017-01-22.
 */

public class DiagnosisPage extends AppCompatActivity{
    TextView listView1In, listView2In, listView3In;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.diagnosislistview);
        ListAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, doctorSubmissions);

        ListView listView = (ListView) findViewById(R.id.diagnosisListView);
        listView.setAdapter(adapter);





    }
}
