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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import cz.msebera.android.httpclient.Header;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button toManagerLogin;
    private String error = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

      toManagerLogin = findViewById(R.id.managerLoginBtn);
      toManagerLogin.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v) {
            toManagerLogin();
          }
        });
      refreshErrorMessage();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

    // login feature backend call
//    public void login(View view) {
//      error = "";
//      final TextView tv = (TextView) findViewById(R.id.userName);
//    }

  private void toManagerLogin() {
    error = "";
    Intent intent = new Intent(MainActivity.this, login.class);
    startActivity(intent);
  }

  public void onClick(View view) {
      switch(view.getId()) {
        case R.id.managerLoginBtn:
        toManagerLogin();
        break;
      }
  }

}
