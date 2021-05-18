package com.mcs.android.testio.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Question implements Parcelable {
    private String id;
    private String testId;
    private String title;
    private List<String> options;
    private String correctAnswer;

    public Question() {
    }

    public Question(String id, String testId, String title, List<String> options, String correctAnswer) {
        this.id = id;
        this.testId = testId;
        this.title = title;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    protected Question(Parcel in) {
        options = new ArrayList<String>();

        id = in.readString();
        testId = in.readString();
        title = in.readString();
        options = in.createStringArrayList();
        correctAnswer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(testId);
        dest.writeString(title);
        dest.writeStringList(options);
        dest.writeString(correctAnswer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
