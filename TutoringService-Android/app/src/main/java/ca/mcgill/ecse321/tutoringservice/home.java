package ca.mcgill.ecse321.tutoringservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity implements View.OnClickListener  {
  private Button toManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_main);

    toManager = findViewById(R.id.managerLoginBtn);
    toManager.setOnClickListener(this);
  }

  public void openManager() {
    Intent intent = new Intent(this, login.class);
    startActivity(intent);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.managerLoginBtn:
        openManager();
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
