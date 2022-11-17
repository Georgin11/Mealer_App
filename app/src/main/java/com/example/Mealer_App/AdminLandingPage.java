package com.example.Mealer_App;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Mealer_App.structure.Complaint;

import java.util.ArrayList;
import java.util.List;

public class AdminLandingPage extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView viewMessage, viewTitle, viewCook, viewClient;
    private ListView lv_Complaints;
    private EditText suspensionLength;
    private Button dismissComplaint, actionComplaint;
    public static int complaintPosition = -1;

    private Complaint selectedComplaint;




    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing_page);

        lv_Complaints = findViewById(R.id.list_Complaints);
        suspensionLength = findViewById(R.id.editText_suspensionLength);

        Database db = new Database(this);

        List<Complaint> complaintList = db.getComplaints();
        List<Complaint> activeComplaints = new ArrayList<Complaint>();

        int i;
        for(i = 0; i < complaintList.toArray().length; i++) {
            if(complaintList.get(i).getDaysSuspended() == -1) {
                activeComplaints.add(complaintList.get(i));
            }
        }

        ArrayAdapter<Complaint> complaintArrayAdapter = new ArrayAdapter<>(AdminLandingPage.this,
                    android.R.layout.simple_list_item_1, activeComplaints);
        if(activeComplaints.isEmpty()) {
            Toast.makeText(this, "No new complaints! Yay!", Toast.LENGTH_LONG).show();
        }
        lv_Complaints.setAdapter(complaintArrayAdapter);

        lv_Complaints.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("SetTextI18n")
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //add code for popup for complaint
                complaintPosition = position;
                //Toast.makeText(AdminLandingPage.this, complaintList.get(position).toString(), Toast.LENGTH_LONG).show();
                dialogBuilder = new AlertDialog.Builder(AdminLandingPage.this);

                final View complaintPopupView = getLayoutInflater().inflate(R.layout.activity_admin_complaint_popup, null);


                selectedComplaint = activeComplaints.get(position);

                viewTitle = complaintPopupView.findViewById(R.id.text_complaintTitle);
                viewMessage = complaintPopupView.findViewById(R.id.text_complaintMessage);
                viewClient = complaintPopupView.findViewById(R.id.text_associatedClient);
                viewCook = complaintPopupView.findViewById(R.id.text_associatedCook);
                dismissComplaint = complaintPopupView.findViewById(R.id.btn_dismissComplaint);
                actionComplaint = complaintPopupView.findViewById(R.id.btn_actionComplaint);
                suspensionLength = complaintPopupView.findViewById(R.id.editText_suspensionLength);

                String clientUsername = "Client: " + selectedComplaint.getClientUsername();
                String cookUsername = "Cook: " + selectedComplaint.getCookUsername();
                viewTitle.setText(selectedComplaint.getTitle());
                viewMessage.setText(selectedComplaint.getMessage());
                viewClient.setText(clientUsername);
                viewCook.setText(cookUsername);


                dialogBuilder.setView(complaintPopupView);
                dialog = dialogBuilder.create();
                dialog.show();

            }

        });


    }

    public void actionComplaintOnClick(View view) {

        int selectedSuspension = Integer.parseInt(suspensionLength.getText().toString().trim());
        Database db = new Database(this);

        selectedComplaint.setDaysSuspended(selectedSuspension);

        db.updateSuspension(selectedComplaint, selectedComplaint.getDB_id());

        dialog.dismiss();
        recreate();

    }

    public void dismissComplaintOnClick(View view) {
        int selectedSuspension = 0;
        Database db = new Database(this);
        selectedComplaint.setDaysSuspended(selectedSuspension);
        db.updateSuspension(selectedComplaint, selectedComplaint.getDB_id());
        dialog.dismiss();
        recreate();
    }

    public void permanentBan(View view) {
        selectedComplaint.setDaysSuspended(-5);
        Database db = new Database(this);
        db.updateSuspension(selectedComplaint, selectedComplaint.getDB_id());
        dialog.dismiss();
        recreate();
    }

    public void logOut(View view) {
        finish();
    }
}
