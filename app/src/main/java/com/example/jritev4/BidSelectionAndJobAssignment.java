package com.example.jritev4;

import java.util.List;

public class BidSelectionAndJobAssignment {

    public void selectAndAssignJob(List<Bid> bids) {
        if (bids.isEmpty()) {
            System.out.println("No bids available. Job assignment cannot be performed.");
            return;
        }

        Bid bestBid = BidEvaluationAlgorithm.evaluateBestBid(bids);
        if (bestBid != null) {
            assignJobToFreelancer(bestBid);
        }
    }

    private void assignJobToFreelancer(Bid bestBid) {
        // Perform job assignment logic here
        String freelancerId = bestBid.getUserId();
        String freelancerName = bestBid.getFreelancerName();
        String jobId = bestBid.getJobId();

        System.out.println("Job assigned to freelancer:");
        System.out.println("Freelancer ID: " + freelancerId);
        System.out.println("Freelancer Name: " + freelancerName);
        System.out.println("Job ID: " + jobId);
    }
}
