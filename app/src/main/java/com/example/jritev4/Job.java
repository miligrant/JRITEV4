package com.example.jritev4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Job implements Serializable {
    private String jobId;
    private String jobTitle;
    private String jobDescription;
    private String jobRequirements;
    private double jobPrice;
    private List<Bid> bids;

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public void setJobPrice(double jobPrice) {
        this.jobPrice = jobPrice;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }


    public Job() {
        // Required empty constructor for Firebase
    }

    public Job(String jobId, String jobTitle, String jobDescription, String jobRequirements, double jobPrice) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobRequirements = jobRequirements;
        this.jobPrice = jobPrice;
        this.bids = new ArrayList<>();
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public double getJobPrice() {
        return jobPrice;
    }

    public List<Bid> getBids() {
        return bids;
    }
}
