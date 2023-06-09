package com.example.jritev4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BidSubmissionActivity extends AppCompatActivity {
    private EditText bidOfferEditText, completionTimeEditText, commentsEditText;
    private Button submitBidButton;
    private DatabaseReference bidsReference;
    private FirebaseAuth firebaseAuth;
    Job jobModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_submission);
        Object job = getIntent().getSerializableExtra("job");
        jobModel = (Job) job;
        bidOfferEditText = findViewById(R.id.bidOfferEditText);
        completionTimeEditText = findViewById(R.id.completionTimeEditText);
        commentsEditText = findViewById(R.id.commentsEditText);
        submitBidButton = findViewById(R.id.submitBidButton);

        firebaseAuth = FirebaseAuth.getInstance();
        bidsReference = FirebaseDatabase.getInstance().getReference().child("bids");

        submitBidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bidOffer = bidOfferEditText.getText().toString().trim();
                String completionTime = completionTimeEditText.getText().toString().trim();
                String comments = commentsEditText.getText().toString().trim();

                submitBid(bidOffer, completionTime, comments, jobModel.getJobId());
            }
        });
    }

    private void submitBid(String bidOffer, String completionTime, String comments, String jobId) {
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();
            String bidId = bidsReference.child(userId).push().getKey();

            Bid bid = new Bid(bidId, userId, Double.valueOf(bidOffer), completionTime, comments, 0.0, "A", "01");

            bidsReference.child(jobId).child(bidId).setValue(bid)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Bid submitted successfully
                                Toast.makeText(BidSubmissionActivity.this,
                                        "Bid submitted successfully",
                                        Toast.LENGTH_SHORT).show();
                                bidsReference.child(jobId).child("currentowner").setValue(bidId);
                                Intent intent = new Intent(BidSubmissionActivity.this, JobDetailsActivity.class);
                                intent.putExtra("job", jobModel);
                                startActivity(intent);
                                // Redirect to the main activity or job details screen
                                // Example: startActivity(new Intent(BidSubmissionActivity.this, MainActivity.class));
                            } else {
                                // Bid submission failed
                                Toast.makeText(BidSubmissionActivity.this,
                                        "Bid submission failed: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
