package com.example.jritev4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    private List<Job> jobList;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        firebaseAuth = FirebaseAuth.getInstance();
        // Initialize RecyclerView and its adapter
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobList = new ArrayList<>();
        jobAdapter = new JobAdapter(jobList);

        // Set item click listener for job selection
        jobAdapter.setOnItemClickListener(new JobAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Job selectedJob = jobList.get(position);
                // Start the JobDetailsActivity and pass the selected job details
                Intent intent = new Intent(HomepageActivity.this, JobDetailsActivity.class);
                intent.putExtra("job", selectedJob);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(jobAdapter);

        // Load jobs from Firebase or any other data source
        // For demonstration purposes, let's assume we have some dummy jobs
        loadDummyJobs();
    }

    @Override
    public void onStart(){
        super.onStart();
        firebaseAuth.signOut();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Log.d("USERHOMEPAGE","USER = "+user.getUid());
        if (user == null){
            Intent intent = new Intent(HomepageActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    private void loadDummyJobs() {
        // Add some dummy jobs to the list
        jobList.add(new Job("01", "Job 1", "Description 1", "Requirement 1", 100.0));
        jobList.add(new Job("02", "Job 2", "Description 2", "Requirement 2", 200.0));
        jobList.add(new Job("03", "Job 3", "Description 3", "Requirement 3", 300.0));

        // Notify the adapter about the data change
        jobAdapter.notifyDataSetChanged();
    }
}
