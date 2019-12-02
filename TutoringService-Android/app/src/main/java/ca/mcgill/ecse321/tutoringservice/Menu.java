package ca.mcgill.ecse321.tutoringservice;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity implements View.OnClickListener {
  private Button viewTutorButton;
  private Button logoutButton;
  private Button viewStudentButton;


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
  }

  public void openViewTutor() {
    Intent intent = new Intent(this, viewTutors.class);
    startActivity(intent);
  }

  public void openViewStudent() {
    Intent intent = new Intent(this, viewStudents.class);
    startActivity(intent);
  }

  public void openManagerLogin() {
    Intent intent = new Intent(this, login.class);
    startActivity(intent);
  }

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

    }
  }

}
