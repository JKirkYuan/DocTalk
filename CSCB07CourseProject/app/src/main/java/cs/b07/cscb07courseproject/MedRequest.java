package cs.b07.cscb07courseproject;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.SparseBooleanArray;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private RatingBar ratingBarIn;
    private EditText commentsIn;

    private RelativeLayout mRelativeLayout;
    private ListView mListView;
    private TextView mTextView;
    private ArrayList<String> selection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.med_request);

        selection = new ArrayList<String>();
        // Get the application context
        mContext = getApplicationContext();
        // Get the activity
        mActivity = MedRequest.this;

        ratingBarIn = (RatingBar) findViewById(R.id.ratingBar);
        commentsIn = (EditText) findViewById(R.id.comments);


        // Get the widgets reference from XML layout
        mListView = (ListView) findViewById(R.id.lv);
        mTextView = (TextView) findViewById(R.id.tv);


        //setUpPage();
        List<String> trees = Arrays.asList(
                "Head",
                "Chest",
                "Abdomen",
                "Hips",
                "Legs",
                "Feet"
        );

        // Initialize a new ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter(
                mActivity,
                android.R.layout.simple_list_item_multiple_choice,
                trees
        );

        // Set the adapter for ListView
        mListView.setAdapter(adapter);
        selection.clear();


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
                        selection.add(item);
                    }
                }
            }
        });
    }
    // Return object designed by Maxim here
    public void sendMedRequest (View v) {
        selection.add(String.valueOf(ratingBarIn.getRating()));
        if(commentsIn.getText().toString() != "") {
            selection.add(commentsIn.getText().toString());
        }
        Log.d("v", selection.toString());
        Intent intentFinishRequest = new Intent(this, FinishMedRequest.class);
        startActivity(intentFinishRequest);
        selection.clear();
    }

}




