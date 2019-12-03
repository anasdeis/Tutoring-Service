package ca.mcgill.ecse321.tutoringservice;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

/**
 * this class contain the functionalities on a the home (menu) page, including the button to jump to View Tutor page,
 * View Student page and View Tutor Application Page
 */
public class Menu extends AppCompatActivity implements View.OnClickListener {
  private Button viewTutorButton;
  private Button logoutButton;
  private Button viewStudentButton;
  private Button viewTutorApplicationButton;

  /**
   * onCreate method which is called automatically when create menu activity
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);


    viewTutorButton = findViewById(R.id.viewTutorButton);
    viewTutorButton.setOnClickListener(this);

    viewStudentButton = findViewById(R.id.viewStudentsButton);
    viewStudentButton.setOnClickListener(this);

    logoutButton = findViewById(R.id.logoutButton);
    logoutButton.setOnClickListener(this);

    viewTutorApplicationButton = findViewById(R.id.viewTutorApplicationButton);
    viewTutorApplicationButton.setOnClickListener(this);
  }

  /**
   * The following four methods are used for the jump between different pages, which means different
   * activities in android
   */
  public void openViewTutor() {
    Intent intent = new Intent(this, viewTutors.class);
    startActivity(intent);
  }

  public void openViewStudent() {
    Intent intent = new Intent(this, viewStudents.class);
    startActivity(intent);
  }

  public void openViewTutorApplication() {
    Intent intent = new Intent(this, viewTutorApplication.class);
    startActivity(intent);
  }

  public void openManagerLogin() {
    Intent intent = new Intent(this, login.class);
    startActivity(intent);
  }

  /**
   * this method contains the page jump, will open another activity upon clicking
   *
   * @param v
   */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.viewTutorButton:
        openViewTutor();
        break;
      case R.id.logoutButton:
        openManagerLogin();
        break;
      case R.id.viewStudentsButton:
        openViewStudent();
        break;
      case R.id.viewTutorApplicationButton:
        openViewTutorApplication();
        break;

    }
  }
}
