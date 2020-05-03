package com.example.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUserName,edtPassword;
    private Button btnLin,btnSin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTitle("LOGIN");


        edtUserName=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassword);
        btnLin=findViewById(R.id.LogIn);
        btnSin=findViewById(R.id.SIgnUP);

        btnLin.setOnClickListener(Login_Activity.this);
        btnSin.setOnClickListener(Login_Activity.this);

        if(ParseUser.getCurrentUser() !=null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();

        }


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.LogIn:
                ParseUser.logInInBackground(edtUserName.getText().toString(),
                        edtPassword.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if(user !=null && e ==null){
                                    FancyToast.makeText(Login_Activity.this,
                                            user.getUsername() + " is Logged in Successfully",
                                            Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                                            transitionToSocialMediaActivity();
                                }else{

                                    FancyToast.makeText(Login_Activity.this,
                                            e.getMessage(),
                                            Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                                    //transitionToSocialMediaActivity();
                                }
                            }
                        });

                break;

            case R.id.SIgnUP:
                Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }

    public void rootLayoutTapped(View view){

        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(Login_Activity.this, Social_Media_Activity.class);
        startActivity(intent);
        finish();
    }
}