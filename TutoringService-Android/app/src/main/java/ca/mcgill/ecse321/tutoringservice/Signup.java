package ca.mcgill.ecse321.tutoringservice;

import android.content.Intent;
import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import cz.msebera.android.httpclient.Header;

public class Signup extends AppCompatActivity implements View.OnClickListener {

  private EditText firstName;
  private EditText lastName;
  private EditText dob;
  private EditText email;
  private EditText phoneNum;
  private EditText managerId;
  private EditText userName;
  private Button createButton;
  private Button homeButton;

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

  public void createManager(View v) {
    error = "";
    final TextView tv = (TextView) firstName;
     String firstName = tv.getText().toString();

    final TextView tv1 = (TextView) lastName;
     String lastName = tv1.getText().toString();

    final TextView tv2 = (TextView) dob;
    String dob = tv2.getText().toString();
    String comps[] = dob.split("-");
    int year = Integer.parseInt(comps[2]);
    int month = Integer.parseInt(comps[1]);
    int day = Integer.parseInt(comps[0]);

    final TextView tv3 = (TextView) email;
     String email = tv3.getText().toString();

    final TextView tv4 = (TextView) phoneNum;
     Integer phoneNum = Integer.parseInt(tv4.getText().toString());

    final TextView tv5 = (TextView) managerId;
    // final Integer managerId = Integer.parseInt(tv5.getText().toString());
     String managerId = tv5.getText().toString();

    final Integer TutoringSystemId = 1;

    final TextView tv6 = (TextView) userName;
     String userName = tv6.getText().toString();


    RequestParams rp = new RequestParams();
    rp.add("first", firstName);
    rp.add("last", lastName);

    NumberFormat formatter = new DecimalFormat("00");
    rp.add("dob", year + "-" + formatter.format(month) + "-" + formatter.format(day));

    rp.add("email", email);
    rp.add("phone", String.valueOf(phoneNum));
    rp.add("userName", userName);
    rp.add("tutoringSystemID", String.valueOf(TutoringSystemId));

    HttpUtils.post("manager/create/" + managerId, rp, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//        refreshErrorMessage();
//        tv.setText("");
//        tv1.setText("");
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
    setContentView(R.layout.activity_signup);

    firstName = findViewById(R.id.firstName);
    lastName = findViewById(R.id.lastName);
    dob = findViewById(R.id.dob);
    email = findViewById(R.id.email);
    phoneNum = findViewById(R.id.phoneNum);
    managerId = findViewById(R.id.managerId);
    userName = findViewById(R.id.userName);

    createButton = findViewById(R.id.createButton);
    createButton.setOnClickListener(this);

    homeButton = findViewById(R.id.homeButton);
    homeButton.setOnClickListener(this);
  }

  public void openHome() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  public void openManagerLogin() {
    Intent intent = new Intent(this, login.class);
    startActivity(intent);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.createButton:
        createManager(v);
        openManagerLogin();
        break;
      case R.id.homeButton:
        openHome();
        break;

    }
  }
}
