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
 * this class contain the functionalities on a the view tutor page, including the button to jump to Manager Home
 * and fire a tutor
 * By clicking fire, the tutor with the specific ID will be removed from the database
 */
public class viewTutors extends AppCompatActivity implements View.OnClickListener {

  private String error = null;

  private Button homeButton;
  private Button fireButton;

  private EditText tutorId;

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

  private List<String> tutorNames = new ArrayList<>();
  private ArrayAdapter<String> tutorAdapter;

  /**
   * onCreate method which is called automatically when create view tutor activity
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_tutors);
    Spinner managerSpinner = (Spinner) findViewById(R.id.spinner2);

    tutorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tutorNames);
    tutorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    managerSpinner.setAdapter(tutorAdapter);

    refreshLists(this.getCurrentFocus());


    tutorId = findViewById(R.id.tutorId);

    homeButton = findViewById(R.id.homeButton);
    homeButton.setOnClickListener(this);
    fireButton = findViewById(R.id.fireButton);
    fireButton.setOnClickListener(this);
  }

  /**
   * this method refresh the displayed list in our android app design
   *
   * @param view
   */
  public void refreshLists(View view) {
    refreshList(tutorAdapter, tutorNames, "tutor/list");
  }

  /**
   * this method will refresh the list of tutors which are stored in the database
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
        names.add("FULLNAME , TUTOR ID , isREGISTERED?");
        for (int i = 0; i < response.length(); i++) {
          try {
            names.add(response.getJSONObject(i).getString("firstName")
              + " " + response.getJSONObject(i).getString("lastName")
              + " , " + response.getJSONObject(i).getString("personId")
              + " , " + response.getJSONObject(i).getString("isRegistered"));
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
   * this method communicate with the backend, it takes in an argument of tutor id which will be used to find the
   * tutor with this ID and then fire the tutor, the tutor will be removed from the database
   *
   * @param v
   */
  public void fireTutor(View v) {
    error = "";
    final TextView tv = (TextView) tutorId;
    String tutorId = tv.getText().toString();

    /**
     * takes in tutorID to find and then delete the tutor
     */
    HttpUtils.delete("tutor/delete/" + tutorId, new RequestParams(), new JsonHttpResponseHandler() {
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
      case R.id.fireButton:
        fireTutor(v);
        refreshLists(this.getCurrentFocus());
        break;
    }
  }
}
