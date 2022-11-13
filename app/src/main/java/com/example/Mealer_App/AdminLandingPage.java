package com.example.Mealer_App;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Mealer_App.structure.Complaint;

import java.util.List;

public class AdminLandingPage extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView viewMessage, viewTitle;
    private ListView lv_Complaints;
    private EditText suspensionLength;
    private Button dismissComplaint, actionComplaint;
    public static int complaintPosition = -1;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing_page);

        lv_Complaints = findViewById(R.id.list_Complaints);


        Database db = new Database(this);

        List<Complaint> complaintList = db.getComplaints();

        ArrayAdapter<Complaint> complaintArrayAdapter = new ArrayAdapter<>(AdminLandingPage.this,
                android.R.layout.simple_list_item_1, complaintList);
        lv_Complaints.setAdapter(complaintArrayAdapter);

        lv_Complaints.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //add code for popup for complaint
                complaintPosition = position;
                //Toast.makeText(AdminLandingPage.this, complaintList.get(position).toString(), Toast.LENGTH_LONG).show();
                dialogBuilder = new AlertDialog.Builder(AdminLandingPage.this);


                final View complaintPopupView = getLayoutInflater().inflate(R.layout.activity_admin_complaint_popup, null);

                Complaint selectedComplaint = complaintList.get(position);

                viewTitle = complaintPopupView.findViewById(R.id.text_complaintTitle);
                viewMessage = complaintPopupView.findViewById(R.id.text_complaintMessage);
                dismissComplaint = complaintPopupView.findViewById(R.id.btn_dismissComplaint);
                actionComplaint = complaintPopupView.findViewById(R.id.btn_actionComplaint);
                suspensionLength = complaintPopupView.findViewById(R.id.editText_suspensionLength);

                viewTitle.setText(selectedComplaint.getTitle());
                viewMessage.setText(selectedComplaint.getMessage());


                dialogBuilder.setView(complaintPopupView);
                dialog = dialogBuilder.create();
                dialog.show();

            }

        });


    }

    public void logOut(View view) {
        finish();
    }
}
