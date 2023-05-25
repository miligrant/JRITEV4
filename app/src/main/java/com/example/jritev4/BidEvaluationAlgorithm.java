package com.example.jritev4;

import java.util.List;

public class BidEvaluationAlgorithm {

    String JobId;
    public static Bid evaluateBestBid(List<Bid> bids) {
        Bid bestBid = null;

        for (Bid bid : bids) {
            if (bestBid == null || isBetterBid(bid, bestBid)) {
                bestBid = bid;
            }
        }

        return bestBid;
    }

    private static boolean isBetterBid(Bid bid, Bid bestBid) {
        // Criteria comparison logic
        // double dif = bid.getBidOffer() - bid.getJobId()
       // if (bid.getBidOffer() < .jobPrice)

        double profileRatingComparison = bid.getProfileRating()-(bestBid.getProfileRating());
        if (profileRatingComparison > 0) {
            double bidComparison = bid.getBidOffer() - bestBid.getBidOffer();
            if (bidComparison < 0) {
                int completionTimeComparison = bid.getCompletionTime().compareTo(bestBid.getCompletionTime());
                if (completionTimeComparison < 0) {
                    return true;
                }
            } else if (bidComparison > 0) {
                return false;
            }
        } else if (profileRatingComparison < 0) {
            return false;
        }

        return false;
    }

    public void retrieveJobId (Bid bid){
        JobId = bid.getJobId();
    }
}

