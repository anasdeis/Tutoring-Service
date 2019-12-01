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
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity implements View.OnClickListener {


  private EditText userNameInput;
  private EditText passwordInput;

  private Button loginButton;
  private Button homeButton;
  private Button signupButton;


  private String error = null;

  private void refreshErrorMessage() {
    // set the error message
    TextView tvError = (TextView) findViewById(R.id.error);
    tvError.setText(error);

    if (error == null || error.length() == 0) {
      tvError.setVisibility(View.GONE);
    } else {
      tvError.setVisibility(View.VISIBLE);
    }
  }

  public void addLogin(View v) {
    error = "";
     final TextView tv = (TextView) findViewById(R.id.userNameInput);
     final String username = tv.getText().toString();
      final  TextView tv1 = (TextView) findViewById(R.id.passwordInput);
     final String password = tv1.getText().toString();

     RequestParams rp = new RequestParams();
      rp.add("password", password);

    HttpUtils.post("login/" + username, rp, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//        refreshErrorMessage();
        tv.setText("");
        tv1.setText("");

      }
      @Override
      public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        try {
          error += errorResponse.get("message").toString();
        } catch (JSONException e) {
          error += e.getMessage();
        }
        //refreshErrorMessage();
      }
    });
  }

  public void login(View v) {
    error = "";
    final TextView tv = (TextView) findViewById(R.id.userNameInput);
    final String username = tv.getText().toString();
    final  TextView tv1 = (TextView) findViewById(R.id.passwordInput);
    final String password = tv1.getText().toString();

    RequestParams rp = new RequestParams();
    rp.add("password", password);

    HttpUtils.get("login/check/" + username, rp, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//        refreshErrorMessage();
        tv.setText("");
        tv1.setText("");
        openManagerHome();
      }
      @Override
      public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        try {
          error += errorResponse.get("message").toString();
        } catch (JSONException e) {
          error += e.getMessage();
        }
        //refreshErrorMessage();
      }
    });
  }

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
//    refreshErrorMessage();
  }

//  private String error = null;
//  private void refreshErrorMessage() {
//    // set error message
//    TextView tvError = (TextView) findViewById(R.id.error);
//    tvError.setError(error);
//
//    if (error == null || error.length() == 0) {
//      tvError.setVisibility(View.GONE);
//    } else {
//      tvError.setVisibility(View.VISIBLE);
//    }
//  }

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
     //   userName = userNameInput.getText().toString();
      //  password = passwordInput.getText().toString();
        login(v);
      //  openManagerHome();
        break;
      case R.id.homeButton:
        openHome();
        break;
      case R.id.signUpButton:
        addLogin(v);
        openSignup();
        break;
    }
  }
}
