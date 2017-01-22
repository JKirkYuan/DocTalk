package cs.b07.cscb07courseproject;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import backend.Doctor;
import backend.Patient;
import backend.Driver;
/**
 * Created by vincent on 2017-01-21.
 */

public class MedRequest extends AppCompatActivity{

    private Context mContext;
    private Activity mActivity;

    private RelativeLayout mRelativeLayout;
    private ListView mListView;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.med_request);

        Intent intent = getIntent();

        // Get the application context
        mContext = getApplicationContext();


        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mListView = (ListView) findViewById(R.id.lv);
        mTextView = (TextView) findViewById(R.id.tv);


        //setUpPage();
        List<String> trees = Arrays.asList(
                "Alder",
                "Basswood",
                "Birch",
                "Buckeye",
                "Cedar",
                "Cherry",
                "Chestnut",
                "Hawthorn",
                "Cypress",
                "Honeylocust"
        );
        // Set the adapter for ListView
        mListView.setAdapter(adapter);

        // Set an item click listener for the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SparseBooleanArray clickedItemPositions = mListView.getCheckedItemPositions();

                // Set the TextView text
                mTextView.setText("Checked items - ");

                for(int index=0;index<clickedItemPositions.size();index++){
                    // Get the checked status of the current item
                    boolean checked = clickedItemPositions.valueAt(index);

                    if(checked){
                        // If the current item is checked
                        int key = clickedItemPositions.keyAt(index);
                        String item = (String) mListView.getItemAtPosition(key);

                        // Display the checked items on TextView
                        mTextView.setText(mTextView.getText() + item + " | ");
                    }
                }
            }
        });
    }
    }



}
