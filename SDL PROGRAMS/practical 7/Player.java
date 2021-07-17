package com.example.admin.musicplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Player extends AppCompatActivity implements View.OnClickListener {
    ArrayList<File> mySongs;
    MediaPlayer mp;
    int position;
    SeekBar sb;
    Button bplay,bprev,bnext;
    Uri u;
    Thread updateSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        sb=(SeekBar)findViewById(R.id.seek);
        bplay=(Button)findViewById(R.id.playpause);
        bnext=(Button)findViewById(R.id.next);
        bprev=(Button)findViewById(R.id.prev);


        bplay.setOnClickListener(this);
        bnext.setOnClickListener(this);
        bprev.setOnClickListener(this);


        updateSeekbar=new Thread()
        {
            @Override
            public void run() {
                int totalDuration=mp.getDuration();
                int currentPosition=0;
                while(currentPosition<totalDuration)
                {
                    try {
                        sleep(500);
                        currentPosition=mp.getCurrentPosition();
                        sb.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        Bundle bundle=getIntent().getExtras();

        mySongs = (ArrayList) bundle.getParcelableArrayList("songlist");
        position= bundle.getInt("pos", 0);


        u=Uri.parse(mySongs.get(position).toString());


            mp = MediaPlayer.create(this, u);
            mp.start();
            sb.setMax(mp.getDuration());
            updateSeekbar.start();

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(seekBar.getProgress());
            }
        });


    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.playpause) {
            if (mp.isPlaying()) {
                mp.pause();
                bplay.setText(">");
            } else {
                mp.start();
                bplay.setText("||");
            }

        }

       else if (v.getId() == R.id.next) {
            mp.stop();
            mp.release();

            if(position==mySongs.size()-1)
                Toast.makeText(this, "this is the last song", Toast.LENGTH_SHORT).show();
            else
                position = position + 1;

            u = Uri.parse(mySongs.get(position).toString());
            mp = MediaPlayer.create(getApplicationContext(), u);
            mp.start();
            sb.setMax(mp.getDuration());

        }
      else  if (v.getId() == R.id.prev) {

            mp.stop();
            mp.release();

            if(position==0)
                Toast.makeText(this, "this is the first song", Toast.LENGTH_SHORT).show();
            else
                position = position - 1;

            u = Uri.parse(mySongs.get(position).toString());
            mp = MediaPlayer.create(getApplicationContext(), u);
            mp.start();
            sb.setMax(mp.getDuration());
        }



    }


}
