package com.example.jritev4;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;



public class JobPostingActivity extends AppCompatActivity {
    // Existing code...

    private DatabaseReference jobsRef;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posting);
        // Existing code...

        // Initialize Firebase Realtime Database reference
        jobsRef = FirebaseDatabase.getInstance().getReference("jobs");

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Existing code...
    }

    private void postJob(String jobTitle, String jobDescription, String jobRequirements, double jobPrice) {
        String jobId = jobsRef.push().getKey();

        Job job = new Job(jobId, jobTitle, jobDescription, jobRequirements, jobPrice);
        jobsRef.child(jobId).setValue(job);

        Toast.makeText(this, "Job posted successfully", Toast.LENGTH_SHORT).show();
    }

    private void submitBid(String jobId, String bidAmount) {
        String bidderId = firebaseAuth.getCurrentUser().getUid();
        String bidderName = firebaseAuth.getCurrentUser().getDisplayName();

        Bid bid = new Bid("0.2",bidderId, Double.valueOf(bidAmount),"7","can do faster", 4.1, bidderName, "2111");
        jobsRef.child(jobId).child("bids").push().setValue(bid);

        Toast.makeText(this, "Bid submitted successfully", Toast.LENGTH_SHORT).show();
    }
}
