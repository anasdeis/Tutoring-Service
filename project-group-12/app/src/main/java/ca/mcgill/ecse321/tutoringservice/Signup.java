package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    private Button homeButton;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        homeButton = findViewById(R.id.homeButton);
        createButton = findViewById(R.id.createButton);

        homeButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
    }

    public void openHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openHome1() {
        Intent intent = new Intent(this, Home1.class);
        startActivity(intent);
        Login login = new Login();
        login.accounts.add(new Account("Test", "123456"));
        Toast.makeText(this, "Manager Account Created!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeButton:
                openHome();
                break;
            case R.id.createButton:
                openHome1();
                break;
        }
    }
}
