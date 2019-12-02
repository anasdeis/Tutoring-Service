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

public class viewStudents extends AppCompatActivity implements View.OnClickListener {

//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_view_students);
//  }
private String error = null;

  private Button homeButton;
  private Button removeButton;

  private EditText studentId;

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

  private List<String> studentNames = new ArrayList<>();
  private ArrayAdapter<String> studentAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_students);
    Spinner studentSpinner = (Spinner) findViewById(R.id.spinner3);

    studentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, studentNames);
    studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    studentSpinner.setAdapter(studentAdapter);

    refreshLists(this.getCurrentFocus());


    studentId = findViewById(R.id.studentId);

    homeButton = findViewById(R.id.homeButton);
    homeButton.setOnClickListener(this);
    removeButton = findViewById(R.id.removeButton);
    removeButton.setOnClickListener(this);
  }
  public void refreshLists(View view) {
    refreshList(studentAdapter ,studentNames, "student/list");

  }

  private void refreshList(final ArrayAdapter<String> adapter, final List<String> names, final String restFunctionName) {
    HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

      @Override
      public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        names.clear();
        names.add("FULLNAME , STUDENT ID , numCoursesEnrolled");
        for( int i = 0; i < response.length(); i++){
          try {
            names.add(response.getJSONObject(i).getString("firstName")
              + " " + response.getJSONObject(i).getString("lastName")
              + " , " + response.getJSONObject(i).getString("personId")
              + " , " + response.getJSONObject(i).getString("numCoursesEnrolled"));
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

  public void removeStudent(View v) {
    error = "";
    final TextView tv = (TextView) studentId;
    String studentId = tv.getText().toString();


    HttpUtils.delete("student/delete/" + studentId, new RequestParams(), new JsonHttpResponseHandler() {
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
      case R.id.removeButton:
        removeStudent(v);
        refreshLists(this.getCurrentFocus());
        break;
    }
  }
}
