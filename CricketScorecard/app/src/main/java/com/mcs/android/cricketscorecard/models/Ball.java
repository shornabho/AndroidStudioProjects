package com.mcs.android.cricketscorecard.models;

public class Ball {
    private int ballId;
    private int matchId;
    private String ballType;
    private int runs;
    private String createdAt;

    public Ball() {
    }

    public Ball(int matchId, String ballType, int runs) {
        this.matchId = matchId;
        this.ballType = ballType;
        this.runs = runs;
    }

    public int getBallId() {
        return ballId;
    }

    public void setBallId(int ballId) {
        this.ballId = ballId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getBallType() {
        return ballType;
    }

    public void setBallType(String ballType) {
        this.ballType = ballType;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
