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
 * this class contain the functionalities on a the view student page, including the button to jump to Manager Home
 * and remove a student from our database
 * By clicking remove, the student with the specific ID will be removed from the database
 */
public class viewStudents extends AppCompatActivity implements View.OnClickListener {

  private String error = null;

  private Button homeButton;
  private Button removeButton;

  private EditText studentId;

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

  private List<String> studentNames = new ArrayList<>();
  private ArrayAdapter<String> studentAdapter;

  /**
   * onCreate method which is called automatically when create view student activity
   *
   * @param savedInstanceState
   */
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

  /**
   * this method refresh the displayed list in our android app design
   *
   * @param view
   */
  public void refreshLists(View view) {
    refreshList(studentAdapter, studentNames, "student/list");
  }

  /**
   * this method will refresh the list of students which are stored in the database
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
        names.add("FULLNAME , STUDENT ID , numCoursesEnrolled");
        for (int i = 0; i < response.length(); i++) {
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

  /**
   * this method communicate with the backend, it takes in an argument of student id which will be used to find the student
   * and then delete from the database
   *
   * @param v
   */
  public void removeStudent(View v) {
    error = "";
    final TextView tv = (TextView) studentId;
    String studentId = tv.getText().toString();

    /**
     * takes in a student id to find and delete student
     */
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

  /**
   * The following method is used for the jump between different pages, which means different
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
      case R.id.removeButton:
        removeStudent(v);
        refreshLists(this.getCurrentFocus());
        break;
    }
  }
}
