package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import ca.mcgill.ecse321.tutoringservice.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
    public static ArrayList<Account> accounts = new ArrayList<>();

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

        accounts.add(new Account("CharlesLiu", "CharlesLiu"));
        accounts.add(new Account("MenglinHe", "MenglinHe"));
        accounts.add(new Account("AnasDeis", "AnasDeis"));

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
                boolean hasAccount = false;
                for(Account account: accounts) {
                    if(account.getUserName().equals(userName) && account.getPassword().equals(password)) {
                        hasAccount = true;
                        openManagerHome();
                    }
                }
                if(!hasAccount)
                    Toast.makeText(this, "Account No Found!", Toast.LENGTH_SHORT).show();

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
