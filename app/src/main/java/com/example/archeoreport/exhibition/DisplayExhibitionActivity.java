package com.example.archeoreport.exhibition;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DisplayExhibitionActivity extends AppCompatActivity {

    EditText  nameTxt, locationTxt, dateTxt;
    List<ListOfExhibitions> exhibitions = new ArrayList<>();
    ListView exhibitionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_exhibition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        nameTxt = (EditText) findViewById(R.id.exhibitionTxt);
        locationTxt = (EditText) findViewById(R.id.locationTxt);
        dateTxt = (EditText) findViewById(R.id.dateTxt);
        exhibitionListView = (ListView) findViewById(R.id.listView);
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabCreator);
        tabSpec.setIndicator("Create a New Exhibition");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabExibitionStore);
        tabSpec.setIndicator("List of Exhibitions");
        tabHost.addTab(tabSpec);

        final Button createBtn = (Button) findViewById(R.id.exhibitionButton);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExhibition(nameTxt.getText().toString(), locationTxt.getText().toString(), dateTxt.getText().toString());
                populateList();
                Toast.makeText(getApplicationContext(),nameTxt.getText().toString() + " has been added to your exhibition!", Toast.LENGTH_SHORT).show();
            }
        });

        nameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //enable button when text is entered
                createBtn.setEnabled(!nameTxt.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private class ExhibitionListAdapter extends ArrayAdapter<ListOfExhibitions>{
        public ExhibitionListAdapter(){
            super(DisplayExhibitionActivity.this, R.layout.listview_item, exhibitions);
        }

        //adding an element to our list
        @Override
        public View getView(int position, View view, ViewGroup parent){
            if(view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            ListOfExhibitions currentExhibition = exhibitions.get(position);

            TextView name = (TextView) view.findViewById(R.id.exhibitName);
            name.setText(currentExhibition.getName());

            TextView location = (TextView) view.findViewById(R.id.location);
            location.setText(currentExhibition.getLocation());

            TextView date = (TextView) view.findViewById(R.id.date);
            date.setText(currentExhibition.getDate());

            return view;
        }
    }

    private void addExhibition(String name, String location, String date){
        exhibitions.add(new ListOfExhibitions(name, location, date));
    }

    private void populateList(){
        ArrayAdapter<ListOfExhibitions> adapter = new ExhibitionListAdapter();
        exhibitionListView.setAdapter(adapter);
    }

    public void enterReportScreen(View view)
    {
        Intent startNewActivity = new Intent(this, ReportNavigation.class);
        startActivity(startNewActivity);
    }


}
