package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTutorApplication extends AppCompatActivity implements View.OnClickListener {
    private static boolean delete = false;
    private Button detailButton;
    private Button goBackButton;
    private Button acceptApplicationButton;
    private String tutorApplicationID;
    private EditText tutorApplicationIDInput;
    private TableLayout table;
    private TableLayout table2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tutor_application);
        table = findViewById(R.id.TutorApplicationInformationTable);
        table2 = findViewById(R.id.TutorApplicationTable);
        if(delete) {
            TableRow row = (TableRow) findViewById(R.id.QWERT);
            table2.removeView(row);
        }
        tutorApplicationIDInput = findViewById(R.id.tutorApplicationIDInput);
        detailButton = findViewById(R.id.showTutorApplicationDetailButton);
        goBackButton = findViewById(R.id.viewTutorApplicationToHome1Button);
        acceptApplicationButton = findViewById(R.id.acceptTutorApplicationButton);

        detailButton.setOnClickListener(this);
        goBackButton.setOnClickListener(this);
        acceptApplicationButton.setOnClickListener(this);
    }

    public void openHome1() {
        Intent intent = new Intent(this, Home1.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showTutorApplicationDetailButton: {
                tutorApplicationID = tutorApplicationIDInput.getText().toString();
                switch (tutorApplicationID) {
                    case "ASDFG":
                        table.setColumnCollapsed(0, false);
                        table.setColumnCollapsed(1, true);
                        table.setColumnCollapsed(2, false);
                        table.setColumnCollapsed(3, true);
                        break;
                    case "QWERT":
                        table.setColumnCollapsed(0, false);
                        table.setColumnCollapsed(1, true);
                        table.setColumnCollapsed(2, true);
                        table.setColumnCollapsed(3, false);
                        break;
                    default:
                        Toast.makeText(this, "Invalid Application ID!", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            }

            case R.id.viewTutorApplicationToHome1Button:
                openHome1();
                break;

            case R.id.acceptTutorApplicationButton:
                table.setColumnCollapsed(0, false);
                table.setColumnCollapsed(1, false);
                table.setColumnCollapsed(2, true);
                table.setColumnCollapsed(3, true);
                if(tutorApplicationID.equals("QWERT")) {
                    TableRow row = (TableRow) findViewById(R.id.QWERT);
                    Tutor tutor = new Tutor("ECSE 326", "2019 Winter");
                    ViewTutor vt = new ViewTutor();
                    vt.tutors.add(tutor);
                    table2.removeView(row);
                    delete = true;
                }
                else if (tutorApplicationID.equals("ASDFG")) {
                    TableRow row = (TableRow) findViewById(R.id.ASDFG);
                    table2.removeView(row);
                }
                else {
                    Toast.makeText(this, "Invalid Application ID!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
