package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import c2.v2;

/**
 * Manages audio alerts for GPS tracking, specifically off-route warning beeps.
 * Uses a robust, self-healing MediaPlayer configured with USAGE_ALARM / STREAM_ALARM
 * so that alerts sound reliably through the device speaker regardless of media volume
 * or background battery optimizations, avoiding the state-machine destruction caused
 * by calling stop() without prepare().
 */
public class OffRouteAudioPlayer {
    private static final String TAG = "GPS-Audio";

    private final Context context;
    private MediaPlayer mediaPlayer;
    private boolean isPrepared = false;

    public OffRouteAudioPlayer(Context context) {
        this.context = context.getApplicationContext();
        initializeMediaPlayer();
    }

    private synchronized void initializeMediaPlayer() {
        try {
            if (this.mediaPlayer != null) {
                try {
                    this.mediaPlayer.release();
                } catch (Exception ignored) {
                }
                this.mediaPlayer = null;
            }

            this.mediaPlayer = MediaPlayer.create(this.context, R.raw.pringtone);
            if (this.mediaPlayer != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    AudioAttributes audioAttributes = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build();
                    this.mediaPlayer.setAudioAttributes(audioAttributes);
                } else {
                    this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                }
                this.mediaPlayer.setLooping(false);
                this.isPrepared = true;
                v2.e(TAG, "OffRouteAudioPlayer initialized successfully with USAGE_ALARM");
            } else {
                v2.e(TAG, "MediaPlayer.create returned null for R.raw.pringtone");
                this.isPrepared = false;
            }
        } catch (Exception e) {
            v2.e(TAG, "Error initializing MediaPlayer: " + e.getMessage());
            this.isPrepared = false;
        }
    }

    /**
     * Plays the off-route warning alert tone.
     * Starts playback from the beginning. If already playing or at the end, it seeks to 0.
     * Recreates the MediaPlayer if it was released or entered an unrecoverable state.
     */
    public synchronized void playAlert() {
        if (this.mediaPlayer == null || !this.isPrepared) {
            initializeMediaPlayer();
        }

        if (this.mediaPlayer != null) {
            try {
                if (this.mediaPlayer.isPlaying()) {
                    this.mediaPlayer.seekTo(0);
                } else {
                    this.mediaPlayer.seekTo(0);
                    this.mediaPlayer.start();
                }
                v2.e(TAG, "Played off-route warning tone via MediaPlayer (ALARM stream)");
                return;
            } catch (IllegalStateException e) {
                v2.e(TAG, "MediaPlayer IllegalStateException, re-initializing: " + e.getMessage());
                initializeMediaPlayer();
                if (this.mediaPlayer != null) {
                    try {
                        this.mediaPlayer.start();
                    } catch (Exception ex) {
                        v2.e(TAG, "Retry playAlert failed: " + ex.getMessage());
                    }
                }
            } catch (Exception e) {
                v2.e(TAG, "Exception playing alert: " + e.getMessage());
            }
        }
    }

    /**
     * Stops any currently playing alert tone cleanly without transitioning to the
     * destructed 'Stopped' state (using pause() and seekTo(0)).
     */
    public synchronized void stopAlert() {
        if (this.mediaPlayer != null) {
            try {
                if (this.mediaPlayer.isPlaying()) {
                    this.mediaPlayer.pause();
                }
                this.mediaPlayer.seekTo(0);
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Releases audio resources when the service is destroyed.
     */
    public synchronized void release() {
        stopAlert();
        if (this.mediaPlayer != null) {
            try {
                this.mediaPlayer.release();
            } catch (Exception ignored) {
            }
            this.mediaPlayer = null;
            this.isPrepared = false;
        }
    }

    public synchronized boolean isLoaded() {
        return this.mediaPlayer != null && this.isPrepared;
    }
}
