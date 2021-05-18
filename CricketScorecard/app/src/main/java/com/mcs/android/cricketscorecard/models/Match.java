package com.mcs.android.cricketscorecard.models;

public class Match {
    private int matchId;
    private String matchName;
    private String createdAt;

    public Match() {
    }

    public Match(String matchName) {
        this.matchName = matchName;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
