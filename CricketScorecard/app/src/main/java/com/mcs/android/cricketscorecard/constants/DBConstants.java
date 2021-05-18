package com.mcs.android.cricketscorecard.constants;

public class DBConstants {

    public static final String DB_NAME = "matches.db";

    // Match Table
    public static final String MATCH_TABLE_NAME = "Match";
    public static final String KEY_MATCH_ID =  "matchId";
    public static final String KEY_MATCH_NAME =  "matchName";
    public static final String KEY_MATCH_CREATED_AT =  "createdAt";

    // Ball Table
    public static final String BALL_TABLE_NAME = "Ball";
    public static final String KEY_BALL_ID =  "ballId";
    public static final String KEY_BALL_MATCH_ID = "matchId";
    public static final String KEY_BALL_TYPE =  "ballType";
    public static final String KEY_BALL_RUNS = "runs";
    public static final String KEY_BALL_CREATED_AT =  "createdAt";

    public static final String BALL_TYPE_NO_BALL = "NO BALL";
    public static final String BALL_TYPE_WIDE_BALL = "WIDE BALL";
    public static final String BALL_TYPE_EXTRAS = "EXTRAS";
    public static final String BALL_TYPE_OUT = "OUT";
    public static final String BALL_TYPE_DOT = "DOT";
    public static final String BALL_TYPE_BOUNDARY = "BOUNDARY";
    public static final String BALL_TYPE_SINGLES = "SINGLE";
    public static final String BALL_TYPE_DOUBLES = "DOUBLES";
    public static final String BALL_TYPE_TRIPLES = "TRIPLES";
}
