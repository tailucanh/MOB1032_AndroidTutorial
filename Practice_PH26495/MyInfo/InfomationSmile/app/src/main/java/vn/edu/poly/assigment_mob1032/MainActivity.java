package vn.edu.poly.assigment_mob1032;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;


import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imb_fb, imb_ins, imb_git, imb_linkedin, imb_tiktok;
    GifImageView gif_call, music_layer;
    Button btn_mess, btn_follow;
    TextView tv_fb, tv_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imb_fb = findViewById(R.id.imb_facebook);
        imb_ins = findViewById(R.id.imb_Ins);
        imb_git = findViewById(R.id.imb_git);
        imb_linkedin = findViewById(R.id.imb_Linkedin);
        imb_tiktok = findViewById(R.id.imb_tiktok);
        gif_call = findViewById(R.id.imb_Call);
        music_layer = findViewById(R.id.dialog_music_play);

        music_layer.setOnClickListener(ic -> {
            dialogMusicPlayer(MainActivity.this);
        });

        imb_fb.setOnClickListener(ic -> {
            ShowDialogWarning(MainActivity.this, "https://www.facebook.com/lucanh.tai");

        });
        imb_ins.setOnClickListener(ic -> {
            ShowDialogWarning(MainActivity.this, "https://www.instagram.com/lucanh18/");
        });

        imb_git.setOnClickListener(ic -> {
            ShowDialogWarning(MainActivity.this, "https://github.com/tailucanh");

        });
        imb_linkedin.setOnClickListener(ic -> {
            ShowDialogWarning(MainActivity.this, "https://www.linkedin.com/in/l%E1%BB%A5c-anh-t%C3%A0i-3265b4222/");

        });
        imb_tiktok.setOnClickListener(ic -> {
            ShowDialogWarning(MainActivity.this, "https://www.tiktok.com/@taismile1504");

        });
        gif_call.setOnClickListener(ic -> {
            CallMe(MainActivity.this);

        });


        btn_mess = findViewById(R.id.btn_Mess);
        btn_follow = findViewById(R.id.btn_Follow);
        btn_mess.setOnClickListener(btn -> {
            ShowDialogWarning(MainActivity.this, "https://www.facebook.com/messages/t/100022570240367");

        });

        btn_follow.setOnClickListener(btn -> {
            ShowDialogWarning(MainActivity.this, "https://www.facebook.com/lucanh.tai");

        });

        tv_fb = findViewById(R.id.tv_fb);
        tv_email = findViewById(R.id.tv_email);


        tv_fb.setOnClickListener(tv -> {
            clickText(MainActivity.this, "https://www.facebook.com/lucanh.tai");

        });
        tv_email.setOnClickListener(tv -> {
            clickText(MainActivity.this, "mailto:lucanhtai1504@gmail.com");

        });

    }



    ImageView disk_music;
    SeekBar seekBar;
    TextView currentTimeTv ,totalTimeTv ;
    public void dialogMusicPlayer(Context context){
        final  Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_music_player);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        disk_music = dialog.findViewById(R.id.disk_music);
        currentTimeTv = dialog.findViewById(R.id.music_time_up);
        totalTimeTv = dialog.findViewById(R.id.music_time_back);

        ImageView play_music = dialog.findViewById(R.id.play_music);
        ImageView stop_music = dialog.findViewById(R.id.stop_music);
        ImageView back_music = dialog.findViewById(R.id.back_music);
        ImageView next_music = dialog.findViewById(R.id.next_music);

        seekBar = dialog.findViewById(R.id.music_barr);
        play_music.setVisibility(View.GONE);
        stop_music.setVisibility( View.VISIBLE);

        int list_musics[] = {R.raw.phuongbuon,R.raw.lockedaway,R.raw.didetrove};


        MediaPlayer mp  = MediaPlayer.create(context,list_musics[0]);;
        MediaPlayer mp2  = MediaPlayer.create(context,list_musics[1]);
        MediaPlayer mp3  = MediaPlayer.create(context,list_musics[2]);
        play_music.setOnClickListener(ic ->{
                if(mp.isPlaying()){
                    mp.pause();
                    stopDiskMusic();
                    stopMusicBar();
                }
                if(mp2.isPlaying()){
                    mp2.pause();
                    stopDiskMusic();
                    stopMusicBar();
                }

                if(mp3.isPlaying()){
                    mp3.pause();
                    stopDiskMusic();
                    stopMusicBar();
                }

                play_music.setVisibility( View.GONE);
                stop_music.setVisibility( View.VISIBLE);

        });
        stop_music.setOnClickListener(ic ->{
                mp2.start();
                totalTimeTv.setText(converToMMSS(String.valueOf(mp.getDuration())));
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if(mp!=null){
                            currentTimeTv.setText(converToMMSS(mp.getCurrentPosition()+""));
                        }
                    }
                };
                handler.postDelayed(runnable,100);
                startMusicBar(mp2);
                startDiskMusic();
                play_music.setVisibility( View.VISIBLE);
                stop_music.setVisibility( View.GONE);

        });


        back_music.setOnClickListener(ic ->{
                mp3.start();
                mp.stop();
                stopMusicBar();
                startMusicBar(mp3);
                play_music.setVisibility( View.VISIBLE); stop_music.setVisibility( View.GONE);


        });

        next_music.setOnClickListener(ic ->{
                mp.start();
                mp2.stop();
                stopMusicBar();
                startMusicBar(mp);
                play_music.setVisibility( View.VISIBLE);
                stop_music.setVisibility( View.GONE);


        });

        if(dialog.isShowing()){
            if(mp.isPlaying()){
                mp.stop();

            }
            if(mp2.isPlaying()){
                mp2.stop();

            }

            if(mp3.isPlaying()){
                mp3.stop();
            }
        }


        dialog.show();
    }

    public String converToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return  String.format("%02d:%02d", TimeUnit.MICROSECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MICROSECONDS.toSeconds(millis) % TimeUnit.HOURS.toSeconds(1));
    }

    Handler mSeekbarUpdateHandler;
    Runnable mUpdateSeekbar;
    public void startMusicBar(MediaPlayer mp){
        seekBar.setMax(mp.getDuration());
        mSeekbarUpdateHandler = new Handler();
        mUpdateSeekbar = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mp.getCurrentPosition());
                mSeekbarUpdateHandler.postDelayed(this, 50);
            }
        };
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mp != null && fromUser){
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar,0);

    }
    public void  stopMusicBar(){
        mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
    }
    public void startDiskMusic(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                disk_music.animate().rotationBy(360).withEndAction(this).setDuration(10000)
                        .setInterpolator(new LinearInterpolator()).start();
            }
        };
        disk_music.animate().rotationBy(360).withEndAction(runnable).setDuration(10000)
                .setInterpolator(new LinearInterpolator()).start();
    }

    public void stopDiskMusic(){
        disk_music.animate().cancel();
    }




    private  void ShowDialogWarning(final Context context,String url){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Warning !!!");
        builder.setMessage("Bạn sắp chuyển hướng đến 1 trang web mới. Hãy xác nhận !");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clickText(context,url);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Đã hủy !",Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        AlertDialog sh = builder.create();
        sh.show();
    }

    private  void CallMe(Context context){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"0931360954"));
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }


    private void clickText(Context context, String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        Toast.makeText(context,"Điều hướng thành công !",Toast.LENGTH_SHORT).show();
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }




}