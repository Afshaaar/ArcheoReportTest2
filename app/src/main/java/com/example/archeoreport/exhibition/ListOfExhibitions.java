package com.example.archeoreport.exhibition;

/**
 * Created by Mo on 09/03/2016.
 */
public class ListOfExhibitions {

    private String _name, _location, _date;

    public ListOfExhibitions(String name, String location, String date){
        _name = name;
        _location = location;
        _date = date;
    }

    public String getName(){
        return _name;
    }

    public String getLocation(){
        return _location;
    }

    public String getDate(){
        return _date;
    }
}
