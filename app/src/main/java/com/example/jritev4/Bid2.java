package com.example.jritev4;

public class Bid2 {
    private String bidderId;
    private String bidderName;
    private String bidAmount;

    public Bid2() {
        // Required empty constructor for Firebase
    }

    public Bid2(String bidderId, String bidderName, String bidAmount) {
        this.bidderId = bidderId;
        this.bidderName = bidderName;
        this.bidAmount = bidAmount;
    }

    public String getBidderId() {
        return bidderId;
    }

    public String getBidderName() {
        return bidderName;
    }

    public String getBidAmount() {
        return bidAmount;
    }
}
