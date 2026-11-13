package com.giobat.AgpsTrackerPP.tron;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

/**
 * Manages tactile (vibration) and acoustic (retro 8-bit tone generator) feedback
 * for Tron gameplay events without requiring external audio assets.
 */
public class TronFeedbackManager {
    private final Vibrator vibrator;
    private ToneGenerator toneGenerator;

    public TronFeedbackManager(Context context) {
        this.vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        try {
            this.toneGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC, 85);
        } catch (Exception ignored) {
            this.toneGenerator = null;
        }
    }

    public void playMatchStart() {
        vibrate(new long[]{0, 80, 60, 120});
        playTone(ToneGenerator.TONE_PROP_ACK, 250);
    }

    public void playProximityAlert() {
        vibrate(new long[]{0, 40, 40, 40});
        playTone(ToneGenerator.TONE_PROP_BEEP, 100);
    }

    public void playCollisionAlert() {
        vibrate(new long[]{0, 250, 100, 500});
        playTone(ToneGenerator.TONE_PROP_NACK, 600);
    }

    private void vibrate(long[] pattern) {
        if (vibrator == null || !vibrator.hasVibrator()) return;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1));
            } else {
                vibrator.vibrate(pattern, -1);
            }
        } catch (Exception ignored) {
        }
    }

    private void playTone(int toneType, int durationMs) {
        if (toneGenerator != null) {
            try {
                toneGenerator.startTone(toneType, durationMs);
            } catch (Exception ignored) {
            }
        }
    }

    public void release() {
        if (toneGenerator != null) {
            try {
                toneGenerator.release();
            } catch (Exception ignored) {
            }
            toneGenerator = null;
        }
    }
}

