package com.example.jritev4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class JobDetailsActivity extends AppCompatActivity {

    private TextView titleTextView, descriptionTextView, requirementTextView, priceTextView, leadingTextView;
    private Button bidButton;
    Job jobModel;
    private DatabaseReference bidsReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        // Get the job details from the intent
        Object job = getIntent().getSerializableExtra("job");
        jobModel = (Job) job;
        // Find views
        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        requirementTextView = findViewById(R.id.requirementTextView);
        priceTextView = findViewById(R.id.priceTextView);
        leadingTextView = findViewById(R.id.ldgtextView);
        bidButton = findViewById(R.id.bidButton);
        bidsReference = FirebaseDatabase.getInstance().getReference().child("bids").child(jobModel.getJobId()).child("currentowner");

        // Set job details
        titleTextView.setText(jobModel.getJobTitle());
        descriptionTextView.setText(jobModel.getJobDescription());
        requirementTextView.setText(jobModel.getJobRequirements());
        priceTextView.setText(String.format(Locale.getDefault(), "$%.2f", jobModel.getJobPrice()));
        ValueEventListener leading = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String leadingId = snapshot.getValue().toString();
                    DatabaseReference leadingReference = FirebaseDatabase.getInstance().getReference().child("bids").child(jobModel.getJobId()).child(leadingId);
                    leadingReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            Log.d("dB",task.getResult().getValue().toString());
                            if (task.getResult().exists())
                            {
                                Bid bids = task.getResult().getValue(Bid.class);
                                leadingTextView.setText(""+bids.getBidOffer());
                            }
                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("error", error.toString());
            }
        };
        bidsReference.addValueEventListener(leading);

        // Handle bidding
        bidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailsActivity.this, BidSubmissionActivity.class);
                intent.putExtra("job", jobModel);
                startActivity(intent);
                // Implement your bid functionality here
                // You can start a bidding process or show a bidding dialog
            }
        });
    }
}
