package com.mcs.android.cricketscorecard.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mcs.android.cricketscorecard.constants.DBConstants;
import com.mcs.android.cricketscorecard.models.Ball;
import com.mcs.android.cricketscorecard.models.Match;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "matches.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createMatchQuery = "CREATE TABLE " + DBConstants.MATCH_TABLE_NAME + " (" +
                DBConstants.KEY_MATCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBConstants.KEY_MATCH_NAME + " TEXT, " +
                DBConstants.KEY_MATCH_CREATED_AT + " TEXT DEFAULT CURRENT_TIMESTAMP" +
                ");";
        String createBallsQuery = "CREATE TABLE " + DBConstants.BALL_TABLE_NAME + " (" +
                DBConstants.KEY_BALL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBConstants.KEY_BALL_MATCH_ID + " INTEGER," +
                DBConstants.KEY_BALL_TYPE + " TEXT, " +
                DBConstants.KEY_BALL_RUNS + " INTEGER," +
                DBConstants.KEY_BALL_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (" + DBConstants.KEY_MATCH_ID + ") REFERENCES " + DBConstants.MATCH_TABLE_NAME + "(" + DBConstants.KEY_MATCH_ID + ")" +
                ");";

        sqLiteDatabase.execSQL(createMatchQuery);
        sqLiteDatabase.execSQL(createBallsQuery);

        Log.d("DBHelper.onCreate()", "Tables Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public int addMatch(Match match) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBConstants.KEY_MATCH_NAME, match.getMatchName());

        int id = (int) database.insert(DBConstants.MATCH_TABLE_NAME, null, values);
        Log.i("Database", "Match inserted");
        database.close();

        return id;
    }

    public int addBall(Ball ball) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBConstants.KEY_BALL_MATCH_ID, ball.getMatchId());
        values.put(DBConstants.KEY_BALL_TYPE, ball.getBallType());
        values.put(DBConstants.KEY_BALL_RUNS, ball.getRuns());

        int id = (int) database.insert(DBConstants.BALL_TABLE_NAME, null, values);
        Log.i("Database", "Ball inserted");
        database.close();

        return id;
    }

    public Match getMatchById(int matchId) {
        Match match = null;
        SQLiteDatabase database = this.getReadableDatabase();

        String allMatchesQuery = "SELECT * FROM " + DBConstants.MATCH_TABLE_NAME + " WHERE " + DBConstants.KEY_MATCH_ID + "=?";
        Cursor cursor = database.rawQuery(allMatchesQuery, new String[] {String.valueOf(matchId)});

        if(cursor.moveToFirst()) {
            match = new Match();
            match.setMatchId(cursor.getInt(0));
            match.setMatchName(cursor.getString(1));
            match.setCreatedAt(cursor.getString(2));
        }
        database.close();
        return match;
    }

    public List<Match> getAllMatches() {
        List<Match> matches = new ArrayList<Match>();
        SQLiteDatabase database = this.getReadableDatabase();

        String allMatchesQuery = "SELECT * FROM " + DBConstants.MATCH_TABLE_NAME;
        Cursor cursor = database.rawQuery(allMatchesQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Match match = new Match();
                match.setMatchId(cursor.getInt(0));
                match.setMatchName(cursor.getString(1));
                match.setCreatedAt(cursor.getString(2));

                matches.add(match);
            } while (cursor.moveToNext());
        }

        database.close();

        return matches;
    }

    public List<Ball> getAllBallsInMatch(int matchId) {
        List<Ball> balls = new ArrayList<Ball>();
        SQLiteDatabase database = this.getReadableDatabase();

        String allBallsQuery = "SELECT * FROM " + DBConstants.BALL_TABLE_NAME + " WHERE " + DBConstants.KEY_BALL_MATCH_ID + "=?"
                + " ORDER BY " + DBConstants.KEY_BALL_ID;
        Cursor cursor = database.rawQuery(allBallsQuery, new String[] {String.valueOf(matchId)});

        if(cursor.moveToFirst()) {
            do {
                Ball ball = new Ball();
                ball.setBallId(cursor.getInt(0));
                ball.setMatchId(cursor.getInt(1));
                ball.setBallType(cursor.getString(2));
                ball.setRuns(cursor.getInt(3));
                ball.setCreatedAt(cursor.getString(4));

                balls.add(ball);
            } while (cursor.moveToNext());
        }

        database.close();

        return balls;
    }

    public Match updateMatchName(int matchId, String newMatchName) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBConstants.KEY_MATCH_NAME, newMatchName);

        database.update(DBConstants.MATCH_TABLE_NAME, values, DBConstants.KEY_MATCH_ID + "=?", new String[] { newMatchName });

        return getMatchById(matchId);
    }

    public void deleteMatchById(int matchId) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBConstants.KEY_MATCH_ID, matchId);

        database.delete(DBConstants.BALL_TABLE_NAME, DBConstants.KEY_BALL_MATCH_ID + "=?", new String[] {String.valueOf(matchId)});
        database.delete(DBConstants.MATCH_TABLE_NAME, DBConstants.KEY_MATCH_ID + "=?", new String[] {String.valueOf(matchId)});
        Log.i("Database", "Match deleted");
        database.close();
    }
}
