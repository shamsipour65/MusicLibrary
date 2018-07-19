package com.example.setareh.musiclibrary;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();
                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };


    private MediaPlayer.OnCompletionListener mCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMediaPlayer();
                }
            };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(R.drawable.makanband, R.raw.ashegh, "عاشق که میشی"));
        tracks.add(new Track(R.drawable.makanband, R.raw.daghighe, "دو دقیقه بودی حالا"));
        tracks.add(new Track(R.drawable.makanband, R.raw.divoone, "دیونه من"));
        tracks.add(new Track(R.drawable.makanband, R.raw.bekhand, "پیشم بخند"));
        tracks.add(new Track(R.drawable.makanband, R.raw.negah, "یک لحظه نگاهم کن"));


        ListView musicListView = (ListView)findViewById(R.id.musicList);

        Adapter adapter = new Adapter(this, tracks);
        musicListView.setAdapter(adapter);

        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, tracks.get(position).getMusic());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });


    }

    private void releaseMediaPlayer(){
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mMediaPlayer!=null) {
            mMediaPlayer.pause();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mMediaPlayer != null){
            mMediaPlayer.start();
        }
    }
}
