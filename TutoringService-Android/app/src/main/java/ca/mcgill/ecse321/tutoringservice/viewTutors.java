package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class viewTutors extends AppCompatActivity implements View.OnClickListener  {

  private String error = null;

  private Button homeButton;

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
  private ArrayAdapter<String>  tutorAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_tutors);
    Spinner managerSpinner = (Spinner) findViewById(R.id.spinner2);

    tutorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tutorNames);
    tutorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    managerSpinner.setAdapter(tutorAdapter);

    refreshLists(this.getCurrentFocus());

    homeButton = findViewById(R.id.homeButton);
    homeButton.setOnClickListener(this);
  }
  public void refreshLists(View view) {
    refreshList(tutorAdapter ,tutorNames, "tutor/list");

  }

  private void refreshList(final ArrayAdapter<String> adapter, final List<String> names, final String restFunctionName) {
    HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

      @Override
      public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        names.clear();
        names.add("FULLNAME , TUTOR ID , isREGISTERED?");
//        names.add("NAME                       TUTOR ID                       isRegistered?");
        for( int i = 0; i < response.length(); i++){
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


  public void openHome() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }


  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.homeButton:
        openHome();
        break;

    }
  }
}
