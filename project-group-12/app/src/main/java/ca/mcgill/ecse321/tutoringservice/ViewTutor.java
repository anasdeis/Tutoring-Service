package ca.mcgill.ecse321.tutoringservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTutor extends AppCompatActivity implements View.OnClickListener {
    public static ArrayList<Tutor> tutors = new ArrayList<>();
    private Button detailButton;
    private Button goBackButton;
    private String tutorID;
    private EditText tutorIDInput;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tutor);
        table = findViewById(R.id.TutorInformationTable);

        tutorIDInput = findViewById(R.id.tutorIDInput);
        detailButton = findViewById(R.id.showTutorDetailButton);
        goBackButton = findViewById(R.id.viewTutorToHome1Button);
        detailButton.setOnClickListener(this);
        goBackButton.setOnClickListener(this);
    }

    public void openHome1() {
        Intent intent = new Intent(this, Home1.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showTutorDetailButton:
            {
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
                        if(tutors.size() == 1) {
                            TextView tv1 = (TextView)findViewById(R.id.subject003);
                            tv1.setText(tutors.get(0).getSubject());
                            TextView tv2 = (TextView)findViewById(R.id.offering003);
                            tv2.setText(tutors.get(0).getOffering());
                        }
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
                break;
            }

            case R.id.viewTutorToHome1Button:
                openHome1();
                break;
        }
    }
}
