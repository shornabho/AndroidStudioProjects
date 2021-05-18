package com.mcs.android.testio.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.Date;

public class Response implements Parcelable {
    private int id;
    private String testId;
    private String questionId;
    private String name;
    @Nullable private String answer;
    @Nullable private Date submittedAt;

    public Response() {
    }

    public Response(int id, String testId, String questionId, String name, @Nullable String answer, @Nullable Date submittedAt) {
        this.id = id;
        this.testId = testId;
        this.questionId = questionId;
        this.name = name;
        this.answer = answer;
        this.submittedAt = submittedAt;
    }

    protected Response(Parcel in) {
        id = in.readInt();
        testId = in.readString();
        questionId = in.readString();
        name = in.readString();
        answer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(testId);
        dest.writeString(questionId);
        dest.writeString(name);
        dest.writeString(answer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }
}
