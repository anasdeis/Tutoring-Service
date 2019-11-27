package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.mcgill.ecse321.tutoringservice.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
    String userName, password;

    EditText userNameInput;
    EditText passwordInput;

    Button loginButton;
    Button homeButton;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);

        loginButton = findViewById(R.id.managerLoginButton);
        homeButton = findViewById(R.id.homeButton);
        signupButton = findViewById(R.id.signUpButton);

        loginButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);


    }

    public void openManagerHome() {
        Intent intent = new Intent(this, Home1.class);
        startActivity(intent);
    }

    public void openHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openSignup() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.managerLoginButton:
                userName = userNameInput.getText().toString();
                password = passwordInput.getText().toString();
                openManagerHome();
                break;
            case R.id.homeButton:
                openHome();
                break;
            case R.id.signUpButton:
                openSignup();
                break;
        }
    }
}
