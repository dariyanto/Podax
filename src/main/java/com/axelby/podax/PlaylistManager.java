package com.axelby.podax;

import android.content.ContentValues;
import android.content.Context;

class PlaylistManager {

	public static void moveToNextInPlaylist(Context context) {
		ContentValues values = new ContentValues();
		values.put(EpisodeProvider.COLUMN_LAST_POSITION, 0);
		values.put(EpisodeProvider.COLUMN_PLAYLIST_POSITION, (Integer) null);
		context.getContentResolver().update(EpisodeProvider.ACTIVE_EPISODE_URI, values, null, null);

		Stats.addCompletion(context);
	}

	public static void changeActiveEpisode(Context context, long activeEpisodeId) {
		ContentValues values = new ContentValues();
		values.put(EpisodeProvider.COLUMN_ID, activeEpisodeId);
		context.getContentResolver().update(EpisodeProvider.ACTIVE_EPISODE_URI, values, null, null);
	}

}
