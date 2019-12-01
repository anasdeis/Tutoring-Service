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

  private EditText username;
  private EditText password;
  private Button loginBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    username = findViewById(R.id.userName);
    password = findViewById(R.id.password);
    loginBtn = findViewById(R.id.Login);
    loginBtn.setOnClickListener(this);


    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
      }
    });
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

  private void login(View view) {
    error = "";
    final TextView tv_username = (TextView) findViewById(R.id.userName);
    final String username = tv_username.getText().toString();
    final TextView  tv_password = (TextView) findViewById(R.id.password);
    final String password = tv_password.getText().toString();

    RequestParams rp = new RequestParams();
    rp.add("password", password);


    HttpUtils.get("login/list/" + username, rp, new JsonHttpResponseHandler(){
      @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        tv_username.setText("");
        tv_password.setText("");
      }
      @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        try {
          error = error + errorResponse.get("message").toString();
        } catch (JSONException e) {
          error = error + e.getMessage();
        }
        refreshErrorMessage();
      }
    });
  }

  public void openManagerHome() {
    Intent intent = new Intent(login.this, home.class);
    startActivity(intent);
  }
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.managerLoginBtn:
        break;
    }
  }

  @Override
  public void onPointerCaptureChanged(boolean hasCapture) {

  }
}
