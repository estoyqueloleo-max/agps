package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import c2.v2;

/**
 * Manages audio alerts for GPS tracking, specifically off-route warning beeps.
 * Uses SoundPool for fast, non-blocking, reliable sound playback that does not suffer
 * from MediaPlayer lifecycle/state-machine traps.
 */
public class OffRouteAudioPlayer {
    private static final String TAG = "GPS-Audio";

    private final Context context;
    private SoundPool soundPool;
    private int offRouteSoundId = 0;
    private boolean isLoaded = false;
    private int currentStreamId = 0;

    private MediaPlayer fallbackMediaPlayer;

    public OffRouteAudioPlayer(Context context) {
        this.context = context.getApplicationContext();
        initializeSoundPool();
        initializeFallbackMediaPlayer();
    }

    private void initializeSoundPool() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build();
                this.soundPool = new SoundPool.Builder()
                        .setMaxStreams(2)
                        .setAudioAttributes(audioAttributes)
                        .build();
            } else {
                this.soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
            }

            this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool sp, int sampleId, int status) {
                    if (status == 0) {
                        isLoaded = true;
                        v2.e(TAG, "Off-route sound loaded successfully into SoundPool (id=" + sampleId + ")");
                    } else {
                        v2.e(TAG, "Failed to load off-route sound into SoundPool, status=" + status);
                    }
                }
            });

            this.offRouteSoundId = this.soundPool.load(this.context, R.raw.pringtone, 1);
        } catch (Exception e) {
            v2.e(TAG, "Error initializing SoundPool: " + e.getMessage());
        }
    }

    private void initializeFallbackMediaPlayer() {
        try {
            this.fallbackMediaPlayer = MediaPlayer.create(this.context, R.raw.pringtone);
            if (this.fallbackMediaPlayer != null) {
                this.fallbackMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                this.fallbackMediaPlayer.setLooping(false);
            }
        } catch (Exception e) {
            v2.e(TAG, "Error creating fallback MediaPlayer: " + e.getMessage());
        }
    }

    /**
     * Plays the off-route warning alert tone.
     * Uses SoundPool as primary fast player, and falls back to MediaPlayer if SoundPool hasn't finished loading.
     */
    public synchronized void playAlert() {
        if (soundPool == null) {
            initializeSoundPool();
        }

        if (soundPool != null && isLoaded && offRouteSoundId != 0) {
            try {
                if (currentStreamId != 0) {
                    soundPool.stop(currentStreamId);
                }
                currentStreamId = soundPool.play(offRouteSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
                v2.e(TAG, "Played off-route warning tone via SoundPool, streamId=" + currentStreamId);
                return;
            } catch (Exception e) {
                v2.e(TAG, "Exception playing SoundPool alert: " + e.getMessage());
            }
        }

        // Fallback to MediaPlayer if SoundPool is not loaded or encountered an error
        try {
            if (fallbackMediaPlayer == null) {
                initializeFallbackMediaPlayer();
            }
            if (fallbackMediaPlayer != null) {
                if (fallbackMediaPlayer.isPlaying()) {
                    fallbackMediaPlayer.seekTo(0);
                } else {
                    fallbackMediaPlayer.start();
                }
                v2.e(TAG, "Played off-route warning tone via fallback MediaPlayer");
            }
        } catch (Exception e) {
            v2.e(TAG, "Exception playing fallback MediaPlayer: " + e.getMessage());
        }
    }

    /**
     * Stops any currently playing alert tone.
     */
    public synchronized void stopAlert() {
        if (soundPool != null && currentStreamId != 0) {
            try {
                soundPool.stop(currentStreamId);
            } catch (Exception ignored) {
            }
            currentStreamId = 0;
        }
        if (fallbackMediaPlayer != null) {
            try {
                if (fallbackMediaPlayer.isPlaying()) {
                    fallbackMediaPlayer.stop();
                }
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Releases audio resources when the service is destroyed.
     */
    public synchronized void release() {
        stopAlert();
        if (soundPool != null) {
            try {
                soundPool.release();
            } catch (Exception ignored) {
            }
            soundPool = null;
            isLoaded = false;
            offRouteSoundId = 0;
        }
        if (fallbackMediaPlayer != null) {
            try {
                fallbackMediaPlayer.release();
            } catch (Exception ignored) {
            }
            fallbackMediaPlayer = null;
        }
    }

    public boolean isLoaded() {
        return isLoaded;
    }
}
