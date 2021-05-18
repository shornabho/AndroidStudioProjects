package com.mcs.android.muze;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mcs.android.muze.models.Song;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton backButton;

    private TextView textViewSongTitle;
    private TextView textViewSongAlbumName;
    private TextView textViewSongArtistName;
    private TextView textViewSongDurationPlayed;
    private TextView textViewSongDurationLeft;
    private SeekBar songSeekBar;
    private ImageView buttonPrevious;
    private ImageView buttonNext;
    private ImageView buttonFastRewind;
    private ImageView buttonFastForward;
    private ImageView buttonPlayPause;

    private Song currentSong;
    private ArrayList<Song> allSongs;
    private int currentSongPosition;
    private static MediaPlayer mediaPlayer;

    final Handler handler = new Handler();
    final int delay = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        currentSongPosition = getIntent().getIntExtra("song_position", 0);
        allSongs = getIntent().getParcelableArrayListExtra("songs_arrayList");
        currentSong = allSongs.get(currentSongPosition);

        // Text Views
        textViewSongTitle = findViewById(R.id.playerTextViewSongTitle);
        textViewSongTitle.setSelected(true);
        textViewSongArtistName = findViewById(R.id.playerTextViewSongArtistName);
        textViewSongArtistName.setSelected(true);
        textViewSongDurationPlayed = findViewById(R.id.playerTextViewSongDurationPlayed);
        textViewSongDurationLeft = findViewById(R.id.playerTextViewSongDurationLeft);

        // Seekbar
        songSeekBar = findViewById(R.id.playerSongSeekBar);
        songSeekBar.setClickable(false);

        // Action Buttons
        buttonPrevious = findViewById(R.id.playerButtonPrevious);
        buttonPrevious.setOnClickListener(this);

        buttonFastRewind = findViewById(R.id.playerButtonFastRewind);
        buttonFastRewind.setOnClickListener(this);

        buttonPlayPause = findViewById(R.id.playerButtonPlayPause);
        buttonPlayPause.setOnClickListener(this);

        buttonNext = findViewById(R.id.playerButtonNext);
        buttonNext.setOnClickListener(this);

        buttonFastForward = findViewById(R.id.playerButtonFastForward);
        buttonFastForward.setOnClickListener(this);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        playCurrentSong();
    }

    private void playCurrentSong() {

        textViewSongTitle.setText(currentSong.getTitle());
//        textViewSongAlbumName.setText(currentSong.getAlbum());
        textViewSongArtistName.setText(currentSong.getArtist());
        textViewSongDurationPlayed.setText(Song.getFormattedDuration(0));
        textViewSongDurationLeft.setText("- " + Song.getFormattedDuration(currentSong.getDuration()));

        songSeekBar.setMax((int) currentSong.getDuration());

        mediaPlayer = MediaPlayer.create(getApplicationContext(), currentSong.getUri());
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                buttonNext.performClick();
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = mediaPlayer.getCurrentPosition();
                songSeekBar.setProgress(currentPosition);
                textViewSongDurationPlayed.setText(Song.getFormattedDuration(currentPosition));
                textViewSongDurationLeft.setText("- " + Song.getFormattedDuration(currentSong.getDuration() - currentPosition));
                handler.postDelayed(this, delay);
            }
        }, delay);

        mediaPlayer.start();
        buttonPlayPause.setImageDrawable(getDrawable(R.drawable.ic_pause_circle));
    }

    private void stopCurrentSong() {
        mediaPlayer.stop();
        mediaPlayer.release();
        buttonPlayPause.setImageDrawable(getDrawable(R.drawable.ic_play_circle));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backButton:
                finish();
                break;
            case R.id.playerButtonPlayPause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    buttonPlayPause.setImageDrawable(getDrawable(R.drawable.ic_play_circle));
                } else {
                    mediaPlayer.start();
                    buttonPlayPause.setImageDrawable(getDrawable(R.drawable.ic_pause_circle));
                }
                break;
            case R.id.playerButtonNext:
                stopCurrentSong();
                currentSongPosition = (currentSongPosition + 1) % allSongs.size();
                currentSong = allSongs.get(currentSongPosition);
                playCurrentSong();
                break;
            case R.id.playerButtonPrevious:
                stopCurrentSong();
                currentSongPosition = (currentSongPosition - 1) < 0 ? allSongs.size() - 1 : currentSongPosition - 1;
                currentSong = allSongs.get(currentSongPosition);
                playCurrentSong();
                break;
            case R.id.playerButtonFastForward:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 10000); // Fast forward by 10 seconds
                }
                break;
            case R.id.playerButtonFastRewind:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000); // Fast rewind by 10 seconds
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCurrentSong();
        mediaPlayer.release();
        handler.removeCallbacksAndMessages(null);
    }
}