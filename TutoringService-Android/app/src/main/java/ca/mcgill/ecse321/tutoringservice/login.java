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

/**
 * this class contain the functionalities on a the login page, including the button to jump to Manager Home,
 * Signup page and Regular Home Page, and the backend call to login to our database
 */
public class login extends AppCompatActivity implements View.OnClickListener {


  private EditText userNameInput;
  private EditText passwordInput;

  private Button loginButton;
  private Button homeButton;
  private Button signupButton;


  private String error = null;

  /**
   * this method is used for refresh the error message with error handling
   * but never been used
   */
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

  /**
   * this method is the implementation of the login function with our backend business method call
   *
   * @param v
   */
  public void login(View v) {
    error = "";
    final TextView tv = (TextView) findViewById(R.id.userNameInput);
    final String username = tv.getText().toString();
    final TextView tv1 = (TextView) findViewById(R.id.passwordInput);
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

  /**
   * onCreate method which is called automatically when create login activity
   *
   * @param savedInstanceState
   */
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

  /**
   * The following three methods are used for the jump between different pages, which means different
   * activities in android
   */
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

  /**
   * this method is an override method with onClick, will check different cases and then preform
   * different operations
   *
   * @param v
   */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.managerLoginButton:
        login(v);
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
