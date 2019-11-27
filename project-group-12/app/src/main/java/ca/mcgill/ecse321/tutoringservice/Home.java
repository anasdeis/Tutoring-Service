package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ca.mcgill.ecse321.tutoringservice.R;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private Button toManagerLogin;
    private Button toStudentLogin;
    private Button toTutorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        toManagerLogin = findViewById(R.id.managerButton);
        toStudentLogin = findViewById(R.id.studentButton);
        toTutorLogin = findViewById(R.id.tutorButton);

        toTutorLogin.setOnClickListener(this);
        toManagerLogin.setOnClickListener(this);
        toStudentLogin.setOnClickListener(this);
    }

    public void openManagerLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.managerButton:
                openManagerLogin();
                break;
            case R.id.tutorButton:
                Toast.makeText(this, "Tutor Feature Not Applicable!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.studentButton:
                Toast.makeText(this, "Student Feature Not Applicable!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
