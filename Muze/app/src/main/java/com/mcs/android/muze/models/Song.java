package com.mcs.android.muze.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;


public class Song implements Parcelable {
    private long id;
    private String title;
    private long duration;
    private String artist;
    private String album;
    private Uri uri;


    public Song(long id, String title, long duration, String artist, String album, Uri uri) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.album = album;
        this.uri = uri;
    }

    protected Song(Parcel in) {
        id = in.readLong();
        title = in.readString();
        duration = in.readLong();
        artist = in.readString();
        album = in.readString();
        uri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };


    public static String padZeroesInSeconds(long value) {
        if (value < 10) {
            return "0" + value;
        }
        else {
            return Long.toString(value);
        }
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeLong(duration);
        parcel.writeString(artist);
        parcel.writeString(album);
        parcel.writeParcelable(uri, i);
    }

    public static String getFormattedDuration(long duration) {
        long songDurationMinutes = duration / 60000;
        long songDurationSeconds = (duration % 60000) / 1000 ;

        return songDurationMinutes + ":" + Song.padZeroesInSeconds(songDurationSeconds);
    }
}
