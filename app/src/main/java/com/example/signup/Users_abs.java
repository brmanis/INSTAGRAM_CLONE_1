package com.example.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Users_abs extends Fragment {

    private ListView listview;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;
    public Users_abs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_abs, container, false);

        listview= view.findViewById(R.id.listView);
        arrayList= new ArrayList();
        final TextView txtLoadingUsers = view.findViewById(R.id.txtLoadingData);
        arrayAdapter=new ArrayAdapter(getContext(),
                android.R.layout.simple_list_item_1, arrayList);

        ParseQuery <ParseUser> parseQuery = ParseUser.getQuery();

        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if(e==null){
                    if(users.size()>0){
                        for(ParseUser user:users){
                            arrayList.add(user.getUsername());

                            //ArrayAdapter will stand between the arraylist and the data.
                        }
                        listview.setAdapter(arrayAdapter);
                        txtLoadingUsers.animate().alpha(0).setDuration(2000);
                        listview.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        return view;
    }
}
