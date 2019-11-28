package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home1 extends AppCompatActivity implements View.OnClickListener {
    private Button toManagerLogin;
    private Button toViewTutor;
    private Button toViewTutorApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home1);

        toManagerLogin = findViewById(R.id.logoutButton);
        toViewTutor = findViewById(R.id.viewTutorButton);
        toViewTutorApplication = findViewById(R.id.viewTutorApplicationButton);
        toManagerLogin.setOnClickListener(this);
        toViewTutor.setOnClickListener(this);
        toViewTutorApplication.setOnClickListener(this);
    }

    public void openManagerLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void openViewTutor() {
        Intent intent = new Intent(this, ViewTutor.class);
        startActivity(intent);
    }

    public void openViewTutorApplication() {
        Intent intent = new Intent(this, ViewTutorApplication.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logoutButton:
                openManagerLogin();
                break;
            case R.id.viewTutorButton:
                openViewTutor();
                break;
            case R.id.viewTutorApplicationButton:
                openViewTutorApplication();
                break;
        }
    }
}
