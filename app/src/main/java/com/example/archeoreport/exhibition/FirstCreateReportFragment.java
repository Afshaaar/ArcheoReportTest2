package com.example.archeoreport.exhibition;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

/**
 * Created by Mo on 10/03/2016.
 */


public class FirstCreateReportFragment extends Fragment {
    View myView;
    ImageView artifactImageView;
    //Spinner spinner;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.report_screen, container, false);
        artifactImageView = (ImageView) myView.findViewById(R.id.addImageArtifact);

        //spinner = (Spinner) getActivity().findViewById(R.id.spinner1);

        //String[] defects = new String[]{"defect1", "defect2", "defect3", "defect4", "defect5"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, defects);
        //spinner.setAdapter(adapter);

        artifactImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select artifact image"), 1);
            }

        });
        return myView;
        }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 1)
                artifactImageView.setImageURI(data.getData());
        }
    }



    }





