package ca.mcgill.ecse321.tutoringservice;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import cz.msebera.android.httpclient.Header;

/**
 *  this class contains the functionalities on the welcome page, where you have three buttons for three viewpoints,
 *  so far, only the manager view point has been implement, but we have wrote the switch cases for tutor and student
 *  for future integration
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button toManagerLogin;
     private String error = null;

  /**
   * onCreate method which which is called automatically when open our android app
   * @param savedInstanceState
   */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toManagerLogin = findViewById(R.id.managerButton);
        toManagerLogin.setOnClickListener(this);
        refreshErrorMessage();
    }

  /**
   * inflate the menu
   * @param menu
   * @return menu
   */
  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

  /**
   * handle action bar items click
   * @param item
   * @return manu item
   */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

  /**
   * method to refresh the error message used for error handling
   */
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

  /**
   * method to open manager login page, which is another activity
   */
  public void openManagerLogin() {
    Intent intent = new Intent(this, login.class);
    startActivity(intent);
  }

  /**
   * onClick method which takes care of the click action, so far we only implement the functionalities for manager
   * the switch cases for tutor and student are implemented but commented out because no actual implementations have
   * been done for these two view points
   * but this will make the integration design simpler in the future
   * @param v
   */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.managerButton:
        openManagerLogin();
        break;
//      case R.id.tutorButton:
//        Toast.makeText(this, "Tutor Feature Not Applicable!", Toast.LENGTH_SHORT).show();
//        break;
//      case R.id.studentButton:
//        Toast.makeText(this, "Student Feature Not Applicable!", Toast.LENGTH_SHORT).show();
//        break;
    }
  }
}
