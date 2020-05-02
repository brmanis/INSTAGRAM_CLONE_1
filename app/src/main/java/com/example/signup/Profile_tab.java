package com.example.signup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_tab extends Fragment {
    private EditText edtPflName, edtpflBio,
            edtpflProfession, edtpflHobbies, edtpflFvrteSport;
    private Button btnUpdateInfo;

    public Profile_tab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtPflName = view.findViewById(R.id.edtProfileName);
        edtpflBio = view.findViewById(R.id.edtBio);
        edtpflProfession=view.findViewById(R.id.edtProfession);
        edtpflHobbies=view.findViewById(R.id.edtHobbies);
        edtpflFvrteSport=view.findViewById(R.id.edtFavoriteSports);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser.get("profileName")== null){
            edtPflName.setText("");
        }else{
            edtPflName.setText(parseUser.get("profileName").toString());

        }
        if(parseUser.get("profileBio")== null){
            edtpflBio.setText("");
        }else{
            edtpflBio.setText(parseUser.get("profileBio").toString());

        }
        if(parseUser.get("profileProfession")== null){
            edtpflProfession.setText("");
        }else{
            edtpflProfession.setText(parseUser.get("profileProfession").toString());

        }

        if(parseUser.get("profileHobbies")== null){
            edtpflHobbies.setText("");
        }else{
            edtpflHobbies.setText(parseUser.get("profileHobbies").toString());

        }
        if(parseUser.get("profileFavSport")== null){
            edtpflFvrteSport.setText("");
        }else{
            edtpflFvrteSport.setText(parseUser.get("profileFavSport").toString());

        }





        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName",edtPflName.getText().toString());
                parseUser.put("profileBio",edtpflBio.getText().toString());
                parseUser.put("profileProfession",edtpflProfession.getText().toString());
                parseUser.put("profileHobbies",edtpflHobbies.getText().toString());
                parseUser.put("profileFavSport",edtpflFvrteSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(getContext(), "Info Updated",
                                    Toast.LENGTH_SHORT,FancyToast.INFO, true).show();
                        }else{

                            FancyToast.makeText(getContext(), e.getMessage(),
                                    Toast.LENGTH_SHORT,FancyToast.ERROR, true).show();
                        }
                    }
                    });
        };
        });
        return view;
    }
}
