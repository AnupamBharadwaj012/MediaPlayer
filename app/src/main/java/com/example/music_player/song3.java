package com.example.music_player;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.*;
//import android.os.Handler;
import android.view.View;
import android.widget.*;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.Button;
//import android.widget.RelativeLayout;

import java.util.concurrent.TimeUnit;


public class song3 extends AppCompatActivity implements View.OnClickListener,Runnable {
    TextView playerPosition,playerDuration,textInfo;
    SeekBar seekBar;
    ImageView btRewind,btPlay,btPause,btFastForward;
    MediaPlayer mediaPlayer;
    Handler handler=new Handler();
    Runnable runnable;
    Button btn,btmenu;
    RelativeLayout MyLayout;
    int i;
    int[] image={R.drawable.poster2a,R.drawable.poster2b,R.drawable.poster2c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song3);

        playerPosition=findViewById(R.id.player_position);
        playerDuration=findViewById(R.id.player_duration);
        seekBar=findViewById(R.id.seek_bar);
        btRewind=findViewById(R.id.bt_rewind);
        btFastForward=findViewById(R.id.bt_fastforward);
        btPause=findViewById(R.id.bt_pause);
        btPlay=findViewById(R.id.bt_play);
        btmenu=findViewById(R.id.menu);
        btmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                Intent intent=new Intent(song3.this,MainActivity.class);
                startActivity(intent);

            }
        });

        textInfo=findViewById(R.id.textInfo);
        textInfo.setBackgroundResource(R.color.purple_200);
        btn=(Button) findViewById(R.id.info);
        btn.setOnClickListener((View.OnClickListener) this);
        MyLayout = (RelativeLayout) findViewById(R.id.MyLayout);
        Thread t = new Thread((Runnable) song3.this);
        t.start();
        mediaPlayer=MediaPlayer.create(this,R.raw.music2);

        runnable=new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500);

            }
        };

        int duration=mediaPlayer.getDuration();
        String duration2=converter(duration);

        playerDuration.setText(duration2);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlay.setVisibility(View.GONE);
                btPause.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable,0);

            }
        });

        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPause.setVisibility(View.GONE);
                btPlay.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);
            }
        });

        btFastForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentTime= mediaPlayer.getCurrentPosition();
                int duration=mediaPlayer.getDuration();
                if(mediaPlayer.isPlaying() && duration!=currentTime){
                    currentTime += 5000;
                    playerPosition.setText(converter(currentTime));
                    mediaPlayer.seekTo(currentTime);
                }
            }
        });

        btRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentTime=mediaPlayer.getCurrentPosition();
                if(mediaPlayer.isPlaying() && currentTime >5000){
                    currentTime-=5000;
                    playerPosition.setText(converter(currentTime));
                    mediaPlayer.seekTo(currentTime);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);

                }
                playerPosition.setText(converter(mediaPlayer.getCurrentPosition()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btPause.setVisibility(View.GONE);
                btPlay.setVisibility(View.VISIBLE);
                mediaPlayer.seekTo(0);
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private String converter(int duration) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration),TimeUnit.MILLISECONDS.toSeconds(duration)-
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    @Override
    public void onClick(View v) {
        if(textInfo.getVisibility()==View.GONE)
            textInfo.setVisibility(View.VISIBLE);
        else
            textInfo.setVisibility(View.GONE);
    }

    @Override
    public void run() {
        for ( i = 1; i <= image.length; ++i) {

            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(i == image.length){
                        i = 0;
                    }
                    MyLayout.setBackgroundResource(image[i]);
                }
            });
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}