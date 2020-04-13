package com.bassemsaleh.flashchatnewfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO: Add member variables here:
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);
        Button mSignInButton = findViewById(R.id.login_sign_in_button);
        mSignInButton.setOnClickListener(this);
        Button mRegisterButton = findViewById(R.id.login_register_button);
        mRegisterButton.setOnClickListener(this);


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // TODO: Grab an instance of FirebaseAuth

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_sign_in_button:
                attemptLogin();
                break;
            case R.id.login_register_button:
                registerNewUser();
                break;
        }
    }

    // Executed when Sign in button pressed
    public void signInExistingUser()   {
        // TODO: Call attemptLogin() here
        attemptLogin();
    }

    // Executed when Register button pressed
    public void registerNewUser() {
        Intent intent = new Intent(this, com.bassemsaleh.flashchatnewfirebase.RegisterActivity.class);
        finish();
        startActivity(intent);
    }

    // TODO: Complete the attemptLogin() method
    private void attemptLogin() {


        // TODO: Use FirebaseAuth to sign in with email & password



    }


    // TODO: Show error on screen with an alert dialog



}