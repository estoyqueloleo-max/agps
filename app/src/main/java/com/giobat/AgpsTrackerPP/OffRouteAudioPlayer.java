package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Build;
import c2.v2;

/**
 * Manages audio alerts for GPS tracking, specifically off-route warning beeps.
 * Uses a MediaPlayer configured with proper AudioAttributes (USAGE_ALARM/USAGE_NOTIFICATION)
 * and initialized via openRawResourceFd() before prepare(), so that setAudioAttributes()
 * is never called on an already-prepared instance.
 * Also provides a resilient fallback to ToneGenerator/RingtoneManager if MediaPlayer fails.
 */
public class OffRouteAudioPlayer {
    private static final String TAG = "GPS-Audio";

    private final Context context;
    private MediaPlayer mediaPlayer;
    private ToneGenerator fallbackToneGenerator;
    private boolean isPrepared = false;

    public OffRouteAudioPlayer(Context context) {
        this.context = context.getApplicationContext();
        initializeMediaPlayer();
        initializeToneGenerator();
    }

    private synchronized void initializeToneGenerator() {
        try {
            if (this.fallbackToneGenerator != null) {
                try {
                    this.fallbackToneGenerator.release();
                } catch (Exception ignored) {
                }
                this.fallbackToneGenerator = null;
            }
            this.fallbackToneGenerator = new ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME);
        } catch (Exception e) {
            try {
                this.fallbackToneGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC, ToneGenerator.MAX_VOLUME);
            } catch (Exception ex) {
                v2.e(TAG, "ToneGenerator init failed: " + ex.getMessage());
            }
        }
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

            MediaPlayer player = new MediaPlayer();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();
                player.setAudioAttributes(audioAttributes);
            } else {
                player.setAudioStreamType(AudioManager.STREAM_ALARM);
            }

            AssetFileDescriptor afd = this.context.getResources().openRawResourceFd(R.raw.pringtone);
            if (afd != null) {
                player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                player.setLooping(false);
                player.prepare();
                this.mediaPlayer = player;
                this.isPrepared = true;
                v2.e(TAG, "OffRouteAudioPlayer initialized successfully with USAGE_ALARM");
            } else {
                // Fallback to simple create without invalid setAudioAttributes
                player.release();
                this.mediaPlayer = MediaPlayer.create(this.context, R.raw.pringtone);
                this.isPrepared = (this.mediaPlayer != null);
                v2.e(TAG, "OffRouteAudioPlayer initialized via fallback create");
            }
        } catch (Exception e) {
            v2.e(TAG, "Error initializing MediaPlayer: " + e.getMessage());
            this.isPrepared = false;
            if (this.mediaPlayer != null) {
                try {
                    this.mediaPlayer.release();
                } catch (Exception ignored) {
                }
                this.mediaPlayer = null;
            }
        }
    }

    /**
     * Plays the off-route warning alert tone.
     * Tries MediaPlayer first; if that fails or is unavailable, triggers ToneGenerator or Ringtone.
     */
    public synchronized void playAlert() {
        boolean played = false;

        if (this.mediaPlayer == null || !this.isPrepared) {
            initializeMediaPlayer();
        }

        if (this.mediaPlayer != null && this.isPrepared) {
            try {
                if (this.mediaPlayer.isPlaying()) {
                    this.mediaPlayer.pause();
                }
                this.mediaPlayer.seekTo(0);
                this.mediaPlayer.start();
                played = true;
                v2.e(TAG, "Played off-route warning tone via MediaPlayer");
            } catch (Exception e) {
                v2.e(TAG, "MediaPlayer play failed: " + e.getMessage() + ", falling back");
                initializeMediaPlayer();
            }
        }

        if (!played) {
            playFallbackAlert();
        }
    }

    private void playFallbackAlert() {
        try {
            if (this.fallbackToneGenerator != null) {
                this.fallbackToneGenerator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 1000);
                v2.e(TAG, "Played off-route alert via ToneGenerator fallback");
                return;
            }
        } catch (Exception e) {
            v2.e(TAG, "ToneGenerator startTone failed: " + e.getMessage());
        }

        try {
            Uri alertUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if (alertUri == null) {
                alertUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
            if (alertUri != null) {
                Ringtone ringtone = RingtoneManager.getRingtone(this.context, alertUri);
                if (ringtone != null) {
                    ringtone.play();
                    v2.e(TAG, "Played off-route alert via RingtoneManager fallback");
                }
            }
        } catch (Exception e) {
            v2.e(TAG, "Ringtone fallback failed: " + e.getMessage());
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
        if (this.fallbackToneGenerator != null) {
            try {
                this.fallbackToneGenerator.stopTone();
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
        if (this.fallbackToneGenerator != null) {
            try {
                this.fallbackToneGenerator.release();
            } catch (Exception ignored) {
            }
            this.fallbackToneGenerator = null;
        }
    }

    public synchronized boolean isLoaded() {
        return (this.mediaPlayer != null && this.isPrepared) || (this.fallbackToneGenerator != null);
    }
}

