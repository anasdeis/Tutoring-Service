package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.mcgill.ecse321.tutoringservice.R;

public class Home extends AppCompatActivity {
    private Button toManagerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        toManagerLogin = (Button) findViewById(R.id.managerButton);
        toManagerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManagerLogin();
            }
        });
    }

    public void openManagerLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
