package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * this class contain the functionalities on a the view tutor application page, including the button to jump to Manager Home
 * and accept/decline a tutor application
 * By clicking accept/decline, the tutor application with the specific ID will be updated
 */
public class viewTutorApplication extends AppCompatActivity implements View.OnClickListener {

  private String error = null;

  private Button homeButton;
  private Button declineButton;
  private Button acceptButton;
  private EditText tutorApplicationId;

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

  private List<String> tutorApplicationNames = new ArrayList<>();
  private ArrayAdapter<String> tutorApplicationAdapter;

  /**
   * onCreate method which is called automatically when create view tutor application activity
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_tutor_application);
    Spinner tutorApplicationSpinner = (Spinner) findViewById(R.id.spinner4);

    tutorApplicationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tutorApplicationNames);
    tutorApplicationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    tutorApplicationSpinner.setAdapter(tutorApplicationAdapter);

    refreshLists(this.getCurrentFocus());


    tutorApplicationId = findViewById(R.id.tutorApplicationId);

    homeButton = findViewById(R.id.homeButton);
    homeButton.setOnClickListener(this);
    declineButton = findViewById(R.id.declineButton);
    declineButton.setOnClickListener(this);

    acceptButton = findViewById(R.id.acceptButton);
    acceptButton.setOnClickListener(this);
  }

  /**
   * this method refresh the displayed list in our android app design
   *
   * @param view
   */
  public void refreshLists(View view) {
    refreshList(tutorApplicationAdapter, tutorApplicationNames, "tutorApplication/list");
  }

  /**
   * this method will refresh the list of tutor applications which are stored in the database
   *
   * @param adapter
   * @param names
   * @param restFunctionName
   */
  private void refreshList(final ArrayAdapter<String> adapter, final List<String> names, final String restFunctionName) {
    HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

      @Override
      public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        names.clear();
        names.add("APPLICATION ID , TUTOR ID , isACCEPTED?");
        for (int i = 0; i < response.length(); i++) {
          try {
            names.add(response.getJSONObject(i).getString("applicationId")
              + " , " + response.getJSONObject(i).getString("tutor")
              + " , " + response.getJSONObject(i).getString("isAccepted"));

          } catch (Exception e) {
            error += e.getMessage();
          }
          // refreshErrorMessage();
        }
        adapter.notifyDataSetChanged();
      }

      @Override
      public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        try {
          error += errorResponse.get("message").toString();
        } catch (JSONException e) {
          error += e.getMessage();
        }
        //  refreshErrorMessage();
      }
    });
  }

  /**
   * this method communicate with the backend, it takes in an argument of tutor application id which will be used to find the
   * tutor application with this ID and then accept this application
   *
   * @param v
   */
  public void acceptTutorApplication(View v) {
    error = "";
    final TextView tv = (TextView) tutorApplicationId;
    String tutorApplicationId = tv.getText().toString();

    RequestParams rp = new RequestParams();
    rp.add("isAccepted", "true");

    /**
     * take in tutorApplicationID as parameter, then find the related ID and accept
     */
    HttpUtils.patch("tutorApplication/update/" + tutorApplicationId, rp, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//        refreshErrorMessage();
        tv.setText("");
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
   * this method communicate with the backend, it takes in an argument of tutor application id which will be used to find the
   * tutor application with this ID and then decline this application
   *
   * @param v
   */
  public void declineTutorApplication(View v) {
    error = "";
    final TextView tv = (TextView) tutorApplicationId;
    String tutorApplicationId = tv.getText().toString();


    HttpUtils.delete("tutorApplication/delete/" + tutorApplicationId, new RequestParams(), new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//        refreshErrorMessage();
        tv.setText("");
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
   * This method is used for the jump between different pages, which means different
   * activities in android
   */
  public void openHome() {
    Intent intent = new Intent(this, MainActivity.class);
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
      case R.id.homeButton:
        openHome();
        break;
      case R.id.declineButton:
        declineTutorApplication(v);
        refreshLists(this.getCurrentFocus());
        break;
      case R.id.acceptButton:
        acceptTutorApplication(v);
        refreshLists(this.getCurrentFocus());
        break;

    }
  }
}
