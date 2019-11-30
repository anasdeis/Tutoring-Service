package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class login extends AppCompatActivity implements View.OnClickListener {

  private String userName, password;

  private EditText userNameInput;
  private EditText passwordInput;

  private Button loginButton;
  private Button homeButton;
  private Button signupButton;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    userNameInput = findViewById(R.id.userNameInput);
    passwordInput = findViewById(R.id.passwordInput);

    loginButton = findViewById(R.id.managerLoginButton);
    homeButton = findViewById(R.id.homeButton);
    signupButton = findViewById(R.id.signUpButton);

    loginButton.setOnClickListener(this);
    homeButton.setOnClickListener(this);
    signupButton.setOnClickListener(this);
    refreshErrorMessage();
  }

  private String error = null;
  private void refreshErrorMessage() {
    // set error message
    TextView tvError = (TextView) findViewById(R.id.error);
    tvError.setError(error);

    if (error == null || error.length() == 0) {
      tvError.setVisibility(View.GONE);
    } else {
      tvError.setVisibility(View.VISIBLE);
    }
  }

  public void openManagerHome() {
    Intent intent = new Intent(this, Menu.class);
    startActivity(intent);
  }

  public void openHome() {
    Intent intent = new Intent(this, MainActivity.class);
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
