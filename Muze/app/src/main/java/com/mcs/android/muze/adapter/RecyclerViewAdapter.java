package com.mcs.android.muze.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.muze.PlayerActivity;
import com.mcs.android.muze.R;
import com.mcs.android.muze.models.Song;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Song> songs;

    public RecyclerViewAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Song songItem = songs.get(position);

        holder.textViewSongName.setText(songItem.getTitle());
        holder.textViewSongName.setSelected(true);
        holder.textViewSongDuration.setText(Song.getFormattedDuration(songItem.getDuration()));
        holder.textViewSongAlbumName.setText(songItem.getAlbum());
        holder.textViewSongAlbumName.setSelected(true);
        holder.textViewSongArtistName.setText(songItem.getArtist());
        holder.textViewSongArtistName.setSelected(true);
        holder.songSelectedPosition = position;

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewSongImage;
        private TextView textViewSongName;
        private TextView textViewSongDuration;
        private TextView textViewSongArtistName;
        private TextView textViewSongAlbumName;
        private Song songSelected;
        private int songSelectedPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewSongImage = itemView.findViewById(R.id.imageViewSongImage);
            textViewSongName = itemView.findViewById(R.id.textViewSongTitle);
            textViewSongDuration = itemView.findViewById(R.id.textViewSongDuration);
            textViewSongArtistName = itemView.findViewById(R.id.textViewSongArtistName);
            textViewSongAlbumName = itemView.findViewById(R.id.textViewSongAlbumName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent playSongIntent = new Intent(context, PlayerActivity.class);
                    playSongIntent.putExtra("song_position", songSelectedPosition);
                    playSongIntent.putExtra("songs_arrayList", songs);
                    context.startActivity(playSongIntent);
                }
            });
        }
    }
}
