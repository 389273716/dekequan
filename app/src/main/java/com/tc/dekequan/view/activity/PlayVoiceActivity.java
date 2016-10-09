package com.tc.dekequan.view.activity;

import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.cleveroad.audiovisualization.DbmHandler;
import com.cleveroad.audiovisualization.GLAudioVisualizationView;
import com.tc.dekequan.R;
import com.tc.dekequan.common.ShareConstants;
import com.tc.dekequan.presenter.BasePresenter;
import com.tomtop.ttutil.PreferencesUtil;

import java.util.Timer;
import java.util.TimerTask;

import cafe.adriel.androidaudiorecorder.Util;
import cafe.adriel.androidaudiorecorder.VisualizerHandler;

/**
 * author：   tc
 * date：     2016/10/9 & 16:13
 * version    1.0
 * description
 * modify by
 */

public class PlayVoiceActivity extends BaseActivity implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mMediaPlayer;
    private Timer mTimer;
    private GLAudioVisualizationView visualizerView;
    private VisualizerHandler visualizerHandler;
    private Button mBtPlay;
    private Button mBtStop;

    @Override
    public void onResume() {
        super.onResume();
        try {
            visualizerView.onResume();
        } catch (Exception e) {
        }
    }

    @Override
    protected void onPause() {
        restartRecording(null);
        try {
            visualizerView.onPause();
        } catch (Exception e) {
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        restartRecording(null);
        setResult(RESULT_CANCELED);
        try {
            visualizerView.release();
        } catch (Exception e) {
        }
        super.onDestroy();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void obtainData() {
        visualizerView = new GLAudioVisualizationView.Builder(this)
                .setLayersCount(1)
                .setWavesCount(6)
                .setWavesHeight(cafe.adriel.androidaudiorecorder.R.dimen.aar_wave_height)
                .setWavesFooterHeight(cafe.adriel.androidaudiorecorder.R.dimen.aar_footer_height)
                .setBubblesPerLayer(20)
                .setBubblesSize(cafe.adriel.androidaudiorecorder.R.dimen.aar_bubble_size)
                .setBubblesRandomizeSize(true)
                .setBackgroundColor(Util.getDarkerColor(R.color.lite_blue))
                .setLayerColors(new int[]{R.color.lite_blue})
                .build();
        visualizerHandler = new VisualizerHandler();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_play_voice);
    }

    @Override
    protected void initView() {
        mBtPlay = (Button) findViewById(R.id.bt_play_voice);
        mBtStop = (Button) findViewById(R.id.bt_stop_voice);
    }

    @Override
    protected void initEvent() {
        mBtPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = PreferencesUtil.getString(PlayVoiceActivity.this, ShareConstants
                                .VOICE_PATH,
                        ShareConstants
                                .KEY_CURRENT_VOICE_PATH, "");
                if (!TextUtils.isEmpty(path))
                    startPlaying(path);
            }
        });
        mBtStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();
            }
        });
    }

    private void stopPlaying() {
//        statusView.setText("");
//        statusView.setVisibility(View.INVISIBLE);
//        playView.setImageResource(cafe.adriel.androidaudiorecorder.R.drawable.aar_ic_play);

        visualizerView.release();
        if (visualizerHandler != null) {
            visualizerHandler.stop();
        }

        if (mMediaPlayer != null) {
            try {
                mMediaPlayer.stop();
                mMediaPlayer.reset();
            } catch (Exception e) {
            }
        }

        stopTimer();
    }

    private boolean isPlaying() {
        try {
            return mMediaPlayer != null && mMediaPlayer.isPlaying();
        } catch (Exception e) {
            return false;
        }
    }

    private void startPlaying(String filePath) {
        try {
//            stopRecording();
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(filePath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();

            visualizerView.linkTo(DbmHandler.Factory.newVisualizerHandler(this, mMediaPlayer));
            visualizerView.post(new Runnable() {
                @Override
                public void run() {
                    mMediaPlayer.setOnCompletionListener(PlayVoiceActivity.this);
                }
            });

//            timerView.setText("00:00:00");
//            statusView.setText(cafe.adriel.androidaudiorecorder.R.string.aar_playing);
//            statusView.setVisibility(View.VISIBLE);
//            playView.setImageResource(cafe.adriel.androidaudiorecorder.R.drawable.aar_ic_stop);

//            playerSecondsElapsed = 0;
            startTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startTimer() {
        stopTimer();
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTimer();
            }
        }, 0, 1000);
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
            mTimer = null;
        }
    }

    private void updateTimer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                if(isRecording) {
//                    recorderSecondsElapsed++;
//                    timerView.setText(Util.formatSeconds(recorderSecondsElapsed));
//                } else if(isPlaying()){
//                    playerSecondsElapsed++;
//                    timerView.setText(Util.formatSeconds(playerSecondsElapsed));
//                }
            }
        });
    }

    public void restartRecording(View v) {
        if (isPlaying()) {
            stopPlaying();
        } else {
            visualizerHandler = new VisualizerHandler();
            visualizerView.linkTo(visualizerHandler);
            visualizerView.release();
            if (visualizerHandler != null) {
                visualizerHandler.stop();
            }
        }
//        saveMenuItem.setVisible(false);
//        statusView.setVisibility(View.INVISIBLE);
//        restartView.setVisibility(View.INVISIBLE);
//        playView.setVisibility(View.INVISIBLE);
//        recordView.setImageResource(cafe.adriel.androidaudiorecorder.R.drawable.aar_ic_rec);
//        timerView.setText("00:00:00");
//        recorderSecondsElapsed = 0;
//        playerSecondsElapsed = 0;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        stopPlaying();
    }

}
