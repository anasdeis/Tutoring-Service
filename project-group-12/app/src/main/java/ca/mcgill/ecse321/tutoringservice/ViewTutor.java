package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

public class ViewTutor extends AppCompatActivity implements View.OnClickListener {

    Button detailButton;
    Button homeButton;
    String tutorID;
    EditText tutorIDInput;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tutor);
        table = findViewById(R.id.informationTable);

        tutorIDInput = findViewById(R.id.tutorIDInput);
        detailButton = findViewById(R.id.showDetailButton);
        detailButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showDetailButton:
                tutorID = tutorIDInput.getText().toString();
                switch (tutorID) {
                    case "001":
                        table.setColumnCollapsed(0, false);
                        table.setColumnCollapsed(1, true);
                        table.setColumnCollapsed(2, false);
                        table.setColumnCollapsed(3, true);
                        table.setColumnCollapsed(4, true);
                        break;
                    case "002":
                        table.setColumnCollapsed(0, false);
                        table.setColumnCollapsed(1, true);
                        table.setColumnCollapsed(2, true);
                        table.setColumnCollapsed(3, false);
                        table.setColumnCollapsed(4, true);
                        break;
                    case "003":
                        table.setColumnCollapsed(0, false);
                        table.setColumnCollapsed(1, true);
                        table.setColumnCollapsed(2, true);
                        table.setColumnCollapsed(3, true);
                        table.setColumnCollapsed(4, false);
                        break;
                    default:
                        Toast.makeText(this, "Invalid Tutor ID!", Toast.LENGTH_SHORT).show();
                        break;
                }
        }
    }
}
