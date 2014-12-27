package com.axelby.podax;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

public class MediaButtonIntentReceiver extends BroadcastReceiver {

    @TargetApi(9)
    @Override
    public void onReceive(Context context, Intent intent) {
        KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);

        if (event == null || event.getAction() != KeyEvent.ACTION_DOWN)
            return;

        switch (event.getKeyCode()) {
            // Simple headsets only send KEYCODE_HEADSETHOOK
            case KeyEvent.KEYCODE_HEADSETHOOK:
                PlayerService.stop(context);
                break;
            case KeyEvent.KEYCODE_MEDIA_PLAY:
                PlayerService.play(context);
                break;
            case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                PlayerService.playpause(context);
                break;
            case KeyEvent.KEYCODE_MEDIA_PAUSE:
                PlayerService.pause(context);
                break;
            case KeyEvent.KEYCODE_MEDIA_STOP:
                PlayerService.stop(context);
                break;
            case KeyEvent.KEYCODE_MEDIA_FAST_FORWARD:
            case KeyEvent.KEYCODE_MEDIA_NEXT:
                EpisodeProvider.movePositionBy(context, EpisodeProvider.ACTIVE_EPISODE_URI, 30);
                break;
            case KeyEvent.KEYCODE_MEDIA_REWIND:
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                EpisodeProvider.movePositionBy(context, EpisodeProvider.ACTIVE_EPISODE_URI, -15);
                break;
            default:
                Log.e("Podax", "No matched event: " + event.getKeyCode());
        }

        if (this.isOrderedBroadcast()) {
            abortBroadcast();
        }
    }

}