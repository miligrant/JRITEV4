package com.example.jritev4;

public class Bid {
    private String id;
    private String userId;
    private double bidOffer;
    private String completionTime;
    private String comments;
    private double profileRating;

    private String jobId;

    private String freelancerName;

    public Bid() {
        // Default constructor required for Firebase
    }

    public Bid(String id, String userId, double bidOffer, String completionTime, String comments, double profileRating, String freelancerName, String jobId) {
        this.id = id;
        this.userId = userId;
        this.bidOffer = bidOffer;
        this.completionTime = completionTime;
        this.comments = comments;
        this.profileRating = profileRating;
        this.freelancerName = freelancerName;
        this.jobId = jobId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public double getBidOffer() {
        return bidOffer;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public String getComments() {
        return comments;
    }

    public double getProfileRating() {
        return profileRating;
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBidOffer(double bidOffer) {
        this.bidOffer = bidOffer;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setProfileRating(double profileRating) {
        this.profileRating = profileRating;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}

