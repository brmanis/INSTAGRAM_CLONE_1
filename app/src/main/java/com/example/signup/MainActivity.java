package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEmail,edtUN,edtPS;
    private Button btnSignUp,btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Instagram Clone");

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("iMDHMXDUqirLynHbcxWFkxnozOdEcYnyvE56MvId")
                .clientKey("uPUS5zlcWmCWYFPKERdxp8NtB4YfuWJi5eFq0yqM")
                .server("https://parseapi.back4app.com/")
                .build()
        );

        edtEmail=findViewById(R.id.edtEnterEmail);
        edtUN=findViewById(R.id.edtEntreUsername);
        edtPS=findViewById(R.id.edtEnterWord);

        edtPS.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode ==KeyEvent.KEYCODE_ENTER &&
                        event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);
                }
                return false;
            }
        });
        btnSignUp=findViewById(R.id.btnSignUp);
        btnLogIn=findViewById(R.id.btnLogin);
        btnSignUp.setOnClickListener(MainActivity.this);
        btnLogIn.setOnClickListener(MainActivity.this);

        if(ParseUser.getCurrentUser() !=null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

                }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnSignUp:
                if(edtEmail.getText().toString().equals("") ||
                        edtUN.getText().toString().equals("") ||
                        edtPS.getText().toString().equals("")){
                    FancyToast.makeText(MainActivity.this,"Email, Username, Password are required", Toast.LENGTH_SHORT,FancyToast.INFO,true).show();

                }else{


                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUN.getText().toString());
                appUser.setPassword(edtPS.getText().toString());
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up " + edtUN.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(MainActivity.this,appUser.getUsername() + " is signed up ",
                                    Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                            transitionToSocialMediaActivity();

                        }else{
                            FancyToast.makeText(MainActivity.this,"There was an error: "
                                    +e.getMessage(),Toast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                        progressDialog.dismiss();
                    }
                });
                }
                break;
            case R.id.btnLogin:
                Intent intent = new Intent(MainActivity.this, Login_Activity.class);
                startActivity(intent);

                break;

        }

    }
    public void rootLayoutTapped(View view){

        try{InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch(Exception e){
            e.printStackTrace();
        }
            }

            private void transitionToSocialMediaActivity(){
                Intent intent = new Intent(MainActivity.this, SOCIAL_MEDIA_ACTIVITY.class);
                startActivity(intent);
            }
}
