package com.example.jritev4;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
public class JobDetailsActivity extends AppCompatActivity {
    // Existing code...

    private DatabaseReference jobsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Existing code...

        jobsRef = FirebaseDatabase.getInstance().getReference("jobs");

        // Retrieve the job details from Firebase Realtime Database
        jobsRef.child(jobId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Job job = dataSnapshot.getValue(Job.class);
                if (job != null) {
                    // Display job details, including bids
                    List<Bid> bids = job.getBids();
                    // Process and display the bids as desired
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });

        // Existing code...
    }

    // Existing code...
}

public class JobPostingActivity extends AppCompatActivity {
    // Existing code...

    private DatabaseReference jobsRef;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Existing code...

        // Initialize Firebase Realtime Database reference
        jobsRef = FirebaseDatabase.getInstance().getReference("jobs");

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Existing code...
    }

    private void postJob(String jobTitle, String jobDescription, String jobRequirements, String jobPrice) {
        String jobId = jobsRef.push().getKey();

        Job job = new Job(jobId, jobTitle, jobDescription, jobRequirements, jobPrice);
        jobsRef.child(jobId).setValue(job);

        Toast.makeText(this, "Job posted successfully", Toast.LENGTH_SHORT).show();
    }

    private void submitBid(String jobId, String bidAmount) {
        String bidderId = firebaseAuth.getCurrentUser().getUid();
        String bidderName = firebaseAuth.getCurrentUser().getDisplayName();

        Bid bid = new Bid(bidderId, bidderName, bidAmount);
        jobsRef.child(jobId).child("bids").push().setValue(bid);

        Toast.makeText(this, "Bid submitted successfully", Toast.LENGTH_SHORT).show();
    }
}
